package com.asiainfo.gim.deploy.domain;

import java.util.List;

public class KickStartConf {
	
	private TemplateInfo tempInfo;
	
	private List<TemplateBasicConf> tempBaicList;
	
	private List<TemplatePartConf> tempPartList;
	
	private List<TemplateVolGroupConf> tempVGList;
	
	private List<TemplateLogVolConf> tempLVList;
	
	private List<TemplateUserConf> tempUserList;

	public TemplateInfo getTempInfo() {
		return tempInfo;
	}

	public void setTempInfo(TemplateInfo tempInfo) {
		this.tempInfo = tempInfo;
	}

	public List<TemplateBasicConf> getTempBaicList() {
		return tempBaicList;
	}

	public void setTempBaicList(List<TemplateBasicConf> tempBaicList) {
		this.tempBaicList = tempBaicList;
	}

	public List<TemplatePartConf> getTempPartList() {
		return tempPartList;
	}

	public void setTempPartList(List<TemplatePartConf> tempPartList) {
		this.tempPartList = tempPartList;
	}

	public List<TemplateVolGroupConf> getTempVGList() {
		return tempVGList;
	}

	public void setTempVGList(List<TemplateVolGroupConf> tempVGList) {
		this.tempVGList = tempVGList;
	}

	public List<TemplateLogVolConf> getTempLVList() {
		return tempLVList;
	}

	public void setTempLVList(List<TemplateLogVolConf> tempLVList) {
		this.tempLVList = tempLVList;
	}

	public List<TemplateUserConf> getTempUserList() {
		return tempUserList;
	}

	public void setTempUserList(List<TemplateUserConf> tempUserList) {
		this.tempUserList = tempUserList;
	}
	
	

}
