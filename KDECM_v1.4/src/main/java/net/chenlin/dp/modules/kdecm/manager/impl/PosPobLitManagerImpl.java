package net.chenlin.dp.modules.kdecm.manager.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.kdecm.dao.PosPobLitMapper;
import net.chenlin.dp.modules.kdecm.entity.Node;
import net.chenlin.dp.modules.kdecm.entity.PosPobLitEntity;
import net.chenlin.dp.modules.kdecm.entity.PosPobLitLevelEntity;
import net.chenlin.dp.modules.kdecm.manager.PosPobLitManager;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午4:54:13
 */
@Component("posPobLitManager")
public class PosPobLitManagerImpl implements PosPobLitManager {

	@Autowired
	private PosPobLitMapper posPobLitMapper;
	

	@Override
	public List<PosPobLitEntity> listPosPobLit(Page<PosPobLitEntity> page, Query search) {
		return posPobLitMapper.listForPage(page, search);
	}

	@Override
	public List<PosPobLitEntity> listPosPobLitByText(Page<PosPobLitEntity> page, Query search) {
		return posPobLitMapper.listPosPobLitForPage(page, search);
	}
	
	@Override
	public List<PosPobLitEntity> listPosPobLitByRelationName(Page<PosPobLitEntity> page, Query search) {
		return posPobLitMapper.listPosPobLitForPage(page, search);
	}
	
	@Override
	public PosPobLitEntity posPobLitById(Query search) {
		return posPobLitMapper.posPobLitById(search);
	}
	
	public List<PosPobLitEntity> listPostilSearch(Page<PosPobLitEntity> page, Query search_postil) {
		return posPobLitMapper.listForPage2(page, search_postil);
	}
	
	public List<PosPobLitEntity> listPostilSearch1(int literatureId) {
		return posPobLitMapper.listForPage1(literatureId);
	}
	
	public List<PosPobLitEntity> listCssSearch(int literatureId) {
		return posPobLitMapper.listForPage3(literatureId);
	}
	
	public List<PosPobLitEntity> listPostilSearch2(String postilContent) {
		return posPobLitMapper.listForPage4(postilContent);
	}
	
	public List<PosPobLitEntity> listForPageuserown(Page<PosPobLitEntity> page, Query search_postil) {
		return posPobLitMapper.listForPageuserown(page, search_postil);
	}
	
	public Node getPostilLevelByLiteratureId(Integer literatureId) {
		String literatureName = "";
		List<PosPobLitEntity> data = posPobLitMapper.listByLiteratureId(literatureId);
		List<PosPobLitLevelEntity> list = new ArrayList<>();
		if (data.size() > 0) {
			literatureName = data.get(0).getLiteratureName();
		}
		int length = data.size();
		for (int i = 0; i < data.size(); i++) {
			PosPobLitEntity posPobLitEntity = data.get(i);
			Integer postilId = posPobLitEntity.getPostilId();
			String postilContent = posPobLitEntity.getPostilContent();
			String postilObjectText = posPobLitEntity.getPostilObjectText(); 
			int rowStart=posPobLitEntity.getPostilObjectRowsStart();
			int rowEnd=posPobLitEntity.getPostilObjectRowEnd();
			int start=posPobLitEntity.getPostilObjectStart();
			int end=data.get(i).getPostilObjectEnd();
//			System.out.println("=================================================================");
//			System.out.println(" rowStart " + rowStart +"   rowEnd " + rowEnd + "   start " + start + "   end " + end);
//			System.out.println("-----------------------------------------------------------------");
			boolean flag = true;
			for (int j = list.size()-1; j >=0; j--) {
				PosPobLitLevelEntity posPobLitLevelEntity = list.get(j);
				int _rowStart=posPobLitLevelEntity.getPostilObjectRowsStart();
				int _rowEnd=posPobLitLevelEntity.getPostilObjectRowEnd();
				int _start=posPobLitLevelEntity.getPostilObjectStart();
				int _end=posPobLitLevelEntity.getPostilObjectEnd();
				if ((rowStart > _rowStart && rowEnd < _rowEnd)||
					(rowStart == _rowStart && rowEnd < _rowEnd && start >= _start)||
					(rowStart > _rowStart && rowEnd == _rowEnd && end <= _end) || 
					(rowStart == _rowStart && rowEnd == _rowEnd && ((start > _start && end < _end)||(start == _start && end < _end)||(start > _start && end == _end)))) {
					//rowStart <= _rowStart && rowEnd >= _rowEnd && start <= _start && end >= _end
					PosPobLitLevelEntity temp = new PosPobLitLevelEntity();
					temp.setParent(j);
					temp.setPostilId(postilId);
					temp.setPostilContent(postilContent);
					temp.setPostilObjectText(postilObjectText);
					temp.setPostilObjectRowsStart(rowStart);
					temp.setPostilObjectRowEnd(rowEnd);
					temp.setPostilObjectStart(start);
					temp.setPostilObjectEnd(end);
					list.add(temp);
					flag = false;
					break;
				}
				
//				System.out.println("_rowStart " + _rowStart +"  _rowEnd " + _rowEnd + "  _start " + _start + "  _end " + _end);
			}
			if (flag) {
				PosPobLitLevelEntity temp = new PosPobLitLevelEntity();
				temp.setParent(-1);
				temp.setPostilId(postilId);
				temp.setPostilContent(postilContent);
				temp.setPostilObjectText(postilObjectText);
				temp.setPostilObjectRowsStart(rowStart);
				temp.setPostilObjectRowEnd(rowEnd);
				temp.setPostilObjectStart(start);
				temp.setPostilObjectEnd(end);
				list.add(temp);
			}
		}
//		System.out.println(JSON.toJSONString(list));
		Node result= new Node();
		result.setId(-1);
		result.setName(literatureName);
		result.setValue("批注层级结构");
		result.setChildren(new ArrayList<>());
		
		for (int i = 0; i < length; i++) {
			PosPobLitLevelEntity temp = list.get(i);
			Node node = new Node();
			node.setParent(temp.getParent());
			node.setId(i);
			node.setPostilId(temp.getPostilId());
			node.setName(temp.getPostilContent());
			node.setValue(temp.getPostilObjectText());
			node.setChildren(new ArrayList<>());
			
			Queue<Node> queue = new LinkedList<>();
			queue.add(result);
			while(!queue.isEmpty()){
				Node n = queue.remove();
				if (n.getId() == node.getParent()) {
					n.getChildren().add(node);
					break;
				}
				else{
					for (int j = 0; j < n.getChildren().size(); j++) {
						queue.add(n.getChildren().get(j));	
					}
				}
			}
		}
//		System.out.println(result);
		return result;
	}
}
