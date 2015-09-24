package com.asiainfo.gim.deploy.api.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.asiainfo.gim.common.spring.SpringContext;
import com.asiainfo.gim.deploy.dao.ImageDefaultConfDao;
import com.asiainfo.gim.deploy.domain.Distro;
import com.asiainfo.gim.deploy.domain.Image;
import com.asiainfo.gim.deploy.domain.ImageDefaultConf;
import com.asiainfo.gim.deploy.domain.IsoFile;
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

	@Resource
	private ImageDefaultConfDao imageDefaultConfDao;

	public void setImageResourceServiceStub(
			ImageResourceServiceStub imageResourceServiceStub) {
		this.imageResourceServiceStub = imageResourceServiceStub;
	}

	public void setImageDefaultConfDao(ImageDefaultConfDao imageDefaultConfDao) {
		this.imageDefaultConfDao = imageDefaultConfDao;
	}

	public List<Image> listOsImage() {
		ImageResourceReq req = new ImageResourceReq();
		return imageResourceServiceStub.listOsImages(req);
	}

	public List<Distro> listOsDistro() {
		ImageResourceReq req = new ImageResourceReq();
		return imageResourceServiceStub.listOsDistros(req);
	}

	public void createImage(Image image) {
		ImageResourceReq req = new ImageResourceReq();
		req.setIso(image.getIsoFile());
		req.setArch(image.getOsarch());
		req.setOsvers(image.getOsvers());
		imageResourceServiceStub.createOsImage(req);
		// 更新镜像默认配置表deploy_image_default_conf
		ImageResourceReq req1 = new ImageResourceReq();
		List<ImageDefaultConf> imageConfList = imageResourceServiceStub
				.listLinuxImageConf(req1);
		for (ImageDefaultConf imageConf : imageConfList) {
			ImageDefaultConf idc = imageDefaultConfDao
					.findImageDefaultConfByImageName(imageConf.getImageName());
			// 不存在，则新增
			if (idc == null) {
				imageDefaultConfDao.createImageDefaultConf(imageConf);
			}
		}
	}

	public void deleteDistro(String distroName) {
		// 删除镜像文件
		List<Distro> distroList = listOsDistro();
		for (Distro distro : distroList) {
			if (StringUtils.equals(distroName, distro.getOsdistroname())) {
				FileUtil.deleteFile(new File(distro.getDirpaths()));
			}
		}
		ImageResourceReq req = new ImageResourceReq();
		req.setOsdistroname(distroName);
		List<Image> imageList = imageResourceServiceStub.listOsImages(req);
		// 删除镜像
		for (Image image : imageList) {
			deleteImage(image.getImagename());
		}
		imageResourceServiceStub.deleteOsDistro(distroName);
	}

	public void deleteImage(String imageName) {
		imageDefaultConfDao.deleteImageDefaultConfByImageName(imageName);
		imageResourceServiceStub.deleteOsImage(imageName);
	}

	public List<IsoFile> listIsoFiles(String dir) {
		String isoPath;
		if (StringUtils.isBlank(dir)) {
			isoPath = SpringContext.getProperty("xcat.mn.isopath");
		} else {
			isoPath = dir;
		}
		List<File> fileList = new ArrayList<File>();
		fileList = FileUtil.listFile(new File(isoPath), fileList);
		List<IsoFile> isoList = new ArrayList<IsoFile>();
		for (File file : fileList) {
			if (file.isFile()
					&& StringUtils.endsWithIgnoreCase(file.getName(), ".iso")) {
				IsoFile iso = new IsoFile();
				iso.setFileName(file.getName());
				iso.setFilePath(file.getAbsolutePath());
				isoList.add(iso);
			}
		}
		return isoList;
	}

	public ImageDefaultConf findImageDefaultConfByImageName(String imageName) {
		return imageDefaultConfDao.findImageDefaultConfByImageName(imageName);
	}

	public void updateLinuxImageConf(ImageDefaultConf imageConf) {
		ImageResourceReq req = new ImageResourceReq();
		req.setImageName(imageConf.getImageName());
		req.setTemplate(imageConf.getTemplate());
		req.setPkgList(imageConf.getPkgList());
		imageResourceServiceStub.updateLinuxImageConf(req);
	}

}
