package net.chenlin.dp.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.poi.hwpf.extractor.WordExtractor;


public class AnalysisWordUtil {
	public String[] ReadWord(String filepath) {
		String[] error = {"Error"};
		try {
			if (filepath.endsWith(".doc")) {
				InputStream is = new FileInputStream(new File(filepath));
				WordExtractor ex = new WordExtractor(is);
				String paraTexts[] = ex.getParagraphText();    
			      /*for (int i=0; i<paraTexts.length; i++) {    
			         System.out.println("Paragraph " + (i+1) + " : " + paraTexts[i]);    
			      } */
				ex.close();
				return paraTexts;
			} else {
				System.out.println("此文件不是后缀为.doc的word文件！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return error;
	}
}
