package net.chenlin.dp.modules.kdecm.service;

import java.util.List;
import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.kdecm.entity.LiteratureEntity;
import net.chenlin.dp.modules.kdecm.entity.LiteratureObjectEntity;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午8:11:20
 */
public interface LiteratureService {

	Page<LiteratureEntity> listLiterature(Map<String, Object> params);
	
	Page<LiteratureEntity> literatureShow(Map<String, Object> params);
	
	List<LiteratureEntity> getName();
	
	Integer saveLiterature(LiteratureEntity literature);
	
	R getLiteratureById(Long id);
	
	R updateLiterature(LiteratureEntity literature);
	
	R batchRemove(Long[] id);
	
	Integer MaxLiteratureID();

	List<LiteratureEntity> literature_search_group();

	List<String> search_mainword(int literatureId);

	List<String> compare_mainword(String str1, String str2, int literatureId);

	List<String> search_author(int literatureId);

	List<String> compare_author(String str1, String str2, int literatureId);

	void addLiteratureKeyword(Query query1);

	
}
