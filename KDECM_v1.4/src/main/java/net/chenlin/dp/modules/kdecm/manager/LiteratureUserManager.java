package net.chenlin.dp.modules.kdecm.manager;

import java.util.List;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.LiteratureEntity;
import net.chenlin.dp.modules.kdecm.entity.LiteratureUserEntity;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午8:11:20
 */
public interface LiteratureUserManager {

	List<LiteratureUserEntity> listLiteratureUser(Page<LiteratureUserEntity> page, Query search);
	
}
