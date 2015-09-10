package com.asiainfo.gim.deploy.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.asiainfo.gim.deploy.dao.PostScriptsDao;
import com.asiainfo.gim.deploy.domain.PostScripts;


@Service
public class PostScriptsService {
	
	private PostScriptsDao postScriptsDao;
	
	public List<PostScripts> listPostScripts(){
		return postScriptsDao.listPostScripts();
	}
	
	public PostScripts findPostScriptsById(int id){
		return postScriptsDao.findPostScriptsById(id);
	}
	
	public void updatePostScripts(PostScripts postScripts){
		postScriptsDao.updatePostScripts(postScripts);
	}
	
	public PostScripts createPostScripts(PostScripts postScripts){
		postScriptsDao.createPostScripts(postScripts);
		return postScripts;
	}
	
	public void deletePostScripts(int id){
		postScriptsDao.deletePostScriptsById(id);
	}

	@Resource
	public void setPostScriptsDao(PostScriptsDao postScriptsDao) {
		this.postScriptsDao = postScriptsDao;
	}

}
