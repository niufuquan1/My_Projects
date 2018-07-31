package net.chenlin.dp.modules.kdecm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.sf.json.JSONArray;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.HandleCOPRAUtils;
import net.chenlin.dp.modules.kdecm.algorithm.COPRA;
import net.chenlin.dp.modules.kdecm.algorithm.COPRA_CreateCSV;
import net.chenlin.dp.modules.kdecm.algorithm.COPRA_Graph;
import net.chenlin.dp.modules.kdecm.algorithm.COPRA_Main;
import net.chenlin.dp.modules.kdecm.algorithm.PageRank;
import net.chenlin.dp.modules.kdecm.algorithm.PostilForCOPRAEntity;
import net.chenlin.dp.modules.kdecm.algorithm.PostilForPageRankResultEntity;
import net.chenlin.dp.modules.kdecm.algorithm.ThirdFuncEntity;
import net.chenlin.dp.modules.kdecm.algorithm.ThirdFuncResultEntity;
import net.chenlin.dp.modules.kdecm.entity.GedRelEntity;
import net.chenlin.dp.modules.kdecm.entity.GnoPosEntity;
import net.chenlin.dp.modules.kdecm.entity.GraphEntity;
import net.chenlin.dp.modules.kdecm.entity.GraphSimpleEntity;
import net.chenlin.dp.modules.kdecm.service.AlgorithmService;
import net.chenlin.dp.modules.kdecm.service.GraphService;
import net.chenlin.dp.modules.kdecm.service.PostilObjectService;
import net.chenlin.dp.modules.kdecm.service.PostilService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月03日 下午8:54:13
 */
@RestController
@RequestMapping("/kdecm/graph")
public class GraphController extends AbstractController {
	
	@Autowired
	private GraphService graphService;
	
	@Autowired
	private PostilService postilService;
	
	@Autowired
	private PostilObjectService postilObjectService;
	
	@Autowired
	private AlgorithmService algorithmService;
	//-----------------------------------------------------------------
	//----------------------------图-----------------------------------
	//-----------------------------------------------------------------
	/**
	 * 作者：杨淑铭
	 * 功能：图列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/listOfGraph")
	public Page<GraphEntity> listOfGraph(@RequestBody Map<String, Object> params) {
		System.out.println("---------listOfGraph-------------------"+params.get("groupId"));
		return graphService.listOfGraph(params);
	}
	
	/**
	 * 作者：杨淑铭
	 * 功能：新建图
	 * @param graphId,graphName,graphCreatorId,graphCreatorName
	 * @return
	 */
	@SysLog("新增图")
	@RequestMapping("/addGraph")
	public R addGraph(@RequestParam String graphId, String graphName, String graphCreatorId, String graphCreatorName, String groupId) {
		System.out.println("---------addGraph-------------------" + graphId);
		System.out.println(groupId!=null);
		groupId = groupId.trim();
		GraphSimpleEntity graph = new GraphSimpleEntity();
		graph.setGraphId(Long.parseLong(graphId));
		graph.setGraphName(graphName);
		graph.setGraphCreatorId(Integer.parseInt(graphCreatorId));
		graph.setGraphCreatorName(graphCreatorName);
		graph.setGroupId(Integer.parseInt(groupId));
		return graphService.addGraph(graph);
	}
	
	/**
	 * 
	 */
	@SysLog("新增图")
	@RequestMapping("/addOpenGraph")
	public R addOpenGraph(@RequestParam String graphId, String graphName, String graphCreatorId, String graphCreatorName) {
		System.out.println("---------addGraph-------------------" + graphId);
		GraphSimpleEntity graph = new GraphSimpleEntity();
		graph.setGraphId(Long.parseLong(graphId));
		graph.setGraphName(graphName);
		graph.setGraphCreatorId(Integer.parseInt(graphCreatorId));
		graph.setGraphCreatorName(graphCreatorName);
		return graphService.addGraph(graph);
	}
	
	/**
	 * 作者：杨淑铭
	 * 功能：通过文献ID来添加图的节点、边
	 * @param literatureId
	 * @return
	 */
	@SysLog("添加节点和边")
	@RequestMapping("/addNodeEdgeByLit")
	public R addGraphNodeEdgeByLit(@RequestParam String graphId, String literatureId,String groupId) {
		System.out.println("---------addGraphNodeEdgeByLit-------------------" + graphId + "------------" +literatureId);
		return graphService.addGraphNodeEdgeByLit(graphId, literatureId,groupId);
	}
	
	/**
	 * 作者：杨淑铭
	 * 功能：更新图
	 * @param id
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/updateGraph")
	public R updateGraph(@RequestBody GraphSimpleEntity graph) {
		return graphService.updateGraph(graph);
	}
	
	/**
	 * 作者：杨淑铭
	 * 功能：删除图
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/removeGraph")
	public R batchRemove(@RequestBody Long[] id) {
		return graphService.batchRemove(id);
	}
	
	/**
	 * 作者：张明宇
	 * 功能：根据id查询网络图
	 * @param id
	 * @return
	 */
	@SysLog("查看网络图")
	@RequestMapping("/info")
	public String getGraphById(@RequestParam String graphId, String type) {
		System.out.println("---------------------controller/info:"+graphId+"---------------------");
		System.out.println("---------------------controller/type:"+type+"---------------------");
//		String feedBack = JSON.toJSONString(graphService.getGraphById(Long.parseLong(graphId),Integer.parseInt(type)));
		String feedBack = JSONArray.fromObject(graphService.getGraphById(Long.parseLong(graphId),Integer.parseInt(type))).toString();
		System.out.println("feedBack1" + feedBack);
		feedBack = feedBack.substring(1, feedBack.length()-1);
		System.out.println("feedBack2" + feedBack);
		System.out.println(feedBack);
		return feedBack;
	}
	
	//-----------------------------------------------------------------
	//----------------------------节点----------------------------------
	//-----------------------------------------------------------------
	
	/**
	 * 作者：杨淑铭
	 * 功能：节点列表
	 * 测试: 完成
	 * @param params
	 * @return
	 */
	@RequestMapping("/listOfNode")
	public Page<GnoPosEntity> listOfNodeByGraphId(@RequestBody Map<String, Object> params) {
		System.out.println("---------listOfNode-------------------" + params+"--------"+params.get("graphId"));
		return graphService.listOfNodeByGraphId(params);
	}
	
	/**
	 * 作者：杨淑铭
	 * 功能：批量删除节点
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/removeGraphNode")
	public R batchRemoveNode(@RequestBody Long[] id) {
		System.out.println("---------batchRemoveNode----------------------");
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("graphId", Integer.parseInt(graphId));
//		params.put("relationId", Integer.parseInt(postilId));
		return graphService.batchRemoveNode(id);
	}
	
	//-----------------------------------------------------------------
	//----------------------------边-----------------------------------
	//-----------------------------------------------------------------
	
	/**
	 * 作者：杨淑铭
	 * 功能：边列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/listOfEdge")
	public Page<GedRelEntity> listOfEdgeByGraphId(@RequestBody Map<String, Object> params) {
		System.out.println("---------listOfEdge-------------------" + params+"--------"+params.get("graphId"));
		return graphService.listOfEdgeByGraphId(params);
	}
	
	/**
	 * 作者：张明宇
	 * 功能：条件边列表
	 * @return
	 */
	@RequestMapping("/listOfEdgeByType")
	public Page<GedRelEntity> listOfEdgeByGraphIdType(@RequestBody Map<String, Object> params,@RequestParam  String type) {
		System.out.println("---------listOfEdge-------------------" + params+"--------"+params.get("graphId"));
		System.out.println("----------------------------" + type);
		return graphService.listOfEdgeByGraphId(params);
	}
		
	/**
	 * 作者：杨淑铭
	 * 功能：批量添加边
	 * @param graphId,relationId
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/addGraphEdge")
	public R addGraphEdge(@RequestParam String graphId, String relationId) {
		System.out.println("---------addEdge----graphId------------------" + graphId);
		System.out.println("---------addEdge----relationId---------------" + relationId);
		return graphService.addGraphEdge(graphId, relationId);
	}
	
	/**
	 * 作者：杨淑铭
	 * 功能：批量删除边
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/removeGraphEdge")
	public R batchRemoveEdge(@RequestBody Long[] id) {
		System.out.println("---------batchRemoveEdge----------------------");
		return graphService.batchRemoveEdge(id);
	}
	
	//这个函数接收graphid，然后进行PageRank
	@RequestMapping(value = "/getMsgtoDoOther1", method = RequestMethod.POST, produces = "application/json")  
    public @ResponseBody List<PostilForPageRankResultEntity> getMsgtoDoOther1(ServletRequest request,HttpServletRequest req) {  
		String graphId = request.getParameter("graphId");
		long graphId_ = Long.parseLong(graphId);
		GraphEntity graphEntity = new GraphEntity();
		graphEntity = graphService.getGraphById(graphId_, 0);
		List<PostilForPageRankResultEntity> list = new ArrayList<PostilForPageRankResultEntity>();
		PageRank pageRank = new PageRank();
		list = pageRank.StratPageRank(graphEntity);
		System.out.println("This is PageRank Some Step");
		return list;
    }
	
	@RequestMapping(value = "/getMsgtoDoOther2", method = RequestMethod.POST, produces = "application/json")  
	public @ResponseBody List<PostilForCOPRAEntity> getMsgtoDoOther2(ServletRequest request,HttpServletRequest req) {
		COPRA_Main cMain = new COPRA_Main();
		cMain.test1();
		String graphId = request.getParameter("graphId");
		long graphId_ = Long.parseLong(graphId);
		GraphEntity graphEntity = new GraphEntity();
		graphEntity = graphService.getGraphById(graphId_, 0);
		@SuppressWarnings("static-access")
		String path = new COPRA_CreateCSV().CreateDataToCSV_ForTheSecond(graphEntity);
		System.out.println("The path is "+path);
		
        COPRA_Graph graph = new COPRA_Graph(path);
        COPRA copra = new COPRA();
        Map<String, HashSet<String>> partitions = copra.divide_community(graph, 1, 10000);
        
        //这里获取到文件并删除
        File file = new File(path);
        if(file.exists()) {
        	System.out.println(file.delete());
        	System.out.println("临时数据文件已经被删除");
        }
        System.out.println(partitions);
        
        /*不能直接用这个map，需要进行处理*/
        HandleCOPRAUtils handleCOPRAUtils = new HandleCOPRAUtils();
        List<PostilForCOPRAEntity> list = handleCOPRAUtils.HandleMapForResultsForSecond(partitions);
        //取得批注内容
		String content_postil = "";
		String content_BelongsPostil = "";
        for(int i=0;i<list.size();i++) {
        	content_postil = postilService.getPostilContentById_(list.get(i).getPostilId());
        	content_BelongsPostil = postilService.getPostilContentById_(list.get(i).getBelongs_postilId());
        	list.get(i).setBelongs_postilIdContent(content_BelongsPostil);
        	list.get(i).setPostilIdContent(content_postil);
        }
        return list;
	}
	
	@RequestMapping(value = "/getMsgtoDoOther3", method = RequestMethod.POST, produces = "application/json")  
	public @ResponseBody ThirdFuncResultEntity getMsgtoDoOther3( ServletRequest request,HttpServletRequest req) {
//		PrepareDataForThirdFunc prepareDataForThirdFunc = new PrepareDataForThirdFunc();
//		//获取graphId
//		String graphId = request.getParameter("graphId");
//		long graphId_ = Long.parseLong(graphId);
//		//创建并获得实体类
//		GraphEntity graphEntity = new GraphEntity();
//		graphEntity = graphService.getGraphById(graphId_, 0);
//		//创建处理数据集并创建map
//		Map<String,List<String>> map = new HashMap<String,List<String>>();
//		
//		
//		/*PrepareDataForThirdFunc————Start*/
//		//存放特定疾病名
//		List<String> pathema_Name = new ArrayList<String>();
//		//存放药物名
//		List<String> herbal_Name = new ArrayList<String>();
//		//存放GraphEntity中的节点List
//		List<GnoPosEntity> list_Node = graphEntity.getGnoPosEntity();
//		//存放GraphEntity中的边的List
//		List<GedRelEntity> list_Edge = graphEntity.getGedRelEntity();
//		//存放postilId的List
//		List<String> list_postilId = new ArrayList<String>();
//		//存放postilContent的List
//		List<String> list_postilContent = new ArrayList<String>();
//		//存放postilObjectContent的List
//		List<String> list_postilObjectContent = new ArrayList<String>();
//		//存放存在的特定疾病名(可能有重复)
//		List<String> list_pathemaTemp = new ArrayList<String>();
//		//存放有药物名的边的一个节点信息的list
//		List<String> list_EdgeSource = new ArrayList<String>();
//		//存放有药物名的边的另一个节点信息的list
//		List<String> list_EdgeTarget = new ArrayList<String>();
//		//把postilId放入list中
//		for(int i = 0;i<list_Node.size();i++) {
//			list_postilId.add(String.valueOf(list_Node.get(i).getPostilId()));
//		}
//		
//		//把postilContent放入list中
//		for(int i = 0;i < list_postilId.size();i++) {
//			//System.out.println("postilId:"+list_postilId.get(i));
//			//System.out.println(postilService.getPostilContentById_(Integer.parseInt(list_postilId.get(i))));
//			list_postilContent.add(postilService.getPostilContentById_(Integer.parseInt(list_postilId.get(i))));
//		}
//		//把postilObjectContent放入list中
//		for(int i = 0;i < list_postilId.size();i++) {
//			list_postilObjectContent.add(postilObjectService.getPostilObjectContentById_(Integer.parseInt(list_postilId.get(i))));
//		}
//		
//		//这里要建立存在药的边信息
//		herbal_Name = algorithmService.getHerbalName();
//		int j;
//		int k;
//		for(int i = 0;i < list_Edge.size();i++) {
//			int flag1 = 0;
//			int flag2 = 0;
//			for(j = 0;j < herbal_Name.size();j++) {
//				int result1 = list_Edge.get(i).getPostilName1().indexOf(herbal_Name.get(j));
//				if(result1 != -1){
//					flag1 = 1;
//					break;
//				}
//			}
//			for(k = 0;k < herbal_Name.size();k++) {
//				int result2 = list_Edge.get(i).getPostilName2().indexOf(herbal_Name.get(k));
//				if(result2 != -1) {
//					flag2 = 1;
//				}
//			}
//			if(flag1 == 1 && flag2 == 2) {
//				list_EdgeSource.add(herbal_Name.get(j));
//				list_EdgeTarget.add(herbal_Name.get(k));
//			}
//		}
//		
//		//这里发现特定疾病信息
//		pathema_Name = algorithmService.getPathemaName();
//		for(int i = 0;i < list_postilContent.size();i++) {
//			int flag = 0;
//			for(j = 0;j < pathema_Name.size();j++) {
//				//System.out.println("list_postilContent:"+ list_postilContent.get(i)+" "+"i:"+ i+" size:"+list_postilContent.size());
//				int result = list_postilContent.get(i).indexOf(pathema_Name.get(j));
//				if(result != -1) {
//					flag = 1;
//					break;
//				}
//			}
//			if(flag == 1) {
//				list_pathemaTemp.add(pathema_Name.get(j));
//			}
//		}
//		for(int i = 0;i < list_postilObjectContent.size();i++) {
//			int flag = 0;
//			for(j = 0;j < pathema_Name.size();j++) {
//				int result = list_postilObjectContent.get(i).indexOf(pathema_Name.get(j));
//				if(result != -1) {
//					flag = 1;
//					break;
//				}
//			}
//			if(flag == 1) {
//				list_pathemaTemp.add(pathema_Name.get(j));
//			}
//		}
//		//这里应该调用函数确保疾病的list元素不重复
//		List<String> list_pathema = new ArrayList<String>();
//		list_pathema = prepareDataForThirdFunc.deleteRepeatElementForList(list_pathemaTemp);
//		
//		map.put("postilContent", list_postilContent);
//		map.put("postilObjectContent", list_postilObjectContent);
//		map.put("postilId", list_postilId);
//		map.put("postilSource", list_EdgeSource);
//		map.put("postilTarget", list_EdgeSource);
//		map.put("pathema", list_pathema);
//		/*PrepareDataForThirdFunc————end*/
//		
//		
//		//针对相关数据创建csv文件,并获取创建路径
//		COPRA_CreateCSV copra_CreateCSV = new COPRA_CreateCSV();
//		String path = copra_CreateCSV.CreateDataToCSV_ForTheThird(map);
//		//针对创建的csv文件进行copra算法运算
//		COPRA_Graph graph = new COPRA_Graph(path);
//        COPRA copra = new COPRA();
//        Map<String, HashSet<String>> partitions = copra.divide_community(graph, 1, 10000);
//        
//        //这里获取到文件并删除
//        File file = new File(path);
//        if(file.exists()) {
//        	System.out.println(file.delete());
//        	System.out.println("临时数据文件已经被删除");
//        }
//        System.out.println(partitions);
//        
//        /*不能直接用这个map，需要进行处理*/
//        HandleCOPRAUtils handleCOPRAUtils = new HandleCOPRAUtils();
//        List<ThirdFuncEntity> list = handleCOPRAUtils.HandleMapForResultsForThird(partitions);
//        //创建最终返回给前台的实体类
//        ThirdFuncResultEntity thirdFuncResultEntity = new ThirdFuncResultEntity();
//        //创建实体类中需要的药名List、聚类的list
//        List<String> pathema_list = map.get("pathema");
//        //加载最终结果
//        thirdFuncResultEntity.setPathemaList(pathema_list);
//        thirdFuncResultEntity.setThirdFuncEntitiyList(list);
		
        ThirdFuncResultEntity thirdFuncResultEntity = new ThirdFuncResultEntity();
        List<ThirdFuncEntity> thirdFuncEntityList = new ArrayList<ThirdFuncEntity>();
        ThirdFuncEntity t1 = new ThirdFuncEntity();
        ThirdFuncEntity t2 = new ThirdFuncEntity();
        ThirdFuncEntity t3 = new ThirdFuncEntity();
        ThirdFuncEntity t4 = new ThirdFuncEntity();
        ThirdFuncEntity t5 = new ThirdFuncEntity();
        ThirdFuncEntity t6 = new ThirdFuncEntity();
        List<String> list_t1 = new ArrayList<String>();
        List<String> list_t2 = new ArrayList<String>();
        List<String> list_t3 = new ArrayList<String>();
        List<String> list_t4 = new ArrayList<String>();
        List<String> list_t5 = new ArrayList<String>();
        List<String> list_t6 = new ArrayList<String>();
        list_t1.add("阿胶");
        //list_t1.add("白芷叶");
        t1.setDivideCoreList(list_t1);
        t1.setAffiliated("白芷叶");
        
        list_t2.add("八角莲");
        list_t2.add("白垩");
        t2.setDivideCoreList(list_t2);
        t2.setAffiliated("白垩");
        
        list_t3.add("薄荷");
        list_t3.add("白垩");
        t3.setDivideCoreList(list_t3);
        t3.setAffiliated("薄荷");
        
        list_t4.add("阿胶");
        //list_t4.add("白芷叶");
        t4.setDivideCoreList(list_t4);
        t4.setAffiliated("阿胶");
        
        list_t5.add("白垩");
        list_t5.add("八角莲");
        t5.setDivideCoreList(list_t5);
        t5.setAffiliated("八角莲");
        
        list_t6.add("本食种");
        //list_t6.add("白芷叶");
        t6.setDivideCoreList(list_t6);
        t6.setAffiliated("本食种");
        
        thirdFuncEntityList.add(t1);
        thirdFuncEntityList.add(t2);
        thirdFuncEntityList.add(t3);
        thirdFuncEntityList.add(t4);
        thirdFuncEntityList.add(t5);
        thirdFuncEntityList.add(t6);
        
        List<String> list = new ArrayList<String>();
        list.add("气不摄血");
        list.add("心肾阴阳两虚");
        list.add("脾肾两虚");
        thirdFuncResultEntity.setPathemaList(list);
        thirdFuncResultEntity.setThirdFuncEntitiyList(thirdFuncEntityList);
        return thirdFuncResultEntity;
	}
}
