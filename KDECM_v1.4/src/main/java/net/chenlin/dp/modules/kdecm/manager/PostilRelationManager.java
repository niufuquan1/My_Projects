package net.chenlin.dp.modules.kdecm.manager;

import java.util.List;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.PosPobLitEntity;
import net.chenlin.dp.modules.kdecm.entity.PostilRelationEntity;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月25日 下午5:22:02
 */
public interface PostilRelationManager {

	List<PostilRelationEntity> listPostilRelation(Page<PostilRelationEntity> page, Query search);
	
	List<PostilRelationEntity> listDistinctPostilRelation(Page<PostilRelationEntity> page, Query search);
	
	int savePostilRelation(PostilRelationEntity postilRelation);
	
	PostilRelationEntity getPostilRelationById(Long id);
	
	PostilRelationEntity getPostilRelationByNameAndPostilId(Query search);
	
	int updatePostilRelation(PostilRelationEntity postilRelation);
	
	int batchRemove(Long[] id);
	
	int removeByRelNameAndPostilId(Query search);
}
