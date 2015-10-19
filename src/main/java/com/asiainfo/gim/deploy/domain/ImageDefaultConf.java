package com.asiainfo.gim.deploy.domain;

import com.asiainfo.gim.deploy.rest.vp.AbstractBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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

	@JsonInclude(Include.NON_NULL)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonInclude(Include.NON_NULL)
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@JsonInclude(Include.NON_NULL)
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@JsonInclude(Include.NON_NULL)
	public String getPkgList() {
		return pkgList;
	}

	public void setPkgList(String pkgList) {
		this.pkgList = pkgList;
	}

	@JsonInclude(Include.NON_NULL)
	public String getPkgDir() {
		return pkgDir;
	}

	public void setPkgDir(String pkgDir) {
		this.pkgDir = pkgDir;
	}

	@JsonInclude(Include.NON_NULL)
	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@JsonInclude(Include.NON_NULL)
	public String getOsDistroName() {
		return osDistroName;
	}

	public void setOsDistroName(String osDistroName) {
		this.osDistroName = osDistroName;
	}

	@JsonInclude(Include.NON_NULL)
	public String getOsarch() {
		return osarch;
	}

	public void setOsarch(String osarch) {
		this.osarch = osarch;
	}

	@JsonInclude(Include.NON_NULL)
	public String getOsname() {
		return osname;
	}

	public void setOsname(String osname) {
		this.osname = osname;
	}

	@JsonInclude(Include.NON_NULL)
	public String getOsvers() {
		return osvers;
	}

	public void setOsvers(String osvers) {
		this.osvers = osvers;
	}

	@JsonInclude(Include.NON_NULL)
	public String getOtherPkgDir() {
		return otherPkgDir;
	}

	public void setOtherPkgDir(String otherPkgDir) {
		this.otherPkgDir = otherPkgDir;
	}

	@JsonInclude(Include.NON_NULL)
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@JsonInclude(Include.NON_NULL)
	public String getProvMethod() {
		return provMethod;
	}

	public void setProvMethod(String provMethod) {
		this.provMethod = provMethod;
	}

	@JsonInclude(Include.NON_NULL)
	public String getRootImgDir() {
		return rootImgDir;
	}

	public void setRootImgDir(String rootImgDir) {
		this.rootImgDir = rootImgDir;
	}

	@JsonInclude(Include.NON_NULL)
	public String getExlist() {
		return exlist;
	}

	public void setExlist(String exlist) {
		this.exlist = exlist;
	}

}
