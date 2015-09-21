package com.asiainfo.gim.deploy.domain;

import com.asiainfo.gim.deploy.rest.vp.AbstractBean;

public class Image extends AbstractBean{
	private String imagename;
	private String profile;
	private String provmethod;
	private String osvers;
	private String osarch;
	private String osname;
	private String imagetype;
	private String osdistroname;
	private String isoFile;
	private String templateId;
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getProvmethod() {
		return provmethod;
	}
	public void setProvmethod(String provmethod) {
		this.provmethod = provmethod;
	}
	public String getOsvers() {
		return osvers;
	}
	public void setOsvers(String osvers) {
		this.osvers = osvers;
	}
	public String getOsarch() {
		return osarch;
	}
	public void setOsarch(String osarch) {
		this.osarch = osarch;
	}
	public String getOsname() {
		return osname;
	}
	public void setOsname(String osname) {
		this.osname = osname;
	}
	public String getImagetype() {
		return imagetype;
	}
	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}
	public String getOsdistroname() {
		return osdistroname;
	}
	public void setOsdistroname(String osdistroname) {
		this.osdistroname = osdistroname;
	}
	public String getIsoFile() {
		return isoFile;
	}
	public void setIsoFile(String isoFile) {
		this.isoFile = isoFile;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

}
