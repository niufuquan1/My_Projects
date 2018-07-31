package net.chenlin.dp.modules.kdecm.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.kdecm.entity.GedRelEntity;
import net.chenlin.dp.modules.kdecm.entity.GnoPosEntity;
import net.chenlin.dp.modules.kdecm.entity.GraphEntity;
import net.chenlin.dp.modules.kdecm.entity.GraphSimpleEntity;
import net.chenlin.dp.modules.kdecm.manager.GraphManager;
import net.chenlin.dp.modules.kdecm.service.GraphService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月03日 下午8:54:13
 */
@Service("graphService")
public class GraphServiceImpl implements GraphService {

	@Autowired
	private GraphManager graphManager;

	@Override
	public Page<GraphEntity> listOfGraph(Map<String, Object> params) {
		Query query = new Query(params);
		Page<GraphEntity> page = new Page<>(query);
		System.out.println(query);
		graphManager.listOfGraph(page, query);
		return page;
	}

	@Override
	public Page<GnoPosEntity> listOfNodeByGraphId(Map<String, Object> params) {
		Query query = new Query(params);
		Page<GnoPosEntity> page = new Page<>(query);
		graphManager.listOfNodeByGraphId(page, query);
		return page;
	}

	@Override
	public Page<GedRelEntity> listOfEdgeByGraphId(Map<String, Object> params) {
		Query query = new Query(params);
		Page<GedRelEntity> page = new Page<>(query);
		graphManager.listOfEdgeByGraphId(page, query);
		return page;
	}
	
	@Override
	public GraphEntity getGraphById(Long id, Integer type) {
		return  graphManager.selectGraphById(id,type);
	}
	
	@Override
	public R addGraph(GraphSimpleEntity graph) {
		int count = graphManager.addGraph(graph);
		return CommonUtils.msg(count);
	}
	
	@Override
	public R addGraphEdge(String graphId, String relationId) {
		int count = graphManager.addGraphEdge(graphId, relationId);
		return CommonUtils.msg(count);
	}
	
	@Override
	public R addGraphNodeEdgeByLit(String graphId, String literatureId,String groupId) {
		int count = graphManager.addGraphNodeEdgeByLit(graphId, literatureId,groupId);
		return CommonUtils.msg(count);
	}
	
	@Override
	public R updateGraph(GraphSimpleEntity graph) {
		int count = graphManager.updateGraph(graph);
		return CommonUtils.msg(count);
	}
	
	@Override
	public R batchRemove(Long[] id) {
		int count = graphManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public R batchRemoveNode(Long[] id) {
		int count = graphManager.batchRemoveNode(id);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemoveEdge(Long[] id) {
		int count = graphManager.batchRemoveEdge(id);
		return CommonUtils.msg(id, count);
	}
}
