package com.asiainfo.gim.deploy.domain;

public class ImageDefaultConf {
	
	private Integer id;
	
	private String imageName;
	
	private String template;
	
	private String pkgList;

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

}
