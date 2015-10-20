package com.asiainfo.gim.deploy.dao;

import java.util.List;

import com.asiainfo.gim.deploy.domain.ImageTemplate;

public interface ImageTemplateDao
{
	public void createImageTemplate(ImageTemplate imageTemplate);
	
	public List<ImageTemplate> listImageTemplate();
	
	public ImageTemplate findImageTemplateById(int id);
	
	public void deleteImageTemplateById(int id);

}
