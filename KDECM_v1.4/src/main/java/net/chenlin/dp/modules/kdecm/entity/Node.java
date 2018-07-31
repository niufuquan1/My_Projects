package net.chenlin.dp.modules.kdecm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.javassist.expr.NewArray;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月24日 上午10:10:13
 */
public class Node implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer parent;
	
	private Integer id;
	
	private Integer postilId;
	
	private String name;
	
	private String value;

	private List<Node> children = new ArrayList<>();

	public Node() {
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPostilId() {
		return postilId;
	}

	public void setPostilId(Integer postilId) {
		this.postilId = postilId;
	}
	
}
