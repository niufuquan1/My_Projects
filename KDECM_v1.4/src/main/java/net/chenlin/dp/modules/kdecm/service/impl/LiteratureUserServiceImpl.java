package net.chenlin.dp.modules.kdecm.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.kdecm.entity.LiteratureEntity;
import net.chenlin.dp.modules.kdecm.entity.LiteratureUserEntity;
import net.chenlin.dp.modules.kdecm.manager.LiteratureManager;
import net.chenlin.dp.modules.kdecm.manager.LiteratureUserManager;
import net.chenlin.dp.modules.kdecm.service.LiteratureService;
import net.chenlin.dp.modules.kdecm.service.LiteratureUserService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午8:11:20
 */
@Service("literatureUserService")
public class LiteratureUserServiceImpl implements LiteratureUserService {

	@Autowired
	private LiteratureUserManager literatureUserManager;

	@Override
	public Page<LiteratureUserEntity> listLiteratureUser(Map<String, Object> params) {
		Query query = new Query(params);
		Page<LiteratureUserEntity> page = new Page<>(query);
		literatureUserManager.listLiteratureUser(page, query);
		return page;
	}
	
}
