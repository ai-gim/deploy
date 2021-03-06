package com.asiainfo.gim.deploy.api.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.asiainfo.gim.common.spring.SpringContext;
import com.asiainfo.gim.deploy.dao.ImageDao;
import com.asiainfo.gim.deploy.dao.ImageDefaultConfDao;
import com.asiainfo.gim.deploy.dao.ImageTemplateDao;
import com.asiainfo.gim.deploy.dao.TemplateFileDao;
import com.asiainfo.gim.deploy.domain.Distro;
import com.asiainfo.gim.deploy.domain.Image;
import com.asiainfo.gim.deploy.domain.ImageDefaultConf;
import com.asiainfo.gim.deploy.domain.ImageTemplate;
import com.asiainfo.gim.deploy.domain.IsoFile;
import com.asiainfo.gim.deploy.domain.TemplateFile;
import com.asiainfo.gim.deploy.file.FileUtil;
import com.asiainfo.gim.deploy.xcat.image.ImageResourceReq;
import com.asiainfo.gim.deploy.xcat.image.ImageResourceServiceStub;

/**
 * @author liuxm
 *
 */
@Service
public class ImageService
{

	@Resource
	private ImageResourceServiceStub imageResourceServiceStub;

	@Resource
	private ImageDefaultConfDao imageDefaultConfDao;

	@Resource
	private ImageDao imageDao;

	@Resource
	private ImageTemplateDao imageTemplateDao;
	
	@Resource
	private TemplateFileDao templateFileDao;

	public void setImageResourceServiceStub(ImageResourceServiceStub imageResourceServiceStub)
	{
		this.imageResourceServiceStub = imageResourceServiceStub;
	}

	public void setImageDefaultConfDao(ImageDefaultConfDao imageDefaultConfDao)
	{
		this.imageDefaultConfDao = imageDefaultConfDao;
	}

	public void setImageDao(ImageDao imageDao)
	{
		this.imageDao = imageDao;
	}

	public void setImageTemplateDao(ImageTemplateDao imageTemplateDao)
	{
		this.imageTemplateDao = imageTemplateDao;
	}

	public List<ImageDefaultConf> listImageDefaultConf()
	{
		return imageDefaultConfDao.listImageDefaultConf();
	}

	public void setTemplateFileDao(TemplateFileDao templateFileDao)
	{
		this.templateFileDao = templateFileDao;
	}

	public List<Distro> listOsDistro()
	{
		ImageResourceReq req = new ImageResourceReq();
		return imageResourceServiceStub.listOsDistros(req);
	}

	public void createImage(Image image)
	{
		ImageResourceReq req = new ImageResourceReq();
		req.setIso(image.getIsoFile());
		req.setArch(image.getOsArch());
		req.setOsvers(image.getOsType() + image.getOsVersion());
		imageResourceServiceStub.copyCds(req);
		image.setCreateTime(new Date());
		imageDao.createImage(image);
		// 更新镜像默认配置表deploy_image_default_conf
		ImageResourceReq req1 = new ImageResourceReq();
		String osdistroname = image.getOsType() + image.getOsVersion() + "-" + image.getOsArch();
		req1.setOsdistroname(osdistroname);
		List<ImageDefaultConf> imageConfList = imageResourceServiceStub.listOsImages(req1);
		for (ImageDefaultConf imageConf : imageConfList)
		{
			ImageDefaultConf idc = imageDefaultConfDao.findImageDefaultConfByImageName(imageConf.getImageName());
			// 不存在，则新增
			if (idc == null)
			{
				imageDefaultConfDao.createImageDefaultConf(imageConf);
			}
		}
	}

	/**
	 * 新增加镜像模板，若存在则不增
	 */
	public ImageTemplate createImageTemplate(ImageTemplate imageTemplate)
	{
		List<ImageTemplate> imgTempList = imageTemplateDao.listImageTemplate();
		for (ImageTemplate imgTemp : imgTempList)
		{
			if (StringUtils.equals(imgTemp.getImageName(), imageTemplate.getImageName())
					&& StringUtils.equals(imgTemp.getTemplateId(), imageTemplate.getTemplateId()))
			{
				return imgTemp;
			}
		}
		if(StringUtils.isBlank(imageTemplate.getName())){
			imageTemplate.setName(UUID.randomUUID().toString());
		}
		ImageResourceReq req = new ImageResourceReq();
		ImageDefaultConf image = imageDefaultConfDao.findImageDefaultConfByImageName(imageTemplate.getImageName());
		image.setImageName(imageTemplate.getName());
		List<TemplateFile> templateFileList = templateFileDao.listTempateFileByTemplateId(imageTemplate.getTemplateId());
		if(StringUtils.startsWith(imageTemplate.getImageName(), "rhel") || StringUtils.startsWith(imageTemplate.getImageName(), "centos")){
			for(TemplateFile tempFile : templateFileList){
				if(StringUtils.equals(tempFile.getType(), "kickstart")){
					image.setTemplate(tempFile.getConfFilePath());
				}
			}
		}
		image.setId(null);
		req.setImage(image);
		imageResourceServiceStub.createOsImage(req);
		imageTemplateDao.createImageTemplate(imageTemplate);
		return imageTemplate;
	}

	public List<Image> listImage()
	{
		return imageDao.listImage();
	}

	public Image getImage(int id)
	{
		return imageDao.findImageById(id);
	}

	public void deleteImage(int id)
	{
		Image image = imageDao.findImageById(id);
		String distroName = image.getOsType() + image.getOsVersion() + "-" + image.getOsArch();
		imageDao.deleteImageById(id);
		deleteDistro(distroName);
	}

	private void deleteDistro(String distroName)
	{
		// 删除镜像文件
		List<Distro> distroList = listOsDistro();
		for (Distro distro : distroList)
		{
			if (StringUtils.equals(distroName, distro.getOsdistroname()))
			{
				FileUtil.deleteFile(new File(distro.getDirpaths()));
			}
		}
		ImageResourceReq req = new ImageResourceReq();
		req.setOsdistroname(distroName);
		List<ImageDefaultConf> imageList = imageResourceServiceStub.listOsImages(req);
		// 删除镜像
		for (ImageDefaultConf image : imageList)
		{
			deleteImage(image.getImageName());
		}
		imageResourceServiceStub.deleteOsDistro(distroName);
	}

	private void deleteImage(String imageName)
	{
		imageDefaultConfDao.deleteImageDefaultConfByImageName(imageName);
		imageResourceServiceStub.deleteOsImage(imageName);
	}

	public List<IsoFile> listIsoFiles(String dir)
	{
		String isoPath;
		if (StringUtils.isBlank(dir))
		{
			isoPath = SpringContext.getProperty("xcat.mn.isopath");
		}
		else
		{
			isoPath = dir;
		}
		List<File> fileList = new ArrayList<File>();
		fileList = FileUtil.listFile(new File(isoPath), fileList);
		List<IsoFile> isoList = new ArrayList<IsoFile>();
		for (File file : fileList)
		{
			if (file.isFile() && StringUtils.endsWithIgnoreCase(file.getName(), ".iso"))
			{
				IsoFile iso = new IsoFile();
				iso.setFileName(file.getName());
				iso.setFilePath(file.getAbsolutePath());
				isoList.add(iso);
			}
		}
		return isoList;
	}

	public ImageDefaultConf findImageDefaultConfByImageName(String imageName)
	{
		return imageDefaultConfDao.findImageDefaultConfByImageName(imageName);
	}

}
