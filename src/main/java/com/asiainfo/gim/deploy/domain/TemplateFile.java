package com.asiainfo.gim.deploy.domain;

public class TemplateFile
{

	private Integer id;

	private String templateId;

	private String type;

	private String confFilePath;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getConfFilePath()
	{
		return confFilePath;
	}

	public void setConfFilePath(String confFilePath)
	{
		this.confFilePath = confFilePath;
	}

}
