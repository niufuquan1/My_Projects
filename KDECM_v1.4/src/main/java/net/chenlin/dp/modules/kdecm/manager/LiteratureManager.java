package net.chenlin.dp.modules.kdecm.manager;

import java.util.List;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.LiteratureEntity;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午8:11:20
 */
public interface LiteratureManager {

	List<LiteratureEntity> listLiterature(Page<LiteratureEntity> page, Query search);
	
	List<LiteratureEntity> literatureShow(Page<LiteratureEntity> page, Query search);
	
	List<LiteratureEntity> getName();
	
	int saveLiterature(LiteratureEntity literature);
	
	LiteratureEntity getLiteratureById(Long id);
	
	int updateLiterature(LiteratureEntity literature);
	
	int batchRemove(Long[] id);
	
	int MaxLiteratureID();

	List<LiteratureEntity> listGroupLiterature();

	List<String> listMainWord(int literatureId);

	List<String> compareMainWord(String str1, String str2, int literatureId);

	List<String> listAuthor(int literatureId);

	List<String> compareAuthor(String str1, String str2, int literatureId);

	void addLiteratureKeyword(Query query1);
}
