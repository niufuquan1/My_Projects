package net.chenlin.dp.modules.kdecm.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.entity.GedRelEntity;
import net.chenlin.dp.modules.kdecm.entity.GnoPosEntity;
import net.chenlin.dp.modules.kdecm.entity.GraphEntity;
import net.chenlin.dp.modules.kdecm.entity.GraphSimpleEntity;
import net.chenlin.dp.modules.sys.dao.BaseMapper;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月03日 下午8:54:13
 */
@Mapper
public interface GraphMapper extends BaseMapper<GraphEntity> {
	
	List<GraphEntity> listOfGraph(Page<GraphEntity> page, Query search);
	
	List<GnoPosEntity> listOfNodeByGraphId(Page<GnoPosEntity> page, Query search);
	
	List<GedRelEntity> listOfEdgeByGraphId(Page<GedRelEntity> page, Query search);//获取'边-批注关系'查询数据：测试完成
	
	List<GnoPosEntity> listOfNodeByGraphId(Long id);//读取节点
	
	List<GedRelEntity> listOfEdgeByGraphId(Long id);//读取边
	
	List<GnoPosEntity> listOfNodeByType(Query search);//读取节点
	
	List<GedRelEntity> listOfEdgeByType(Query search);//读取边
	
	List<GedRelEntity> listOfNodeByEdge(Page<GedRelEntity> page,Query search);//张明宇：根据添加的边
	
	GraphSimpleEntity selectGraph (Long graphId);
	
	GnoPosEntity selectGraphNodeBygraphIdpostilId(Query search);//（graphId、relationId）查找边
	
	GedRelEntity selectGraphEdgeBygraphIdrelationId(Query search);//（graphId、relationId）查找边
	
	List<Long> selectPostilidByLiteratureid(Query search);//（graphId、literatureId）查找节点
	
	List<Long> selectRelationidByLiteratureid(Query search);//（graphId、literatureId）查找边
	
	int addGraph(GraphSimpleEntity graph);
	
	int addGraphNode(Query query);//添加节点

	int addGraphEdge(Query query);//添加边
	
//	int addGraphNodeEdgeByLit(Query query);
	
	int updateGraph(GraphSimpleEntity graph);//更新图（名称）
	
	int batchRemoveNode(Long[] id);//批量删除节点
	
	int removeGraphNode(Query query);//删除网络所有节点
	
	int batchRemoveEdge(Long[] id);//批量删除边
	
	int removeGraphEdge(Query query);//删除网络所有边
}
