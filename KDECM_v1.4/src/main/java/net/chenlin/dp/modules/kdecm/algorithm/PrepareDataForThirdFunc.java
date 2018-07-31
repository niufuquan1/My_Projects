package net.chenlin.dp.modules.kdecm.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import net.chenlin.dp.modules.kdecm.entity.GedRelEntity;
import net.chenlin.dp.modules.kdecm.entity.GnoPosEntity;
import net.chenlin.dp.modules.kdecm.entity.GraphEntity;
import net.chenlin.dp.modules.kdecm.service.AlgorithmService;
import net.chenlin.dp.modules.kdecm.service.PostilObjectService;
import net.chenlin.dp.modules.kdecm.service.PostilService;

public class PrepareDataForThirdFunc {
	@Autowired
	private PostilObjectService postilObjectService;
	@Autowired
	private PostilService postilService;
	@Autowired
	private AlgorithmService algorithmService;
	//该函数为准备初始数据集的函数
	/*首先，获取每一个节点的批注id，然后通过批注id获取批注内容与批注对象的内容
	 * 然后封装到一个map中
	 * 说明一下这个map：这个map将会有6个键值，第一个是postilContent，第二个是postilObjectContent
	 * 第三个是postilId,第四个是postilSource,边的一个点的信息,第五个是postilTarget,边的另一个点的信息,
	 * 第六个是pathema,该批注或批注内容中涉及到存在的疾病的信息,这6个键对应的值都为一个List*/
	public Map<String, List<String>> PrepareData(GraphEntity gEntity){
		//存放特定疾病名
		List<String> pathema_Name = new ArrayList<String>();
		//存放药物名
		List<String> herbal_Name = new ArrayList<String>();
		//存放GraphEntity中的节点List
		List<GnoPosEntity> list_Node = gEntity.getGnoPosEntity();
		//存放GraphEntity中的边的List
		List<GedRelEntity> list_Edge = gEntity.getGedRelEntity();
		//存放最终结果的map
		Map<String, List<String>> map = new HashMap<String,List<String>>();
		//存放postilId的List
		List<String> list_postilId = new ArrayList<String>();
		//存放postilContent的List
		List<String> list_postilContent = new ArrayList<String>();
		//存放postilObjectContent的List
		List<String> list_postilObjectContent = new ArrayList<String>();
		//存放存在的特定疾病名(可能有重复)
		List<String> list_pathemaTemp = new ArrayList<String>();
		//存放有药物名的边的一个节点信息的list
		List<String> list_EdgeSource = new ArrayList<String>();
		//存放有药物名的边的另一个节点信息的list
		List<String> list_EdgeTarget = new ArrayList<String>();
		//把postilId放入list中
		for(int i = 0;i<list_Node.size();i++) {
			list_postilId.add(String.valueOf(list_Node.get(i).getPostilId()));
		}
		
		//把postilContent放入list中
		for(int i = 0;i < list_postilId.size();i++) {
			System.out.println("postilId:"+list_postilId.get(i));
			list_postilContent.add(postilService.getPostilContentById_(Integer.parseInt(list_postilId.get(i))));
		}
		//把postilObjectContent放入list中
		for(int i = 0;i < list_postilId.size();i++) {
			list_postilObjectContent.add(postilObjectService.getPostilObjectContentById_(Integer.parseInt(list_postilId.get(i))));
		}
		
		//这里要建立存在药的边信息
		herbal_Name = algorithmService.getHerbalName();
		int j;
		int k;
		for(int i = 0;i < list_Edge.size();i++) {
			int flag1 = 0;
			int flag2 = 0;
			for(j = 0;j < herbal_Name.size();j++) {
				int result1 = list_Edge.get(i).getPostilName1().indexOf(herbal_Name.get(j));
				if(result1 != -1){
					flag1 = 1;
					break;
				}
			}
			for(k = 0;k < herbal_Name.size();k++) {
				int result2 = list_Edge.get(i).getPostilName2().indexOf(herbal_Name.get(k));
				if(result2 != -1) {
					flag2 = 1;
				}
			}
			if(flag1 == 1 && flag2 == 2) {
				list_EdgeSource.add(herbal_Name.get(j));
				list_EdgeTarget.add(herbal_Name.get(k));
			}
		}
		
		//这里发现特定疾病信息
		pathema_Name = algorithmService.getPathemaName();
		for(int i = 0;i < list_postilContent.size();i++) {
			int flag = 0;
			for(j = 0;j < pathema_Name.size();j++) {
				int result = list_postilContent.get(i).indexOf(pathema_Name.get(j));
				if(result != -1) {
					flag = 1;
					break;
				}
			}
			if(flag == 1) {
				list_pathemaTemp.add(pathema_Name.get(j));
			}
		}
		for(int i = 0;i < list_postilObjectContent.size();i++) {
			int flag = 0;
			for(j = 0;j < pathema_Name.size();j++) {
				int result = list_postilObjectContent.get(i).indexOf(pathema_Name.get(j));
				if(result != -1) {
					flag = 1;
					break;
				}
			}
			if(flag == 1) {
				list_pathemaTemp.add(pathema_Name.get(j));
			}
		}
		//这里应该调用函数确保疾病的list元素不重复
		List<String> list_pathema = new ArrayList<String>();
		list_pathema = deleteRepeatElementForList(list_pathemaTemp);
		
		map.put("postilContent", list_postilContent);
		map.put("postilObjectContent", list_postilObjectContent);
		map.put("postilId", list_postilId);
		map.put("postilSource", list_EdgeSource);
		map.put("postilTarget", list_EdgeSource);
		map.put("pathema", list_pathema);
		
		return map;
	}
	
	//去除list中重复String类型元素的函数
	public List<String> deleteRepeatElementForList(List<String> list){
		List<String> list_ = new ArrayList<String>();
		for (String o : list) {
	        if (!list_ .contains(o)) 
	        	list_.add(o);
	    }
		return list_;
	}
	
	public void test() {
		String test = postilService.getPostilContentById_(19);
		System.out.println(test);
	}
}
