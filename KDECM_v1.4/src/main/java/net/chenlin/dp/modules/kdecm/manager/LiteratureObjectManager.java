package net.chenlin.dp.modules.kdecm.manager;

import java.util.List;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.LiteratureObjectEntity;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月31日 下午4:41:04
 */
public interface LiteratureObjectManager {

	List<LiteratureObjectEntity> listLiteratureObject(Page<LiteratureObjectEntity> page, Query search);
	
	List<LiteratureObjectEntity> literatureShow(long userId, int literatureId);
	
	List<LiteratureObjectEntity> literature_search_pub(String literatureObjectText);
	
	int saveLiteratureObject(LiteratureObjectEntity literatureObject);
	
	LiteratureObjectEntity getLiteratureObjectById(Long id);
	
	int updateLiteratureObject(LiteratureObjectEntity literatureObject);
	
	int batchRemove(Long[] id);
	
	void SaveAnalysisResult(LiteratureObjectEntity literatureObject);
	
}
