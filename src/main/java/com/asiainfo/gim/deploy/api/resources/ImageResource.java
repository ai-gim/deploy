package com.asiainfo.gim.deploy.api.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import com.asiainfo.gim.common.spring.SpringContext;
import com.asiainfo.gim.deploy.api.service.ImageService;
import com.asiainfo.gim.deploy.api.service.TemplateService;
import com.asiainfo.gim.deploy.api.validator.ImageValidator;
import com.asiainfo.gim.deploy.domain.Distro;
import com.asiainfo.gim.deploy.domain.Image;
import com.asiainfo.gim.deploy.domain.ImageDefaultConf;
import com.asiainfo.gim.deploy.domain.IsoFile;
import com.asiainfo.gim.deploy.domain.TemplateInfo;

@Path("/imageres")
@Produces(MediaType.APPLICATION_JSON)
public class ImageResource {
	private ImageService imageService;

	private TemplateService templateService;

	@PathParam("distroname")
	private String distroName;

	@PathParam("imagename")
	private String imageName;

	public ImageResource() {
		imageService = (ImageService) SpringContext.getBean("imageService");
		templateService = (TemplateService) SpringContext
				.getBean("templateService");
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void createImage(@ImageValidator Image image) {
		imageService.createImage(image);
	}

	@PUT
	@Path("/images/{imagename}")
	public void updateLinuxImageConf(@ImageValidator Image image) {
		// 设置镜像模板
		String templateId = image.getTemplateId();
		ImageDefaultConf imageConf;
		if (StringUtils.isNotBlank(templateId)) {
			TemplateInfo tempInfo = templateService.getTemplateInfo(templateId);
			imageConf = new ImageDefaultConf();
			imageConf.setImageName(imageName);
			imageConf.setTemplate(tempInfo.getConfFilePath());
		} else {
			imageConf = imageService
					.findImageDefaultConfByImageName(imageName);
		}
		if (imageConf != null) {
			imageService.updateLinuxImageConf(imageConf);
		}
	}

	@DELETE
	@Path("/distros/{distroname}")
	public void deleteDistro() {
		imageService.deleteDistro(distroName);
	}

	@DELETE
	@Path("/images/{imagename}")
	public void deleteImage() {
		imageService.deleteImage(imageName);
	}

	@GET
	@Path("/images")
	public List<Image> listImages() {
		return imageService.listOsImage();
	}

	@GET
	@Path("/distros")
	public List<Distro> listDistros() {
		return imageService.listOsDistro();
	}

	@GET
	@Path("/isofiles")
	public List<IsoFile> listIsoFiles(
			@QueryParam(value = "isodir") String isoDir) {
		return imageService.listIsoFiles(isoDir);
	}
}
