package net.chenlin.dp.modules.kdecm.service;

import java.util.List;
import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.kdecm.entity.PostilEntity;
import net.chenlin.dp.modules.kdecm.entity.PostilObjectEntity;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月02日 下午1:22:02
 */
public interface PostilObjectService {

	Page<PostilObjectEntity> listPostilObject(Map<String, Object> params);
	
	R savePostilObject(PostilObjectEntity postilObject);
	
	R getPostilObjectById(Long id);
	
	R updatePostilObject(PostilObjectEntity postilObject);
	
	R batchRemove(Long[] id);

	R insertPostilObject(PostilObjectEntity postilObject);
	
	List<PostilObjectEntity> loanPostilObject(int[] postilObjectIds);
	
	String getPostilObjectContentById_(int postilId);
	
}
