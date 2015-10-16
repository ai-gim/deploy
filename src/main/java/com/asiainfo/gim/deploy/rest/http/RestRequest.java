package com.asiainfo.gim.deploy.rest.http;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;

public class RestRequest
{
	private List<Header> headers = new ArrayList<Header>();

	private HttpMethod method;

	private String path;

	private Object body;

	private String queryString = "";

	public RestRequest(HttpMethod method, String path)
	{
		this.method = method;
		this.path = path;
		headers.add(new Header("Content-Type", "application/json;charset=UTF-8"));
	}

	public void addHeader(Header header)
	{
		headers.add(header);
	}

	public void setBody(Object body)
	{
		this.body = body;
	}

	public Object getBody()
	{
		return body;
	}

	public List<Header> getHeaders()
	{
		return headers;
	}

	public HttpMethod getMethod()
	{
		return method;
	}

	public String getPath()
	{
		return path + queryString;
	}

	public void setQueryString(String queryString)
	{
		this.queryString = queryString;
	}

}
