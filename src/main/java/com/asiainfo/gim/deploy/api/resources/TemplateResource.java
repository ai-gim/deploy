package com.asiainfo.gim.deploy.api.resources;

import java.util.List;

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
import com.asiainfo.gim.deploy.domain.TemplateConf;
import com.asiainfo.gim.deploy.domain.TemplateInfo;

@Path("/templateres")
@Produces(MediaType.APPLICATION_JSON)
public class TemplateResource {
	
	private TemplateService templateService;
	
	@PathParam("templateid")
	private String templateId;
	
	public TemplateResource(){
		templateService = (TemplateService) SpringContext.getBean("templateService");
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TemplateConf createOrUpdateTemplate(@TemplateValidator TemplateConf templateConf){
		if(StringUtils.isNotBlank(templateConf.getTempInfo().getTemplateId())){
			TemplateInfo info = templateService.getTemplateInfo(templateConf.getTempInfo().getTemplateId());
			if(info == null){
				throw new ResourceNotFoundException();
			}
			return templateService.updateTemplate(templateConf);
		}else{
			return templateService.createTemplate(templateConf);
		}
	}
	
	@DELETE
	@Path("{templateid}")
	public void deleteTemplate(){
		templateService.deleteTemplate(templateId);
	}
	
	@GET
	@Path("{templateid}")
	public TemplateConf getTemplate(){
		TemplateInfo info = templateService.getTemplateInfo(templateId);
		if(info == null){
			throw new ResourceNotFoundException();
		}
		return templateService.queryTemplate(templateId);
	}
	
	@GET
	public List<TemplateInfo> listTemplate(){
		return templateService.listTemplateInfo();
	}
	
}
