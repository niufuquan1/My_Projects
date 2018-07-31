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
import net.chenlin.dp.modules.kdecm.entity.LiteratureEntity;
import net.chenlin.dp.modules.kdecm.entity.LiteratureObjectEntity;
import net.chenlin.dp.modules.kdecm.entity.PosPobLitEntity;
import net.chenlin.dp.modules.kdecm.manager.LiteratureManager;
import net.chenlin.dp.modules.kdecm.service.LiteratureService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午8:11:20
 */
@Service("literatureService")
public class LiteratureServiceImpl implements LiteratureService {

	@Autowired
	private LiteratureManager literatureManager;

	@Override
	public Page<LiteratureEntity> listLiterature(Map<String, Object> params) {
		Query query = new Query(params);
		Page<LiteratureEntity> page = new Page<>(query);
		literatureManager.listLiterature(page, query);
		return page;
	}
	
	@Override
	public Page<LiteratureEntity> literatureShow(Map<String, Object> params) {
		Query query = new Query(params);
		Page<LiteratureEntity> page = new Page<>(query);
		literatureManager.literatureShow(page, query);
		return page;
	}
	
	@Override
	public List<LiteratureEntity> getName() {
		return literatureManager.getName();
	}

	@Override
	public Integer saveLiterature(LiteratureEntity role) {
		int count = literatureManager.saveLiterature(role);
		return count;
	}

	@Override
	public R getLiteratureById(Long id) {
		LiteratureEntity literature = literatureManager.getLiteratureById(id);
		return CommonUtils.msg(literature);
	}

	@Override
	public R updateLiterature(LiteratureEntity literature) {
		int count = literatureManager.updateLiterature(literature);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemove(Long[] id) {
		int count = literatureManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}
	
	public Integer MaxLiteratureID() {
		Integer maxLiteratureId = literatureManager.MaxLiteratureID();
		if(maxLiteratureId!=null) {
			return maxLiteratureId;
		}
		return 0;
	}

	@Override
	public List<LiteratureEntity> literature_search_group() {
		List<LiteratureEntity> cssList = new ArrayList<>();
		cssList = literatureManager.listGroupLiterature();
		return cssList;
	}

	@Override
	public List<String> search_mainword(int literatureId) {
		List<String> cssList = new ArrayList<>();
		cssList = literatureManager.listMainWord(literatureId);
		return cssList;
	}

	@Override
	public List<String> compare_mainword(String str1, String str2,int literatureId) {
		List<String> cssList = new ArrayList<>();
		cssList = literatureManager.compareMainWord(str1,str2,literatureId);
		return cssList;
	}

	@Override
	public List<String> search_author(int literatureId) {
		List<String> cssList = new ArrayList<>();
		cssList = literatureManager.listAuthor(literatureId);
		return cssList;
	}

	@Override
	public List<String> compare_author(String str1, String str2, int literatureId) {
		List<String> cssList = new ArrayList<>();
		cssList = literatureManager.compareAuthor(str1,str2,literatureId);
		return cssList;
	}

	@Override
	public void addLiteratureKeyword(Query query1) {
		literatureManager.addLiteratureKeyword(query1);
	}

	
	

}
