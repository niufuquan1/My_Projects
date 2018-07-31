package net.chenlin.dp.modules.blog.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.blog.dao.TBlogMapper;
import net.chenlin.dp.modules.blog.entity.TBlogEntity;
import net.chenlin.dp.modules.blog.manager.TBlogManager;

/**
 * 博客
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月15日 PM2:11:50
 */
@Component("tBlogManager")
public class TBlogManagerImpl implements TBlogManager {

	@Autowired
	private TBlogMapper tBlogMapper;
	

	@Override
	public List<TBlogEntity> listTBlog(Page<TBlogEntity> page, Query search) {
		return tBlogMapper.listForPage(page, search);
	}

	@Override
	public int saveTBlog(TBlogEntity tBlog) {
		return tBlogMapper.save(tBlog);
	}

	@Override
	public TBlogEntity getTBlogById(Long id) {
		TBlogEntity tBlog = tBlogMapper.getObjectById(id);
		return tBlog;
	}

	@Override
	public int updateTBlog(TBlogEntity tBlog) {
		return tBlogMapper.update(tBlog);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = tBlogMapper.batchRemove(id);
		return count;
	}
	
}
