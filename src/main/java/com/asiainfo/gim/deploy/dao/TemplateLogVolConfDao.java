package com.asiainfo.gim.deploy.dao;

import java.util.List;

import com.asiainfo.gim.deploy.domain.TemplateLogVolConf;

public interface TemplateLogVolConfDao {
	
	public List<TemplateLogVolConf> listTempLogVolByTemplateId(String templateId);
	
	public void createTempLogVolConf(TemplateLogVolConf templateLogVolConf);
	
	public void updateTempLogVolConf(TemplateLogVolConf templateLogVolConf);
	
	public void deleteTempLogVolConfByTemplateId(String templateId);
	
	public void deleteTempLogVolConf(String id);

}
