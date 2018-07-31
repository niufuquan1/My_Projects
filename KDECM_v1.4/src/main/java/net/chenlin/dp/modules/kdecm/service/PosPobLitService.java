package net.chenlin.dp.modules.kdecm.service;

import java.util.List;
import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
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
public interface PosPobLitService {

	Page<PosPobLitEntity> listPosPobLit(Map<String, Object> params);

	List<PosPobLitEntity> listPostilSearch1(int literatureId);
	
	PosPobLitEntity posPobLitById(Map<String, Object> params);
	
	Page<PosPobLitEntity> listPosPobLitByText(Map<String, Object> params);
	
	Page<PosPobLitEntity> listPosPobLitByRelationName(Map<String, Object> params);
	
	Page<PosPobLitEntity> listPostilSearch(Map<String, Object> params);
	
	List<PosPobLitEntity> listCssSearch(int literatureId);
	
	List<PosPobLitEntity> listPostilSearch2(String postilContent);
	
	Page<PosPobLitEntity> listForPageuserown(Map<String, Object> params);

	Node getPostilLevelByLiteratureId(Integer literatureId);
}
