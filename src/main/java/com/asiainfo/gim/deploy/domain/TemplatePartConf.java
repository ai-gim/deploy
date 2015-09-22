package com.asiainfo.gim.deploy.domain;

import org.apache.commons.lang.StringUtils;

public class TemplatePartConf {
	
	private Integer id;
	
	private String templateId;
	
	private String mntPoint;
	
	private Integer size;
	
	private Integer grow;
	
	private Integer maxSize;
	
	private Integer noFormat;
	
	private String onPart;
	
	private String onDisk;
	
	private Integer asPrimary;
	
	private String fsType;
	
	private String start;
	
	private String end;
	
	private Integer bytesPerInode;
	
	private Integer recommended;
	
	private Integer onBiosDisk;
	
	private String fsOptions;

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

	public String getMntPoint() {
		return mntPoint;
	}

	public void setMntPoint(String mntPoint) {
		this.mntPoint = mntPoint;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getGrow() {
		return grow;
	}

	public void setGrow(Integer grow) {
		this.grow = grow;
	}

	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	public Integer getNoFormat() {
		return noFormat;
	}

	public void setNoFormat(Integer noFormat) {
		this.noFormat = noFormat;
	}

	public String getOnPart() {
		return onPart;
	}

	public void setOnPart(String onPart) {
		this.onPart = onPart;
	}

	public String getOnDisk() {
		return onDisk;
	}

	public void setOnDisk(String onDisk) {
		this.onDisk = onDisk;
	}

	public Integer getAsPrimary() {
		return asPrimary;
	}

	public void setAsPrimary(Integer asPrimary) {
		this.asPrimary = asPrimary;
	}

	public String getFsType() {
		return fsType;
	}

	public void setFsType(String fsType) {
		this.fsType = fsType;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Integer getBytesPerInode() {
		return bytesPerInode;
	}

	public void setBytesPerInode(Integer bytesPerInode) {
		this.bytesPerInode = bytesPerInode;
	}

	public Integer getRecommended() {
		return recommended;
	}

	public void setRecommended(Integer recommended) {
		this.recommended = recommended;
	}

	public Integer getOnBiosDisk() {
		return onBiosDisk;
	}

	public void setOnBiosDisk(Integer onBiosDisk) {
		this.onBiosDisk = onBiosDisk;
	}

	public String getFsOptions() {
		return fsOptions;
	}

	public void setFsOptions(String fsOptions) {
		this.fsOptions = fsOptions;
	}
	
	public String toKickStartStr(){
		StringBuffer sb = new StringBuffer();
		sb.append("part " + mntPoint);
		sb.append(" --size " + size);
		if(StringUtils.isNotBlank(onDisk)){
			sb.append(" --ondisk " + onDisk);
		}
		if(StringUtils.isNotBlank(fsType)){
			sb.append(" --fstype " + fsType);
		}
		sb.append("\n");
		return sb.toString();
	}

}
