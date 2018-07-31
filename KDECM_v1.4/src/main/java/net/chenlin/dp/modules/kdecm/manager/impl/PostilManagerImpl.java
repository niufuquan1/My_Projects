package net.chenlin.dp.modules.kdecm.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.dao.PostilMapper;
import net.chenlin.dp.modules.kdecm.entity.PosPobLitEntity;
import net.chenlin.dp.modules.kdecm.entity.PostilEntity;
import net.chenlin.dp.modules.kdecm.manager.PostilManager;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月25日 下午3:59:52
 */
@Component("postilManager")
public class PostilManagerImpl implements PostilManager {

	@Autowired
	private PostilMapper postilMapper;
	

	@Override
	public List<PostilEntity> listPostil(Page<PostilEntity> page, Query search) {
		return postilMapper.listForPage(page, search);
	}

	@Override
	public List<PostilEntity> listPostilForGroup(Page<PostilEntity> page, Query search) {
		return postilMapper.listForGroup(page, search);
	}
	
	@Override
	public int savePostil(PostilEntity postil) {
		return postilMapper.save(postil);
	}

	@Override
	public PostilEntity getPostilById(Long id) {
		PostilEntity postil = postilMapper.getObjectById(id);
		return postil;
	}

	@Override
	public int updatePostil(PostilEntity postil) {
		return postilMapper.update(postil);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = postilMapper.batchRemove(id);
		return count;
	}

	@Override
	public int insertPostil(PostilEntity postil) {
		// TODO Auto-generated method stub
		int postilId = postilMapper.insert(postil);
		return postilId;
	}

	@Override
	public int remove(Long postilId) {
		// TODO Auto-generated method stub
		int count = postilMapper.remove(postilId);
		return count;
	}

	@Override
	public List<PosPobLitEntity> listRecommend(String selectedText) {
		// TODO Auto-generated method stub
		return postilMapper.listRecommend(selectedText);
	}
	
	@Override
	public List<PostilEntity> loanPostil(int groupId, int literatureId) {
		// TODO Auto-generated method stub
		List<PostilEntity> postilList = new ArrayList<>();
		postilList = postilMapper.loanPostil(groupId, literatureId);
		return postilList;
	}

	@Override
	public int updatePostil2(PostilEntity postil) {
		// TODO Auto-generated method stub
		return postilMapper.update2(postil);
	}

	@Override
	public int removeByForId(String postilForid) {
		// TODO Auto-generated method stub
		int count = postilMapper.removeByForId(postilForid);
		return count;
	}
	
	public String getPostilContentById_(int postilId) {
		String content = postilMapper.getPostilContentById_(postilId);
		return content;
	}
}
