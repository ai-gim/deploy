package com.asiainfo.gim.deploy.dao;

import java.util.List;

import com.asiainfo.gim.deploy.domain.TemplateVolGroupConf;

public interface TemplateVolGroupConfDao {
	
	public List<TemplateVolGroupConf> listTempVolGroupByTemplateId(String templateId);
	
	public void createTempVolGroupConf(TemplateVolGroupConf templateVolGroupConf);
	
	public void updateTempVolGroupConf(TemplateVolGroupConf templateVolGroupConf);
	
	public void deleteTempVolGroupConfByTemplateId(String templateId);
	
	public void deleteTempVolGroupConf(String id);

}
