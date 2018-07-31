package net.chenlin.dp.modules.kdecm.service.impl;



import java.util.List;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.AnalysisWordUtil;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.kdecm.entity.LiteratureObjectEntity;
import net.chenlin.dp.modules.kdecm.entity.ProGroupEntity;
import net.chenlin.dp.modules.kdecm.manager.ProGroupManager;
import net.chenlin.dp.modules.kdecm.service.ProGroupService;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;



/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月28日 下午9:27:22
 */
@Service("proGroupService")
public class ProGroupServiceImpl implements ProGroupService {

	@Autowired
	private ProGroupManager proGroupManager;

	@Override
	public Page<ProGroupEntity> listProGroup(Map<String, Object> params) {
		Query query = new Query(params);
		Page<ProGroupEntity> page = new Page<>(query);
		proGroupManager.listProGroup(page, query);
		return page;
	}

	@Override
	public Page<ProGroupEntity> listGroupByUserID(Map<String, Object> params) {
		Query query = new Query(params);
		Page<ProGroupEntity> page = new Page<>(query);
		proGroupManager.listGroupByUserID(page, query);
		return page;
	}
	
	@Override
	public R saveProGroup(ProGroupEntity role) {
		int count = proGroupManager.saveProGroup(role);
		return CommonUtils.msg(count);
	}

	@Override
	public R getProGroupById(Long id) {
		ProGroupEntity proGroup = proGroupManager.getProGroupById(id);
		return CommonUtils.msg(proGroup);
	}

	@Override
	public R updateProGroup(ProGroupEntity proGroup) {
		int count = proGroupManager.updateProGroup(proGroup);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemove(Long[] id) {
		int count = proGroupManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}
	
	public List<SysUserEntity> listSysUser(){
		List<SysUserEntity> list = proGroupManager.listSysUser();
		return list;
	}
	
	public Integer MaxGroupID() {
		Integer maxGroupId = proGroupManager.MaxGroupID();
		if(maxGroupId!=null) {
			return maxGroupId;
		}
		return 0;
	}
	
	public Integer addGroup(ProGroupEntity proGroup) {
		int flag = proGroupManager.addGroup(proGroup);
		return flag;
	}
	
	public Integer addGroupUser(int groupid,int userId) {
		int flag = proGroupManager.addGroupUser(groupid, userId);
		return flag;
	}
	
	@Override
	public R batchRemove_(Long[] id) {
		int count = proGroupManager.batchRemove_(id);
		return CommonUtils.msg(id, count);
	}
	
	public Integer ChangeGroupProperty(String groupid,String OpenNessProperty) {
		int flag = proGroupManager.ChangeGroupProperty(groupid, OpenNessProperty);
		return flag;
	}
	
	public List<SysUserEntity> listGroupSysUser(int GroupId){
		List<SysUserEntity> list = proGroupManager.listGroupSysUser(GroupId);
		return list;
	}
	
	public Integer delGroupUser(int groupid,int userId) {
		int flag = proGroupManager.delGroupUser(groupid, userId);
		return flag;
	}
	
	public Integer ChangeGroupName(int groupId,String groupName) {
		int flag = proGroupManager.ChangeGroupName(groupId,groupName);
		return flag;
	}
}
