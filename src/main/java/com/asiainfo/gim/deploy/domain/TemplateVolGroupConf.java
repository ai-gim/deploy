package com.asiainfo.gim.deploy.domain;

public class TemplateVolGroupConf {
	
	private Integer id;
	
	private String templateId;
	
	private String name;
	
	private String partition;
	
	private Integer noFormat;
	
	private Integer useExisting;
	
	private Integer preSize;

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

	public String getPartition() {
		return partition;
	}

	public void setPartition(String partition) {
		this.partition = partition;
	}

	public Integer getNoFormat() {
		return noFormat;
	}

	public void setNoFormat(Integer noFormat) {
		this.noFormat = noFormat;
	}

	public Integer getUseExisting() {
		return useExisting;
	}

	public void setUseExisting(Integer useExisting) {
		this.useExisting = useExisting;
	}

	public Integer getPreSize() {
		return preSize;
	}

	public void setPreSize(Integer preSize) {
		this.preSize = preSize;
	}
	
	public String toKickStartStr(){
		return "volgroup " + name + " " + partition + "\n\r";
	}

}
