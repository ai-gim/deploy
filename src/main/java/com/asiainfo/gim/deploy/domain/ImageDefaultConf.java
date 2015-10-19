package com.asiainfo.gim.deploy.domain;

import com.asiainfo.gim.deploy.rest.vp.AbstractBean;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageDefaultConf extends AbstractBean{
	
	private Integer id;
	
	@JsonProperty(value="imagename")
	private String imageName;
	
	private String template;
	
	@JsonProperty(value="pkglist")
	private String pkgList;
	
	@JsonProperty(value="pkgdir")
	private String pkgDir;
	
	@JsonProperty(value="imagetype")
	private String imageType;
	
	@JsonProperty(value="osdistroname")
	private String osDistroName;
	
	private String osarch;
	
	private String osname;
	
	private String osvers;
	
	@JsonProperty(value="otherpkgdir")
	private String otherPkgDir;
	
	private String profile;
	
	@JsonProperty(value="provmethod")
	private String provMethod;
	
	@JsonProperty(value="rootimgdir")
	private String rootImgDir;
	
	private String exlist;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getPkgList() {
		return pkgList;
	}

	public void setPkgList(String pkgList) {
		this.pkgList = pkgList;
	}

	public String getPkgDir() {
		return pkgDir;
	}

	public void setPkgDir(String pkgDir) {
		this.pkgDir = pkgDir;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getOsDistroName() {
		return osDistroName;
	}

	public void setOsDistroName(String osDistroName) {
		this.osDistroName = osDistroName;
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

	public String getOsvers() {
		return osvers;
	}

	public void setOsvers(String osvers) {
		this.osvers = osvers;
	}

	public String getOtherPkgDir() {
		return otherPkgDir;
	}

	public void setOtherPkgDir(String otherPkgDir) {
		this.otherPkgDir = otherPkgDir;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getProvMethod() {
		return provMethod;
	}

	public void setProvMethod(String provMethod) {
		this.provMethod = provMethod;
	}

	public String getRootImgDir() {
		return rootImgDir;
	}

	public void setRootImgDir(String rootImgDir) {
		this.rootImgDir = rootImgDir;
	}

	public String getExlist() {
		return exlist;
	}

	public void setExlist(String exlist) {
		this.exlist = exlist;
	}

}
