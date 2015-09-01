package com.asiainfo.gim.deploy.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import com.asiainfo.gim.common.rest.exception.ResourceNotFoundException;
import com.asiainfo.gim.common.spring.SpringContext;
import com.asiainfo.gim.deploy.api.service.TemplateService;
import com.asiainfo.gim.deploy.api.validator.TemplateValidator;
import com.asiainfo.gim.deploy.domain.KickStartConf;
import com.asiainfo.gim.deploy.domain.TemplateInfo;

@Path("/templateres")
@Produces(MediaType.APPLICATION_JSON)
public class TemplateResource {
	
	private TemplateService templateService;
	
	@PathParam("templateid")
	private String templateId;
	
	public TemplateResource(){
		templateService = SpringContext.getBean(TemplateService.class);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public KickStartConf createOrUpdateTemplate(@TemplateValidator KickStartConf kickStartConf){
		if(StringUtils.isNotBlank(kickStartConf.getTempInfo().getTemplateId())){
			TemplateInfo info = templateService.getTemplateInfo(kickStartConf.getTempInfo().getTemplateId());
			if(info == null){
				throw new ResourceNotFoundException();
			}
			return templateService.updateTemplate(kickStartConf);
		}else{
			return templateService.createTemplate(kickStartConf);
		}
	}
	
	@DELETE
	@Path("{templateid}")
	public void deleteTemplate(){
		templateService.deleteTemplate(templateId);
	}
	
	@GET
	@Path("{templateid}")
	public KickStartConf getTemplate(){
		TemplateInfo info = templateService.getTemplateInfo(templateId);
		if(info == null){
			throw new ResourceNotFoundException();
		}
		return templateService.queryTemplate(templateId);
	}
	
}
