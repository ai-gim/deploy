package com.asiainfo.gim.deploy.dao;

import java.util.List;

import com.asiainfo.gim.deploy.domain.PostScripts;

public interface PostScriptsDao {
	
	public List<PostScripts> listPostScripts();
	
	public PostScripts findPostScriptsById(Integer id);
	
	public void createPostScripts(PostScripts postScripts);
	
	public void deletePostScriptsById(Integer id);
	
	public void updatePostScripts(PostScripts postScripts);

}
