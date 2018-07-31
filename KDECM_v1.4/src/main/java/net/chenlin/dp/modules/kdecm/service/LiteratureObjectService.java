package net.chenlin.dp.modules.kdecm.service;

import java.util.List;
import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.kdecm.entity.LiteratureObjectEntity;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月31日 下午4:41:04
 */
public interface LiteratureObjectService {

	Page<LiteratureObjectEntity> listLiteratureObject(Map<String, Object> params);
	
	List<LiteratureObjectEntity> literatureShow(long userId, int literatureId);
	
	List<LiteratureObjectEntity> literature_search_pub(String literatureObjectText);
	
	R saveLiteratureObject(LiteratureObjectEntity literatureObject);
	
	R getLiteratureObjectById(Long id);
	
	R updateLiteratureObject(LiteratureObjectEntity literatureObject);
	
	R batchRemove(Long[] id);
	
	void AnalysisWord(String literaturePath,int literatureId,String LiteratureName);
	
}
