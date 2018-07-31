package net.chenlin.dp.modules.kdecm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月24日 上午10:10:13
 */
public class PosPobLitEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer postilId;
	private Long userId;
	private Integer postilObjectId;
	private Date postilTime;
	private String postilContent;
	private Integer postilType;
	private Integer postilOpenness;
	private String postilObjectText;
	private Integer postilObjectRowsStart;
	private Integer postilObjectStart;
	private Integer postilObjectRowEnd;
	private Integer postilObjectEnd;
	private Integer literatureId;
	private String literatureName;
	private String literaturePath;
	private Date literatureTime;
	private Integer groupId;

	public PosPobLitEntity() {
		super();
	}

	public Integer getPostilId() {
		return postilId;
	}

	public void setPostilId(Integer postilId) {
		this.postilId = postilId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getPostilObjectId() {
		return postilObjectId;
	}

	public void setPostilObjectId(Integer postilObjectId) {
		this.postilObjectId = postilObjectId;
	}

	public Date getPostilTime() {
		return postilTime;
	}

	public void setPostilTime(Date postilTime) {
		this.postilTime = postilTime;
	}

	public String getPostilContent() {
		return postilContent;
	}

	public void setPostilContent(String postilContent) {
		this.postilContent = postilContent;
	}

	public Integer getPostilType() {
		return postilType;
	}

	public void setPostilType(Integer postilType) {
		this.postilType = postilType;
	}

	public Integer getPostilOpenness() {
		return postilOpenness;
	}

	public void setPostilOpenness(Integer postilOpenness) {
		this.postilOpenness = postilOpenness;
	}

	public String getPostilObjectText() {
		return postilObjectText;
	}

	public void setPostilObjectText(String postilObjectText) {
		this.postilObjectText = postilObjectText;
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

	public Integer getLiteratureId() {
		return literatureId;
	}

	public void setLiteratureId(Integer literatureId) {
		this.literatureId = literatureId;
	}

	public String getLiteratureName() {
		return literatureName;
	}

	public void setLiteratureName(String literatureName) {
		this.literatureName = literatureName;
	}

	public String getLiteraturePath() {
		return literaturePath;
	}

	public void setLiteraturePath(String literaturePath) {
		this.literaturePath = literaturePath;
	}

	public Date getLiteratureTime() {
		return literatureTime;
	}

	public void setLiteratureTime(Date literatureTime) {
		this.literatureTime = literatureTime;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	
}
