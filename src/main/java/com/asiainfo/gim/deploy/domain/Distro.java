package com.asiainfo.gim.deploy.domain;

public class Distro {
	private String osdistroname;
	private String basename;
	private String majorversion;
	private String minorversion;
	private String arch;
	private String type;
	private String dirpaths;
	public String getOsdistroname() {
		return osdistroname;
	}
	public void setOsdistroname(String osdistroname) {
		this.osdistroname = osdistroname;
	}
	public String getBasename() {
		return basename;
	}
	public void setBasename(String basename) {
		this.basename = basename;
	}
	public String getMajorversion() {
		return majorversion;
	}
	public void setMajorversion(String majorversion) {
		this.majorversion = majorversion;
	}
	public String getMinorversion() {
		return minorversion;
	}
	public void setMinorversion(String minorversion) {
		this.minorversion = minorversion;
	}
	public String getArch() {
		return arch;
	}
	public void setArch(String arch) {
		this.arch = arch;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDirpaths() {
		return dirpaths;
	}
	public void setDirpaths(String dirpaths) {
		this.dirpaths = dirpaths;
	}

}
