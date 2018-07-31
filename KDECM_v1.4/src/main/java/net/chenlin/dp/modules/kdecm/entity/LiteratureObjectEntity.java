package net.chenlin.dp.modules.kdecm.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月31日 下午4:41:04
 */
public class LiteratureObjectEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer literatureObjectId;
	
	/**
	 * 
	 */
	private Integer literatureId;
	
	/**
	 * 
	 */
	private String literatureObjectText;
	
	/**
	 * 
	 */
	private Integer literatureObjectType;
	
	/**
	 * 
	 */
	private Integer literatureObjectRow;
	

	public LiteratureObjectEntity() {
		super();
	}

	public void setLiteratureObjectId(Integer literatureObjectId) {
		this.literatureObjectId = literatureObjectId;
	}
	
	public Integer getLiteratureObjectId() {
		return literatureObjectId;
	}
	
	public void setLiteratureId(Integer literatureId) {
		this.literatureId = literatureId;
	}
	
	public Integer getLiteratureId() {
		return literatureId;
	}
	
	public void setLiteratureObjectText(String literatureObjectText) {
		this.literatureObjectText = literatureObjectText;
	}
	
	public String getLiteratureObjectText() {
		return literatureObjectText;
	}
	
	public void setLiteratureObjectType(Integer literatureObjectType) {
		this.literatureObjectType = literatureObjectType;
	}
	
	public Integer getLiteratureObjectType() {
		return literatureObjectType;
	}
	
	public void setLiteratureObjectRow(Integer literatureObjectRow) {
		this.literatureObjectRow = literatureObjectRow;
	}
	
	public Integer getLiteratureObjectRow() {
		return literatureObjectRow;
	}
	
}
