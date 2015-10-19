package com.asiainfo.gim.deploy;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import com.asiainfo.gim.common.rest.exception.DefaultExceptionMapper;
import com.asiainfo.gim.common.rest.filter.LogFilter;
import com.asiainfo.gim.deploy.api.filter.AuthorizationFilter;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


public class DeployApplication extends ResourceConfig
{
	public DeployApplication()
	{
		packages("com.asiainfo.gim.deploy.api.resources");

		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

		register(JacksonJsonProvider.class);	
		register(DefaultExceptionMapper.class);
		register(LogFilter.class);
		
		register(MultiPartFeature.class);

		register(AuthorizationFilter.class);
	}
}
