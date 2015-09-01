package com.asiainfo.gim.deploy.api.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.gim.deploy.dao.TemplateBasicConfDao;
import com.asiainfo.gim.deploy.dao.TemplateInfoDao;
import com.asiainfo.gim.deploy.dao.TemplateLogVolConfDao;
import com.asiainfo.gim.deploy.dao.TemplatePartConfDao;
import com.asiainfo.gim.deploy.dao.TemplateUserConfDao;
import com.asiainfo.gim.deploy.dao.TemplateVolGroupConfDao;
import com.asiainfo.gim.deploy.domain.KickStartConf;
import com.asiainfo.gim.deploy.domain.TemplateBasicConf;
import com.asiainfo.gim.deploy.domain.TemplateInfo;
import com.asiainfo.gim.deploy.domain.TemplateLogVolConf;
import com.asiainfo.gim.deploy.domain.TemplatePartConf;
import com.asiainfo.gim.deploy.domain.TemplateUserConf;
import com.asiainfo.gim.deploy.domain.TemplateVolGroupConf;

@Service
@Transactional
public class TemplateService {

	private TemplateBasicConfDao basicConfDao;

	private TemplateInfoDao templateInfoDao;

	private TemplateLogVolConfDao logVolConfDao;

	private TemplatePartConfDao partConfDao;

	private TemplateUserConfDao userConfDao;

	private TemplateVolGroupConfDao volGroupConfDao;

	public KickStartConf createTemplate(KickStartConf kickStartConf) {
		TemplateInfo templateInfo = kickStartConf.getTempInfo();
		String templateId = UUID.randomUUID().toString();
		templateInfo.setTemplateId(templateId);
		templateInfo.setCreateTime(new Date());
		templateInfoDao.createTemplateInfo(templateInfo);
		createTempConf(kickStartConf);

		return kickStartConf;
	}
	
	public void deleteTemplate(String templateId){
		templateInfoDao.deleteTemplateInfo(templateId);
		deleteTempConf(templateId);
	}
	
	public KickStartConf queryTemplate(String templateId){
		TemplateInfo info = templateInfoDao.findTempateInfoByTemplateId(templateId);
		List<TemplateBasicConf> basicConfs = basicConfDao.listTempBasicConfByTemplateId(templateId);
		List<TemplatePartConf> partConfs = partConfDao.listTempPartByTemplateId(templateId);
		List<TemplateVolGroupConf> groupConfs = volGroupConfDao.listTempVolGroupByTemplateId(templateId);
		List<TemplateLogVolConf> logVolConfs = logVolConfDao.listTempLogVolByTemplateId(templateId);
		List<TemplateUserConf> userConfs = userConfDao.listTempUserByTemplateId(templateId);
		KickStartConf kickStartConf = new KickStartConf();
		kickStartConf.setTempInfo(info);
		kickStartConf.setTempBaicList(basicConfs);
		kickStartConf.setTempLVList(logVolConfs);
		kickStartConf.setTempPartList(partConfs);
		kickStartConf.setTempUserList(userConfs);
		kickStartConf.setTempVGList(groupConfs);
		return kickStartConf;
	}
	
	public KickStartConf updateTemplate(KickStartConf kickStartConf){
		String templateId = kickStartConf.getTempInfo().getTemplateId();
		templateInfoDao.updateTemplateInfo(kickStartConf.getTempInfo());
		deleteTempConf(templateId);
		createTempConf(kickStartConf);
		return kickStartConf;
	}
	
	public TemplateInfo getTemplateInfo(String templateId){
		return templateInfoDao.findTempateInfoByTemplateId(templateId);
	}
	
	public List<TemplateInfo> listTemplateInfo(){
		return templateInfoDao.listTemplateInfo();
	}
	
	private void deleteTempConf(String templateId){
		basicConfDao.deleteTempBasicConfByTemplateId(templateId);
		logVolConfDao.deleteTempLogVolConfByTemplateId(templateId);
		partConfDao.deleteTempPartConfByTemplateId(templateId);
		userConfDao.deleteTempUserConfByTemplateId(templateId);
		volGroupConfDao.deleteTempVolGroupConfByTemplateId(templateId);
	}
	
	private void createTempConf(KickStartConf kickStartConf){
		String templateId = kickStartConf.getTempInfo().getTemplateId();
		List<TemplateBasicConf> tempBaicList = kickStartConf.getTempBaicList();
		for (TemplateBasicConf basicConf : tempBaicList) {
			basicConf.setTemplateId(templateId);
			basicConfDao.createTempBasicConf(basicConf);
		}
		List<TemplatePartConf> tempPartList = kickStartConf.getTempPartList();
		for (TemplatePartConf partConf : tempPartList) {
			partConf.setTemplateId(templateId);
			partConfDao.createTempPartConf(partConf);
		}
		List<TemplateVolGroupConf> tempVGList = kickStartConf.getTempVGList();
		for (TemplateVolGroupConf vgConf : tempVGList) {
			vgConf.setTemplateId(templateId);
			volGroupConfDao.createTempVolGroupConf(vgConf);
		}
		List<TemplateLogVolConf> tempLVList = kickStartConf.getTempLVList();
		for (TemplateLogVolConf lvConf : tempLVList) {
			lvConf.setTemplateId(templateId);
			logVolConfDao.createTempLogVolConf(lvConf);
		}
		List<TemplateUserConf> tempUserList = kickStartConf.getTempUserList();
		for (TemplateUserConf userConf : tempUserList) {
			userConf.setTemplateId(templateId);
			userConfDao.createTempUserConf(userConf);
		}
	}

	@Resource
	public void setBasicConfDao(TemplateBasicConfDao basicConfDao) {
		this.basicConfDao = basicConfDao;
	}

	@Resource
	public void setTemplateInfoDao(TemplateInfoDao templateInfoDao) {
		this.templateInfoDao = templateInfoDao;
	}

	@Resource
	public void setLogVolConfDao(TemplateLogVolConfDao logVolConfDao) {
		this.logVolConfDao = logVolConfDao;
	}

	@Resource
	public void setPartConfDao(TemplatePartConfDao partConfDao) {
		this.partConfDao = partConfDao;
	}

	@Resource
	public void setUserConfDao(TemplateUserConfDao userConfDao) {
		this.userConfDao = userConfDao;
	}

	@Resource
	public void setVolGroupConfDao(TemplateVolGroupConfDao volGroupConfDao) {
		this.volGroupConfDao = volGroupConfDao;
	}

}
