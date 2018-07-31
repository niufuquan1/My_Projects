package net.chenlin.dp.modules.kdecm.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午8:11:20
 */
public class LiteratureEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer literatureId;
	
	/**
	 * 
	 */
	private String literatureName;
	
	/**
	 * 
	 */
	private String literaturePath;
	
	/**
	 * 
	 */
	private Long userId;
	
	/**
	 * 
	 */
	private Date literatureTime;
	
	private int literatureAge;
	
	private String literatureReference;
	
	private String literatureAuthor;
	
	private String literatureKeyword;
	
	

	public String getLiteratureAuthor() {
		return literatureAuthor;
	}

	public void setLiteratureAuthor(String literatureAuthor) {
		this.literatureAuthor = literatureAuthor;
	}

	public String getLiteratureKeyword() {
		return literatureKeyword;
	}

	public void setLiteratureKeyword(String literatureKeyword) {
		this.literatureKeyword = literatureKeyword;
	}

	public int getLiteratureAge() {
		return literatureAge;
	}

	public void setLiteratureAge(int literatureAge) {
		this.literatureAge = literatureAge;
	}

	public String getLiteratureReference() {
		return literatureReference;
	}

	public void setLiteratureReference(String literatureReference) {
		this.literatureReference = literatureReference;
	}

	public LiteratureEntity() {
		super();
	}

	public void setLiteratureId(Integer literatureId) {
		this.literatureId = literatureId;
	}
	
	public Integer getLiteratureId() {
		return literatureId;
	}
	
	public void setLiteratureName(String literatureName) {
		this.literatureName = literatureName;
	}
	
	public String getLiteratureName() {
		return literatureName;
	}
	
	public void setLiteraturePath(String literaturePath) {
		this.literaturePath = literaturePath;
	}
	
	public String getLiteraturePath() {
		return literaturePath;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setLiteratureTime(Date literatureTime) {
		this.literatureTime = literatureTime;
	}
	
	public Date getLiteratureTime() {
		return literatureTime;
	}
	
}
