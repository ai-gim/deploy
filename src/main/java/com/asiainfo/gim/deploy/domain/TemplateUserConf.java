package com.asiainfo.gim.deploy.domain;

import org.apache.commons.lang.StringUtils;

public class TemplateUserConf {

	private Integer id;
	
	private String templateId;
	
	private String name;
	
	private String groups;
	
	private String homeDir;
	
	private String password;
	
	private Integer isCrypted;
	
	private String shell;
	
	private String uid;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getHomeDir() {
		return homeDir;
	}

	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIsCrypted() {
		return isCrypted;
	}

	public void setIsCrypted(Integer isCrypted) {
		this.isCrypted = isCrypted;
	}

	public String getShell() {
		return shell;
	}

	public void setShell(String shell) {
		this.shell = shell;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String toKickStartStr(){
		StringBuffer sb = new StringBuffer();
		sb.append("user --name " + name);
		if(StringUtils.isNotBlank(groups)){
			sb.append(" --groups " + groups);
		}
		if(StringUtils.isNotBlank(password)){
			sb.append(" --password " + password);
		}
		sb.append("\n");
		return sb.toString();
	}
	
}
