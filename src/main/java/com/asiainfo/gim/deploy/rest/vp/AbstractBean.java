package com.asiainfo.gim.deploy.rest.vp;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AbstractBean
{
	private Map<String, Object> extra;
	
	@JsonAnySetter
	public void addExtraProperty(String name, Object o)
	{
		if(extra == null)
		{
			extra = new HashMap<String, Object>();
		}
		extra.put(name, o);
	}

	@JsonIgnore
	public Map<String, Object> getExtra()
	{
		return extra;
	}
}
