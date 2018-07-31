package net.chenlin.dp.modules.kdecm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.PosPobLitEntity;
import net.chenlin.dp.modules.kdecm.entity.PostilEntity;
import net.chenlin.dp.modules.sys.dao.BaseMapper;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月25日 下午3:59:52
 */
@Mapper
public interface PostilMapper extends BaseMapper<PostilEntity> {
	
	//2018/5/31 15:45 肖凌云   返回插入Postil的Id的insert函数
	int insert(PostilEntity postil); 
	
	List<PostilEntity> listForGroup(Page<PostilEntity> page, Query query);

	List<PosPobLitEntity> listRecommend(String selectedText);
	
	//2018-6-8 15:15 肖凌云  返回要加载的Postil列表
	List<PostilEntity> loanPostil(@Param("groupId")int groupId, @Param("literatureId")int literatureId);
	
	//2018-6-23 update2
	int update2(PostilEntity postil);
	
	//2018-2-23 removeByForId
	int removeByForId(@Param("postilForid")String postilForid);
	
	String getPostilContentById_(int postilId);
}
