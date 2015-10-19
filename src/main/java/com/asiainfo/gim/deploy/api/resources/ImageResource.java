package com.asiainfo.gim.deploy.api.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.asiainfo.gim.common.spring.SpringContext;
import com.asiainfo.gim.deploy.api.service.ImageService;
import com.asiainfo.gim.deploy.api.validator.ImageValidator;
import com.asiainfo.gim.deploy.domain.Image;
import com.asiainfo.gim.deploy.domain.ImageDefaultConf;
import com.asiainfo.gim.deploy.domain.IsoFile;

@Path("/imageres")
@Produces(MediaType.APPLICATION_JSON)
public class ImageResource
{
	private ImageService imageService;

	@PathParam("imageid")
	private Integer imageId;

	public ImageResource()
	{
		imageService = (ImageService) SpringContext.getBean("imageService");
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void createImage(@ImageValidator Image image)
	{
		imageService.createImage(image);
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
