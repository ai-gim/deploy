package com.asiainfo.gim.deploy.dao;

import java.util.List;

import com.asiainfo.gim.deploy.domain.TemplatePartConf;

public interface TemplatePartConfDao {
	
	public List<TemplatePartConf> listTempPartByTemplateId(String templateId);
	
	public void createTempPartConf(TemplatePartConf templatePartConf);
	
	public void updateTempPartConf(TemplatePartConf templatePartConf);
	
	public void deleteTempPartConfByTemplateId(String templateId);
	
	public void deleteTempPartConf(String id);

}
