package com.asiainfo.gim.deploy.dao;

import java.util.List;

import com.asiainfo.gim.deploy.domain.Image;

public interface ImageDao {
	
	public void createImage(Image image);
	
	public List<Image> listImage();
	
	public Image findImageById(int id);
	
	public void deleteImageById(int id);

}
