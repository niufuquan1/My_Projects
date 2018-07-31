package net.chenlin.dp.modules.kdecm.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.kdecm.entity.PostilRelationEntity;
import net.chenlin.dp.modules.kdecm.manager.PostilRelationManager;
import net.chenlin.dp.modules.kdecm.service.PostilRelationService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月25日 下午5:22:02
 */
@Service("postilRelationService")
public class PostilRelationServiceImpl implements PostilRelationService {

	@Autowired
	private PostilRelationManager postilRelationManager;

	@Override
	public Page<PostilRelationEntity> listPostilRelation(Map<String, Object> params) {
		Query query = new Query(params);
		Page<PostilRelationEntity> page = new Page<>(query);
		postilRelationManager.listPostilRelation(page, query);
		return page;
	}
	
	@Override
	public Page<PostilRelationEntity> listDistinctPostilRelation(Map<String, Object> params) {
		Query query = new Query(params);
		Page<PostilRelationEntity> page = new Page<>(query);
		postilRelationManager.listDistinctPostilRelation(page, query);
		return page;
	}

	@Override
	public R savePostilRelation(PostilRelationEntity role) {
		int count = postilRelationManager.savePostilRelation(role);
		return CommonUtils.msg(count);
	}

	@Override
	public R getPostilRelationById(Long id) {
		PostilRelationEntity postilRelation = postilRelationManager.getPostilRelationById(id);
		return CommonUtils.msg(postilRelation);
	}

	@Override
	public R updatePostilRelation(PostilRelationEntity postilRelation) {
		int count = postilRelationManager.updatePostilRelation(postilRelation);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemove(Long[] id) {
		int count = postilRelationManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public PostilRelationEntity getPostilRelationByNameAndPostilId(Map<String, Object> params) {
		Query query = new Query(params);
		return postilRelationManager.getPostilRelationByNameAndPostilId(query);
	}

	@Override
	public R removeByRelNameAndPostilId(Map<String, Object> params) {
		Query query = new Query(params);
		int count = postilRelationManager.removeByRelNameAndPostilId(query);
		return CommonUtils.msg(count);
	}

	

}
