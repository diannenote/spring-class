package oracle.java.omyBatis3.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@RequestMapping(value="upLoadFormStart")
	public String upLoadFormStart(Model model) {
		System.out.println("upLoadFormStart start");
		return "upLoadFormStart";
	}
	
//	@RequestMapping(value="uploadForm", method=RequestMethod.GET)
//	public void uploadForm() {
//		System.out.println("uploadForm GET start");
//	}
	
	@RequestMapping(value="uploadForm", method=RequestMethod.POST)
	public String uploadForm(HttpServletRequest request, MultipartFile file, String path, Model model) throws Exception{
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		System.out.println("uploadForm POST start");
		
		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());
		
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes(), uploadPath);
		logger.info("savedName", savedName);
		
		return "uploadResult";
		
	}
	
	private String uploadFile(String originalName, byte[] fileData, String uploadPath) throws Exception {

		UUID uid = UUID.randomUUID();
		System.out.println("uploadPath->" + uploadPath);
		
		File frileDirectory = new File(uploadPath);
		if (!frileDirectory.exists()) {
			frileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성: " + uploadPath);
		}
		
		String savedName = uid.toString() + "_" + originalName;

		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);
		
		return savedName;
	}

	
	
}
