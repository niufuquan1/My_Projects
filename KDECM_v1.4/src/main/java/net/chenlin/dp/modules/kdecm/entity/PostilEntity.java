package net.chenlin.dp.modules.kdecm.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年6月20日 下午8:47:31
 */
public class PostilEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer postilId;
	
	/**
	 * 
	 */
	private Long userId;
	
	/**
	 * 
	 */
	private Integer postilObjectId;
	
	/**
	 * 
	 */
	private Date postilTime;
	
	/**
	 * 
	 */
	private String postilContent;
	
	/**
	 * 
	 */
	private Integer postilType;
	
	/**
	 * 
	 */
	private Integer postilOpenness;
	
	/**
	 * 
	 */
	private Integer groupId;
	
	/**
	 * 
	 */
	private String postilForid;
	

	public PostilEntity() {
		super();
	}

	public void setPostilId(Integer postilId) {
		this.postilId = postilId;
	}
	
	public Integer getPostilId() {
		return postilId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setPostilObjectId(Integer postilObjectId) {
		this.postilObjectId = postilObjectId;
	}
	
	public Integer getPostilObjectId() {
		return postilObjectId;
	}
	
	public void setPostilTime(Date postilTime) {
		this.postilTime = postilTime;
	}
	
	public Date getPostilTime() {
		return postilTime;
	}
	
	public void setPostilContent(String postilContent) {
		this.postilContent = postilContent;
	}
	
	public String getPostilContent() {
		return postilContent;
	}
	
	public void setPostilType(Integer postilType) {
		this.postilType = postilType;
	}
	
	public Integer getPostilType() {
		return postilType;
	}
	
	public void setPostilOpenness(Integer postilOpenness) {
		this.postilOpenness = postilOpenness;
	}
	
	public Integer getPostilOpenness() {
		return postilOpenness;
	}
	
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	public Integer getGroupId() {
		return groupId;
	}
	
	public void setPostilForid(String postilForid) {
		this.postilForid = postilForid;
	}
	
	public String getPostilForid() {
		return postilForid;
	}
	
}
