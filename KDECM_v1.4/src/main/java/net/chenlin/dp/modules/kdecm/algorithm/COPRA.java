package net.chenlin.dp.modules.kdecm.algorithm;
import net.chenlin.dp.modules.kdecm.algorithm.COPRA_Graph;
import java.util.*;
/*基于标签传播的重叠社区发现算法*/
/*首先，应该把有关系的边整理成为csv文件，然后通过算法显示出来相关群，然后用表格显示出*/
/*来源：https://blog.csdn.net/u010658028/article/details/80352437*/
public class COPRA {
	public static List<String> getRandomList(List<String> paramList,int count){
        /**
         * @function: 从list中随机抽取若干不重复元素
         * @param paramList:被抽取list
         * @param count:抽取元素的个数
         * @return: 由抽取元素组成的新list
         * */
        if(paramList.size()<count){
            return paramList;
        }
        Random random=new Random();
        List<Integer> tempList=new ArrayList<Integer>();
        List<String> newList=new ArrayList<>();
        int temp=0;
        for(int i=0;i<count;i++){
            temp=random.nextInt(paramList.size());//将产生的随机数作为被抽list的索引
            if(!tempList.contains(temp)){
                tempList.add(temp);
                newList.add(paramList.get(temp));
            }
            else{
                i--;
            }
        }
        return newList;
    }

    public Map<String, HashSet<String>> divide_community(COPRA_Graph graph, int v, int maxIterations){
        /**
         * @function: 使用COPRA算法划分社区
         * @graph ：图
         * @v ：一个节点可以属于的最大社区数
         * @maxIteration ：最大迭代次数
         * */

        /**
         * 初始，为每一个节点附上唯一的社区编号
         * */
    	//System.out.println("进入divide");
        Iterable nodes = graph.nodes();
        for (Object id : nodes){
            graph.updateNodeCommunityLabel((String)id, (String)id);
        }
        graph.coverCommunityInfo();

        Random random = new Random();
        /**
         * 更新节点社区信息
         * */
        int interations = 0;
        Map<String, Integer> communitySizePast = new HashMap<>();
        Map<String, Integer> communitySizeNow = new HashMap<>();
        Integer flag = 0;
        while (interations < maxIterations){
            for (Object id : nodes){
                /**
                 * 统计节点的邻居节点的社区分布
                 * */
                Map<String, Integer> labels_freq = new HashMap<>();
                ArrayList<String> neighbors = graph.neighbors((String)id);
                for (String n : neighbors){
                    HashSet<String> n_labels = graph.getCommnityLabel(n);
                    for (String label : n_labels){
                        if (labels_freq.keySet().contains(label)){
                            labels_freq.put(label, labels_freq.get(label) + 1);
                        }else{
                            labels_freq.put(label, 1);
                        }
                    }
                }

                int temp_count = 0;
                List<String> label_list = new ArrayList<>();
                List<String> label_list_add = new ArrayList<>();
                /**
                 * 计算节点与社区的隶属度
                 * 节点将被分配隶属度大于阈值的社区标签
                 * */
                for (Map.Entry<String, Integer> entry : labels_freq.entrySet()){

                    if (entry.getValue() / (float)neighbors.size() >= 1 / (float)v) { //
                        temp_count += 1;
                        label_list.add(entry.getKey());
//                        graph.updateNodeCommunityLabel((String)id, entry.getKey());
                    }
                }
                // 隶属度大于阈值的社区数量超过v，则随机选取v个隶属度大于阈值的社区
                if (temp_count >= v){
                    label_list_add = getRandomList(label_list, v);
                    for (String l : label_list_add){
                        graph.updateNodeCommunityLabel((String)id, l);
                    }
                //隶属度大于阈值的社区数量不超过v，则选取所有的隶属度大于阈值的社区
                }else if (temp_count > 0){
                    for (String l : label_list){
                        graph.updateNodeCommunityLabel((String)id, l);
                    }
                }
                //节点对于每一个社区的隶属度都低于阈值，随机选择一个社区
                if (temp_count == 0){
                    int maxNum = labels_freq.keySet().size();
                    int index = random.nextInt(maxNum)%(maxNum+1);
                    List<String> labels_list = new ArrayList<>(labels_freq.keySet());
                    graph.updateNodeCommunityLabel((String)id, labels_list.get(index));
                }
            }
            graph.coverCommunityInfo();
            interations += 1;

            /**
             * 前后两次社区的数量不变，则停止算法
             * */
            Map<String, HashSet<String>> partitions = graph.getNodeCommunityInfo();
            for (Map.Entry<String, HashSet<String>> entry : partitions.entrySet()){
                for (String label : entry.getValue()){
                    if (communitySizeNow.containsKey(label)){
                        communitySizeNow.put(label, communitySizeNow.get(label) + 1);
                    }else{
                        communitySizeNow.put(label, 1);
                    }
                }
            }

            Integer community_num_now = communitySizeNow.keySet().size();
            Integer community_num_past = communitySizePast.keySet().size();
            if (community_num_now.equals(community_num_past)){
                flag = 1;
            }

            // 社区数量不变，停止迭代
            if (flag.equals(1)){
                interations = maxIterations;
            }
            // 更新过去的社区信息，清空当前的社区信息
            communitySizePast.clear();
            for (Map.Entry<String, Integer> entry : communitySizeNow.entrySet()){
                communitySizePast.put(entry.getKey(), new Integer(entry.getValue()));
            }
            communitySizeNow.clear();
        }

        return graph.getNodeCommunityInfo();
    }
}
