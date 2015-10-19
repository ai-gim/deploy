package com.asiainfo.gim.deploy.xcat.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpMethod;

import com.asiainfo.gim.deploy.domain.Node;
import com.asiainfo.gim.deploy.json.JsonSerializer;
import com.asiainfo.gim.deploy.rest.ServiceStub;
import com.asiainfo.gim.deploy.rest.http.RestRequest;



public class NodeResourceServiceStub extends ServiceStub {

	public List<Node> nodesList(NodeResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.GET, req.getPath());
		List<Node> nodeList = new ArrayList<Node>();
		if (StringUtils.isNotEmpty(req.getNodeRange())) {
			Map<String, Object> nodesMap = call(request, HashMap.class);
			for (String key : nodesMap.keySet()) {
				String object = JsonSerializer.o2j(nodesMap.get(key));
				Node node = JsonSerializer.j2o(object, Node.class);
				node.setName(key);
				nodeList.add(node);
			}
		} else {
			List<String> nodes = call(request, ArrayList.class);
			for (String nodeName : nodes) {
				Node node = new Node();
				node.setName(nodeName);
				nodeList.add(node);
			}
		}
		return nodeList;
	}

	public void addNode(NodeResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.POST, req.getPath());
		request.setBody(req.getNode());
		call(request, null);
	}

	public void removeNode(NodeResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.DELETE, req.getPath());
		call(request, null);
	}

	public void makeHosts(NodeResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.POST, req.getPath()
				+ "/host");
		call(request, null);
	}

	public void makeDns(NodeResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.POST, req.getPath()
				+ "/dns");
		call(request, null);
	}

	public void removeDns(NodeResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.DELETE, req.getPath()
				+ "/dns");
		call(request, null);
	}

	public void makeDhcp(NodeResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.POST, req.getPath()
				+ "/dhcp");
		call(request, null);
	}

	public void removeDhcp(NodeResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.DELETE, req.getPath()
				+ "/dhcp");
		call(request, null);
	}

	public void resetNextBoot(NodeResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.PUT, req.getPath()
				+ "/nextboot");
		request.setBody(req.getNode());
		call(request, null);
	}

	public void rePower(NodeResourceReq req) {
		RestRequest request = prepare(req, HttpMethod.PUT, req.getPath()
				+ "/power");
		request.setBody(req.getNode());
		call(request, null);
	}
	
	public void setBootState(NodeResourceReq req){
		RestRequest request = prepare(req, HttpMethod.PUT, req.getPath()
				+ "/bootstate");
		request.setBody(req.getNode());
		call(request, null);
	}
	
	public Map<String, String> getNodeStat(NodeResourceReq req){
		RestRequest request = prepare(req, HttpMethod.GET, req.getPath() + "/nodestat");
		Map<String, Object> nodesMap = call(request, HashMap.class);
		Map<String, String> statMap = new HashMap<String, String>();
		for (String key : nodesMap.keySet()) {
			String object = JsonSerializer.o2j(nodesMap.get(key));
			HashMap<String, String> node = JsonSerializer.j2o(object, HashMap.class);
			statMap.put(key, node.get("nodestat"));
		}
		return statMap;
	}

}
