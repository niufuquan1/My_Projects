package net.chenlin.dp.modules.kdecm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.LiteratureEntity;
import net.chenlin.dp.modules.kdecm.entity.LiteratureUserEntity;
import net.chenlin.dp.modules.sys.dao.BaseMapper;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午8:11:20
 */
@Mapper
public interface LiteratureUserMapper extends BaseMapper<LiteratureUserEntity> {
}
