package net.chenlin.dp.modules.kdecm.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月03日 下午8:53:05
 */
public class GraphSimpleEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long graphId;
	
	private String graphName;
	
	private Integer graphCreatorId;
	
	private String graphCreatorName;
	
	private Integer groupId;

	public Long getGraphId() {
		return graphId;
	}

	public void setGraphId(Long graphId) {
		this.graphId = graphId;
	}

	public String getGraphName() {
		return graphName;
	}

	public void setGraphName(String graphName) {
		this.graphName = graphName;
	}

	public Integer getGraphCreatorId() {
		return graphCreatorId;
	}

	public void setGraphCreatorId(Integer graphCreatorId) {
		this.graphCreatorId = graphCreatorId;
	}

	public String getGraphCreatorName() {
		return graphCreatorName;
	}

	public void setGraphCreatorName(String graphCreatorName) {
		this.graphCreatorName = graphCreatorName;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GraphSimpleEntity [graphId=" + graphId + ", graphName=" + graphName + ", graphCreatorId="
				+ graphCreatorId + ", graphCreatorName=" + graphCreatorName + ", groupId=" + groupId + "]";
	}
	
}
