package net.chenlin.dp.modules.kdecm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.PostilRelationEntity;
import net.chenlin.dp.modules.sys.dao.BaseMapper;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月25日 下午5:22:02
 */
@Mapper
public interface PostilRelationMapper extends BaseMapper<PostilRelationEntity> {
	List<PostilRelationEntity> listForPageDistinctRelation(Page<PostilRelationEntity> page, Query query);
	PostilRelationEntity getObjectByNameAndPostilId(Query query);
	int removeByRelNameAndPostilId(Query search);
}
