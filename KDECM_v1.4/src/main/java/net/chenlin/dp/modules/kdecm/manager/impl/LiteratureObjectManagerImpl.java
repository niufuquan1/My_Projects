package net.chenlin.dp.modules.kdecm.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.dao.LiteratureObjectMapper;
import net.chenlin.dp.modules.kdecm.entity.LiteratureObjectEntity;
import net.chenlin.dp.modules.kdecm.manager.LiteratureObjectManager;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月31日 下午4:41:04
 */
@Component("literatureObjectManager")
public class LiteratureObjectManagerImpl implements LiteratureObjectManager {

	@Autowired
	private LiteratureObjectMapper literatureObjectMapper;
	

	@Override
	public List<LiteratureObjectEntity> listLiteratureObject(Page<LiteratureObjectEntity> page, Query search) {
		return literatureObjectMapper.listForPage(page, search);
	}

	@Override
	public List<LiteratureObjectEntity> literatureShow(long userId, int literatureId) {
		return literatureObjectMapper.listForPage2(userId, literatureId);
	}
	
	@Override
	public List<LiteratureObjectEntity> literature_search_pub(String literatureObjectText) {
		return literatureObjectMapper.listForPage3(literatureObjectText);
	}
	
	@Override
	public int saveLiteratureObject(LiteratureObjectEntity literatureObject) {
		return literatureObjectMapper.save(literatureObject);
	}

	@Override
	public LiteratureObjectEntity getLiteratureObjectById(Long id) {
		LiteratureObjectEntity literatureObject = literatureObjectMapper.getObjectById(id);
		return literatureObject;
	}

	@Override
	public int updateLiteratureObject(LiteratureObjectEntity literatureObject) {
		return literatureObjectMapper.update(literatureObject);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = literatureObjectMapper.batchRemove(id);
		return count;
	}
	
	public void SaveAnalysisResult(LiteratureObjectEntity literatureObject) {
		literatureObjectMapper.SaveAnalysisResult(literatureObject);
	}
}
