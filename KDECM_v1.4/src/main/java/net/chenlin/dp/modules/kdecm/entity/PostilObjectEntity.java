package net.chenlin.dp.modules.kdecm.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月24日 上午11:14:28
 */
public class PostilObjectEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer postilObjectId;
	
	/**
	 * 
	 */
	private String postilObjectText;
	
	/**
	 * 
	 */
	private Integer postilObjectRowsStart;
	
	/**
	 * 
	 */
	private Integer postilObjectStart;
	
	/**
	 * 
	 */
	private Integer postilObjectRowEnd;
	
	/**
	 * 
	 */
	private Integer postilObjectEnd;
	
	/**
	 * 
	 */
	private Integer literatureId;
	
	/**
	 * 
	 */
	private String postilObjectPos;
	

	public PostilObjectEntity() {
		super();
	}

	public void setPostilObjectId(Integer postilObjectId) {
		this.postilObjectId = postilObjectId;
	}
	
	public Integer getPostilObjectId() {
		return postilObjectId;
	}
	
	public void setPostilObjectText(String postilObjectText) {
		this.postilObjectText = postilObjectText;
	}
	
	public String getPostilObjectText() {
		return postilObjectText;
	}
	
	public void setPostilObjectRowsStart(Integer postilObjectRowsStart) {
		this.postilObjectRowsStart = postilObjectRowsStart;
	}
	
	public Integer getPostilObjectRowsStart() {
		return postilObjectRowsStart;
	}
	
	public void setPostilObjectStart(Integer postilObjectStart) {
		this.postilObjectStart = postilObjectStart;
	}
	
	public Integer getPostilObjectStart() {
		return postilObjectStart;
	}
	
	public void setPostilObjectRowEnd(Integer postilObjectRowEnd) {
		this.postilObjectRowEnd = postilObjectRowEnd;
	}
	
	public Integer getPostilObjectRowEnd() {
		return postilObjectRowEnd;
	}
	
	public void setPostilObjectEnd(Integer postilObjectEnd) {
		this.postilObjectEnd = postilObjectEnd;
	}
	
	public Integer getPostilObjectEnd() {
		return postilObjectEnd;
	}
	
	public void setLiteratureId(Integer literatureId) {
		this.literatureId = literatureId;
	}
	
	public Integer getLiteratureId() {
		return literatureId;
	}
	
	public void setPostilObjectPos(String postilObjectPos) {
		this.postilObjectPos = postilObjectPos;
	}
	
	public String getPostilObjectPos() {
		return postilObjectPos;
	}
	
}
