package net.chenlin.dp.modules.kdecm.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.dao.LiteratureMapper;
import net.chenlin.dp.modules.kdecm.entity.LiteratureEntity;
import net.chenlin.dp.modules.kdecm.manager.LiteratureManager;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午8:11:20
 */
@Component("literatureManager")
public class LiteratureManagerImpl implements LiteratureManager {

	@Autowired
	private LiteratureMapper literatureMapper;
	

	@Override
	public List<LiteratureEntity> listLiterature(Page<LiteratureEntity> page, Query search) {
		return literatureMapper.listForPage(page, search);
	}
	
	@Override
	public List<LiteratureEntity> literatureShow(Page<LiteratureEntity> page, Query search) {
		return literatureMapper.listForPage2(page, search);
	}
	
	@Override
	public List<LiteratureEntity> getName() {
		return literatureMapper.strGetName();
	}

	@Override
	public int saveLiterature(LiteratureEntity literature) {
		return literatureMapper.save(literature);
	}

	@Override
	public LiteratureEntity getLiteratureById(Long id) {
		LiteratureEntity literature = literatureMapper.getObjectById(id);
		return literature;
	}

	@Override
	public int updateLiterature(LiteratureEntity literature) {
		return literatureMapper.update(literature);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = literatureMapper.batchRemove(id);
		return count;
	}
	public int MaxLiteratureID() {
		return literatureMapper.MaxLiteratureID();
	}

	@Override
	public List<LiteratureEntity> listGroupLiterature() {
		return literatureMapper.listForGroup();
	}

	@Override
	public List<String> listMainWord(int literatureId) {
		return literatureMapper.listForMainWord(literatureId);
	}

	@Override
	public List<String> compareMainWord(String str1, String str2, int literatureId) {
		return literatureMapper.compareForMainWord(str1,str2,literatureId);
	}

	@Override
	public List<String> listAuthor(int literatureId) {
		return literatureMapper.listForAuthor(literatureId);

	}

	@Override
	public List<String> compareAuthor(String str1, String str2, int literatureId) {
		return literatureMapper.compareForAuthor(str1,str2,literatureId);
	}

	@Override
	public void addLiteratureKeyword(Query query1) {
		literatureMapper.addLiteratureKeyword(query1);
		
	}
}
