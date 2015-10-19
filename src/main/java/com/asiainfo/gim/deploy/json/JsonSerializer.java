package com.asiainfo.gim.deploy.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer
{
	public static String o2j(Object obj)
	{
		ObjectMapper om = new ObjectMapper();
		try
		{
			return om.writeValueAsString(obj);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T j2o(String jsonString, Class<T> clazz)
	{
		ObjectMapper om = new ObjectMapper();
		try
		{
			return om.readValue(jsonString, clazz);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
