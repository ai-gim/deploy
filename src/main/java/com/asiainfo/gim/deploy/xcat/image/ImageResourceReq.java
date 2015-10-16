package com.asiainfo.gim.deploy.xcat.image;

import java.util.List;

import com.asiainfo.gim.deploy.domain.ImageDefaultConf;
import com.asiainfo.gim.deploy.rest.vp.AbstractReq;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ImageResourceReq extends AbstractReq{
	
	private String profile;
	
	private String provmethod;
	
	private String arch;
	
	private String osvers;
	
	private String iso;
	
	private String file;
	
	private String imageName;
	
	private List<Params> params;
	
	private String osdistroname;
	
	private String template;
	
	private String pkgList;
	
	private ImageDefaultConf image;

	@JsonInclude(Include.NON_NULL)
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@JsonInclude(Include.NON_NULL)
	public String getProvmethod() {
		return provmethod;
	}

	public void setProvmethod(String provmethod) {
		this.provmethod = provmethod;
	}

	@JsonInclude(Include.NON_NULL)
	public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	@JsonInclude(Include.NON_NULL)
	public String getOsvers() {
		return osvers;
	}

	public void setOsvers(String osvers) {
		this.osvers = osvers;
	}

	@JsonInclude(Include.NON_NULL)
	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}
	
	@JsonInclude(Include.NON_NULL)
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@JsonInclude(Include.NON_NULL)
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@JsonInclude(Include.NON_NULL)
	public List<Params> getParams() {
		return params;
	}

	public void setParams(List<Params> params) {
		this.params = params;
	}

	@JsonInclude(Include.NON_NULL)
	public String getOsdistroname() {
		return osdistroname;
	}

	public void setOsdistroname(String osdistroname) {
		this.osdistroname = osdistroname;
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
	public ImageDefaultConf getImage() {
		return image;
	}

	public void setImage(ImageDefaultConf image) {
		this.image = image;
	}
	
}

class Params{
	@JsonProperty(value="-n")
	private String name;
	@JsonProperty(value="-a")
	private String arch;
	@JsonInclude(Include.NON_NULL)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonInclude(Include.NON_NULL)
	public String getArch() {
		return arch;
	}
	public void setArch(String arch) {
		this.arch = arch;
	}
}
