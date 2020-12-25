package com.myspring.pro28.ex02;

import java.io.File;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownloadController {
	private static String CURR_IMAGE_REPO_PATH = "C:\\DEV\\spring\\image_repo";
	
	@RequestMapping("/download")
	public void download(@RequestParam("imageFileName") String imageFileName, HttpServletResponse response) throws Exception{
		OutputStream out = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH+"\\"+imageFileName;
		File image = new File(filePath);
		if(image.exists()) {
			Thumbnails.of(image).size(50, 50).outputFormat("png").toOutputStream(out);
		}else {
			return;
		}
		
		byte[] buffer = new byte[1024*8];
		out.write(buffer);
		out.close();
	}
}
