package net.chenlin.dp.modules.kdecm.algorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class COPRA_Main {
	public static void main(String[] args)  {
        test1();
    }
	
	public static void test() {
		COPRA_Graph graph = new COPRA_Graph("C:\\My_DevSomething\\MyWorkSpace\\KDECM_v1.4\\Algorithm_DataSet\\test.csv");
        COPRA copra = new COPRA();
        Map<String, HashSet<String>> partitions = copra.divide_community(graph, 2, 10000);
        System.out.println(partitions);

        /**
         * 统计社区数量
         * */
        Set<String> community = new HashSet<>();
        for (Map.Entry<String, HashSet<String>> entry : partitions.entrySet()){
            community.addAll(entry.getValue());
        }
        System.out.println("社区数量: " + community.size());
        
//        String temp = partitions.get("你").toString();
//        System.out.println(temp);
//        temp = temp.substring(1, temp.length()-1);
//        System.out.println(temp);
        
//        String basePath = new File("").getPath();
//        System.out.println("文件路径："+basePath);
//        String path = this.getClass().getResource("/").getPath();
//        System.out.println("文件路径："+path);
	}
	
	public static void test1() {
		 String path= "C:\\Users\\niu\\Desktop";//所创建文件的路径
	     File f = new File(path);
	     if(!f.exists()){
	    	 f.mkdirs();//创建目录
	     }  
	     String fileName = "test.txt";//文件名及类型
	     File file = new File(path, fileName);
	     try {
	    	 BufferedWriter testOutputStream = null;
		     testOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
		 	        file), "UTF-8"), 1024);
		     testOutputStream.write(path);
			 testOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

