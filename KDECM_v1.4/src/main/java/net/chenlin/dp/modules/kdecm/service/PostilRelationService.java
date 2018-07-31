package net.chenlin.dp.modules.kdecm.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.kdecm.entity.PostilRelationEntity;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月25日 下午5:22:02
 */
public interface PostilRelationService {

	Page<PostilRelationEntity> listPostilRelation(Map<String, Object> params);
	
	Page<PostilRelationEntity> listDistinctPostilRelation(Map<String, Object> params);
	
	PostilRelationEntity getPostilRelationByNameAndPostilId(Map<String, Object> params);
	
	R savePostilRelation(PostilRelationEntity postilRelation);
	
	R getPostilRelationById(Long id);
	
	R updatePostilRelation(PostilRelationEntity postilRelation);
	
	R batchRemove(Long[] id);
	
	R removeByRelNameAndPostilId(Map<String, Object> params);
}
