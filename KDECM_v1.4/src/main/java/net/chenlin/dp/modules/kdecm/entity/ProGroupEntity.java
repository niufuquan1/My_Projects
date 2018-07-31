package net.chenlin.dp.modules.kdecm.entity;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月28日 下午9:27:22
 */
public class ProGroupEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer groupId;
	
	/**
	 * 
	 */
	private String groupName;
	
	/**
	 * 
	 */
	private Long userId;
	
	private int ifopenness;

	public int getIfopenness() {
		return ifopenness;
	}

	public void setIfopenness(int ifopenness) {
		this.ifopenness = ifopenness;
	}

	public ProGroupEntity() {
		super();
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	public Integer getGroupId() {
		return groupId;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
}
