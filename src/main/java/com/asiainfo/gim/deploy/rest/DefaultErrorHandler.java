package com.asiainfo.gim.deploy.rest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

public class DefaultErrorHandler implements ErrorHandler
{
	public void handleError(RestClientException e)
	{
		if(e instanceof HttpStatusCodeException)
		{
			HttpStatusCodeException ex = (HttpStatusCodeException)e;
			int responseCode = ex.getStatusCode().value();
			String message = ex.getResponseBodyAsString();
			if(StringUtils.isEmpty(message))
			{
				message = e.getMessage();
			}
			throw new XcatClinetException(responseCode, message);
		}
		else
		{
			throw new XcatClinetException(e);
		}
	}
}
