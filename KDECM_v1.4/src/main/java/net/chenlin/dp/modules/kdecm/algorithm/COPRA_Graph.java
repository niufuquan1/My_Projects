package net.chenlin.dp.modules.kdecm.algorithm;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
public class COPRA_Graph {
	    /**
	     * 图数据结构：邻接表
	     * */
	    private Map<String, ArrayList<String>> adjT;
	    /**
	     * 节点属性列表，维护节点的id和社区信息
	     * */
	    private Map<String, HashSet<String>> nodeCommunityInfoPast = new HashMap<>();
	    private Map<String, HashSet<String>> nodeCommunityInfoNew = new HashMap<>();

	    public COPRA_Graph(){
	        this.adjT = new HashMap<>();
	    }

	    public COPRA_Graph(String edgePath){
	        this.adjT = new HashMap<String, ArrayList<String>>();
	        try{
	            BufferedReader reader = new BufferedReader(new FileReader(edgePath));
	            String line = null;
	            while((line=reader.readLine())!=null){
	                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
//	                System.out.println(item[1]);

	                if (! this.adjT.containsKey(item[0])){
	                    this.adjT.put(item[0], new ArrayList<>());
	                    this.nodeCommunityInfoNew.put(item[0], new HashSet<>());
	                }
	                if (! this.adjT.containsKey(item[1])){
	                    this.adjT.put(item[1], new ArrayList<>());
	                    this.nodeCommunityInfoNew.put(item[1], new HashSet<>());
	                }

	                if (! this.adjT.get(item[0]).contains(item[1])){
	                    this.adjT.get(item[0]).add(item[1]);
	                }
	                if (! this.adjT.get(item[1]).contains(item[0])){
	                    this.adjT.get(item[1]).add(item[0]);
	                }

	            }
	            reader.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }

	    }

	    /**
	     * 判断节点之间是否有边
	     * */
	    public boolean hasEdge(String v, String w){
	        return this.adjT.get(v).contains(w);
	    }

	    /**
	     * 获取节点的邻居节点
	     * */
	    public ArrayList<String> neighbors(String node){
	        return this.adjT.get(node);
	    }

	    /**
	     * 获取网络中的所有节点
	     * */
	    public Iterable<String> nodes(){
	        return this.adjT.keySet();
	    }

	    /**
	     * 获取所有节点的社区信息
	     * */
	    public Map<String, HashSet<String>> getNodeCommunityInfo() {
	        return this.nodeCommunityInfoPast;
	    }

	    /**
	     * 获取节点的社区信息
	     * */
	    public HashSet<String> getCommnityLabel(String node){
	        return this.nodeCommunityInfoPast.get(node);
	    }

	    /**
	     * 更新节点的社区信息
	     * */
	    public void updateNodeCommunityLabel(String node, String cLabel){
	        this.nodeCommunityInfoNew.get(node).add(cLabel);
	    }

	    /**
	     * 在社区信息一轮更新完成后，将原始的社区信息进行覆盖
	     * */
	    public void coverCommunityInfo(){
	        this.nodeCommunityInfoPast.clear();
	        for (Map.Entry<String, HashSet<String>> entry : this.nodeCommunityInfoNew.entrySet()){
	            nodeCommunityInfoPast.put(entry.getKey(), new HashSet<>(entry.getValue()));
	        }
	        for (Map.Entry<String, HashSet<String>> entry : this.nodeCommunityInfoNew.entrySet()){
	            entry.getValue().clear();
	        }
	    }
}

