package net.chenlin.dp.modules.sys.controller;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.constant.SystemConstant;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.MD5Utils;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import net.chenlin.dp.modules.sys.service.SysUserService;

/**
 * 系统用户
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2017年8月8日 下午9:04:59
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;

	/**
	 * 用户列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<SysUserEntity> list(@RequestBody Map<String, Object> params) {
		if (getUserId() != SystemConstant.SUPER_ADMIN) {
			params.put("userIdCreate", getUserId());
		}
		return sysUserService.listUser(params);
	}

	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	public R info() {
		return R.ok().put("user", getUser());
	}

	/**
	 * 用户权限
	 * 
	 * @return
	 */
	@RequestMapping("/perms")
	public R listUserPerms() {
		return sysUserService.listUserPerms(getUserId());
	}

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	@SysLog("新增用户")
	@RequestMapping("/save")
	public R save(@RequestBody SysUserEntity user) {
		user.setUserIdCreate(getUserId());
		return sysUserService.saveUser(user);
	}

	/**
	 * 根据id查询详情
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/infoUser")
	public R getById(@RequestBody Long userId) {
		return sysUserService.getUserById(userId);
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */
	@SysLog("修改用户")
	@RequestMapping("/update")
	public R update(@RequestBody SysUserEntity user) {
		return sysUserService.updateUser(user);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@SysLog("删除用户")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return sysUserService.batchRemove(id);
	}

	/**
	 * 用户修改密码
	 * 
	 * @param pswd
	 * @param newPswd
	 * @return
	 */
	@SysLog("修改密码")
	@RequestMapping("/updatePswd")
	public R updatePswdByUser(String pswd, String newPswd) {
		SysUserEntity user = getUser();
		user.setPassword(pswd);// 原密码
		user.setEmail(newPswd);// 邮箱临时存储新密码
		return sysUserService.updatePswdByUser(user);
	}

	/**
	 * 启用账户
	 * 
	 * @param id
	 * @return
	 */
	@SysLog("启用账户")
	@RequestMapping("/enable")
	public R updateUserEnable(@RequestBody Long[] id) {
		return sysUserService.updateUserEnable(id);
	}

	/**
	 * 禁用账户
	 * 
	 * @param id
	 * @return
	 */
	@SysLog("禁用账户")
	@RequestMapping("/disable")
	public R updateUserDisable(@RequestBody Long[] id) {
		return sysUserService.updateUserDisable(id);
	}

	/**
	 * 重置密码
	 * 
	 * @param user
	 * @return
	 */
	@SysLog("重置密码")
	@RequestMapping("/reset")
	public R updatePswd(@RequestBody SysUserEntity user) {
		return sysUserService.updatePswd(user);
	}

	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String resetPwd(ServletRequest request, HttpServletRequest req) throws Exception {
		String validateCode = request.getParameter("username");
		SysUserEntity sysuserentity = sysUserService.getUserEmail(validateCode);
		String password = request.getParameter("password");
		String flag = request.getParameter("flag");
		String username = sysuserentity.getUsername();
		int flag_ = Integer.parseInt(flag);
		System.out.println("username:" + username + "password:" + password + "flag:" + flag);
		if (flag_ == 1) {
			System.out.println(username);
			String newPsw = MD5Utils.encrypt(username, password);
			System.out.println("修改后：" + newPsw);
			sysUserService.UpatePwdforEmail(validateCode, newPsw);
			return "{\"success\":" + "\"" + "1" + "\"" + "}";
		} else {
			String newPsw = MD5Utils.encrypt(username, password);
			sysUserService.UpdatePwdforPhone(validateCode, newPsw);
		}
		return null;
	}
}
