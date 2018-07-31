package net.chenlin.dp.modules.kdecm.controller;

import java.util.ArrayList;
import java.util.List;
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
import net.chenlin.dp.modules.kdecm.entity.PostilEntity;
import net.chenlin.dp.modules.kdecm.entity.PostilObjectEntity;
import net.chenlin.dp.modules.kdecm.service.PostilObjectService;
import net.chenlin.dp.modules.kdecm.service.PostilService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月25日 下午3:59:52
 */
@RestController
@RequestMapping("/kdecm/postil")
public class PostilController extends AbstractController {
	
	@Autowired
	private PostilService postilService;
	
	@Autowired
	private PostilObjectService postilObjectService;
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<PostilEntity> list(@RequestBody Map<String, Object> params) {
		return postilService.listPostil(params);
	}
	

	@RequestMapping("/listPostilForGroup")
	public Page<PostilEntity> listPostilForGroup(@RequestBody Map<String, Object> params) {
		return postilService.listPostilForGroup(params);
	}
	
	/**
	 * 新增
	 * @param postil
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody PostilEntity postil) {
		return postilService.savePostil(postil);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return postilService.getPostilById(id);
	}
	
	/**
	 * 修改
	 * @param postil
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody PostilEntity postil) {
		return postilService.updatePostil(postil);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		for (int i = 0; i < id.length; i++) {
			System.out.println("id===="+id[i]);
		}
		return postilService.batchRemove(id);
	}
	
	/**
	 * 增添批注，在另一个Conroller中增加批注对象
	 * @param postil
	 * @return
	 * @肖凌云
	 * @2018/5/28 19:05，修改2018/5/31 14:50
	 */
	@SysLog("新增如果成功返回postilId")
	@RequestMapping("/insert")
	public R insert(@RequestBody PostilEntity postil) {
		System.out.println("欢迎来到自己写的方法");
		//System.out.println(postil.getPostilTime());
		R r = postilService.insertPostil(postil);
		//System.out.println("postilId="+postil.getPostilId());
		//int code = Integer.parseInt(String.valueOf(r.get("code")));
		r.put("postilId", postil.getPostilId());
		return r;
	}
	
	@SysLog("单个删除")
	@RequestMapping("/delete")
	public R remove(@RequestParam(value="postilForid",required=false) String postilForid) {
		System.out.println("欢迎来到自己写的删除方法");
		System.out.println("postilForid="+postilForid);
		R r = postilService.removeByForId(postilForid);

		//System.out.println("postilId="+postil.getPostilId());
		return r;
	}
	
	/**
	 * 批注推荐
	 * @param 选中的批注原文
	 * @return
	 * @author RickyZZQ
	 * @2018/6/12 9:44
	 */
	@SysLog("批注推荐")
	@RequestMapping("/recommend")
	public List<PosPobLitEntity> listRecommend(@RequestParam(value="selectedText",required=false) String selectedText) {
		System.out.println("批注推荐");
		System.out.println("selectedText="+selectedText);
		return postilService.listRecommend(selectedText);
	}
	
	@SysLog("批注加载")
	@RequestMapping("/loadPostil")
	public R loanPostil(@RequestParam(value="groupId",required=false) int groupId,
			@RequestParam(value="literatureId",required=false) int literatureId) {
		
		System.out.println("欢迎进入到loanPostil");
		//System.out.println(userId);
		//System.out.println(literatureId);
		List<PostilEntity> postilList = new ArrayList<>();
		List<PostilObjectEntity> postilObjectList = new ArrayList<>();
		R r = new R();
		postilList = postilService.loanPostil(groupId, literatureId);
		System.out.println(postilList.size());
		if(postilList.size()!=0) {
			int[] postilObjectIds = new int[postilList.size()];
			for (int i = 0; i < postilList.size(); i++) {
				postilObjectIds[i]=postilList.get(i).getPostilObjectId();
				System.out.println("postilObjectIds="+postilObjectIds[i]);
			}
			
			postilObjectList = postilObjectService.loanPostilObject(postilObjectIds);
			System.out.println(postilObjectList);
			r.put("postilList", postilList);
			r.put("postilObjectList", postilObjectList);
			r.put("result", true);
		}else {
			r.put("result", false);
		}
		
		return r;
	}
	
	@SysLog("修改2")
	@RequestMapping("/update2")
	public R update2(@RequestBody PostilEntity postil) {
		System.out.println("欢迎进入postil 6-23");
		System.out.println("forid="+postil.getPostilForid());
		return postilService.updatePostil2(postil);
	}
	
}
