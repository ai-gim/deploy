package com.asiainfo.gim.deploy.api.resources;

import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import com.asiainfo.gim.common.rest.exception.RestException;
import com.asiainfo.gim.common.spring.SpringContext;
import com.asiainfo.gim.deploy.api.service.ImageService;
import com.asiainfo.gim.deploy.api.service.NodeService;
import com.asiainfo.gim.deploy.api.service.PostScriptsService;
import com.asiainfo.gim.deploy.api.validator.NodeValidator;
import com.asiainfo.gim.deploy.domain.ImageTemplate;
import com.asiainfo.gim.deploy.domain.Node;

@Path("/noderes")
@Produces(MediaType.APPLICATION_JSON)
public class NodeResource {

	private PostScriptsService postScriptsService;

	private NodeService nodeService;
	
	private ImageService imageService;

	@PathParam("noderange")
	private String nodeRange;

	public NodeResource() {
		postScriptsService = (PostScriptsService) SpringContext
				.getBean("postScriptsService");
		nodeService = (NodeService) SpringContext.getBean("nodeService");
		imageService = (ImageService) SpringContext.getBean("imageService");
	}

	@POST
	public Node addNode(@NodeValidator Node node) {
		// 设置安装os后执行脚本
		List<Integer> bootScriptIds = node.getBootScriptIds();
		if (bootScriptIds != null) {
			String scriptsName = "";
			for (Integer id : bootScriptIds) {
				String scriptFileName = postScriptsService.findPostScriptsById(
						id).getFileName();
				scriptsName += scriptFileName + ",";
			}
			node.setPostbootscripts(scriptsName.substring(0,
					scriptsName.length() - 1));
		}
		// 设置安装节点
		List<Node> nodeList = nodeService.listNode(null);
		for (Node n : nodeList) {
			if (StringUtils.equals(n.getName(), node.getName())) {
				throw new RestException("nodeName conflict!");
			}
		}
		//获取本机ip，即xcat master ip，设置节点xcat、nfs
		String xcatIp = SpringContext.getProperty("xcat.mn.ip");
		node.setXcatmaster(xcatIp);
		node.setNfsserver(xcatIp);
		return nodeService.addNode(node);
	}

	@POST
	@Path("/installos")
	public void installOs(@NodeValidator Node node) {
		// 增加镜像模板
		ImageTemplate imageTemplate = new ImageTemplate();
		imageTemplate.setImageName(node.getOsImageType());
		imageTemplate.setTemplateId(node.getTemplateId());
		imageTemplate = imageService.createImageTemplate(imageTemplate);
		// 安装os
		nodeService.installOsToNode(node.getName(), imageTemplate.getName());
	}

	@GET
	@Path("/stat/{noderange}")
	public Map<String, String> getNodeStat() {
		return nodeService.getNodeStat(nodeRange);
	}

	@GET
	@Path("/{noderange}")
	public List<Node> listNodes() {
		if(StringUtils.equals("null", nodeRange)){
			nodeRange = null;
		}
		return nodeService.listNode(nodeRange);
	}

	@DELETE
	@Path("{noderange}")
	public void deleteNode() {
		nodeService.removeNode(nodeRange);
	}

}
