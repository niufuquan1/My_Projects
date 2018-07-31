package net.chenlin.dp.modules.kdecm.manager;

import java.util.List;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.PostilEntity;
import net.chenlin.dp.modules.kdecm.entity.Node;
import net.chenlin.dp.modules.kdecm.entity.PosPobLitEntity;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午4:54:13
 */
public interface PosPobLitManager {

	List<PosPobLitEntity> listPosPobLit(Page<PosPobLitEntity> page, Query search);
	
	List<PosPobLitEntity> listPosPobLitByText(Page<PosPobLitEntity> page, Query search);
	
	List<PosPobLitEntity> listPosPobLitByRelationName(Page<PosPobLitEntity> page, Query search);
	
	PosPobLitEntity posPobLitById(Query search);
	
	List<PosPobLitEntity> listPostilSearch(Page<PosPobLitEntity> page, Query search_postil);
	
	List<PosPobLitEntity> listPostilSearch1(int literatureId);
	
	List<PosPobLitEntity> listCssSearch(int literatureId);
	
	List<PosPobLitEntity> listPostilSearch2(String postilContent);
	
	List<PosPobLitEntity> listForPageuserown(Page<PosPobLitEntity> page, Query search_postil);
	
	Node getPostilLevelByLiteratureId(Integer literatureId);
}
