package com.asiainfo.gim.deploy.dao;

import java.util.List;

import com.asiainfo.gim.deploy.domain.ImageDefaultConf;

public interface ImageDefaultConfDao {
	
	public List<ImageDefaultConf> listImageDefaultConf();
	
	public ImageDefaultConf findImageDefaultConfByImageName(String imageName);
	
	public void createImageDefaultConf(ImageDefaultConf imageDefaultConf);
	
	public void deleteImageDefaultConfByImageName(String imageName);
	
	public void updateImageDefaultConf(ImageDefaultConf imageDefaultConf);

}
