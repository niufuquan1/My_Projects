package net.chenlin.dp.modules.kdecm.manager;

import java.util.List;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
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
public interface PostilManager {

	List<PostilEntity> listPostil(Page<PostilEntity> page, Query search);
	
	List<PostilEntity> listPostilForGroup(Page<PostilEntity> page, Query search);
	
	int savePostil(PostilEntity postil);
	
	PostilEntity getPostilById(Long id);
	
	int updatePostil(PostilEntity postil);
	
	int batchRemove(Long[] id);
	
	int insertPostil(PostilEntity postil);
	
	int remove(Long postilId);

	List<PosPobLitEntity> listRecommend(String selectedText);
	
	List<PostilEntity> loanPostil(int groupId, int literatureId);
	
	int updatePostil2(PostilEntity postil);
	
	int removeByForId(String postilForid); 
	
	
	String getPostilContentById_(int postilId);
}
