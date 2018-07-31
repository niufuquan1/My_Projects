package net.chenlin.dp.modules.kdecm.manager.impl;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.dao.ProGroupMapper;
import net.chenlin.dp.modules.kdecm.entity.ProGroupEntity;
import net.chenlin.dp.modules.kdecm.manager.ProGroupManager;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;


/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月28日 下午9:27:22
 */
@Component("proGroupManager")
public class ProGroupManagerImpl implements ProGroupManager {

	@Autowired
	private ProGroupMapper proGroupMapper;
	

	@Override
	public List<ProGroupEntity> listProGroup(Page<ProGroupEntity> page, Query search) {
		return proGroupMapper.listForPage(page, search);
	}

	@Override
	public List<ProGroupEntity> listGroupByUserID(Page<ProGroupEntity> page, Query search) {
		return proGroupMapper.listGroupByUserID(page, search);
	}
	
	@Override
	public int saveProGroup(ProGroupEntity proGroup) {
		return proGroupMapper.save(proGroup);
	}

	@Override
	public ProGroupEntity getProGroupById(Long id) {
		ProGroupEntity proGroup = proGroupMapper.getObjectById(id);
		return proGroup;
	}

	@Override
	public int updateProGroup(ProGroupEntity proGroup) {
		return proGroupMapper.update(proGroup);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = proGroupMapper.batchRemove(id);
		return count;
	}
	
	public List<SysUserEntity> listSysUser(){
		return proGroupMapper.listSysUser();
	}
	
	public Integer MaxGroupID() {
		return proGroupMapper.MaxGroupID();
	}
	
	public Integer addGroup(ProGroupEntity proGroup) {
		return proGroupMapper.addGroup(proGroup);
	}
	
	public Integer addGroupUser(int groupid,int userId) {
		return proGroupMapper.addGroupUser(groupid, userId);
	}
	
	@Override
	public int batchRemove_(Long[] id) {
		int count = proGroupMapper.batchRemove_(id);
		return count;
	}
	
	public Integer ChangeGroupProperty(String groupid,String OpenNessProperty) {
		return proGroupMapper.ChangeGroupProperty(groupid, OpenNessProperty);
	}
	
	public List<SysUserEntity> listGroupSysUser(int GroupId){
		return proGroupMapper.listGroupSysUser(GroupId);
	}
	
	public Integer delGroupUser(int groupid,int userId) {
		return proGroupMapper.delGroupUser(groupid, userId);
	}
	
	public Integer ChangeGroupName(int groupId,String groupName) {
		return proGroupMapper.ChangeGroupName(groupId, groupName);
	}
}
