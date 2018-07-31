package net.chenlin.dp.modules.kdecm.service;
 
import java.util.List;
import java.util.Map;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
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
public interface ProGroupService {

	Page<ProGroupEntity> listProGroup(Map<String, Object> params);
	
	Page<ProGroupEntity> listGroupByUserID(Map<String, Object> params);
	
	R saveProGroup(ProGroupEntity proGroup);
	
	R getProGroupById(Long id);
	
	R updateProGroup(ProGroupEntity proGroup);
	
	R batchRemove(Long[] id);
	
	List<SysUserEntity> listSysUser();
	
	Integer MaxGroupID();
	
	Integer addGroup(ProGroupEntity proGroup);
	
	Integer addGroupUser(int groupid,int userId);
	
	R batchRemove_(Long[] id);
	
	Integer ChangeGroupProperty(String groupid,String OpenNessProperty);

	List<SysUserEntity> listGroupSysUser(int GroupId);
	
	Integer delGroupUser(int groupid,int userId);
	
	Integer ChangeGroupName(int groupId,String groupName);
}
