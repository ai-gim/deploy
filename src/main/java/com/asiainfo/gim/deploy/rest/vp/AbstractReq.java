package com.asiainfo.gim.deploy.rest.vp;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractReq
{
	private Map<String, String> queryParams = new HashMap<String, String>();

	private String token;

	public void addQueryParams(String name, String value)
	{
		queryParams.put(name, value);
	}

	@JsonIgnore
	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	@JsonIgnore
	public String getQueryString()
	{
		StringBuilder sb = new StringBuilder("?");
		for (Map.Entry<String, String> entry : queryParams.entrySet())
		{
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		return sb.substring(0, sb.length() - 1);
	}


}
