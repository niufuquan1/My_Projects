package net.chenlin.dp.modules.kdecm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.sf.json.JSONObject;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.UploadFile;
import net.chenlin.dp.modules.kdecm.entity.LiteratureEntity;
import net.chenlin.dp.modules.kdecm.entity.LiteratureObjectEntity;
import net.chenlin.dp.modules.kdecm.service.LiteratureObjectService;
import net.chenlin.dp.modules.kdecm.service.LiteratureService;

/**
 * 
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2018年5月27日 下午8:11:20
 */
@RestController
@RequestMapping("/kdecm/literature")
public class LiteratureController extends AbstractController {
	
	@Autowired
	private LiteratureService literatureService;
	@Autowired
	private LiteratureObjectService literatureObjectService;
	private static String Project_Base_Path = System.getProperty("user.dir");//获取当前工程路径 
	private String User_ID = null;
	private String LiteratureName = null;
	private String LiteratureAge = null;
	private String LiteratureKeyword = null;
	private String LiteratureAuthor = null;
	private String LiteratureReference = null;
	private static Integer LiteratureID = null;
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<LiteratureEntity> list(@RequestBody Map<String, Object> params) {
		return literatureService.listLiterature(params);
	}
	
	/**
	 * 显示文献
	 * @param params
	 * @return
	 */
	@RequestMapping("/literatureShow")
	public Page<LiteratureEntity> literatureShow(@RequestBody Map<String, Object> params) {
		System.out.println("params===="+params);
		return literatureService.literatureShow(params);
	}
	
	
	/**
	 * 组文献内容
	 * @param params
	 * @return
	 */
	@RequestMapping("/groupliterature")
	public List<LiteratureEntity> group_literature() {
		List<LiteratureEntity> literatureList = new ArrayList<>();
		literatureList = literatureService.literature_search_group();
		return literatureList;
	}
	
	
	/**
	 * 关键词内容
	 * @param params
	 * @return
	 */
	@RequestMapping("/mainWordShow")
	public List<String> mainWordShow(@RequestParam(value="literatureId",required=false) int literatureId) {
		List<String> mainWord = new ArrayList<>();
		mainWord = literatureService.search_mainword(literatureId);
		return mainWord;
	}
	
	/**
	 * 关键词内容匹配
	 * @param params
	 * @return
	 */
	@RequestMapping("/mainWordCompare")
	public List<String> mainWordCompare(@RequestParam(value="str1",required=false) String str1,
			@RequestParam(value="str2",required=false) String str2,
			@RequestParam(value="literatureId",required=false) int literatureId) {
		List<String> mainWordCompare = new ArrayList<>();
		mainWordCompare = literatureService.compare_mainword(str1,str2,literatureId);
		return mainWordCompare;
	}
	
	
	/**
	 * 作者内容
	 * @param params
	 * @return
	 */
	@RequestMapping("/authorShow")
	public List<String> authorShow(@RequestParam(value="literatureId",required=false) int literatureId) {
		List<String> author = new ArrayList<>();
		author = literatureService.search_author(literatureId);
		return author;
	}
	
	/**
	 * 作者匹配
	 * @param params
	 * @return
	 */
	@RequestMapping("/authorCompare")
	public List<String> authorCompare(@RequestParam(value="str1",required=false) String str1,
			@RequestParam(value="str2",required=false) String str2,
			@RequestParam(value="literatureId",required=false) int literatureId) {
		List<String> authorCompare = new ArrayList<>();
		authorCompare = literatureService.compare_author(str1,str2,literatureId);
		return authorCompare;
	}
	
	
	/**
	 * 获取文献名称
	 * @param params
	 * @return
	 */
	@RequestMapping("/getName")
	public List getName() {
		List<LiteratureEntity> result = new ArrayList<>();
		result = literatureService.getName();
		return result;
	}
	
	/**
	 * 新增
	 * @param literature
	 * @return
	 */

	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return literatureService.getLiteratureById(id);
	}
	
	/**
	 * 修改
	 * @param literature
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody LiteratureEntity literature) {
		return literatureService.updateLiterature(literature);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return literatureService.batchRemove(id);
	}
	
	@RequestMapping(value = "/uploadLiterature", method = RequestMethod.POST, produces = "application/json")  
    public  String UploadLitreature(@RequestParam(value = "Literaturefile", required = false)MultipartFile file,
            ServletRequest request,HttpServletRequest req) {  
       System.out.println(file);
        //创建文献对象实例
		LiteratureEntity literatureEntity = new LiteratureEntity();
		//设置文献名
		literatureEntity.setLiteratureName(LiteratureName);
		//设置文献上传时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currenttime = new Date();
		String time = sdf.format(currenttime);
		Date time_;
		try {
			time_ = sdf.parse(time);
			literatureEntity.setLiteratureTime(time_);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//设置上传用户ID
		literatureEntity.setUserId(Long.valueOf(User_ID));
		//对文献进行上传工作
		UploadFile uploadFile = new UploadFile();
		String save_path = Project_Base_Path + "\\LiteratureUploadFile";
		System.out.println(save_path);
		literatureEntity.setLiteraturePath(save_path+"\\"+file.getOriginalFilename());
		String save_path_ = uploadFile.SaveLiterature(file,save_path);
        //对文献数据进行数据持久化操作
		LiteratureID = literatureService.MaxLiteratureID();//获取文献ID
		LiteratureID++;
		literatureEntity.setLiteratureId(LiteratureID);
		literatureEntity.setLiteratureAge(Integer.parseInt(LiteratureAge));
		literatureEntity.setLiteratureAuthor(LiteratureAuthor);
		literatureEntity.setLiteratureReference(LiteratureReference);
		String [] listarray = LiteratureKeyword.split("、");
	
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("literatureId", LiteratureID);
		for(int i=0;i<listarray.length;i++) {
			params1.put("literatureKeyword", listarray[i]);
			Query query1 = new Query(params1);
			literatureService.addLiteratureKeyword(query1);
		}
        int flag2 = literatureService.saveLiterature(literatureEntity);
        /*这块创建返回的json数据对象,根据函数返回情况给前台相关状态代码，1为成功，-1为失败*/
        if(flag2>0) {
        	JSONObject object=new JSONObject();
            object.accumulate("StatusCode", "1");
            object.accumulate("filePath", save_path_);
        	return object.toString();
        }else {
        	JSONObject object=new JSONObject();
            object.accumulate("StatusCode", "-1");
        	return object.toString();
        }    
	}
	
	@RequestMapping(value = "/sendOtherMsg", method = RequestMethod.POST, produces = "application/json")  
    public @ResponseBody Integer sendOtherMsg(ServletRequest request,HttpServletRequest req) {  
		LiteratureName = request.getParameter("name");
		User_ID = request.getParameter("userid");
		LiteratureAge = request.getParameter("age");
		LiteratureKeyword = request.getParameter("keyword");
		LiteratureAuthor = request.getParameter("author");
		LiteratureReference = request.getParameter("reference");
        return 1;
        }
	//本来该类应该放在文献对象控制类中，但是由于该类可以获取到文献id和文献名称，故放在该类可以接受
	@RequestMapping(value = "/AnalysisFile", method = RequestMethod.POST, produces = "application/json")  
    public @ResponseBody void AnalysisFile(ServletRequest request,HttpServletRequest req) {  
		String literaturePath = request.getParameter("literaturePath");
		System.out.println("文献路径为："+literaturePath);
		System.out.println("文献名称为："+LiteratureName);
		System.out.println("文献ID为："+LiteratureID);
		literatureObjectService.AnalysisWord(literaturePath,LiteratureID,LiteratureName);
        }
	
}
