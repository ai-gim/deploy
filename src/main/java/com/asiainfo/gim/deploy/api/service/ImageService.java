package com.asiainfo.gim.deploy.api.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.asiainfo.gim.common.spring.SpringContext;
import com.asiainfo.gim.deploy.domain.Distro;
import com.asiainfo.gim.deploy.domain.Image;
import com.asiainfo.gim.deploy.file.FileUtil;
import com.asiainfo.gim.deploy.xcat.image.ImageResourceReq;
import com.asiainfo.gim.deploy.xcat.image.ImageResourceServiceStub;

/**
 * @author liuxm
 *
 */
@Service
public class ImageService {

	@Resource
	private ImageResourceServiceStub imageResourceServiceStub;

	public void setImageResourceServiceStub(
			ImageResourceServiceStub imageResourceServiceStub) {
		this.imageResourceServiceStub = imageResourceServiceStub;
	}

	public List<Image> listOsImage() {
		ImageResourceReq req = new ImageResourceReq();
		return imageResourceServiceStub.listOsImages(req);
	}
	
	public List<Distro> listOsDistro(){
		ImageResourceReq req = new ImageResourceReq();
		return imageResourceServiceStub.listOsDistros(req);
	}

	public void createImage(Image image) {
		ImageResourceReq req = new ImageResourceReq();
		String isoPath = SpringContext.getProperty("xcat.mn.isopath");
		req.setIso(isoPath + "/" + image.getIsoFile());
		req.setArch(image.getOsarch());
		req.setOsvers(image.getOsvers());
		imageResourceServiceStub.createOsImage(req);
	}

	public void deleteDistro(String distroName) {
		imageResourceServiceStub.deleteOsDistro(distroName);
	}
	
	public void deleteImage(String imageName){
		imageResourceServiceStub.deleteOsImage(imageName);
	}
	
	public List<String> listIsoFiles(String dir){
		String isoPath;
		if(StringUtils.isBlank(dir)){
			isoPath = SpringContext.getProperty("xcat.mn.isopath");
		}else{
			isoPath = dir;
		}
		List<File> fileList = FileUtil.listFile(isoPath);
		List<String> isoList = new ArrayList<String>();
		for(File file : fileList){
			if(file.isFile() && StringUtils.endsWithIgnoreCase(file.getName(), ".iso")){
				isoList.add(file.getName());
			}
		}
		return isoList;
	}

}
