package net.chenlin.dp.modules.kdecm.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.kdecm.entity.GedRelEntity;
import net.chenlin.dp.modules.kdecm.entity.GnoPosEntity;
import net.chenlin.dp.modules.kdecm.entity.GraphEntity;
import net.chenlin.dp.modules.kdecm.entity.GraphSimpleEntity;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月03日 下午8:54:13
 */
public interface GraphService {

	Page<GraphEntity> listOfGraph(Map<String, Object> params);
	
	Page<GnoPosEntity> listOfNodeByGraphId(Map<String, Object> params);
	
	Page<GedRelEntity> listOfEdgeByGraphId(Map<String, Object> params);
	
	GraphEntity getGraphById(Long id, Integer type);
	
	R addGraph(GraphSimpleEntity graph);
	
	R addGraphEdge(String graphId, String relationId);
	
	R addGraphNodeEdgeByLit(String graphId, String literatureId,String groupId);
	
	R updateGraph(GraphSimpleEntity graph);
	
	R batchRemove(Long[] id);
	
	R batchRemoveNode(Long[] id);
	
	R batchRemoveEdge(Long[] id);
}
