package net.chenlin.dp.common.utils;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	public String SaveLiterature(MultipartFile file,String save_path) {
		if (!file.isEmpty()) {  
            try {  
                File filepath = new File(save_path);
                if (!filepath.exists()) 
                    filepath.mkdirs();
                // 文件保存路径  
                String savePath = save_path +"\\"+ file.getOriginalFilename();  
                // 转存文件  
                file.transferTo(new File(savePath)); 
                return savePath;
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
		return "Error";
	}
}