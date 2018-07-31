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
import net.chenlin.dp.modules.kdecm.entity.PostilObjectEntity;
import net.chenlin.dp.modules.kdecm.manager.PostilObjectManager;
import net.chenlin.dp.modules.kdecm.service.PostilObjectService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月02日 下午1:22:02
 */
@Service("postilObjectService")
public class PostilObjectServiceImpl implements PostilObjectService {

	@Autowired
	private PostilObjectManager postilObjectManager;

	@Override
	public Page<PostilObjectEntity> listPostilObject(Map<String, Object> params) {
		Query query = new Query(params);
		Page<PostilObjectEntity> page = new Page<>(query);
		postilObjectManager.listPostilObject(page, query);
		return page;
	}

	@Override
	public R savePostilObject(PostilObjectEntity role) {
		int count = postilObjectManager.savePostilObject(role);
		return CommonUtils.msg(count);
	}

	@Override
	public R getPostilObjectById(Long id) {
		PostilObjectEntity postilObject = postilObjectManager.getPostilObjectById(id);
		return CommonUtils.msg(postilObject);
	}

	@Override
	public R updatePostilObject(PostilObjectEntity postilObject) {
		int count = postilObjectManager.updatePostilObject(postilObject);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemove(Long[] id) {
		int count = postilObjectManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public R insertPostilObject(PostilObjectEntity postilObject) {
		// TODO Auto-generated method stub
		System.out.println("欢迎进入到自己写的方法的ServiceImpl层");
		int count = postilObjectManager.insertPostilObject(postilObject);
		return CommonUtils.msg(count);
	}

	@Override
	public List<PostilObjectEntity> loanPostilObject(int[] postilObjectIds) {
		// TODO Auto-generated method stub
		List<PostilObjectEntity> postilObjectList = new ArrayList<>();
		postilObjectList = postilObjectManager.loanPostilObject(postilObjectIds);
		return postilObjectList;
	}
	
	public String getPostilObjectContentById_(int postilId) {
		String postilObjectContent = "";
		postilObjectContent = postilObjectManager.getPostilObjectContentById_(postilId);
		return postilObjectContent;
	}
}
