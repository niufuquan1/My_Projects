package net.chenlin.dp.modules.kdecm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.kdecm.entity.PostilEntity;
import net.chenlin.dp.modules.kdecm.entity.PostilObjectEntity;
import net.chenlin.dp.modules.kdecm.service.PostilObjectService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月02日 下午1:22:02
 */
@RestController
@RequestMapping("/kdecm/postil_object")
public class PostilObjectController extends AbstractController {
	
	@Autowired
	private PostilObjectService postilObjectService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<PostilObjectEntity> list(@RequestBody Map<String, Object> params) {
		return postilObjectService.listPostilObject(params);
	}
		
	/**
	 * 新增
	 * @param postilObject
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody PostilObjectEntity postilObject) {
		return postilObjectService.savePostilObject(postilObject);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return postilObjectService.getPostilObjectById(id);
	}
	
	/**
	 * 修改
	 * @param postilObject
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody PostilObjectEntity postilObject) {
		return postilObjectService.updatePostilObject(postilObject);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return postilObjectService.batchRemove(id);
	}
	
	/**
	 * Conroller中增加批注对象
	 * @param postil_object
	 * @return
	 * @肖凌云
	 * @2018/6/2 13:31
	 */
	@SysLog("新增如果成功返回postilObjectId")
	@RequestMapping("/insert")
	public R insert(@RequestBody PostilObjectEntity postilObject) {
		System.out.println("欢迎来到postil objcet Contrller");	
		R r = postilObjectService.insertPostilObject(postilObject);
		//System.out.println("postilObjcetId="+postilObject.getPostilObjectId());
		r.put("postilObjectId", postilObject.getPostilObjectId());
		return r;
	}
	
	@SysLog("返回样式对象")
	@RequestMapping("/loadPostilObjectCss")
	public R loadPostilObjectCss(@RequestBody PostilObjectEntity postilObject) {
		System.out.println("欢迎来到postil objcet Contrller");	
		R r = postilObjectService.insertPostilObject(postilObject);
		//System.out.println("postilObjcetId="+postilObject.getPostilObjectId());
		r.put("postilObjectId", postilObject.getPostilObjectId());
		return r;
	}
	
}
