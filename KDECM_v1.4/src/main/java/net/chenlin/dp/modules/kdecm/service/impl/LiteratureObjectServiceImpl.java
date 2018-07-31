package net.chenlin.dp.modules.kdecm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.AnalysisWordUtil;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.kdecm.entity.LiteratureObjectEntity;
import net.chenlin.dp.modules.kdecm.manager.LiteratureObjectManager;
import net.chenlin.dp.modules.kdecm.service.LiteratureObjectService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月31日 下午4:41:04
 */
@Service("literatureObjectService")
public class LiteratureObjectServiceImpl implements LiteratureObjectService {

	@Autowired
	private LiteratureObjectManager literatureObjectManager;

	@Override
	public Page<LiteratureObjectEntity> listLiteratureObject(Map<String, Object> params) {
		Query query = new Query(params);
		Page<LiteratureObjectEntity> page = new Page<>(query);
		literatureObjectManager.listLiteratureObject(page, query);
		return page;
	}

	@Override
	public List<LiteratureObjectEntity> literatureShow(long userId, int literatureId) {
		List<LiteratureObjectEntity> litObjList = new ArrayList<>();
		litObjList = literatureObjectManager.literatureShow(userId, literatureId);
		return litObjList;
	}
	
	@Override
	public List<LiteratureObjectEntity> literature_search_pub(String literatureObjectText) {
		List<LiteratureObjectEntity> litObjList = new ArrayList<>();
		litObjList = literatureObjectManager.literature_search_pub(literatureObjectText);
		return litObjList;
	}
	
	@Override
	public R saveLiteratureObject(LiteratureObjectEntity role) {
		int count = literatureObjectManager.saveLiteratureObject(role);
		return CommonUtils.msg(count);
	}

	@Override
	public R getLiteratureObjectById(Long id) {
		LiteratureObjectEntity literatureObject = literatureObjectManager.getLiteratureObjectById(id);
		return CommonUtils.msg(literatureObject);
	}

	@Override
	public R updateLiteratureObject(LiteratureObjectEntity literatureObject) {
		int count = literatureObjectManager.updateLiteratureObject(literatureObject);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemove(Long[] id) {
		int count = literatureObjectManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	//该函数为分析文献的函数
	public void AnalysisWord(String literaturePath,int literatureId,String LiteratureName) {
		LiteratureObjectEntity literatureObject = new LiteratureObjectEntity();
		AnalysisWordUtil analysisWordUtil = new AnalysisWordUtil();
		String para[] = analysisWordUtil.ReadWord(literaturePath);
		System.out.println("进入Service了！");
		//首先把书名作为一级标题存入数据库中
		literatureObject.setLiteratureId(literatureId);
		literatureObject.setLiteratureObjectText(LiteratureName);
		literatureObject.setLiteratureObjectType(1);
		literatureObject.setLiteratureObjectRow(1);
		literatureObjectManager.SaveAnalysisResult(literatureObject);
		int flag1 = 0;//0代表没读出目录的字，也没读出第一次绪论的字，1代表读出了目录的字，
					  //2代表读出绪论/前言的字但肯定是目录内容，
					  //3代表读出了绪论/前言两次，说明读到了正文开始的内容！		
		for(int i = 0;i<para.length;i++) {
			String buffer = StringUtils.deleteWhitespace(para[i]);//去除字符串中所有空格
			//首先判断是否读到了目录
			if(buffer.length()== 0) {
				continue;
			}else if(flag1 == 0) {
				if(buffer.contentEquals("目录")) {
					flag1 = 1;
					continue;
				}
			}else if(flag1 == 1) {
				if(buffer.contentEquals("绪论")||buffer.contentEquals("前言")) {
					flag1 = 2;
					continue;
				}
			}else if(flag1 == 2) {
				if(buffer.contentEquals("绪论")||buffer.contentEquals("前言")) {
				//这里判定为已经开始读取正文部分
					literatureObject.setLiteratureObjectText(buffer);
					literatureObject.setLiteratureObjectType(2);
					literatureObject.setLiteratureObjectRow(i+2);
					literatureObjectManager.SaveAnalysisResult(literatureObject);//这里将绪论这个存储为二级标题
					flag1 = 3;
					continue;
				}
			}else if(flag1 == 3) {
				//先判断是否是第一标题,例如：第一章、第二章等,注意这里判断的是小于100章的内容
				if((buffer.startsWith("第",0)&&buffer.startsWith("章",2))||(buffer.startsWith("第",0)&&buffer.startsWith("章",3))) {
					//这里存储第二级标题
					literatureObject.setLiteratureObjectText(buffer);
					literatureObject.setLiteratureObjectType(2);
					literatureObject.setLiteratureObjectRow(i+2);
					literatureObjectManager.SaveAnalysisResult(literatureObject);
					continue;
				}else if((buffer.startsWith("第",0)&&buffer.startsWith("节",2))||(buffer.startsWith("第",0)&&buffer.startsWith("节",3))) {
					//这里判断的是三级标题，注意判断的是小于100节的内容
					literatureObject.setLiteratureObjectText(buffer);
					literatureObject.setLiteratureObjectType(3);
					literatureObject.setLiteratureObjectRow(i+2);
					literatureObjectManager.SaveAnalysisResult(literatureObject);
					continue;
				}else {
					//这里判断的是正文
					literatureObject.setLiteratureObjectText(buffer);
					literatureObject.setLiteratureObjectType(4);
					literatureObject.setLiteratureObjectRow(i+2);
					literatureObjectManager.SaveAnalysisResult(literatureObject);
					continue;
				}
			}
		}
	}
}
