package net.chenlin.dp.modules.kdecm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月31日 上午11:26:10
 */
public class GraphEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private GraphSimpleEntity graphSimpleEntity = null;
	
	private List<GnoPosEntity> gnoPosEntity = null;
	
	private List<GedRelEntity> gedRelEntity = null;

	public GraphSimpleEntity getGraphSimpleEntity() {
		return graphSimpleEntity;
	}

	public void setGraphSimpleEntity(GraphSimpleEntity graphSimpleEntity) {
		this.graphSimpleEntity = graphSimpleEntity;
	}

	public List<GnoPosEntity> getGnoPosEntity() {
		return gnoPosEntity;
	}

	public void setGnoPosEntity(List<GnoPosEntity> gnoPosEntity) {
		this.gnoPosEntity = gnoPosEntity;
	}

	public List<GedRelEntity> getGedRelEntity() {
		return gedRelEntity;
	}

	public void setGedRelEntity(List<GedRelEntity> gedRelEntity) {
		this.gedRelEntity = gedRelEntity;
	}
}
