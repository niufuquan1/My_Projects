package net.chenlin.dp.common.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Similarity {
	  //从指定位置开始找相同词汇
	  private  static String getSameWord(String doc1, String doc2, int idx1, int idx2){
	      String sameword="";
	      int i=idx1;
	      int j=idx2;
	      while(i<doc1.length() && j<doc2.length()){
	          char d1=doc1.charAt(i);
	          if(!isChineseCharacters(d1)){
	              break;
	          }
	          if(d1==doc2.charAt(j)){
	              i++;
	              j++;
	          }else {
	              break;
	          }
	      }
	      sameword=doc1.substring(idx1,i);
	      if(sameword.length()<2){
	          sameword="";
	      }
	      return sameword;
	  }
	  private static Map<Integer, List<Integer>> generateDocMap(String doc1){
	      Map<Integer, List<Integer>> Doc1Map= new HashMap<Integer, List<Integer>>();
	      for (int i=0; i<doc1.length(); i++){
	          char d1=doc1.charAt(i);
	          if(isChineseCharacters(d1)){
	              int charIndex = getGB2312Id(d1);
	              if(charIndex != -1){
	                  List<Integer> idxs = Doc1Map.get(charIndex);
	                  if(idxs != null){
	                      Doc1Map.get(charIndex).add(i);
	                  }else {
	                      idxs = new ArrayList<Integer>();
	                      idxs.add(i);
	                      Doc1Map.put(charIndex, idxs);
	                  }
	              }
	          }
	      }
	      return Doc1Map;
	  }
	  private static double getSimilarity(String doc1, String doc2){
	      if(doc1!=null && doc1.trim().length()>0 && doc2!=null && doc2.trim().length()>0){
	          Map<Integer, int[]> AlgorithmMap = new HashMap<Integer, int[]>();
	          //将两个字符串中的中文字符以及出现的总数封装到，AlgorithmMap中
	          for (int i = 0; i < doc1.length(); i++){
	              char d1 = doc1.charAt(i);
	              if(isChineseCharacters(d1)){//标点和数字不处理
	                  int charIndex = getGB2312Id(d1);//保存字符对应的GB2312编码
	                  if(charIndex != -1){
	                      int[] fq = AlgorithmMap.get(charIndex);
	                      if(fq != null && fq.length == 2){
	                          fq[0]++;//已有该字符，加1
	                      }else {
	                          fq = new int[2];
	                          fq[0] = 1;
	                          fq[1] = 0;
	                          AlgorithmMap.put(charIndex, fq);//新增字符入map
	                      }
	                  }
	              }
	          }
	          for (int i=0; i < doc2.length(); i++){
	              char d2 = doc2.charAt(i);
	              if(isChineseCharacters(d2)){
	                  int charIndex = getGB2312Id(d2);
	                  if(charIndex != -1){
	                      int[] fq = AlgorithmMap.get(charIndex);
	                      if(fq !=null && fq.length == 2){
	                          fq[1]++; //已有该字符，加1
	                      }else{
	                          fq = new int[2];
	                          fq[0] = 0;
	                          fq[1] = 1;
	                          AlgorithmMap.put(charIndex, fq);
	                      }
	                  }
	              }
	          }
	          Iterator<Integer> iterator=AlgorithmMap.keySet().iterator();
	          double sqdoc1=0;
	          double sqdoc2=0;
	          double denominator=0;
	          while(iterator.hasNext()){
	              int[] c = AlgorithmMap.get(iterator.next());
	              denominator += c[0]*c[1];
	              sqdoc1 += c[0]*c[0];
	              sqdoc2 += c[1]*c[1];
	          }
	          return denominator / Math.sqrt(sqdoc1*sqdoc2);

	      }else {
	          throw new NullPointerException(" the Document is null or have not chars!!");
	      }

	  }
	  private static boolean isChineseCharacters(char ch){
	      return (ch >= 0x4E00 && ch <= 0x9FA5);
	  }
	  private static short getGB2312Id(char ch){
	      try {
	          byte[] buffer = Character.toString(ch).getBytes("GB2312");
	          if (buffer.length != 2) {
	              // 正常情况下buffer应该是两个字节，否则说明ch不属于GB2312编码，故返回'?'，此时说明不认识该字符
	              return -1;
	          }
	          int b0 = (int) (buffer[0] & 0x0FF) - 161; // 编码从A1开始，因此减去0xA1=161
	          int b1 = (int) (buffer[1] & 0x0FF) - 161;
	          return (short) (b0 * 94 + b1);// 第一个字符和最后一个字符没有汉字，因此每个区只收16*6-2=94个汉字
	      } catch (UnsupportedEncodingException e) {
	          e.printStackTrace();
	      }
	      return -1;
	  }

	  /*
	  *找相同词汇
	  *
	  *
	  * */
	  public  Set<String> getSameWords(String doc1, String doc2){
	      if(doc1!=null && doc1.trim().length()>0 && doc2!=null && doc2.trim().length()>0){
	          Map<Integer, List<Integer>> Doc1Map= generateDocMap(doc1);

	          Set<String> SameWords = new HashSet<String>();

	          for (int i=0; i < doc2.length(); i++){
	              char d2=doc2.charAt(i);
	              if(isChineseCharacters(d2)){
	                  int charIndex= getGB2312Id(d2);  //文献2中的某个字
	                  if(charIndex != -1){
	                      //判断文献2中这个字是否出现在文献1
	                      List<Integer> idxs = Doc1Map.get(charIndex);
	                      if(idxs != null){
	                          int cnt=0;
	                          int doc1Idx=-1;
	                          String sameword;
	                          // 这个字出现在文献1中的位置索引，可能同一个字出现在多个位置
	                          while(cnt < idxs.size()){   //依次取出该字所在的所有位置
	                              doc1Idx=idxs.get(cnt);
	                              sameword= getSameWord(doc1,doc2,doc1Idx,i);
	                              if(sameword!=""){
	                                  SameWords.add(sameword);
	                                  int len=sameword.length();
	                                  i=i+len-1;
	                                  break;
	                              }
	                              cnt++;
	                          }
	                      }
	                  }
	              }
	          }

	          return SameWords;
	      }else{
	          throw new NullPointerException(" the Document is null or have not chars!!");
	      }
	  }

	  /*
	  *
	  *算相似度
	  * */
	  public  String getSimilarityPercent(String doc1, String doc2){
		  System.out.println(doc1);
		  System.out.println(doc2);
	      double per = getSimilarity(doc1, doc2);
	      per=per*100;
	      DecimalFormat df = new DecimalFormat("0.00");
	      String percent = df.format(per);
	      percent=percent+"%";
	      System.out.println(percent);
	      return percent;
	  }
}
