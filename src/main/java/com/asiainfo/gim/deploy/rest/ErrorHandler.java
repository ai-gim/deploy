package com.asiainfo.gim.deploy.rest;

import org.springframework.web.client.RestClientException;

public interface ErrorHandler
{
	public void handleError(RestClientException e);
}
