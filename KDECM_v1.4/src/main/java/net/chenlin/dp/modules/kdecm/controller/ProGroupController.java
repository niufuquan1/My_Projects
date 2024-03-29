package net.chenlin.dp.modules.kdecm.controller;



import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import net.sf.json.JSONArray;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.kdecm.entity.ProGroupEntity;
import net.chenlin.dp.modules.kdecm.service.ProGroupService;


/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月28日 下午9:27:22
 */
@RestController
@RequestMapping("/kdecm/group")
public class ProGroupController extends AbstractController {
	
	@Autowired
	private ProGroupService proGroupService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<ProGroupEntity> list(@RequestBody Map<String, Object> params) {
		return proGroupService.listProGroup(params);
	}
		
	@RequestMapping("/listgroupbyuserid")
	public Page<ProGroupEntity> listGroupByUserID(@RequestBody Map<String, Object> params) {
		return proGroupService.listGroupByUserID(params);
	}
	
	/**
	 * 新增
	 * @param proGroup
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody ProGroupEntity proGroup) {
		return proGroupService.saveProGroup(proGroup);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return proGroupService.getProGroupById(id);
	}
	
	/**
	 * 修改
	 * @param proGroup
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody ProGroupEntity proGroup) {
		return proGroupService.updateProGroup(proGroup);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		R r = new R();
		proGroupService.batchRemove_(id);//该函数是为了级联删除批注组表人员的数据
		r = proGroupService.batchRemove(id);		
		return r;
	}
	
	@RequestMapping(value = "/GetUserList", method = RequestMethod.POST, produces = "application/json")  
    public @ResponseBody List<SysUserEntity> ListSysUser(Model model, ServletRequest request,HttpServletRequest req) {  
        List<SysUserEntity> list = new ArrayList<>();
        list = proGroupService.listSysUser();
        return list;
        }
	
	@RequestMapping(value = "/GetUserSelect", method = RequestMethod.POST, produces = "application/json")  
    public @ResponseBody int SysUserSelect(Model model, ServletRequest request,HttpServletRequest req) {  
  
		int flag = -1;
		int groupid = proGroupService.MaxGroupID();//从数据库中查找到组号;
		groupid++;//自增一位后得到本组组号
		String groupName = request.getParameter("groupName");//获得前台的以json格式封装的字符串
		System.out.println(groupName);
		String createUserId = request.getParameter("createUserId");
		System.out.println(createUserId);
		String userIdList = request.getParameter("userIdList");
		System.out.println(userIdList);
		ArrayList<String> arrayuserIdList = new ArrayList<String>();  
		JSONArray jsonArray = JSONArray.fromObject(userIdList);
		for (int i = 0; i < jsonArray.size(); i++) {  
			String userId = jsonArray.getJSONObject(i).getString("userid");
			arrayuserIdList.add(userId);
		}
		
		//创建批注组实例对象，进行数据持久化操作
		ProGroupEntity proGroupEntity = new ProGroupEntity();
		proGroupEntity.setGroupId(groupid);
		proGroupEntity.setGroupName(groupName);
		proGroupEntity.setUserId(Long.valueOf(createUserId));
		int flag1 = proGroupService.addGroup(proGroupEntity);
		List<Integer> flag2 = new ArrayList<Integer>();
		//创建批注组组员对象表
		for(int j=0;j<arrayuserIdList.size();j++) {
			//System.out.println(arrayuserIdList.get(j));
			flag2.add(proGroupService.addGroupUser(groupid, Integer.parseInt(arrayuserIdList.get(j))));
		}
		if(flag1 > 0&& flag2.isEmpty()!=true ) {
			return 1;
		}else {
			return -1;
		}   
	}
	
	@RequestMapping(value = "/ChangeGroupProperty", method = RequestMethod.POST, produces = "application/json")  
    public @ResponseBody Integer ChangeGroupProperty(Model model, ServletRequest request,HttpServletRequest req) {  
		String openNessProperty = request.getParameter("openNess");
        String groupId = request.getParameter("groupId");
        System.out.println(groupId);
        System.out.println(openNessProperty);
        int flag = proGroupService.ChangeGroupProperty(groupId, openNessProperty);
        if(flag>0) {
        	return 1;
        }else {
        	return 0;
        }
    }
	
	@RequestMapping(value = "/getGroupUserList", method = RequestMethod.POST, produces = "application/json")  
    public @ResponseBody List<SysUserEntity> getGroupUserList(Model model, ServletRequest request,HttpServletRequest req) {  
		 	String groupId_ = request.getParameter("groupId");
			int groupId = Integer.parseInt(groupId_);
			List<SysUserEntity> list = new ArrayList<>();
	        list = proGroupService.listGroupSysUser(groupId);
	        return list;
    }
	
	@RequestMapping(value = "/addGroupUserMoveOn", method = RequestMethod.POST, produces = "application/json")  
    public @ResponseBody int addGroupUserMoveOn(Model model, ServletRequest request,HttpServletRequest req) {  
			//获取组id
			String groupId_ = request.getParameter("groupId");
			int groupId = Integer.parseInt(groupId_);
			//获取添加人员id
			String userIdList = request.getParameter("userIdList");
			ArrayList<String> arrayuserIdList = new ArrayList<String>();  
			JSONArray jsonArray = JSONArray.fromObject(userIdList);
			for (int i = 0; i < jsonArray.size(); i++) {  
				String userId = jsonArray.getJSONObject(i).getString("userid");
				arrayuserIdList.add(userId);
			}
			List<Integer> flag = new ArrayList<Integer>();
			//添加组成员
			for(int j=0;j<arrayuserIdList.size();j++) {
				//System.out.println(arrayuserIdList.get(j));
				flag.add(proGroupService.addGroupUser(groupId, Integer.parseInt(arrayuserIdList.get(j))));
			}
			if(flag.isEmpty()!=true) {
				return 1;
			}else {
				return -1;
			}     
    }
	
	@RequestMapping(value = "/delGroupUserMoveOn", method = RequestMethod.POST, produces = "application/json")  
    public @ResponseBody int delGroupUserMoveOn(Model model, ServletRequest request,HttpServletRequest req) {  
			//获取组id
			String groupId_ = request.getParameter("groupId");
			int groupId = Integer.parseInt(groupId_);
			//获取删除人员id
			String userIdList = request.getParameter("userIdList");
			ArrayList<String> arrayuserIdList = new ArrayList<String>();  
			JSONArray jsonArray = JSONArray.fromObject(userIdList);
			for (int i = 0; i < jsonArray.size(); i++) {  
				String userId = jsonArray.getJSONObject(i).getString("userid");
				arrayuserIdList.add(userId);
			}
			List<Integer> flag = new ArrayList<Integer>();
			//删除组成员
			for(int j=0;j<arrayuserIdList.size();j++) {
				//System.out.println(arrayuserIdList.get(j));
				flag.add(proGroupService.delGroupUser(groupId, Integer.parseInt(arrayuserIdList.get(j))));
			}
			if(flag.isEmpty()!=true) {
				return 1;
			}else {
				return -1;
			}     
    }
	
	@RequestMapping(value = "/ChangeGroupName", method = RequestMethod.POST, produces = "application/json")  
    public @ResponseBody int ChangeGroupName(Model model, ServletRequest request,HttpServletRequest req) {  
			//获取组id
			String groupId_ = request.getParameter("groupId");
			int groupId = Integer.parseInt(groupId_);
			//获取组名称
			String groupName = request.getParameter("groupName");
			int flag = proGroupService.ChangeGroupName(groupId,groupName);
			if(flag >= 0) {
				return 1;
			}else {
				return -1;
			}     
    }
}
