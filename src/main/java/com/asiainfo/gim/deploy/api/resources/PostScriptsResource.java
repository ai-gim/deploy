package com.asiainfo.gim.deploy.api.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.asiainfo.gim.common.rest.exception.ResourceNotFoundException;
import com.asiainfo.gim.common.rest.exception.RestException;
import com.asiainfo.gim.common.spring.SpringContext;
import com.asiainfo.gim.deploy.api.service.PostScriptsService;
import com.asiainfo.gim.deploy.api.validator.PostScriptsValidator;
import com.asiainfo.gim.deploy.domain.PostScripts;

@Path("/postscripts")
@Produces(MediaType.APPLICATION_JSON)
public class PostScriptsResource {
	
	private PostScriptsService postScriptsService;
	
	@PathParam("id") 
	private int id;
	
	public PostScriptsResource(){
		postScriptsService = (PostScriptsService) SpringContext.getBean("postScriptsService");
	}
	
	@GET
	public List<PostScripts> listPostScripts(){
		return postScriptsService.listPostScripts();
	}
	
	@GET
	@Path("{id}")
	public PostScripts findPostScripts(){
		return postScriptsService.findPostScriptsById(id);
	}
	
	@DELETE
	@Path("{id}")
	public void deletePostScripts(){
		PostScripts scripts = postScriptsService.findPostScriptsById(id);
		if(scripts == null){
			return;
		}
		if(StringUtils.isNotEmpty(scripts.getFileName())){
			String postScriptsDir = SpringContext.getProperty("xcat.mn.postscriptsdir");
			File scriptsFile = new File(postScriptsDir , scripts.getFileName());
			if(scriptsFile.exists() && scriptsFile.isFile()){
				scriptsFile.delete();
			}
		}
		postScriptsService.deletePostScripts(id);
	}
	
	@PUT
	@Path("{id}")
	public void updatePostScripts(@PostScriptsValidator PostScripts postScripts){
		PostScripts scripts = postScriptsService.findPostScriptsById(id);
		if(scripts == null){
			throw new ResourceNotFoundException();
		}
		postScripts.setId(id);
		postScriptsService.updatePostScripts(postScripts);
	}
	
	@POST
	public PostScripts createPostScripts(@PostScriptsValidator PostScripts postScripts){
		String postScriptsDir = SpringContext.getProperty("xcat.mn.postscriptsdir");
		postScripts.setFilePath(postScriptsDir);
		postScripts.setCreateTime(new Date());
		return postScriptsService.createPostScripts(postScripts);
	}
	
	@POST
	@Path("fileupload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String uploadPostScripts(@FormDataParam("file") InputStream file,
            @FormDataParam("file") FormDataContentDisposition fileDisposition,
            @FormDataParam("fileName") String fileName){
		if(StringUtils.isBlank(fileName)){
			fileName = "custom_" + UUID.randomUUID().toString();
		}
		String postScriptsDir = SpringContext.getProperty("xcat.mn.postscriptsdir");
		File scriptsFile = new File(postScriptsDir, fileName);
		if(scriptsFile.exists()){
			throw new RestException(fileName + " file exists!");
		}
        try {
            OutputStream outputStream = new FileOutputStream(scriptsFile);
            int length = 0;
            byte[] buff = new byte[256];
            while (-1 != (length = file.read(buff))) {
                outputStream.write(buff, 0, length);
            }
            file.close();
            outputStream.close();
        }  catch (IOException e) {
            throw new RestException(e);
        }
 
        return fileName;
	}
	
	@GET
	@Path("filedown/{fileName}")
	public Response downPostScripts(@PathParam("fileName") String fileName){
		String postScriptsDir = SpringContext.getProperty("xcat.mn.postscriptsdir");
		File file = new File(postScriptsDir, fileName);
        if (!file.exists() || file.isDirectory()) {
            throw new ResourceNotFoundException();
        }
        String mt = new MimetypesFileTypeMap().getContentType(file);
        return Response.ok(file, mt).build();
	}

}
