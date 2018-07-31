package net.chenlin.dp.modules.kdecm.manager;

import java.util.List;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
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
public interface PostilObjectManager {

	List<PostilObjectEntity> listPostilObject(Page<PostilObjectEntity> page, Query search);
	
	int savePostilObject(PostilObjectEntity postilObject);
	
	PostilObjectEntity getPostilObjectById(Long id);
	
	int updatePostilObject(PostilObjectEntity postilObject);
	
	int batchRemove(Long[] id);
	
	int insertPostilObject(PostilObjectEntity postilObject);
	
	List<PostilObjectEntity> loanPostilObject(int[] postilObjectIds);
	
	String getPostilObjectContentById_(int postilId);
}
