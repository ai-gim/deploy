package com.asiainfo.gim.deploy.domain;

public class TemplateLogVolConf {
	
	private Integer id;
	
	private String templateId;
	
	private String mntPoint;
	
	private String vgName;
	
	private Integer size;
	
	private String name;
	
	private Integer useExisting;
	
	private Integer noFormat;
	
	private String fsType;
	
	private Integer bytePerInode;
	
	private String fsOptions;
	
	private String precent;

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

	public String getVgName() {
		return vgName;
	}

	public void setVgName(String vgName) {
		this.vgName = vgName;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUseExisting() {
		return useExisting;
	}

	public void setUseExisting(Integer useExisting) {
		this.useExisting = useExisting;
	}

	public Integer getNoFormat() {
		return noFormat;
	}

	public void setNoFormat(Integer noFormat) {
		this.noFormat = noFormat;
	}

	public String getFsType() {
		return fsType;
	}

	public void setFsType(String fsType) {
		this.fsType = fsType;
	}

	public Integer getBytePerInode() {
		return bytePerInode;
	}

	public void setBytePerInode(Integer bytePerInode) {
		this.bytePerInode = bytePerInode;
	}

	public String getFsOptions() {
		return fsOptions;
	}

	public void setFsOptions(String fsOptions) {
		this.fsOptions = fsOptions;
	}

	public String getPrecent() {
		return precent;
	}

	public void setPrecent(String precent) {
		this.precent = precent;
	}
	
	public String toKickStartStr(){
		StringBuffer sb = new StringBuffer();
		sb.append("logvol " + mntPoint);
		sb.append(" --vgname " + vgName);
		sb.append(" --size " + size);
		sb.append(" --name " + name);
		sb.append("\n");
		return sb.toString();
	}
}
