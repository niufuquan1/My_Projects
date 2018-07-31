package net.chenlin.dp.modules.kdecm.manager.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.dao.GraphMapper;
import net.chenlin.dp.modules.kdecm.entity.GedRelEntity;
import net.chenlin.dp.modules.kdecm.entity.GnoPosEntity;
import net.chenlin.dp.modules.kdecm.entity.GraphEntity;
import net.chenlin.dp.modules.kdecm.entity.GraphSimpleEntity;
import net.chenlin.dp.modules.kdecm.manager.GraphManager;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月03日 下午8:54:13
 */
@Component("graphManager")
public class GraphManagerImpl implements GraphManager {

	@Autowired
	private GraphMapper graphMapper;
	
	//列表：网络图
	@Override
	public List<GraphEntity> listOfGraph(Page<GraphEntity> page, Query search) {
		return graphMapper.listOfGraph(page, search);
	}

	//列表：网络节点
	@Override
	public List<GnoPosEntity> listOfNodeByGraphId(Page<GnoPosEntity> page, Query search) {
		return graphMapper.listOfNodeByGraphId(page, search);
	}
	
	//列表：网络边
	@Override
	public List<GedRelEntity> listOfEdgeByGraphId(Page<GedRelEntity> page, Query search) {
		return graphMapper.listOfEdgeByGraphId(page, search);
	}
	
	@Override
	public GraphEntity selectGraphById(Long graphId, Integer type) {
		GraphEntity graph = new GraphEntity();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("graphId", graphId);
		params.put("type", type);
		Query query = new Query(params);
		try {
			switch (type) {
			case 1:
			case 2:
			case 3:
				graph.setGraphSimpleEntity(graphMapper.selectGraph(graphId));
				graph.setGnoPosEntity(graphMapper.listOfNodeByGraphId(graphId));
				graph.setGedRelEntity(graphMapper.listOfEdgeByType(query));
				break;
			default:
				graph.setGraphSimpleEntity(graphMapper.selectGraph(graphId));
				graph.setGnoPosEntity(graphMapper.listOfNodeByGraphId(graphId));
				graph.setGedRelEntity(graphMapper.listOfEdgeByGraphId(graphId));
				break;
			}
//			System.out.println("---------------------"+"manager0:"+gse.toString()+"---------------------");
//			System.out.println("---------------------"+"manager1:"+graph.toString()+"---------------------");
//			ArrayList<GraphNodeEntity> a = graphMapper.selectGraphPostil(graphId);
//			System.out.println("---------------------"+"manager2:"+a.toString()+"---------------------");
//			ArrayList<GraphEdgeEntity> b = graphMapper.selectGraphEdge(graphId);
//			System.out.println("---------------------"+"manager3:"+b.toString()+"---------------------");
		} catch (Exception e) {
			throw e;
		}
		System.out.println("---------------------"+"manager4:"+graph.toString()+"---------------------");
		return graph;
	}
	
	@Override
	public int addGraph(GraphSimpleEntity graph) {
		System.out.println("-----------graph-----------:"+graph);
		int count = graphMapper.addGraph(graph);
		return count;
	}
	
	@Override
	public int addGraphEdge(String graphId, String relationId) {//列表：批量添加边
		int count = -1;//大于0：成功；小于0：失败
		String[] tempId = relationId.split(",");
		for(int i = 0;i<tempId.length;i++){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("graphId", Long.parseLong(graphId));
			params.put("relationId", Long.parseLong(tempId[i]));
			Query query = new Query(params);
			count = graphMapper.addGraphEdge(query);
		}
		return count;
	}

	@Override
	public int addGraphNodeEdgeByLit(String graphId, String literatureId,String groupId) {
		int count = -1;
		String[] tempId = literatureId.split(",");
		for (int i = 0; i < tempId.length; i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("literatureId", Long.parseLong(tempId[i]));
			params.put("groupId", groupId);
			Query query = new Query(params);
			List<Long> postilIds = graphMapper.selectPostilidByLiteratureid(query);
			System.out.println("节点ID" + postilIds.toString());
			for (Long postilId : postilIds) {
				Map<String, Object> params1 = new HashMap<String, Object>();
				params1.put("graphId", Long.parseLong(graphId));
				params1.put("postilId", postilId);
				Query query1 = new Query(params1);
				count = graphMapper.addGraphNode(query1);
			}			
			List<Long> relationIds = graphMapper.selectRelationidByLiteratureid(query);
			System.out.println("边ID" + relationIds.toString());
			for (Long relationId : relationIds) {
				Map<String, Object> params1 = new HashMap<String, Object>();
				params1.put("graphId", Long.parseLong(graphId));
				params1.put("relationId", relationId);
				Query query1 = new Query(params1);
				count = graphMapper.addGraphEdge(query1);
			}		
		}
		return count;
	}
	
	@Override
	public int updateGraph(GraphSimpleEntity graph) {
		int count = graphMapper.updateGraph(graph);
		return count;
	}
	
	@Override
	public int batchRemove(Long[] id) {
		int count = graphMapper.batchRemove(id);
		for (int i = 0; i < id.length; i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("graphId", id[i]);
			Query query = new Query(params);
			graphMapper.removeGraphNode(query);
			graphMapper.removeGraphEdge(query);
		}
		return count;
	}

	@Override
	public int batchRemoveNode(Long[] id) {
		int count = graphMapper.batchRemoveNode(id);
		return count;
	}

	@Override
	public int batchRemoveEdge(Long[] id) {
		int count = graphMapper.batchRemoveEdge(id);
		return count;
	}

	

	

}
