package net.chenlin.dp.modules.sys.manager;

import java.util.List;
import java.util.Set;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import net.chenlin.dp.modules.sys.entity.SysUserTokenEntity;

/**
 * 系统用户
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2017年8月11日 上午11:43:01
 */
public interface SysUserManager {

	SysUserEntity getByUserName(String username);
	
	List<SysUserEntity> listUser(Page<SysUserEntity> page, Query search);
	
	int saveUser(SysUserEntity user);
	
	SysUserEntity getById(Long userId);
	
	int updateUser(SysUserEntity user);
	
	int batchRemove(Long[] id);
	
	Set<String> listUserPerms(Long userId);
	
	Set<String> listUserRoles(Long userId);
	
	int updatePswdByUser(Query query);
	
	int updateUserEnable(Long[] id);
	
	int updateUserDisable(Long[] id);
	
	int updatePswd(SysUserEntity user);
	
	SysUserEntity getUserById(Long userId);
	
	SysUserTokenEntity getByToken(String token);
	
	SysUserTokenEntity saveUserToken(Long userId);
	
	int updateUserToken(Long userId);
	
	SysUserEntity getUserEmail(String email);
	
	SysUserEntity getUserphone(String phone);
	
	int UpatePwdforEmail(String ValidateCode,String pwd);
	
	int UpdatePwdforPhone(String ValidateCode,String pwd);
	
	int regist(SysUserEntity user);
	
}
