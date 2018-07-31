package net.chenlin.dp.modules.blog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.blog.entity.TBlogEntity;
import net.chenlin.dp.modules.blog.service.TBlogService;

/**
 * 博客
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月15日 PM2:11:50
 */
@RestController
@RequestMapping("/sys/blog")
public class TBlogController extends AbstractController {
	
	@Autowired
	private TBlogService tBlogService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<TBlogEntity> list(@RequestBody Map<String, Object> params) {
		return tBlogService.listTBlog(params);
	}
		
	/**
	 * 新增
	 * @param tBlog
	 * @return
	 */
	@SysLog("新增博客")
	@RequestMapping("/save")
	public R save(@RequestBody TBlogEntity tBlog) {
		return tBlogService.saveTBlog(tBlog);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return tBlogService.getTBlogById(id);
	}
	
	/**
	 * 修改
	 * @param tBlog
	 * @return
	 */
	@SysLog("修改博客")
	@RequestMapping("/update")
	public R update(@RequestBody TBlogEntity tBlog) {
		return tBlogService.updateTBlog(tBlog);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除博客")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return tBlogService.batchRemove(id);
	}
	
}
