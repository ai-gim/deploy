package com.asiainfo.gim.deploy.dao;

import java.util.List;

import com.asiainfo.gim.deploy.domain.TemplateBasicConf;

public interface TemplateBasicConfDao {
	
	public List<TemplateBasicConf> listTempBasicConfByTemplateId(String templateId);
	
	public void createTempBasicConf(TemplateBasicConf templateBasicConf);
	
	public void updateTempBasicConf(TemplateBasicConf templateBasicConf);
	
	public void deleteTempBasicConfByTemplateId(String templateId);
	
	public void deleteTempBasicConf(Integer id);

}
