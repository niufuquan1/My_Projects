package net.chenlin.dp.modules.kdecm.algorithm;

public class PostilForCOPRAEntity {
	private Integer postilId;
	private Integer belongs_postilId;
	private String postilIdContent;
	private String belongs_postilIdContent;
	
	
	public String getPostilIdContent() {
		return postilIdContent;
	}
	public void setPostilIdContent(String postilIdContent) {
		this.postilIdContent = postilIdContent;
	}
	public String getBelongs_postilIdContent() {
		return belongs_postilIdContent;
	}
	public void setBelongs_postilIdContent(String belongs_postilIdContent) {
		this.belongs_postilIdContent = belongs_postilIdContent;
	}
	public Integer getPostilId() {
		return postilId;
	}
	public void setPostilId(Integer postilId) {
		this.postilId = postilId;
	}
	public Integer getBelongs_postilId() {
		return belongs_postilId;
	}
	public void setBelongs_postilId(Integer belongs_postilId) {
		this.belongs_postilId = belongs_postilId;
	}
	
}
