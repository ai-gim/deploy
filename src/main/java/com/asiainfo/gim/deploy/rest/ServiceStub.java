package com.asiainfo.gim.deploy.rest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.asiainfo.gim.deploy.json.JsonSerializer;
import com.asiainfo.gim.deploy.rest.http.Header;
import com.asiainfo.gim.deploy.rest.http.HttpClientPassHttps;
import com.asiainfo.gim.deploy.rest.http.RestRequest;
import com.asiainfo.gim.deploy.rest.vp.AbstractReq;
import com.asiainfo.gim.deploy.rest.vp.NoneBodyRsp;


public abstract class ServiceStub {
	private String baseUrl;

	private String userName;

	private String password;

	private ErrorHandler errorHandler = new DefaultErrorHandler();

	protected RestTemplate rt;

	public ServiceStub() {
		HttpClient client = new DefaultHttpClient();
		HttpClientPassHttps.enableSSL(client);
		rt = new RestTemplate(
				new HttpComponentsClientHttpRequestFactory(client));
	}

	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	protected <T> T callWithLargeFile(RestRequest request, File file,
			Class<T> responseType) {
		String url = baseUrl + request.getPath();

		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod(String.valueOf(request.getMethod()));

			for (Header header : request.getHeaders()) {
				con.setRequestProperty(header.getName(), header.getValue());
			}

			con.setChunkedStreamingMode(1024 * 1024);
			con.connect();
			BufferedOutputStream output = new BufferedOutputStream(
					con.getOutputStream());
			BufferedInputStream input = new BufferedInputStream(
					new FileInputStream(file));
			output.flush();
			IOUtils.copy(input, output);
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			IOUtils.copy(con.getInputStream(), out);

			byte[] responseBody = out.toByteArray();
			int code = con.getResponseCode();
			if (code >= 400) {
				HttpStatus status = HttpStatus.valueOf(code);
				throw new HttpClientErrorException(status,
						status.getReasonPhrase(), responseBody,
						Charset.forName("UTF-8"));
			} else {
				if (responseType == null || responseType.equals(NoneBodyRsp.class)) {
					return null;
				} else {
					return JsonSerializer.j2o(
							new String(responseBody, "UTF-8"), responseType);
				}
			}
		} catch (MalformedURLException e) {
			HttpStatus status = HttpStatus.valueOf(500);
			throw new HttpClientErrorException(status,
					status.getReasonPhrase(), e.getMessage().getBytes(),
					Charset.forName("UTF-8"));
		} catch (ProtocolException e) {
			HttpStatus status = HttpStatus.valueOf(500);
			throw new HttpClientErrorException(status,
					status.getReasonPhrase(), e.getMessage().getBytes(),
					Charset.forName("UTF-8"));
		} catch (FileNotFoundException e) {
			HttpStatus status = HttpStatus.valueOf(500);
			throw new HttpClientErrorException(status,
					status.getReasonPhrase(), e.getMessage().getBytes(),
					Charset.forName("UTF-8"));
		} catch (IOException e) {
			HttpStatus status = HttpStatus.valueOf(500);
			throw new HttpClientErrorException(status,
					status.getReasonPhrase(), e.getMessage().getBytes(),
					Charset.forName("UTF-8"));
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}

	protected <T> T call(RestRequest request, Class<T> responseType) {
		HttpHeaders headers = new HttpHeaders();
		for (Header header : request.getHeaders()) {
			headers.add(header.getName(), header.getValue());
		}

		HttpEntity<String> httpEntity = null;
		if (request.getMethod() != HttpMethod.GET && request.getBody() != null) {
			String content = JsonSerializer.o2j(request.getBody());
			httpEntity = new HttpEntity<String>(content, headers);
		} else {
			httpEntity = new HttpEntity<String>(null, headers);
		}

		ResponseEntity<String> re = null;
		try {
			String url = baseUrl;
			re = rt.exchange(url + request.getPath(), request.getMethod(),
					httpEntity, String.class);
		} catch (RestClientException e) {
			handerError(e);
		}

		if (responseType == null || responseType.equals(NoneBodyRsp.class)) {
			return null;
		}

		return JsonSerializer.j2o(re.getBody(), responseType);
	}

	protected RestRequest prepare(AbstractReq req, HttpMethod method, String url) {
		RestRequest request = new RestRequest(method, url);
		if (StringUtils.isNotEmpty(req.getToken())) {
			request.addHeader(new Header("X-Auth-Token", req.getToken()));
		}
		if (StringUtils.isNotEmpty(userName)
				&& StringUtils.isNotEmpty(password)) {
			req.addQueryParams("userName", userName);
			req.addQueryParams("password", password);
		}
		if (StringUtils.isNotEmpty(req.getQueryString())) {
			request.setQueryString(req.getQueryString());
		}
		return request;
	}

	/**
	 * handle rest call errors
	 * 
	 * @param e
	 */
	private void handerError(RestClientException e) {
		if (errorHandler != null) {
			errorHandler.handleError(e);
		} else {
			throw e;
		}
	}
}
