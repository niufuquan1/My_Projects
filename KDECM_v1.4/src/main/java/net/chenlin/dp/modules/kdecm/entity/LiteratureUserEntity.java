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
public class LiteratureUserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer literatureId;
	private String literatureName;
	private String literaturePath;
	private Long userId;
	private String username;
	private Date literatureTime;

	public LiteratureUserEntity() {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
