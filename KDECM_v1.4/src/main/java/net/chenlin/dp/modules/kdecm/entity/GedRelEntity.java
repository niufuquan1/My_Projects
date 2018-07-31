package net.chenlin.dp.modules.kdecm.entity;

import java.io.Serializable;

/**
 * 批注网络图-边
 *
 * @author YangShuming
 * @email 593019072@qq.com
 * @date 2018年6月11日
 */
public class GedRelEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long graphEdgeId;
	private Long graphId;
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

	public GedRelEntity() {
		super();
	}

	public void setGraphId(Long graphId) {
		this.graphId = graphId;
	}
	
	public Long getGraphId() {
		return graphId;
	}
	
	public Long getGraphEdgeId() {
		return graphEdgeId;
	}

	public void setGraphEdgeId(Long graphEdgeId) {
		this.graphEdgeId = graphEdgeId;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public Integer getRelationType() {
		return relationType;
	}

	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}

	public String getRelationTypeName() {
		return relationTypeName;
	}

	public void setRelationTypeName(String relationTypeName) {
		this.relationTypeName = relationTypeName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getPostilId1() {
		return postilId1;
	}

	public void setPostilId1(Integer postilId1) {
		this.postilId1 = postilId1;
	}

	public String getPostilName1() {
		return postilName1;
	}

	public void setPostilName1(String postilName1) {
		this.postilName1 = postilName1;
	}

	public Integer getLiteratureId1() {
		return literatureId1;
	}

	public void setLiteratureId1(Integer literatureId1) {
		this.literatureId1 = literatureId1;
	}

	public String getLiteratureName1() {
		return literatureName1;
	}

	public void setLiteratureName1(String literatureName1) {
		this.literatureName1 = literatureName1;
	}

	public Integer getPostilId2() {
		return postilId2;
	}

	public void setPostilId2(Integer postilId2) {
		this.postilId2 = postilId2;
	}

	public String getPostilName2() {
		return postilName2;
	}

	public void setPostilName2(String postilName2) {
		this.postilName2 = postilName2;
	}

	public Integer getLiteratureId2() {
		return literatureId2;
	}

	public void setLiteratureId2(Integer literatureId2) {
		this.literatureId2 = literatureId2;
	}

	public String getLiteratureName2() {
		return literatureName2;
	}

	public void setLiteratureName2(String literatureName2) {
		this.literatureName2 = literatureName2;
	}
	
}
