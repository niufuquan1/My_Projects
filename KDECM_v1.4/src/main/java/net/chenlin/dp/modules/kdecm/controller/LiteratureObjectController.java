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
import net.chenlin.dp.modules.kdecm.entity.LiteratureObjectEntity;
import net.chenlin.dp.modules.kdecm.service.LiteratureObjectService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月31日 下午4:41:04
 */
@RestController
@RequestMapping("/kdecm/literatureObject")
public class LiteratureObjectController extends AbstractController {
	
	@Autowired
	private LiteratureObjectService literatureObjectService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<LiteratureObjectEntity> list(@RequestBody Map<String, Object> params) {
		return literatureObjectService.listLiteratureObject(params);
	}
		
	/**
	 * 文献详情
	 * @param params
	 * @return
	 */
	@RequestMapping("/literatureShow")
	public List<LiteratureObjectEntity> literatureShow(@RequestParam(value="userId",required=false) long userId, 
			@RequestParam(value="literatureId",required=false) int literatureId) {
		List<LiteratureObjectEntity> postilList = new ArrayList<>();
		postilList = literatureObjectService.literatureShow(userId, literatureId);
		return postilList;
	}
	
	/**
	 * 搜索文献内容
	 * @param params
	 * @return
	 */
	@RequestMapping("/literature_search_pub")
	public List<LiteratureObjectEntity> literature_search_pub(@RequestParam(value="literatureObjectText",required=false) String literatureObjectText) {
		List<LiteratureObjectEntity> postilList = new ArrayList<>();
		postilList = literatureObjectService.literature_search_pub(literatureObjectText);
		return postilList;
	}
	
	/**
	 * 新增
	 * @param literatureObject
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody LiteratureObjectEntity literatureObject) {
		return literatureObjectService.saveLiteratureObject(literatureObject);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return literatureObjectService.getLiteratureObjectById(id);
	}
	
	/**
	 * 修改
	 * @param literatureObject
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody LiteratureObjectEntity literatureObject) {
		return literatureObjectService.updateLiteratureObject(literatureObject);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return literatureObjectService.batchRemove(id);
	}
	
}
