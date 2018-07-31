package net.chenlin.dp.modules.kdecm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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
 * @date 2018年5月27日 下午4:54:13
 */
@Mapper
public interface PosPobLitMapper extends BaseMapper<PosPobLitEntity> {
	
	PosPobLitEntity posPobLitById(Query search);
	
	List<PosPobLitEntity> listPosPobLitForPage(Page<PosPobLitEntity> page, Query query);
	
	List<PosPobLitEntity> listByLiteratureId(Integer literatureId);
	
	List<PosPobLitEntity> listForPage1(@Param("literatureId")int literatureId);
	
	List<PosPobLitEntity> listForPage2(Page<PosPobLitEntity> page, Query search_postil);
	
	List<PosPobLitEntity> listForPage3(@Param("literatureId")int literatureId);
	
	List<PosPobLitEntity> listForPage4(@Param("postilContent")String postilContent);
	
	List<PosPobLitEntity> listForPageuserown(Page<PosPobLitEntity> page, Query search_postil);
}
