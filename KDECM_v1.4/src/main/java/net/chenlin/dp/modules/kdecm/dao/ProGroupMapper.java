package net.chenlin.dp.modules.kdecm.dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.ProGroupEntity;
import net.chenlin.dp.modules.sys.dao.BaseMapper;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月28日 下午9:27:22
 */
@Mapper
public interface ProGroupMapper extends BaseMapper<ProGroupEntity> {
	List<SysUserEntity> listSysUser();
	Integer MaxGroupID();
	Integer addGroup(ProGroupEntity proGroup);//创建组信息
	Integer addGroupUser(@Param("groupid") Integer groupid,@Param("userid") Integer userid);//添加该组组员
	Integer ChangeGroupProperty(@Param("groupId") String groupid,@Param("OpenNessProperty") String OpenNessProperty);
	int batchRemove_(Object[] id);
	List<ProGroupEntity> listGroupByUserID(Page<ProGroupEntity> page, Query search);
	List<SysUserEntity> listGroupSysUser(int GroupId);
	Integer delGroupUser(@Param("groupid") Integer groupid,@Param("userid") Integer userid);//删除该组组员
	Integer ChangeGroupName(@Param("groupId") Integer groupid,@Param("groupName") String groupName);
}
