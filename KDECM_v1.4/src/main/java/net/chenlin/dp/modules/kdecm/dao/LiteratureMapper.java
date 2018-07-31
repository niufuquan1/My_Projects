package net.chenlin.dp.modules.kdecm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.LiteratureEntity;
import net.chenlin.dp.modules.sys.dao.BaseMapper;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午8:11:20
 */
@Mapper
public interface LiteratureMapper extends BaseMapper<LiteratureEntity> {
	List<LiteratureEntity> listForPage2(Page<LiteratureEntity> page, Query search);
	Integer MaxLiteratureID();
	List<LiteratureEntity> strGetName();
	List<LiteratureEntity> listForGroup();
	List<String> listForMainWord(int literatureId);
	List<String> compareForMainWord(String str1, String str2, int literatureId);
	List<String> listForAuthor(int literatureId);
	List<String> compareForAuthor(String str1, String str2, int literatureId);
	void addLiteratureKeyword(Query query1);
	//LiteratureEntity addOtherMsg(String String age,String keyword,String author,String reference);
}
