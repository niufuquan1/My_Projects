package net.chenlin.dp.modules.kdecm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.kdecm.entity.PosPobLitEntity;
import net.chenlin.dp.modules.kdecm.entity.PostilEntity;
import net.chenlin.dp.modules.kdecm.manager.PostilManager;
import net.chenlin.dp.modules.kdecm.service.PostilService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月25日 下午3:59:52
 */
@Service("postilService")
public class PostilServiceImpl implements PostilService {

	@Autowired
	private PostilManager postilManager;

	@Override
	public Page<PostilEntity> listPostil(Map<String, Object> params) {
		Query query = new Query(params);
		Page<PostilEntity> page = new Page<>(query);
		postilManager.listPostil(page, query);
		return page;
	}

	@Override
	public Page<PostilEntity> listPostilForGroup(Map<String, Object> params) {
		Query query = new Query(params);
		Page<PostilEntity> page = new Page<>(query);
		postilManager.listPostilForGroup(page, query);
		return page;
	}
	
	@Override
	public R savePostil(PostilEntity role) {
		int count = postilManager.savePostil(role);
		return CommonUtils.msg(count);
	}

	@Override
	public R getPostilById(Long id) {
		PostilEntity postil = postilManager.getPostilById(id);
		return CommonUtils.msg(postil);
	}

	@Override
	public R updatePostil(PostilEntity postil) {
		int count = postilManager.updatePostil(postil);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemove(Long[] id) {
		int count = postilManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public R insertPostil(PostilEntity postil) {
		// TODO Auto-generated method stub
		System.out.println("欢迎进入到自己写的方法的ServiceImpl层");
		int count = postilManager.insertPostil(postil);
		return CommonUtils.msg(count);
	}

	@Override
	public R remove(Long postilId) {
		// TODO Auto-generated method stub
		int count = postilManager.remove(postilId);
		return CommonUtils.msg(count);
	}

	@Override
	public List<PosPobLitEntity> listRecommend(String selectedText) {
		// TODO Auto-generated method stub
		List<PosPobLitEntity> recommend = postilManager.listRecommend(selectedText);
		return recommend;
	}
	
	@Override
	public List<PostilEntity> loanPostil(int groupId, int literatureId) {
		// TODO Auto-generated method stub
		List<PostilEntity> postilList = new ArrayList<>();
		postilList = postilManager.loanPostil(groupId, literatureId);
		return postilList;
	}

	@Override
	public R updatePostil2(PostilEntity postil) {
		// TODO Auto-generated method stub
		int count = postilManager.updatePostil2(postil);
		return CommonUtils.msg(count);
	}

	@Override
	public R removeByForId(String postilForid) {
		// TODO Auto-generated method stub
		int count = postilManager.removeByForId(postilForid);
		return CommonUtils.msg(count);
	}
	
	public String getPostilContentById_(int postilId) {
		String content = postilManager.getPostilContentById_(postilId);
		return content;
	}
}
