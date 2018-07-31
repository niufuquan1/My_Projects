package net.chenlin.dp.modules.kdecm.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.dao.PostilRelationMapper;
import net.chenlin.dp.modules.kdecm.entity.PostilRelationEntity;
import net.chenlin.dp.modules.kdecm.manager.PostilRelationManager;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月25日 下午5:22:02
 */
@Component("postilRelationManager")
public class PostilRelationManagerImpl implements PostilRelationManager {

	@Autowired
	private PostilRelationMapper postilRelationMapper;
	

	@Override
	public List<PostilRelationEntity> listPostilRelation(Page<PostilRelationEntity> page, Query search) {
		return postilRelationMapper.listForPage(page, search);
	}
	
	@Override
	public List<PostilRelationEntity> listDistinctPostilRelation(Page<PostilRelationEntity> page, Query search) {
		return postilRelationMapper.listForPageDistinctRelation(page, search);
	}

	@Override
	public int savePostilRelation(PostilRelationEntity postilRelation) {
		return postilRelationMapper.save(postilRelation);
	}

	@Override
	public PostilRelationEntity getPostilRelationById(Long id) {
		PostilRelationEntity postilRelation = postilRelationMapper.getObjectById(id);
		return postilRelation;
	}

	@Override
	public int updatePostilRelation(PostilRelationEntity postilRelation) {
		return postilRelationMapper.update(postilRelation);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = postilRelationMapper.batchRemove(id);
		return count;
	}

	@Override
	public PostilRelationEntity getPostilRelationByNameAndPostilId(Query search) {
		return postilRelationMapper.getObjectByNameAndPostilId(search);
	}

	@Override
	public int removeByRelNameAndPostilId(Query search) {
		int count = postilRelationMapper.removeByRelNameAndPostilId(search);
		return count;
	}

	
	
}
