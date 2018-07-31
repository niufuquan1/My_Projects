package net.chenlin.dp.modules.kdecm.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.dao.LiteratureMapper;
import net.chenlin.dp.modules.kdecm.dao.LiteratureUserMapper;
import net.chenlin.dp.modules.kdecm.entity.LiteratureEntity;
import net.chenlin.dp.modules.kdecm.entity.LiteratureUserEntity;
import net.chenlin.dp.modules.kdecm.manager.LiteratureManager;
import net.chenlin.dp.modules.kdecm.manager.LiteratureUserManager;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午8:11:20
 */
@Component("literatureUserManager")
public class LiteratureUserManagerImpl implements LiteratureUserManager {

	@Autowired
	private LiteratureUserMapper literatureUserMapper;
	

	@Override
	public List<LiteratureUserEntity> listLiteratureUser(Page<LiteratureUserEntity> page, Query search) {
		return literatureUserMapper.listForPage(page, search);
	}
}
