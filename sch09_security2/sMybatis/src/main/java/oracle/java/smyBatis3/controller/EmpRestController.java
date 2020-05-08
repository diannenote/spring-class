package oracle.java.smyBatis3.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oracle.java.smyBatis3.controller.VO.SampleVO;
import oracle.java.smyBatis3.model.Dept;
import oracle.java.smyBatis3.service.EmpService;


@RestController
@RequestMapping("/sample")
public class EmpRestController {
	@Autowired
	private EmpService es;

	@RequestMapping("/sendVO")
	public SampleVO sendVO() {
	    SampleVO vo = new SampleVO();
	    vo.setFirstName("길동");
	    vo.setLastName("홍");
	    vo.setMno(123);

	    return vo;
	}	 
   @RequestMapping("/hello")
   public String sayHello() {
    return "Hello World ";
   }
   @RequestMapping("/sendVO2")
   public SampleVO sendVO2(int deptno) {
	  System.out.println("@RestController deptno->"+deptno);
	  SampleVO vo = new SampleVO();
	  vo.setFirstName("길동");
	  vo.setLastName("홍");
	  vo.setMno(123);
	  return vo;
	  }	 
    
   @RequestMapping("/sendVO3")
   public List<Dept> sendVO3() {
	System.out.println("@RestController sendVO3  START");
	List<Dept> deptList = es.select();
	return deptList;
	}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
	  
}
