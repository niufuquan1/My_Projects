package net.chenlin.dp.modules.kdecm.algorithm;

import java.util.ArrayList;
import java.util.List;

public class PostilForPageRankResultEntity {
	private int type;
	private List<Integer> list = new ArrayList<Integer>();
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}
	
	
}
