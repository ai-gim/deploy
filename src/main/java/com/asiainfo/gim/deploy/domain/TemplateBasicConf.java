package com.asiainfo.gim.deploy.domain;

import org.apache.commons.lang.StringUtils;

public class TemplateBasicConf {
	
	private Integer id;
	
	private String templateId;
	
	private String attr;
	
	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String toKickStartStr(){
		StringBuffer sb = new StringBuffer();
		if(StringUtils.equals("lang", attr)){
			sb.append("lang " + value);
		}else if(StringUtils.equals("timezone", attr)){
			sb.append("timezone " + value);
		}else if(StringUtils.equals("rootpw", attr)){
			sb.append("rootpw " + value);
		}
		sb.append("\n\r");
		return sb.toString();
	}

}
