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
import com.asiainfo.gim.deploy.domain.Image;
import com.asiainfo.gim.deploy.domain.ImageDefaultConf;
import com.asiainfo.gim.deploy.domain.IsoFile;
import com.asiainfo.gim.deploy.domain.TemplateFile;

@Path("/imageres")
@Produces(MediaType.APPLICATION_JSON)
public class ImageResource
{
	private ImageService imageService;

	private TemplateService templateService;

	@PathParam("imageid")
	private Integer imageId;

	@PathParam("imagename")
	private String imageName;

	public ImageResource()
	{
		imageService = (ImageService) SpringContext.getBean("imageService");
		templateService = (TemplateService) SpringContext.getBean("templateService");
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void createImage(@ImageValidator Image image)
	{
		imageService.createImage(image);
	}

	@PUT
	@Path("/images/{imagename}")
	public void updateLinuxImageConf(@ImageValidator Image image)
	{
		// 设置镜像模板
		String templateId = image.getTemplateId();
		ImageDefaultConf imageConf;
		if (StringUtils.isNotBlank(templateId))
		{
			imageConf = new ImageDefaultConf();
			imageConf.setImageName(imageName);
			List<TemplateFile> tempFileList = templateService.listTemplateFileByTemplateId(templateId);
			imageConf.setTemplate(tempFileList.get(0).getConfFilePath());
		}
		else
		{
			imageConf = imageService.findImageDefaultConfByImageName(imageName);
		}
		if (imageConf != null)
		{
			imageService.updateLinuxImageConf(imageConf);
		}
	}

	@DELETE
	@Path("/images/{imageid}")
	public void deleteImage()
	{
		imageService.deleteImage(imageId);
	}

	@GET
	@Path("/images/{imageid}")
	public Image getImage()
	{
		return imageService.getImage(imageId);
	}

	@GET
	@Path("/images")
	public List<Image> listImages()
	{
		return imageService.listImage();
	}

	@GET
	@Path("/imagetypes")
	public List<ImageDefaultConf> listImageDefaultConf()
	{
		return imageService.listImageDefaultConf();
	}

	@GET
	@Path("/isofiles")
	public List<IsoFile> listIsoFiles(@QueryParam(value = "isodir") String isoDir)
	{
		return imageService.listIsoFiles(isoDir);
	}
}
