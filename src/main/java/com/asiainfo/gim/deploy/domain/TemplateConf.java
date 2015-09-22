package com.asiainfo.gim.deploy.domain;

import java.util.List;

public class TemplateConf {

	private TemplateInfo tempInfo;

	private List<TemplateBasicConf> tempBaicList;

	private List<TemplatePartConf> tempPartList;

	private List<TemplateVolGroupConf> tempVGList;

	private List<TemplateLogVolConf> tempLVList;

	private List<TemplateUserConf> tempUserList;

	public TemplateInfo getTempInfo() {
		return tempInfo;
	}

	public void setTempInfo(TemplateInfo tempInfo) {
		this.tempInfo = tempInfo;
	}

	public List<TemplateBasicConf> getTempBaicList() {
		return tempBaicList;
	}

	public void setTempBaicList(List<TemplateBasicConf> tempBaicList) {
		this.tempBaicList = tempBaicList;
	}

	public List<TemplatePartConf> getTempPartList() {
		return tempPartList;
	}

	public void setTempPartList(List<TemplatePartConf> tempPartList) {
		this.tempPartList = tempPartList;
	}

	public List<TemplateVolGroupConf> getTempVGList() {
		return tempVGList;
	}

	public void setTempVGList(List<TemplateVolGroupConf> tempVGList) {
		this.tempVGList = tempVGList;
	}

	public List<TemplateLogVolConf> getTempLVList() {
		return tempLVList;
	}

	public void setTempLVList(List<TemplateLogVolConf> tempLVList) {
		this.tempLVList = tempLVList;
	}

	public List<TemplateUserConf> getTempUserList() {
		return tempUserList;
	}

	public void setTempUserList(List<TemplateUserConf> tempUserList) {
		this.tempUserList = tempUserList;
	}

	public String toKickStartStr() {
		String br = "\n";
		StringBuffer sb = new StringBuffer();
		sb.append("keyboard \"us\"" + br);
		sb.append("zerombr" + br);
		sb.append("clearpart --all --initlabel" + br);
		sb.append("%include /tmp/partitioning" + br);
		sb.append("bootloader" + br);
		sb.append("install" + br);
		sb.append("skipx" + br);
		sb.append("auth --useshadow --enablemd5" + br);
		sb.append("reboot" + br);
		sb.append("firewall --disabled" + br);
		sb.append("selinux --disabled" + br);
		if (tempBaicList != null) {
			for (TemplateBasicConf basicConf : tempBaicList) {
				sb.append(basicConf.toKickStartStr());
			}
		}
		if (tempUserList != null) {
			for (TemplateUserConf user : tempUserList) {
				sb.append(user.toKickStartStr());
			}
		}
		if (tempPartList != null) {
			for (TemplatePartConf p : tempPartList) {
				sb.append(p.toKickStartStr());
			}
		}
		if (tempVGList != null) {
			for (TemplateVolGroupConf vg : tempVGList) {
				sb.append(vg.toKickStartStr());
			}
		}
		if (tempLVList != null) {
			for (TemplateLogVolConf lv : tempLVList) {
				sb.append(lv.toKickStartStr());
			}
		}
		sb.append("%packages" + br);
		sb.append("#INCLUDE_DEFAULT_PKGLIST#" + br);
		sb.append("%pre" + br);
		sb.append("#INCLUDE:#ENV:XCATROOT#/share/xcat/install/scripts/pre.rh#" + br);
		sb.append("%post" + br);
		sb.append("#INCLUDE:#ENV:XCATROOT#/share/xcat/install/scripts/post.rh#" + br);
		return sb.toString();
	}

}
