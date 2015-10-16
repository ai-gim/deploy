package com.asiainfo.gim.deploy.domain;

import java.util.List;

import com.asiainfo.gim.deploy.rest.vp.AbstractBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Node extends AbstractBean{
	
	private String name;
	private String profile;
	private String primarynic;
	private String status;
	private String netboot;
	private String ip;
	private String arch;
	private String os;
	private String kernel;
	private String power;
	private String mgt;
	private String groups;
	@JsonProperty(value="interface")
	private String intf;
	private String nfsserver;
	private String mac;
	private String xcatmaster;
	private String installnic;
	private String nictypes;
	private String nicips;
	private String nodetype;
	private String bmc;
	private Integer bmcport;
	private String bmcusername;
	private String bmcpassword;
	private String order;
	private String action;
	private String osimage;
	private String postscripts;
	private String postbootscripts;
	private List<Integer> bootScriptIds;
	@JsonInclude(Include.NON_NULL)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonInclude(Include.NON_NULL)
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	@JsonInclude(Include.NON_NULL)
	public String getPrimarynic() {
		return primarynic;
	}
	public void setPrimarynic(String primarynic) {
		this.primarynic = primarynic;
	}
	@JsonInclude(Include.NON_NULL)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@JsonInclude(Include.NON_NULL)
	public String getNetboot() {
		return netboot;
	}
	public void setNetboot(String netboot) {
		this.netboot = netboot;
	}
	@JsonInclude(Include.NON_NULL)
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@JsonInclude(Include.NON_NULL)
	public String getArch() {
		return arch;
	}
	public void setArch(String arch) {
		this.arch = arch;
	}
	@JsonInclude(Include.NON_NULL)
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	@JsonInclude(Include.NON_NULL)
	public String getKernel() {
		return kernel;
	}
	public void setKernel(String kernel) {
		this.kernel = kernel;
	}
	@JsonInclude(Include.NON_NULL)
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	@JsonInclude(Include.NON_NULL)
	public String getMgt() {
		return mgt;
	}
	public void setMgt(String mgt) {
		this.mgt = mgt;
	}
	@JsonInclude(Include.NON_NULL)
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	@JsonInclude(Include.NON_NULL)
	public String getIntf() {
		return intf;
	}
	public void setIntf(String intf) {
		this.intf = intf;
	}
	@JsonInclude(Include.NON_NULL)
	public String getNfsserver() {
		return nfsserver;
	}
	public void setNfsserver(String nfsserver) {
		this.nfsserver = nfsserver;
	}
	@JsonInclude(Include.NON_NULL)
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	@JsonInclude(Include.NON_NULL)
	public String getXcatmaster() {
		return xcatmaster;
	}
	public void setXcatmaster(String xcatmaster) {
		this.xcatmaster = xcatmaster;
	}
	@JsonInclude(Include.NON_NULL)
	public String getInstallnic() {
		return installnic;
	}
	public void setInstallnic(String installnic) {
		this.installnic = installnic;
	}
	@JsonInclude(Include.NON_NULL)
	public String getNictypes() {
		return nictypes;
	}
	public void setNictypes(String nictypes) {
		this.nictypes = nictypes;
	}
	@JsonInclude(Include.NON_NULL)
	public String getNicips() {
		return nicips;
	}
	public void setNicips(String nicips) {
		this.nicips = nicips;
	}
	@JsonInclude(Include.NON_NULL)
	public String getNodetype() {
		return nodetype;
	}
	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}
	@JsonInclude(Include.NON_NULL)
	public String getBmc() {
		return bmc;
	}
	public void setBmc(String bmc) {
		this.bmc = bmc;
	}
	@JsonInclude(Include.NON_NULL)
	public Integer getBmcport() {
		return bmcport;
	}
	public void setBmcport(Integer bmcport) {
		this.bmcport = bmcport;
	}
	@JsonInclude(Include.NON_NULL)
	public String getBmcusername() {
		return bmcusername;
	}
	public void setBmcusername(String bmcusername) {
		this.bmcusername = bmcusername;
	}
	@JsonInclude(Include.NON_NULL)
	public String getBmcpassword() {
		return bmcpassword;
	}
	public void setBmcpassword(String bmcpassword) {
		this.bmcpassword = bmcpassword;
	}
	@JsonInclude(Include.NON_NULL)
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	@JsonInclude(Include.NON_NULL)
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@JsonInclude(Include.NON_NULL)
	public String getOsimage() {
		return osimage;
	}
	public void setOsimage(String osimage) {
		this.osimage = osimage;
	}
	@JsonInclude(Include.NON_NULL)
	public String getPostscripts() {
		return postscripts;
	}
	public void setPostscripts(String postscripts) {
		this.postscripts = postscripts;
	}
	@JsonInclude(Include.NON_NULL)
	public String getPostbootscripts() {
		return postbootscripts;
	}
	public void setPostbootscripts(String postbootscripts) {
		this.postbootscripts = postbootscripts;
	}
	@JsonInclude(Include.NON_NULL)
	public List<Integer> getBootScriptIds() {
		return bootScriptIds;
	}
	public void setBootScriptIds(List<Integer> bootScriptIds) {
		this.bootScriptIds = bootScriptIds;
	}
}
