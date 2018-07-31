package net.chenlin.dp.modules.blog.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.blog.entity.TBlogEntity;
import net.chenlin.dp.modules.sys.dao.BaseMapper;

/**
 * 博客
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月15日 PM2:11:50
 */
@Mapper
public interface TBlogMapper extends BaseMapper<TBlogEntity> {
	
}
