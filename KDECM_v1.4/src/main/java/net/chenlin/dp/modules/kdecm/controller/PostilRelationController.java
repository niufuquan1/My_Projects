package net.chenlin.dp.modules.kdecm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.kdecm.entity.PosPobLitEntity;
import net.chenlin.dp.modules.kdecm.entity.PostilRelationEntity;
import net.chenlin.dp.modules.kdecm.service.PostilRelationService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月25日 下午5:22:02
 */
@RestController
@RequestMapping("/kdecm/postil_relation")
public class PostilRelationController extends AbstractController {
	
	@Autowired
	private PostilRelationService postilRelationService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<PostilRelationEntity> list(@RequestBody Map<String, Object> params) {
		return postilRelationService.listPostilRelation(params);
	}
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/listDistinct")
	public Page<PostilRelationEntity> listDistinct(@RequestBody Map<String, Object> params) {
		return postilRelationService.listDistinctPostilRelation(params);
	}
		
	/**
	 * 新增
	 * @param postilRelation
	 * @return
	 */
	/*@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody PostilRelationEntity postilRelation) {
		System.out.println("-------添加批注关系-------");
		return postilRelationService.savePostilRelation(postilRelation);
	}*/
	
	/**
	 * 新增
	 * @param postilRelation
	 * @return
	 */
	@SysLog("添加批注关系")
	@RequestMapping("/add")
	public R add(@RequestParam String relationName,String relationType,String userId,String userName,String postilId1,String postilName1,
			String literatureId1,String literatureName1,String postilId2,String postilName2,String literatureId2,String literatureName2,String groupId) {
		PostilRelationEntity postilRelation = new PostilRelationEntity();
		postilRelation.setRelationName(relationName);
		postilRelation.setRelationType(Integer.parseInt(relationType));
		postilRelation.setUserId(Long.parseLong(userId));
		postilRelation.setUserName(userName);
		postilRelation.setPostilId1(Integer.parseInt(postilId1));
		postilRelation.setPostilName1(postilName1);
		postilRelation.setLiteratureId1(Integer.parseInt(literatureId1));
		postilRelation.setLiteratureName1(literatureName1);
		postilRelation.setPostilId2(Integer.parseInt(postilId2));
		postilRelation.setPostilName2(postilName2);
		postilRelation.setLiteratureId2(Integer.parseInt(literatureId2));
		postilRelation.setLiteratureName2(literatureName2);
		postilRelation.setGroupId(Integer.parseInt(groupId));
		return postilRelationService.savePostilRelation(postilRelation);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return postilRelationService.getPostilRelationById(id);
	}
	
	/**
	 * 根据name,id1,id2查询详情
	 * @param relationName,postilId1,postilId2
	 * @return
	 */
	@RequestMapping("/editRel")
	public PostilRelationEntity getPostilRelationByNameAndPostilId(@RequestParam String relationName, String postilId1, String postilId2, String groupId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("relationName", relationName);
		params.put("postilId1", Integer.parseInt(postilId1));
		params.put("postilId2", Integer.parseInt(postilId2));
		params.put("groupId", Integer.parseInt(groupId));
		System.out.println("-------editRel--relationName-----" + relationName);
		System.out.println("-------editRel--postilId1-----" + postilId1);
		System.out.println("-------editRel--postilId2-----" + postilId2);
		System.out.println("-------editRel--groupId-----" + groupId);
		return postilRelationService.getPostilRelationByNameAndPostilId(params);
	}
	
	/**
	 * 修改
	 * @param postilRelation
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody PostilRelationEntity postilRelation) {
		return postilRelationService.updatePostilRelation(postilRelation);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	/*@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return postilRelationService.batchRemove(id);
	}*/
	
	/**
	 * 删除批注关系
	 * @param relationName, postilId1, postilId2, groupId
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/removeRel")
	public R removeByRelNameAndPostilId(@RequestParam String relationName, String postilId1, String postilId2, String groupId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("relationName", relationName);
		params.put("postilId1", Integer.parseInt(postilId1));
		params.put("postilId2", Integer.parseInt(postilId2));
		params.put("groupId", Integer.parseInt(groupId));
		System.out.println("-------removeRel-------" + relationName);
		System.out.println("-------removeRel-------" + postilId1);
		System.out.println("-------removeRel-------" + postilId2);
		return postilRelationService.removeByRelNameAndPostilId(params);
	}
	
}
