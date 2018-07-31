package net.chenlin.dp.modules.kdecm.manager;



import java.util.List;


import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.ProGroupEntity;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;


/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月28日 下午9:27:22
 */
public interface ProGroupManager {

	List<ProGroupEntity> listProGroup(Page<ProGroupEntity> page, Query search);
	
	List<ProGroupEntity> listGroupByUserID(Page<ProGroupEntity> page, Query search);
	
	int saveProGroup(ProGroupEntity proGroup);
	
	ProGroupEntity getProGroupById(Long id);
	
	int updateProGroup(ProGroupEntity proGroup);
	
	int batchRemove(Long[] id);
	
	int batchRemove_(Long[] id);
	
	List<SysUserEntity> listSysUser();
	
	Integer MaxGroupID();
	
	Integer addGroup(ProGroupEntity proGroup);
	
	Integer addGroupUser(int groupid,int userId);
	
	Integer ChangeGroupProperty(String groupid,String OpenNessProperty);

	List<SysUserEntity> listGroupSysUser(int GroupId);
	
	Integer delGroupUser(int groupid,int userId);
	
	Integer ChangeGroupName(int groupId,String groupName);
}
