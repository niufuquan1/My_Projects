package net.chenlin.dp.modules.kdecm.service;

import java.util.List;
import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.kdecm.entity.PosPobLitEntity;
import net.chenlin.dp.modules.kdecm.entity.PostilEntity;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月25日 下午3:59:52
 */
public interface PostilService {

	Page<PostilEntity> listPostil(Map<String, Object> params);
	
	Page<PostilEntity> listPostilForGroup(Map<String, Object> params);
	
	R savePostil(PostilEntity postil);
	
	R getPostilById(Long id);
	
	R updatePostil(PostilEntity postil);
	
	R batchRemove(Long[] id);
	
	R insertPostil(PostilEntity postil);
	
	R remove(Long postilId);

	List<PosPobLitEntity> listRecommend(String selectedText);
	
	List<PostilEntity> loanPostil(int groupId, int literatureId);
	
	R updatePostil2(PostilEntity postil);
	
	R removeByForId(String postilForid);
	
	String getPostilContentById_(int postilId);
	
}
