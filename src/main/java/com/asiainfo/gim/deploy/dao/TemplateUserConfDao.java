package com.asiainfo.gim.deploy.dao;

import java.util.List;

import com.asiainfo.gim.deploy.domain.TemplateUserConf;

public interface TemplateUserConfDao {
	
	public List<TemplateUserConf> listTempUserByTemplateId(String templateId);
	
	public void createTempUserConf(TemplateUserConf templateUserConf);
	
	public void updateTempUserConf(TemplateUserConf templateUserConf);
	
	public void deleteTempUserConfByTemplateId(String templateId);
	
	public void deleteTempUserConf(String id);

}
