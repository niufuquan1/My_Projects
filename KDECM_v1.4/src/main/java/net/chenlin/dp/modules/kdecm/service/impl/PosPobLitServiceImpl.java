package net.chenlin.dp.modules.kdecm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.kdecm.entity.PostilEntity;
import net.chenlin.dp.modules.kdecm.entity.Node;
import net.chenlin.dp.modules.kdecm.entity.PosPobLitEntity;
import net.chenlin.dp.modules.kdecm.manager.PosPobLitManager;
import net.chenlin.dp.modules.kdecm.manager.PostilManager;
import net.chenlin.dp.modules.kdecm.service.PosPobLitService;
import net.chenlin.dp.modules.kdecm.service.PostilService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午4:54:13
 */
@Service("posPobLitService")
public class PosPobLitServiceImpl implements PosPobLitService {

	@Autowired
	private PosPobLitManager posPobLitManager;

	@Override
	public Page<PosPobLitEntity> listPosPobLit(Map<String, Object> params) {
		Query query = new Query(params);
		Page<PosPobLitEntity> page = new Page<>(query);
		posPobLitManager.listPosPobLit(page, query);
		return page;
	}
	
	@Override
	public Page<PosPobLitEntity> listPosPobLitByText(Map<String, Object> params) {
		Query query = new Query(params);
		Page<PosPobLitEntity> page = new Page<>(query);
		posPobLitManager.listPosPobLitByText(page, query);
		return page;
	}
	
	@Override
	public Page<PosPobLitEntity> listPosPobLitByRelationName(Map<String, Object> params) {
		Query query = new Query(params);
		Page<PosPobLitEntity> page = new Page<>(query);
		posPobLitManager.listPosPobLitByText(page, query);
		return page;
	}

	@Override
	public PosPobLitEntity posPobLitById(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query query = new Query(params);
		return posPobLitManager.posPobLitById(query);
	}
	
	@Override
	public Page<PosPobLitEntity> listPostilSearch(Map<String, Object> params) {
		Query query = new Query(params);
		Page<PosPobLitEntity> page = new Page<>(query);
		posPobLitManager.listPostilSearch(page, query);
		return page;
	}
	
	@Override
	public List<PosPobLitEntity> listPostilSearch1(int literatureId) {
		List<PosPobLitEntity> cssList = new ArrayList<>();
		cssList = posPobLitManager.listPostilSearch1(literatureId);
		return cssList;		
	}
	
	@Override
	public List<PosPobLitEntity> listCssSearch(int literatureId) {
		List<PosPobLitEntity> cssList = new ArrayList<>();
		cssList = posPobLitManager.listCssSearch(literatureId);
		return cssList;
	}
	
	@Override
	public List<PosPobLitEntity> listPostilSearch2(String postilContent) {
		List<PosPobLitEntity> cssList = new ArrayList<>();
		cssList = posPobLitManager.listPostilSearch2(postilContent);
		return cssList;
	}
	
	@Override
	public Page<PosPobLitEntity> listForPageuserown(Map<String, Object> params) {
		Query query = new Query(params);
		Page<PosPobLitEntity> page = new Page<>(query);
		posPobLitManager.listForPageuserown(page, query);
	    return page;
	}
	
	@Override
	public Node getPostilLevelByLiteratureId(Integer literatureId) {
		return posPobLitManager.getPostilLevelByLiteratureId(literatureId);
	}
}
