package net.chenlin.dp.modules.blog.manager;

import java.util.List;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.blog.entity.TBlogEntity;

/**
 * 博客
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月15日 PM2:11:50
 */
public interface TBlogManager {

	List<TBlogEntity> listTBlog(Page<TBlogEntity> page, Query search);
	
	int saveTBlog(TBlogEntity tBlog);
	
	TBlogEntity getTBlogById(Long id);
	
	int updateTBlog(TBlogEntity tBlog);
	
	int batchRemove(Long[] id);
	
}
