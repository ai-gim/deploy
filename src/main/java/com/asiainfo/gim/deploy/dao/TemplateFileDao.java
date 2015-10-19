package com.asiainfo.gim.deploy.dao;

import java.util.List;

import com.asiainfo.gim.deploy.domain.TemplateFile;

public interface TemplateFileDao {
	
	public List<TemplateFile> listTempateFileByTemplateId(String templateId);
	
	public void createTemplateFile(TemplateFile templateFile);
	
	public void deleteTemplateFile(String templateId);

}
