package net.chenlin.dp.modules.kdecm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.sf.json.JSONArray;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.kdecm.entity.PostilEntity;
import net.chenlin.dp.modules.kdecm.entity.PosPobLitEntity;
import net.chenlin.dp.modules.kdecm.service.PosPobLitService;
import net.chenlin.dp.modules.kdecm.service.PostilService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午4:54:13
 */
@RestController
@RequestMapping("/kdecm/posPobLit")
public class PosPobLitController extends AbstractController {
	
	@Autowired
	private PosPobLitService posPobLitService;
		
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<PosPobLitEntity> list(@RequestBody Map<String, Object> params) {
		System.out.println("--------posPobLit----list------------------");
		System.out.println("--------posPobLit----relationName------------------"+ params.get("relationName"));
		System.out.println("--------posPobLit----flag------------------"+ params.get("flag"));
		System.out.println("--------posPobLit----postilId1------------------"+ params.get("postilId1"));
		return posPobLitService.listPosPobLit(params);
	}
	
	@RequestMapping("/listByRelationName")
	public Page<PosPobLitEntity> listByRelationName(@RequestBody Map<String, Object> params) {
		System.out.println("--------posPobLit----list------------------");
		System.out.println("--------posPobLit----list------------------"+ params.get("relationName"));
		System.out.println("--------posPobLit----list------------------"+ params.get("flag"));
		return posPobLitService.listPosPobLit(params);
	}
	
	@RequestMapping("/posPobLitById")
	public PosPobLitEntity posPobLitById(@RequestParam String postilId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postilId", Integer.parseInt(postilId));
		return posPobLitService.posPobLitById(params);
	}
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/listByText")
	public Page<PosPobLitEntity> listByText(@RequestBody Map<String, Object> params) {
		
		System.out.println("-------test-------"+params);
		System.out.println("-------test-------"+params.get("searchtext"));
		return posPobLitService.listPosPobLitByText(params);
	}
	
	/** 
	 * 批注列表搜索
	 * @param params
	 * @return
	 */
	@RequestMapping("/postil_search")
	public Page<PosPobLitEntity> postil_search(@RequestBody Map<String, Object> params) {
		return posPobLitService.listPostilSearch(params);
	}
	
	/** 
	 * 批注列表搜索
	 * @param params
	 * @return
	 */
	@RequestMapping("/postil_search1")
	public List<PosPobLitEntity> postil1_search(@RequestParam(value="literatureId",required=false) int literatureId) {
		List<PosPobLitEntity> cssList = new ArrayList<>();
		System.out.println(literatureId);
		cssList = posPobLitService.listPostilSearch1(literatureId);
		return cssList;
	}
	
	/** 
	 * 高亮/下划线/删除线搜索
	 * @param params
	 * @return
	 */
	@RequestMapping("/css_search")
	public List<PosPobLitEntity> css_search(@RequestParam(value="literatureId",required=false) int literatureId) {
		System.out.println(literatureId);
		List<PosPobLitEntity> cssList = new ArrayList<>();
		cssList = posPobLitService.listCssSearch(literatureId);
		return cssList;
	}
	
	/** 
	 * 公开批注搜索
	 * @param params
	 * @return
	 */
	@RequestMapping("/postil_search_pub")
	public List<PosPobLitEntity> postil_search_pub(@RequestParam(value="postilContent",required=false) String postilContent) {
		List<PosPobLitEntity> posList = new ArrayList<>();
		posList = posPobLitService.listPostilSearch2(postilContent);
		return posList;
	}
	
	/** 
	 * 用户查看自己最近的批注列表搜索
	 * @param params
	 * @return
	 */

	@RequestMapping("/listForPageuserown")
	public Page<PosPobLitEntity> listForPageuserown(@RequestBody Map<String, Object> params) {
		System.out.println(123456);
		return posPobLitService.listForPageuserown(params);			
	}

	/**
	 * 作者：杨淑铭
	 * 功能：查看一篇文献的批注层级结构
	 * @param literatureId
	 * @return
	 */
	@SysLog("查看网络图")
	@RequestMapping("/postilLevels")
	public String getPostilLevelByLiteratureId(@RequestParam String literatureId) {
		System.out.println("---------------------postilLevels/info:"+literatureId+"---------------------");
//		String feedBack = JSON.toJSONString(posPobLitService.getPostilLevelByLiteratureId(Integer.parseInt(literatureId)));
		String feedBack = JSONArray.fromObject(posPobLitService.getPostilLevelByLiteratureId(Integer.parseInt(literatureId))).toString();
		System.out.println("feedBack1" + feedBack);
		feedBack = feedBack.substring(1, feedBack.length()-1);
		System.out.println("feedBack2" + feedBack);
		return feedBack;
	}
}
