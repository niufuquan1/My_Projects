package net.chenlin.dp.modules.kdecm.manager;

import java.util.List;
import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
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
public interface GraphManager {

	List<GraphEntity> listOfGraph(Page<GraphEntity> page, Query search);
	
	List<GnoPosEntity> listOfNodeByGraphId(Page<GnoPosEntity> page, Query search);
	
	List<GedRelEntity> listOfEdgeByGraphId(Page<GedRelEntity> page, Query search);
	
	GraphEntity selectGraphById(Long graphId, Integer type);
	
	int addGraph(GraphSimpleEntity graph);
	
	int addGraphEdge(String graphId, String relationId);
	
	int addGraphNodeEdgeByLit(String graphId, String literatureId,String groupId);
	
	int updateGraph(GraphSimpleEntity graph);
	
	int batchRemove(Long[] id);
	
	int batchRemoveNode(Long[] id);
	
	int batchRemoveEdge(Long[] id);
}
