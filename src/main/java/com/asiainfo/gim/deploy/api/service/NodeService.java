package com.asiainfo.gim.deploy.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.asiainfo.gim.deploy.domain.Node;
import com.asiainfo.gim.deploy.xcat.node.NodeResourceReq;
import com.asiainfo.gim.deploy.xcat.node.NodeResourceServiceStub;

/**
 * 
 * @author liuxm
 *
 */
@Service
public class NodeService {
	
	private static Log log = LogFactory.getLog(NodeService.class);
	
	@Resource
	private NodeResourceServiceStub nodeResourceServiceStub;

	public void setNodeResourceServiceStub(
			NodeResourceServiceStub nodeResourceServiceStub) {
		this.nodeResourceServiceStub = nodeResourceServiceStub;
	}

	public Node addNode(Node node) {
		NodeResourceReq req = new NodeResourceReq();
		req.setNodeRange(node.getName());
		//去掉不合理参数
		String action = node.getAction();
		String order = node.getOrder();
		String osImageType = node.getOsImageType();
		String osImage = node.getOsimage();
		String templateId = node.getTemplateId();
		List<Integer> bootScriptIds = node.getBootScriptIds();
		node.setName(null);
		node.setAction(null);
		node.setOrder(null);
		node.setOsimage(null);
		node.setOsImageType(null);
		node.setBootScriptIds(null);
		node.setTemplateId(null);
		req.setNode(node);
		nodeResourceServiceStub.addNode(req);
		nodeResourceServiceStub.makeHosts(req);
		try{
			nodeResourceServiceStub.makeDns(req);
		}catch(Exception e){
			log.error(e.getMessage(), e);
		}
		nodeResourceServiceStub.makeDhcp(req);
		node.setName(req.getNodeRange());
		node.setAction(action);
		node.setOrder(order);
		node.setOsimage(osImage);
		node.setOsImageType(osImageType);
		node.setBootScriptIds(bootScriptIds);
		node.setTemplateId(templateId);
		return node;
	}
	
	public void removeNode(String nodeRange) {
		NodeResourceReq req = new NodeResourceReq();
		req.setNodeRange(nodeRange);
		try{
			nodeResourceServiceStub.removeDns(req);
		}catch(Exception e){
			log.error(e.getMessage(), e);
		}
		nodeResourceServiceStub.removeDhcp(req);
		nodeResourceServiceStub.removeNode(req);
	}
	
	public void installOsToNode(String nodeRange, String osImage) {
		NodeResourceReq req = new NodeResourceReq();
		req.setNodeRange(nodeRange);
		Node node = new Node();
		node.setOsimage(osImage);
		req.setNode(node);
		nodeResourceServiceStub.setBootState(req);
		// 设置下次启动引导
		Node node1 = new Node();
		node1.setOrder("net");
		req.setNode(node1);
		nodeResourceServiceStub.resetNextBoot(req);
		// 重启
		Node node2 = new Node();
		node2.setAction("reset");
		req.setNode(node2);
		nodeResourceServiceStub.rePower(req);
	}
	
	public List<Node> listNode(String nodeRange) {
		NodeResourceReq req = new NodeResourceReq();
		req.setNodeRange(nodeRange);
		List<Node> nodeList = null;
		try{
			nodeList = nodeResourceServiceStub.nodesList(req);
		}catch(Exception e){
			log.error(e.getMessage(), e);
			nodeList = new ArrayList<Node>();
		}
		return nodeList;
	}
	
	public Map<String, String> getNodeStat(String nodeRange){
		NodeResourceReq req = new NodeResourceReq();
		req.setNodeRange(nodeRange);
		return nodeResourceServiceStub.getNodeStat(req);
	}

}
