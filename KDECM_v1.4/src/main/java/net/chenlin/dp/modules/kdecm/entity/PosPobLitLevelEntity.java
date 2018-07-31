package net.chenlin.dp.modules.kdecm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月24日 上午10:10:13
 */
public class PosPobLitLevelEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer parent;
	
	private Integer postilId;
	
	private String postilContent;
	
	private String postilObjectText;
	
	private Integer postilObjectRowsStart;
	
	private Integer postilObjectStart;
	
	private Integer postilObjectRowEnd;
	
	private Integer postilObjectEnd;

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Integer getPostilId() {
		return postilId;
	}

	public void setPostilId(Integer postilId) {
		this.postilId = postilId;
	}

	public String getPostilContent() {
		return postilContent;
	}

	public void setPostilContent(String postilContent) {
		this.postilContent = postilContent;
	}

	public String getPostilObjectText() {
		return postilObjectText;
	}

	public void setPostilObjectText(String postilObjectText) {
		this.postilObjectText = postilObjectText;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getPostilObjectRowsStart() {
		return postilObjectRowsStart;
	}

	public void setPostilObjectRowsStart(Integer postilObjectRowsStart) {
		this.postilObjectRowsStart = postilObjectRowsStart;
	}

	public Integer getPostilObjectStart() {
		return postilObjectStart;
	}

	public void setPostilObjectStart(Integer postilObjectStart) {
		this.postilObjectStart = postilObjectStart;
	}

	public Integer getPostilObjectRowEnd() {
		return postilObjectRowEnd;
	}

	public void setPostilObjectRowEnd(Integer postilObjectRowEnd) {
		this.postilObjectRowEnd = postilObjectRowEnd;
	}

	public Integer getPostilObjectEnd() {
		return postilObjectEnd;
	}

	public void setPostilObjectEnd(Integer postilObjectEnd) {
		this.postilObjectEnd = postilObjectEnd;
	}
}
