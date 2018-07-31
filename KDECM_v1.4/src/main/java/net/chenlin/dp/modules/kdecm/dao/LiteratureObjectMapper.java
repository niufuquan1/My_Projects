package net.chenlin.dp.modules.kdecm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.LiteratureObjectEntity;
import net.chenlin.dp.modules.sys.dao.BaseMapper;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月31日 下午4:41:04
 */
@Mapper
public interface LiteratureObjectMapper extends BaseMapper<LiteratureObjectEntity> {
	List<LiteratureObjectEntity> listForPage2(@Param("userId")long userId, @Param("literatureId")int literatureId);
	
	List<LiteratureObjectEntity> listForPage3(@Param("literatureObjectText")String literatureObjectText);
	
	//该函数应该会经常调用，用来存储文献对象,文献对象的id设置为递增，
		Integer SaveAnalysisResult(LiteratureObjectEntity literatureObject);
}
