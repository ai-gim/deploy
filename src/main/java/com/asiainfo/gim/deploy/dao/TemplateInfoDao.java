package com.asiainfo.gim.deploy.dao;

import java.util.List;

import com.asiainfo.gim.deploy.domain.TemplateInfo;

public interface TemplateInfoDao {
	
	public List<TemplateInfo> listTemplateInfo();
	
	public TemplateInfo findTempateInfoByTemplateId(String templateId);
	
	public void createTemplateInfo(TemplateInfo templateInfo);
	
	public void updateTemplateInfo(TemplateInfo templateInfo);
	
	public void deleteTemplateInfo(String templateId);

}
