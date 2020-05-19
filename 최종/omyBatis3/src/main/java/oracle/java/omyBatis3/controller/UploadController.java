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
//  @Resource(name = "uploadPath")
//  private String uploadPath;
  // upLoadForm 시작 화면 
  @RequestMapping(value = "upLoadFormStart")
  public String upLoadFormStart(Model model) {
		System.out.println("upLoadFormStart Start");
	    return "upLoadFormStart";
  }

   @RequestMapping(value = "uploadForm", method = RequestMethod.POST)
  public String uploadForm( HttpServletRequest request,MultipartFile file, String path , Model model ) 
		  throws Exception {
	String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
	System.out.println("uploadForm POST Start");
    logger.info("originalName: " + file.getOriginalFilename());
    logger.info("size: " + file.getSize());
    logger.info("contentType: " + file.getContentType());
    String savedName = uploadFile(file.getOriginalFilename(), file.getBytes(), uploadPath);
    logger.info("savedName: " + savedName);
    model.addAttribute("savedName", savedName);
    return "uploadResult";
  }

  private String uploadFile(String originalName, byte[] fileData , String uploadPath) 
		  throws Exception {
     UUID uid = UUID.randomUUID();
   // requestPath = requestPath + "/resources/image";
    System.out.println("uploadPath->"+uploadPath);
    // Directory 생성 
	File fileDirectory = new File(uploadPath);
	if (!fileDirectory.exists()) {
		fileDirectory.mkdirs();
		System.out.println("업로드용 폴더 생성 : " + uploadPath);
	}

    String savedName = uid.toString() + "_" + originalName;
//    String path1 = "C:\\spring\\springSrc39\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\sMybatis\\resources\\image";
    File target = new File(uploadPath, savedName);
//    File target = new File(requestPath, savedName);
    FileCopyUtils.copy(fileData, target);
    return savedName;
  }
  
 



}
