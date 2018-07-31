package net.chenlin.dp.modules.blog.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.blog.entity.TBlogEntity;

/**
 * 博客
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月15日 PM2:11:50
 */
public interface TBlogService {

	Page<TBlogEntity> listTBlog(Map<String, Object> params);
	
	R saveTBlog(TBlogEntity tBlog);
	
	R getTBlogById(Long id);
	
	R updateTBlog(TBlogEntity tBlog);
	
	R batchRemove(Long[] id);
	
}
