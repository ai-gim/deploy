package com.asiainfo.gim.deploy.xcat.node;

import com.asiainfo.gim.deploy.domain.Node;
import com.asiainfo.gim.deploy.rest.vp.AbstractReq;

public class NodeResourceReq extends AbstractReq
{
	private String nodeRange;
	
	private Node node;

	public String getNodeRange()
	{
		return nodeRange;
	}

	public void setNodeRange(String nodeRange)
	{
		this.nodeRange = nodeRange;
	}
	
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public String getPath(){
		String path = "/nodes";
		if(nodeRange != null){
			path = path + "/" + nodeRange;
		}
		return path;
	}

}
