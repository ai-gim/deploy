package com.asiainfo.gim.deploy.xcat.image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpMethod;

import com.asiainfo.gim.deploy.domain.Distro;
import com.asiainfo.gim.deploy.domain.Image;
import com.asiainfo.gim.deploy.json.JsonSerializer;
import com.asiainfo.gim.deploy.rest.ServiceStub;
import com.asiainfo.gim.deploy.rest.http.RestRequest;

public class ImageResourceServiceStub extends ServiceStub {

	public List<Image> listOsImages(ImageResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.GET,
				"/tables/osimage/rows");
		Map<String, Object> imageMap = call(request, HashMap.class);
		List<Image> imageList = new ArrayList<Image>();
		for (String key : imageMap.keySet()) {
			List<Object> images = (List<Object>) imageMap.get(key);
			for (Object o : images) {
				String object = JsonSerializer.o2j(o);
				Image image = JsonSerializer.j2o(object, Image.class);
				imageList.add(image);
			}
		}
		// 过滤出合适的image
		Iterator<Image> it = imageList.iterator();
		while (it.hasNext()) {
			Image image = it.next();
			if (StringUtils.isNotEmpty(req.getArch())
					&& !StringUtils.equals(req.getArch(), image.getOsarch())) {
				it.remove();
			} else if (StringUtils.isNotEmpty(req.getProfile())
					&& !StringUtils
							.equals(req.getProfile(), image.getProfile())) {
				it.remove();
			} else if (StringUtils.isNotEmpty(req.getProvmethod())
					&& !StringUtils.equals(req.getProvmethod(),
							image.getProvmethod())) {
				it.remove();
			} else if (StringUtils.isNotEmpty(req.getOsdistroname())
					&& !StringUtils.equals(req.getOsdistroname(),
							image.getOsdistroname())) {
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

	public void createOsImage(ImageResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.POST, "/osimages");
		if (StringUtils.isNotBlank(req.getArch())
				|| StringUtils.isNotBlank(req.getOsvers())) {
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

	public void deleteOsImage(String imageName) {
		ImageResourceReq req = new ImageResourceReq();
		req.setImageName(imageName);
		RestRequest request = prepare(req, HttpMethod.DELETE, "/osimages/"
				+ req.getImageName());
		call(request, null);
	}

	public void deleteOsDistro(String distroName) {
		// 删除image
		ImageResourceReq req = new ImageResourceReq();
		req.setOsdistroname(distroName);
		List<Image> imageList = listOsImages(req);
		for (Image image : imageList) {
			deleteOsImage(image.getImagename());
		}
		// 删除distro
		RestRequest request = prepare(req, HttpMethod.DELETE,
				"/tables/osdistro/rows/osdistroname=" + req.getOsdistroname());
		call(request, null);
	}

}
