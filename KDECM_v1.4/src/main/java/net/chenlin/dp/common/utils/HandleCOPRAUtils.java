package net.chenlin.dp.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.TempFile;

import net.chenlin.dp.modules.kdecm.algorithm.PostilForCOPRAEntity;
import net.chenlin.dp.modules.kdecm.algorithm.ThirdFuncEntity;

public class HandleCOPRAUtils {
	public List<PostilForCOPRAEntity> HandleMapForResultsForSecond(Map<String, HashSet<String>> partitions){
		List<PostilForCOPRAEntity> list = new ArrayList<PostilForCOPRAEntity>();
		Iterator iter = partitions.keySet().iterator();
		while(iter.hasNext()) {
			//新建每一个实体类
			PostilForCOPRAEntity pfCopraEntity= new PostilForCOPRAEntity();
			//取其中一个的键值
			String key = (String)iter.next();
			//取得每一个批注id
			int postilId = Integer.parseInt(key.trim());
			System.out.println("The PostilId is: "+ postilId);
			pfCopraEntity.setBelongs_postilId(Integer.parseInt(partitions.get(key).iterator().next().trim()));
			pfCopraEntity.setPostilId(postilId);
			
			//将实体类中的相关属性进行加载
			list.add(pfCopraEntity);
		}
		return list;
	}
	
	public List<ThirdFuncEntity> HandleMapForResultsForThird(Map<String, HashSet<String>> partitions){
		//创建实体类
		List<ThirdFuncEntity> list = new ArrayList<ThirdFuncEntity>();
		//处理map
		Iterator iter = partitions.keySet().iterator();
		while(iter.hasNext()) {
			//创建实体类
			ThirdFuncEntity thirdFuncEntity = new ThirdFuncEntity();
			//取其中一个的键值
			String key = (String)iter.next();
			//加载数据
			thirdFuncEntity.setAffiliated(key);
			String temp = partitions.get(key).toString();
			temp = temp.substring(1, temp.length()-1);
			String [] list_temp = temp.split(",");
			List<String> list_temp2 = new ArrayList<String>();
			for(int i = 0;i<list_temp.length;i++) {
				list_temp2.add(list_temp[i]);
			}
			thirdFuncEntity.setDivideCoreList(list_temp2);
			//放入list中
			list.add(thirdFuncEntity);
		}
		
		return list;
	}
}
