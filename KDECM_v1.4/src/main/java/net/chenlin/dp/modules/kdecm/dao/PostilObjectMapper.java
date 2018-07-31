package net.chenlin.dp.modules.kdecm.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import net.chenlin.dp.modules.kdecm.entity.PostilEntity;
import net.chenlin.dp.modules.kdecm.entity.PostilObjectEntity;
import net.chenlin.dp.modules.sys.dao.BaseMapper;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月02日 下午1:22:02
 */
@Mapper
public interface PostilObjectMapper extends BaseMapper<PostilObjectEntity> {

	//2018/6/2 13:42 肖凌云   返回插入PostilObject的Id的insert函数
	int insert(PostilObjectEntity postilObject);
	
	//2018-6-8 15:43 肖凌云 返回需要加载的PostilObject列表
	List<PostilObjectEntity> loanPostilObject(int[] postilObjectIds);
	
	String getPostilObjectContentById_(int postilId);
}
