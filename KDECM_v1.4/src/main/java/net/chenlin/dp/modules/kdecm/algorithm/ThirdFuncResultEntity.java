package net.chenlin.dp.modules.kdecm.algorithm;

import java.util.List;

public class ThirdFuncResultEntity {
	private List<String> pathemaList;//症状名
	private List<ThirdFuncEntity> thirdFuncEntityList;//所得的聚类结果
	
	public List<String> getPathemaList() {
		return pathemaList;
	}
	public void setPathemaList(List<String> pathemaList) {
		this.pathemaList = pathemaList;
	}
	public List<ThirdFuncEntity> getThirdFuncEntityList() {
		return thirdFuncEntityList;
	}
	public void setThirdFuncEntitiyList(List<ThirdFuncEntity> thirdFuncEntityList) {
		this.thirdFuncEntityList = thirdFuncEntityList;
	}
	
	
	
}
