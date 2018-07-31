package net.chenlin.dp.modules.kdecm.entity;

import java.io.Serializable;


/**
 * 批注关系
 *
 * @author YangShuming
 * @email 593019072@qq.com
 * @date 2018年6月11日
 */
public class PostilRelationEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer relationId;
	private String relationName;
	private Integer relationType;
	private String relationTypeName;
	private Long userId;
	private String userName;
	private Integer postilId1;
	private String postilName1;
	private Integer literatureId1;
	private String literatureName1;
	private Integer postilId2;
	private String postilName2;
	private Integer literatureId2;
	private String literatureName2;
	private Integer groupId;

	public PostilRelationEntity() {
		super();
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	
	public Integer getRelationId() {
		return relationId;
	}
	
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	
	public String getRelationName() {
		return relationName;
	}
	
	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}
	
	public Integer getRelationType() {
		return relationType;
	}
	
	public void setRelationTypeName(String relationTypeName) {
		this.relationTypeName = relationTypeName;
	}
	
	public String getRelationTypeName() {
		return relationTypeName;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setPostilId1(Integer postilId1) {
		this.postilId1 = postilId1;
	}
	
	public Integer getPostilId1() {
		return postilId1;
	}
	
	public void setPostilName1(String postilName1) {
		this.postilName1 = postilName1;
	}
	
	public String getPostilName1() {
		return postilName1;
	}
	
	public void setLiteratureId1(Integer literatureId1) {
		this.literatureId1 = literatureId1;
	}
	
	public Integer getLiteratureId1() {
		return literatureId1;
	}
	
	public void setLiteratureName1(String literatureName1) {
		this.literatureName1 = literatureName1;
	}
	
	public String getLiteratureName1() {
		return literatureName1;
	}
	
	public void setPostilId2(Integer postilId2) {
		this.postilId2 = postilId2;
	}
	
	public Integer getPostilId2() {
		return postilId2;
	}
	
	public void setPostilName2(String postilName2) {
		this.postilName2 = postilName2;
	}
	
	public String getPostilName2() {
		return postilName2;
	}
	
	public void setLiteratureId2(Integer literatureId2) {
		this.literatureId2 = literatureId2;
	}
	
	public Integer getLiteratureId2() {
		return literatureId2;
	}
	
	public void setLiteratureName2(String literatureName2) {
		this.literatureName2 = literatureName2;
	}
	
	public String getLiteratureName2() {
		return literatureName2;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
}
