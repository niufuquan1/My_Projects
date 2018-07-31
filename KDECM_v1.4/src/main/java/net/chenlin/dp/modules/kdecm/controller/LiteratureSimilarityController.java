package net.chenlin.dp.modules.kdecm.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.utils.Similarity;
import net.chenlin.dp.modules.kdecm.entity.LiteratureObjectEntity;
import net.chenlin.dp.modules.kdecm.entity.LiteratureSimilarityEntity;
import net.chenlin.dp.modules.kdecm.entity.PostilEntity;
import net.chenlin.dp.modules.kdecm.service.LiteratureObjectService;
import net.chenlin.dp.modules.kdecm.service.PostilService;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.sf.json.JSONArray;


@RestController
@RequestMapping("/kdecm/literatureSimilarity")
public class LiteratureSimilarityController extends AbstractController{
	
	@Autowired
	private LiteratureObjectService literatureObjectService;
	
	@Autowired
	private PostilService postilService;
	
	private Similarity similarity = new Similarity();
	

	@RequestMapping(value = "/getLiteratureSimilarity", method = RequestMethod.POST, produces = "application/json")  
    public @ResponseBody List<LiteratureSimilarityEntity> getLiteratureSimilarity( ServletRequest request,HttpServletRequest req) {  
		//获取前端传来的文献id
		String ids = request.getParameter("ids");
		String groupIdStr = request.getParameter("groupId");
		String userIdStr = request.getParameter("userId");
		System.out.println("文献id："+ids+"   "+ids.length());
		System.out.println("groupId："+groupIdStr);
		System.out.println("userId："+userIdStr);
		int groupId = (int) Long.parseLong(groupIdStr);
		long userId= Long.parseLong(userIdStr);
		
		List<LiteratureSimilarityEntity> literatureSimilarityResult = new  ArrayList<>();
		//转为int存储
		List<Integer> idsArr = new ArrayList<Integer> ();
		JSONArray jsonArray = JSONArray.fromObject(ids);
		for(int i=0; i < jsonArray.size(); i++) {
			idsArr.add(jsonArray.getInt(i));
		}	
		List<Integer> literatureId1 = new ArrayList<Integer> ();
		List<Integer> literatureId2 = new ArrayList<Integer> ();
		System.out.println("idsArr.length = "+idsArr.size());
		for(int i=0; i < idsArr.size()-1; i++) {
			for(int j=i+1; j < idsArr.size(); j++) {
				literatureId1.add(idsArr.get(i));
				literatureId2.add(idsArr.get(j));
				//System.out.println(idsArr.get(i)+"  "+idsArr.get(j));
			}
		}
		for(int k=0; k < literatureId1.size(); k++) {
			List<LiteratureObjectEntity> literatureList1 = new ArrayList<>();
			List<LiteratureObjectEntity> literatureList2 = new ArrayList<>();
			literatureList1 = literatureObjectService.literatureShow(userId, literatureId1.get(k));
			literatureList2 = literatureObjectService.literatureShow(userId, literatureId2.get(k));
			String doc1="";
			String doc2="";
			for(int i=0; i < literatureList1.size(); i++) {
				doc1+=literatureList1.get(i).getLiteratureObjectText();
			}
			for(int i=0; i < literatureList2.size(); i++) {
				doc2+=literatureList2.get(i).getLiteratureObjectText();
			}
			//System.out.println(doc1);
			//System.out.println(doc2);
			
	     	List<PostilEntity> postilList1 = new ArrayList<>();
			List<PostilEntity> postilList2 = new ArrayList<>();
			postilList1 = postilService.loanPostil(groupId, literatureId1.get(k).intValue());
			postilList2 = postilService.loanPostil(groupId, literatureId2.get(k).intValue());
			
			String doc1Postil="";
			String doc2Postil="";
			for(int i=0; i < postilList1.size(); i++) {
				doc1Postil += postilList1.get(i).getPostilContent();
			}
			for(int i=0; i < postilList2.size(); i++) {
				doc2Postil += postilList2.get(i).getPostilContent();
			}
			System.out.println("文献1的批注内容："+doc1Postil);
			System.out.println("文献2的批注内容："+doc2Postil);
			
			//计算两篇文献的内容相似度，批注相似度以及文献的相同词汇
			int id1=literatureId1.get(k).intValue();
			int id2=literatureId2.get(k).intValue();
			String literatureSimPer="";
			Set<String> literatureSameWords=null;
			String postilSimPer = "";
			if(doc1!="" && doc2!="") {
				literatureSimPer= similarity.getSimilarityPercent(doc1, doc2);
				literatureSameWords= similarity.getSameWords(doc1, doc2);
				if(doc1Postil!="" && doc2Postil!="") {
					postilSimPer = similarity.getSimilarityPercent(doc1Postil, doc2Postil);
				}
			}
			
			LiteratureSimilarityEntity entity = new LiteratureSimilarityEntity();
			entity.setLiteratureId1(id1);
			entity.setLiteratureId2(id2);
			entity.setLiteratureSimPercent(literatureSimPer);
			entity.setPostilSimPercent(postilSimPer);
			entity.setSameWords(literatureSameWords);
			literatureSimilarityResult.add(entity);
			System.out.println("批注相似度："+postilSimPer);
			System.out.println("文献相似度："+literatureSimPer);
			System.out.println("两篇文献中的相同词汇"+literatureSameWords);
		}
		return literatureSimilarityResult;
    }
	
	/*
	@RequestMapping(value = "/getLiteratureSimilarity_", method = RequestMethod.POST, produces = "application/json")  
    public @ResponseBody List<String> getLiteratureSimilarity_( ServletRequest request,HttpServletRequest req) {  
		//获取前端传来的文献id
		String ids = request.getParameter("ids");
		System.out.println(ids+"   "+ids.length());
		
		List<String> sim= new ArrayList<>();
		//转为int存储
		List<Integer> idsArr = new ArrayList<Integer> ();
		ArrayList<String> arrayuserIdList = new ArrayList<String>();  
		JSONArray jsonArray = JSONArray.fromObject(ids);
		for(int i=0; i < jsonArray.size(); i++) {
			idsArr.add(jsonArray.getInt(i));
		}	
		
		List<Integer> literatureId1 = new ArrayList<Integer> ();
		List<Integer> literatureId2 = new ArrayList<Integer> ();
		System.out.println("idsArr.length = "+idsArr.size());
		for(int i=0; i < idsArr.size()-1; i++) {
			for(int j=i+1; j < idsArr.size(); j++) {
				literatureId1.add(idsArr.get(i));
				literatureId2.add(idsArr.get(j));
				//System.out.println(idsArr.get(i)+"  "+idsArr.get(j));
			}
		}
		for(int k=0; k < literatureId1.size(); k++) {
			List<LiteratureObjectEntity> literatureList1 = new ArrayList<>();
			List<LiteratureObjectEntity> literatureList2 = new ArrayList<>();
			long userId=5;
			literatureList1 = literatureObjectService.literatureShow(userId, literatureId1.get(k));
			literatureList2 = literatureObjectService.literatureShow(userId, literatureId2.get(k));
			//System.out.println(literatureList1.size());
			String doc1="";
			String doc2="";
			for(int i=0; i < literatureList1.size(); i++) {
				doc1+=literatureList1.get(i).getLiteratureObjectText();
			}
			//System.out.println(doc1);
			for(int i=0; i < literatureList2.size(); i++) {
				doc2+=literatureList2.get(i).getLiteratureObjectText();
			}
			//System.out.println(doc2);
			String simPer= similarity.getSimilarityPercent(doc1, doc2);
			sim.add(simPer);
			System.out.println("str="+simPer);
		}
		return sim;
    }
*/
	
}

