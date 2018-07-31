package net.chenlin.dp.modules.blog.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.blog.entity.TBlogEntity;
import net.chenlin.dp.modules.blog.manager.TBlogManager;
import net.chenlin.dp.modules.blog.service.TBlogService;

/**
 * 博客
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月15日 PM2:11:50
 */
@Service("tBlogService")
public class TBlogServiceImpl implements TBlogService {

	@Autowired
	private TBlogManager tBlogManager;

	@Override
	public Page<TBlogEntity> listTBlog(Map<String, Object> params) {
		Query query = new Query(params);
		Page<TBlogEntity> page = new Page<>(query);
		tBlogManager.listTBlog(page, query);
		return page;
	}

	@Override
	public R saveTBlog(TBlogEntity role) {
		int count = tBlogManager.saveTBlog(role);
		return CommonUtils.msg(count);
	}

	@Override
	public R getTBlogById(Long id) {
		TBlogEntity tBlog = tBlogManager.getTBlogById(id);
		return CommonUtils.msg(tBlog);
	}

	@Override
	public R updateTBlog(TBlogEntity tBlog) {
		int count = tBlogManager.updateTBlog(tBlog);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemove(Long[] id) {
		int count = tBlogManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
