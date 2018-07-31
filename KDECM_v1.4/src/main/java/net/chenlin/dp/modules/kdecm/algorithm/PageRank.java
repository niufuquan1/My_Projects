package net.chenlin.dp.modules.kdecm.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.chenlin.dp.modules.kdecm.entity.GraphEntity;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
/*http://duyunfei.iteye.com/blog/1532798/   相关算法介绍在这里*/
public class PageRank {
	public static double MAX = 0.00000000001;  
	  
    /* 阻尼系数 */  
    public static double alpha = 0.85; 
    
    public static double theta = 1.5;
  
    public static double[] init;  
  
    public static double[] pr;  
    
    public List<PostilForPageRankEntity> list = new ArrayList<PostilForPageRankEntity>();
    
    public List<PostilForPageRankResultEntity> rlist = new ArrayList<PostilForPageRankResultEntity>();
    
    
    /*用一个json对象存入数据对象*/
	//public static JSONArray JsonResult = new JSONArray();
  
    /* pagerank步骤 */  
  
    /** 
     * 加载文件夹下的网页文件，并且初始化pr值(即init数组)，计算每个节点的外链和内链 
     */  
    /*该函数是PageRank算法执行入口*/
    public List<PostilForPageRankResultEntity> StratPageRank(GraphEntity graphEntity) {
    	loadGraphData(graphEntity);
    	pr = doPageRank();  
    	while (!(checkMax())) {  
            System.arraycopy(pr, 0, init, 0, init.length);  
            pr = doPageRank();  
        }
    	
    	for (int i = 0; i < pr.length; i++) {  
            PostilForPageRankEntity pfpre=list.get(i);  
            pfpre.setPr(pr[i]);  
        } 
    	
    	Collections.sort(list,new Comparator(){
            public int compare(Object o1, Object o2) {  
                PostilForPageRankEntity p1=(PostilForPageRankEntity)o1;  
                PostilForPageRankEntity p2=(PostilForPageRankEntity)o2;  
                int em=0;  
                if(p1.getPr()> p2.getPr()){  
                    em=-1;  
                }else{  
                    em=1;  
                }  
                return em;
            }
         });
    	definePostilType();
    	return rlist;
    }
    /*该函数是进行数据处理的函数*/
    public void loadGraphData(GraphEntity graphEntity) {
    	init = new double[graphEntity.getGnoPosEntity().size()];
    	for(int i = 0;i<graphEntity.getGnoPosEntity().size();i++) {
    		int postilId = graphEntity.getGnoPosEntity().get(i).getPostilId();//节点对应批注id
    		//int in = 0;//初始化链入为0
    		//int out = 0;//初始化链出为0
    		PostilForPageRankEntity postilForPageRankEntity = new PostilForPageRankEntity();
    		//JSONObject obj = new JSONObject();//用来存放一个一个对应的批注id以及它的链入、链出array
    		List<Integer> inlink = new ArrayList<Integer>();
    		List<Integer> outlink = new ArrayList<Integer>();
    		//obj.put("postilId", postilId);
    		//从数据库中可以读出，强化关系的type为1，单向关系为2，双向关系为3
    		for(int j=0;j<graphEntity.getGedRelEntity().size();j++) {
    			postilForPageRankEntity.setPostilId(postilId);
    			//如果关系实体中批注一的id与节点id相同的情况
    			if(graphEntity.getGedRelEntity().get(j).getPostilId1() == postilId) {
    				//如果是单项关系，则为链出A——>B
    				if(graphEntity.getGedRelEntity().get(j).getRelationType() == 2) {
    					outlink.add(graphEntity.getGedRelEntity().get(j).getPostilId2());//将外链到的id记录下来
    					//out++;
    				}else if(graphEntity.getGedRelEntity().get(j).getRelationType() == 3) {
    					outlink.add(graphEntity.getGedRelEntity().get(j).getPostilId2());
    					inlink.add(graphEntity.getGedRelEntity().get(j).getPostilId2());
    					//in++;
    					//out++;
    				}
    				  //如果关系实体中批注二的id与节点相同的情况
    			}else if(graphEntity.getGedRelEntity().get(j).getPostilId2() == postilId) {
    				if(graphEntity.getGedRelEntity().get(j).getRelationType() == 2) {
    					inlink.add(graphEntity.getGedRelEntity().get(j).getPostilId1());
    					//in++;
    				}else if(graphEntity.getGedRelEntity().get(j).getRelationType() == 3) {
    					inlink.add(graphEntity.getGedRelEntity().get(j).getPostilId1());
    					outlink.add(graphEntity.getGedRelEntity().get(j).getPostilId1());
    					//in++;
    					//out++;
    				}
    			}else {
    				continue;
    			}
    		}
    		postilForPageRankEntity.setInLinks(inlink);
    		postilForPageRankEntity.setOutLinks(outlink);
    		list.add(postilForPageRankEntity);

    		//obj.put("in", in);//最后计算完毕后将in、out添加到json对象中
    		//obj.put("out", out);
    		//JsonResult.add(i, obj);//将obj添加到json数组中
    		init[i] = 0.0;
    	}
    	
    }
  
    /** 
     * 计算pagerank 
     *  
     * @param init 
     * @param alpho 
     * @return 
     */  
    private double[] doPageRank() {  
        double[] pr = new double[init.length];  
        for (int i = 0; i < init.length; i++) {  
        	PostilForPageRankEntity pfpre0 = list.get(i);
            double temp = 0;   
            for (int j = 0; j < init.length; j++) {  
                PostilForPageRankEntity pfpre = list.get(j);
                // 计算对本页面链接相关总值  
                if (i != j && pfpre.getOutLinks().size() != 0  
                        && pfpre.getOutLinks().contains(pfpre0.getPostilId())/*he0.getInLinks().contains(he.getPath())*/) {  
                    temp = temp + init[j] / pfpre.getOutLinks().size();  
                }   
            }  
            //经典的pr公式  
            pr[i] = alpha + (1 - alpha) * temp;  
        }  
        return pr;  
    }  
  
    /** 
     * 判断前后两次的pr数组之间的差别是否大于我们定义的阀值 假如大于，那么返回false，继续迭代计算pr 
     *  
     * @param pr 
     * @param init 
     * @param max 
     * @return 
     */  
    private static boolean checkMax() {  
        boolean flag = true;  
        for (int i = 0; i < pr.length; i++) {  
            if (Math.abs(pr[i] - init[i]) > MAX) {  
                flag = false;  
                break;  
            }  
        }  
        return flag;  
    } 
    
    private void definePostilType(){
    	List<Integer> list_Core = new ArrayList<Integer>();
        List<Integer> list_Important = new ArrayList<Integer>();
        List<Integer> list_Ordinary =  new ArrayList<Integer>();
        
        PostilForPageRankResultEntity pr_Core = new PostilForPageRankResultEntity();
        PostilForPageRankResultEntity pr_Important = new PostilForPageRankResultEntity();
        PostilForPageRankResultEntity pr_Ordinary = new PostilForPageRankResultEntity();
        
        pr_Core.setType(1);
        pr_Important.setType(2);
        pr_Ordinary.setType(3);
        
    	int i;
    	/*定义只有前总的30%的批注可能成为核心批注，之后总的40%的批注可能进入重要批注，剩余的批注全部为不重要批注*/
    	int flag = 0;
    	double d_value = 0.0;
    	System.out.println("list的大小："+list.size());
    	int coreNum = list.size()*3/10;//前30%的个数
    	System.out.println("可能的coreNum："+coreNum);
    	for(i = 0;i<coreNum-1; i++) {
    		//处理核心批注,满足条件的加入核心批注的list，不满足条件一律放入重要批注list
    		d_value = list.get(i).getPr()-list.get(i+1).getPr();//定义两个pr值的差值
    		if(flag == 1) {
    			list_Important.add(list.get(i).getPostilId());
    			if(i==coreNum-2) {
    				list_Important.add(list.get(i+1).getPostilId());//把最后一个元素考虑到
    			}
    		}else if(Math.abs(d_value)<theta && flag==0) {
    			list_Core.add(list.get(i).getPostilId());
    			if(i == coreNum-2) {
    				list_Core.add(list.get(i+1).getPostilId());
    			}
    		}else {
    			flag = 1;
    			list_Important.add(list.get(i).getPostilId());
    		}
    	}
    	int importantNum = (list.size()-coreNum)*4/10;//重要批注可能的个数
    	flag = 0;
    	for(i = 0;i<importantNum-1;i++) {
    		//处理重要批注，满足条件的加入重要批注list，不满足条件一律放入一般批注list
    		d_value = list.get(coreNum+i).getPr()-list.get(coreNum+i+1).getPr();
    		if(flag == 1) {
    			list_Ordinary.add(list.get(coreNum+i).getPostilId());
    			if(i==coreNum+importantNum-2) {
    				list_Ordinary.add(list.get(coreNum+i+1).getPostilId());
    			}
    		}else if(Math.abs(d_value)<theta && flag == 0) {
    			list_Important.add(list.get(coreNum+i).getPostilId());
    		}else {
    			flag = 1;
    			list_Ordinary.add(list.get(coreNum+i).getPostilId());
    		}
    	}
    	int ordinaryNum = list.size()-coreNum-importantNum;
    	for(i = 0;i<ordinaryNum;i++) {
    		list_Ordinary.add(list.get(i+coreNum+importantNum).getPostilId());
    	}
    	
    	for(int num:list_Core) {
    		System.out.println("list_Core:"+num);
    	}
    	for(int num:list_Important) {
    		System.out.println("list_Important:"+num);
    	}
    	for(int num:list_Ordinary) {
    		System.out.println("list_Ordinary:"+num);
    	}
        
    	pr_Core.setList(list_Core);
    	pr_Important.setList(list_Important);
    	pr_Ordinary.setList(list_Ordinary);
    	
    	rlist.add(pr_Core);
    	rlist.add(pr_Important);
    	rlist.add(pr_Ordinary);
    }

}
