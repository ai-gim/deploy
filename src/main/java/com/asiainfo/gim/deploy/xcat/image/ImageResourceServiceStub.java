package com.asiainfo.gim.deploy.xcat.image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpMethod;

import com.asiainfo.gim.deploy.domain.Distro;
import com.asiainfo.gim.deploy.domain.ImageDefaultConf;
import com.asiainfo.gim.deploy.json.JsonSerializer;
import com.asiainfo.gim.deploy.rest.ServiceStub;
import com.asiainfo.gim.deploy.rest.http.RestRequest;

public class ImageResourceServiceStub extends ServiceStub {

	public List<ImageDefaultConf> listOsImages(ImageResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.GET,
				"/tables/osimage/rows");
		Map<String, Object> imageMap = call(request, HashMap.class);
		List<ImageDefaultConf> imageList = new ArrayList<ImageDefaultConf>();
		for (String key : imageMap.keySet()) {
			List<Object> images = (List<Object>) imageMap.get(key);
			for (Object o : images) {
				String object = JsonSerializer.o2j(o);
				ImageDefaultConf image = JsonSerializer.j2o(object, ImageDefaultConf.class);
				imageList.add(image);
			}
		}
		//补充镜像配置信息
		List<ImageDefaultConf> imageConfList = listLinuxImageConf(req);
		for(ImageDefaultConf image : imageList){
			for(ImageDefaultConf imageConf : imageConfList){
				if(StringUtils.equals(image.getImageName(), imageConf.getImageName())){
					image.setExlist(imageConf.getExlist());
					image.setOtherPkgDir(imageConf.getOtherPkgDir());
					image.setPkgDir(imageConf.getPkgDir());
					image.setPkgList(imageConf.getPkgList());
					image.setRootImgDir(imageConf.getRootImgDir());
					image.setTemplate(imageConf.getTemplate());
				}
			}
		}
		// 过滤出合适的image
		Iterator<ImageDefaultConf> it = imageList.iterator();
		while (it.hasNext()) {
			ImageDefaultConf image = it.next();
			if (StringUtils.isNotEmpty(req.getArch())
					&& !StringUtils.equals(req.getArch(), image.getOsarch())) {
				it.remove();
			} else if (StringUtils.isNotEmpty(req.getProfile())
					&& !StringUtils
							.equals(req.getProfile(), image.getProfile())) {
				it.remove();
			} else if (StringUtils.isNotEmpty(req.getProvmethod())
					&& !StringUtils.equals(req.getProvmethod(),
							image.getProvMethod())) {
				it.remove();
			} else if (StringUtils.isNotEmpty(req.getOsdistroname())
					&& !StringUtils.equals(req.getOsdistroname(),
							image.getOsDistroName())) {
				it.remove();
			}

		}
		return imageList;
	}

	public List<Distro> listOsDistros(ImageResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.GET,
				"/tables/osdistro/rows");
		Map<String, Object> map = call(request, HashMap.class);
		List<Distro> distroList = new ArrayList<Distro>();
		for (String key : map.keySet()) {
			List<Object> distros = (List<Object>) map.get(key);
			for (Object o : distros) {
				String object = JsonSerializer.o2j(o);
				Distro distro = JsonSerializer.j2o(object, Distro.class);
				distroList.add(distro);
			}
		}
		return distroList;
	}

	public void copyCds(ImageResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.POST, "/osimages");
		if(StringUtils.isBlank(req.getArch())){
			req.setArch(null);
		}
		if(StringUtils.isBlank(req.getOsvers())){
			req.setOsvers(null);
		}
		if (StringUtils.isNotEmpty(req.getArch())
				|| StringUtils.isNotEmpty(req.getOsvers())) {
			Params params = new Params();
			params.setName(req.getOsvers());
			params.setArch(req.getArch());
			List<Params> paramsList = new ArrayList<Params>();
			paramsList.add(params);
			req.setParams(paramsList);
			req.setArch(null);
			req.setOsvers(null);
		}
		request.setBody(req);
		call(request, null);
	}
	
	public void createOsImage(ImageResourceReq req){
		ImageDefaultConf image = req.getImage();
		RestRequest request = prepare(req, HttpMethod.POST, "/osimages/" + image.getImageName());
		image.setImageName(null);
		request.setBody(image);
		call(request, null);
	}

	public void deleteOsImage(String imageName) {
		ImageResourceReq req = new ImageResourceReq();
		req.setImageName(imageName);
		RestRequest request = prepare(req, HttpMethod.DELETE, "/osimages/"
				+ req.getImageName());
		call(request, null);
	}

	public void deleteOsDistro(String distroName) {
		ImageResourceReq req = new ImageResourceReq();
		RestRequest request = prepare(req, HttpMethod.DELETE,
				"/tables/osdistro/rows/osdistroname=" + distroName);
		call(request, null);
	}
	
	private List<ImageDefaultConf> listLinuxImageConf(ImageResourceReq req){
		RestRequest request = prepare(req, HttpMethod.GET, "/tables/linuximage/rows");
		Map<String, Object> map = call(request, HashMap.class);
		List<ImageDefaultConf> imageConfList = new ArrayList<ImageDefaultConf>();
		for (String key : map.keySet()) {
			List<Object> imageConfs = (List<Object>) map.get(key);
			for (Object o : imageConfs) {
				String object = JsonSerializer.o2j(o);
				ImageDefaultConf imageConf = JsonSerializer.j2o(object, ImageDefaultConf.class);
				imageConfList.add(imageConf);
			}
		}
		return imageConfList;
	}
	
	public ImageDefaultConf getImageByImageName(String imageName){
		ImageResourceReq req = new ImageResourceReq();
		RestRequest request = prepare(req, HttpMethod.GET, "/osimages/" + imageName);
		Map<String, Object> map = call(request, HashMap.class);
		ImageDefaultConf imageConf = new ImageDefaultConf();
		for (String key : map.keySet()) {
			List<Object> imageConfs = (List<Object>) map.get(key);
			for (Object o : imageConfs) {
				String object = JsonSerializer.o2j(o);
				imageConf = JsonSerializer.j2o(object, ImageDefaultConf.class);
			}
		}
		return imageConf;
	}
	
	public void updateLinuxImageConf(ImageResourceReq req){
		RestRequest request = prepare(req, HttpMethod.PUT, "/tables/linuximage/rows/imagename=" + req.getImageName());
		request.setBody(req);
		call(request, null);
	}

}
