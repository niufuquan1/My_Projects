package net.chenlin.dp.modules.kdecm.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.dao.PostilObjectMapper;
import net.chenlin.dp.modules.kdecm.entity.PostilObjectEntity;
import net.chenlin.dp.modules.kdecm.manager.PostilObjectManager;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月02日 下午1:22:02
 */
@Component("postilObjectManager")
public class PostilObjectManagerImpl implements PostilObjectManager {

	@Autowired
	private PostilObjectMapper postilObjectMapper;
	

	@Override
	public List<PostilObjectEntity> listPostilObject(Page<PostilObjectEntity> page, Query search) {
		return postilObjectMapper.listForPage(page, search);
	}

	@Override
	public int savePostilObject(PostilObjectEntity postilObject) {
		return postilObjectMapper.save(postilObject);
	}

	@Override
	public PostilObjectEntity getPostilObjectById(Long id) {
		PostilObjectEntity postilObject = postilObjectMapper.getObjectById(id);
		return postilObject;
	}

	@Override
	public int updatePostilObject(PostilObjectEntity postilObject) {
		return postilObjectMapper.update(postilObject);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = postilObjectMapper.batchRemove(id);
		return count;
	}

	@Override
	public int insertPostilObject(PostilObjectEntity postilObject) {
		// TODO Auto-generated method stub
		int count = postilObjectMapper.insert(postilObject);
		return count;
	}
	
	@Override
	public List<PostilObjectEntity> loanPostilObject(int[] postilObjectIds) {
		// TODO Auto-generated method stub
		List<PostilObjectEntity> postilObjectList = new ArrayList<>();
		postilObjectList = postilObjectMapper.loanPostilObject(postilObjectIds);
		return postilObjectList;
	}
	
	public String getPostilObjectContentById_(int postilId) {
		String postilObjectContent = "";
		postilObjectContent = postilObjectMapper.getPostilObjectContentById_(postilId);
		return postilObjectContent;
	}
}
