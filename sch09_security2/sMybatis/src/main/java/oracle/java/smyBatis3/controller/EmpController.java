package oracle.java.smyBatis3.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.java.smyBatis3.model.Dept;
import oracle.java.smyBatis3.model.DeptVO;
import oracle.java.smyBatis3.model.Emp;
import oracle.java.smyBatis3.model.EmpDept;
import oracle.java.smyBatis3.model.Member1;
import oracle.java.smyBatis3.service.EmpService;
import oracle.java.smyBatis3.service.Paging;

//import org.rosuda.JRI.REXP;
//import org.rosuda.JRI.Rengine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class EmpController {
	@Autowired
	private EmpService es;
	@Autowired
	private JavaMailSender mailSender;  // Spring F/W
	
	@RequestMapping(value="list")
	public String list(Emp emp, String currentPage, Model model) {
		int total = es.total();
		System.out.println("total=>" + total);
		System.out.println("currentPage=>" + currentPage);
		//                     14     NULL(0,1....)
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart());   // 시작시 1
		emp.setEnd(pg.getEnd());     // 시작시 10 
		List<Emp> list = es.list(emp);
//		String status = es.idCompare(id1,id2);
		
	//	model.addAttribute("k33","k33333");
		model.addAttribute("list",list);
		model.addAttribute("pg",pg);
		return "list";
	}

    // Emp 입력 Test 
	@RequestMapping(value="insertEmp")
	public String insertEmp(Model model) {
		es.insertEmp();
		return "redirect:list.do";
	}
	

	@RequestMapping(value="detail")
	public String detail( HttpServletRequest request , int empno, Model model) {
		// session Test (id)
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println("session id->"+id);
		Emp emp = es.detail(empno);
		model.addAttribute("emp",emp);
		return "detail";
	}
 
	
	@RequestMapping(value="updateForm")
	public String updateForm(int empno,Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("emp",emp);
		return "updateForm";
	}

	@RequestMapping(value="update",  method=RequestMethod.POST)
	public String update(Emp emp, Model model) {
		//System.out.println("hiredate = "+emp.getHiredate() );
		int k = es.update(emp);
		//System.out.println("es.update(emp) CNT -->"+k);
		//  model.addAttribute("kkk","Message kkk");   // Test Controller간 Data 전달
		return "redirect:list.do";		
	//	return "forward:list.do";		  //  Controller간 Data 전달시 활용(Model등에 담아서리....)
	}

	
	
	@RequestMapping(value="writeForm")
	public String writeForm(Model model) {
	// 	Emp emp = null;
		List<Emp> list = es.listManager();
		model.addAttribute("empMngList",list);   // emp Manager List
		List<Dept> deptList = es.select();
		model.addAttribute("deptList", deptList); // dept
		return "writeForm";
	}

	@RequestMapping(value="write",method=RequestMethod.POST)
	public String write(Emp emp, Model model) {
		System.out.println("emp.getHiredate->"+emp.getHiredate());
		int result = es.insert(emp);
		if (result > 0) return "redirect:list.do";
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:writeForm.do";
		}
	}
	
	@RequestMapping(value="confirm")
	public String confirm(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("empno", empno);
		if (emp !=null) {
			model.addAttribute("msg","중복된 사번입니다");			
			return "forward:writeForm.do";
		} else {
			model.addAttribute("msg","사용 가능한 사번입니다");
			return "forward:writeForm.do";
		}
	}

	@RequestMapping(value="delete")
	public String delete(int empno,Model model) {
		es.delete(empno);
		return "redirect:list.do";
	}

	@RequestMapping(value="listEmp")
	public String listEmp(Model model) {
		EmpDept empDept = null;
		List<EmpDept> listEmp = es.listEmp(empDept);
		model.addAttribute("listEmp",listEmp);
		return "listEmp";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	// Ajax  List Test
	@RequestMapping(value="listEmpAjax2")
	public String listEmpAjax2(Model model) {
		EmpDept empDept = null;
		System.out.println("Ajax  List Test Start");
		List<EmpDept> listEmp = es.listEmp(empDept);
		model.addAttribute("result","kkk");
		model.addAttribute("listEmp",listEmp);
		return "listEmpAjax2";
	}
		
	// Ajax  List Test
	@RequestMapping(value="listEmpAjax")
	public String listEmpAjax(Model model) {
		EmpDept empDept = null;
		System.out.println("Ajax  List Test Start");
		List<EmpDept> listEmp = es.listEmp(empDept);
		model.addAttribute("result","kkk");
		model.addAttribute("listEmp",listEmp);
		return "listEmpAjax";
	}
		
	// Ajax  Test  
	@RequestMapping(value = "getDeptName", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String getDeptName(int deptno, Model model) {
		System.out.println("deptno->"+deptno);
		return es.deptName(deptno);
	}
	
	// interCepter 시작 화면 
	@RequestMapping(value = "interCepterForm", method = RequestMethod.GET)
	public String interCepterForm(Model model) {
		  System.out.println("interCepterForm Start");
	    return "interCepterForm";
	}
	
	// interCepter 진행 Test
		@RequestMapping(value="interCepter")
		public String interCepter(String id, Model model) {
			System.out.println("interCepter  Test Start");
			System.out.println("interCepter id->"+id);
			int memCnt = es.memCount(id);

			System.out.println("memCnt ->"+ memCnt);
		//	List<EmpDept> listEmp = es.listEmp(empDept); User 가져오는 Service
		//   member1의   Count가져오는  Service 수행 
		//	member.setId("kkk");

			model.addAttribute("id",id);
			model.addAttribute("memCnt",memCnt);
			System.out.println("interCepter  Test End");
			return "interCepter";   // User 존재하면  User 이용 조회 Page
		}
		
		// interCepter 진행 Test
		@RequestMapping(value="doMemberList")
		public String doMemberList(Model model, HttpServletRequest request){
			String ID =  (String) request.getSession().getAttribute("ID");
			System.out.println("doMemberList  Test Start  ID ->"+ID);
			Member1 member1 = null;
			// Member1 List Get Service
			List<Member1> listMem = es.listMem(member1);
			model.addAttribute("ID",ID);
			model.addAttribute("listMem",listMem);
			return "doMemberList";   // User 존재하면  User 이용 조회 Page
		}
		
		// SampleInterceptor 내용을 받아 처리 
		@RequestMapping(value = "doMemberWrite", method = RequestMethod.GET)
		public String doMemberWrite( Model model,  HttpServletRequest request) {
			 String ID =  (String) request.getSession().getAttribute("ID");
		     System.out.println("doMemberWrite....................");
			 model.addAttribute("id",ID);
		     return "doMemberWrite";
		}  

			
			//  Procedure Test 입력화면 
			@RequestMapping(value = "writeDeptIn", method = RequestMethod.GET)
			public String writeDeptIn(Locale locale, Model model) {
				 System.out.println("writeDeptIn Start");
			    return "writeDept3";
			}
			// Procedure Test 입력후 VO 전달 Test
			@RequestMapping(value="writeDept",method=RequestMethod.POST)
			public String writeDept(DeptVO deptVO, Model model) {
			//	DeptVO RdeptVO = es.insertDept(deptVO);   // Procedure Call 
				es.insertDept(deptVO);   // Procedure Call 
				if (deptVO == null) {
					System.out.println("deptVO NULL");
				}else {
					System.out.println("RdeptVO.getOdeptno()->"+deptVO.getOdeptno());
					System.out.println("RdeptVO.getOdname()->"+deptVO.getOdname());
					System.out.println("RdeptVO.getOloc()->"+deptVO.getOloc());
					model.addAttribute("msg", "정상 입력 되었습니다 ^^");
					model.addAttribute("dept", deptVO);
					
				}
				return "writeDept3";
			}
	
			// mailTransport 코드
			@RequestMapping(value = "mailTransport")
			public String mailTransport(HttpServletRequest request, Model model) {
				System.out.println("mailSending");
				String tomail = "ttaekwang3@naver.com";              // 받는 사람 이메일
				System.out.println(tomail);
				String setfrom = "ttaekwang3@gmail.com";
				String title = "mailTransport 입니다";                 // 제목
				try {
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
					messageHelper.setFrom(setfrom);    // 보내는사람 생략하거나 하면 정상작동을 안함
					messageHelper.setTo(tomail);       // 받는사람 이메일
					messageHelper.setSubject(title);   // 메일제목은 생략이 가능하다
					String tempPassword = (int) (Math.random() * 999999) + 1 + "";
					messageHelper.setText("임시 비밀번호입니다 : " + tempPassword); // 메일 내용
					System.out.println("임시 비밀번호입니다 : " + tempPassword);
					DataSource dataSource = new FileDataSource("c:\\log\\kh3.jpg");
				    messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), dataSource);
					mailSender.send(message);
					model.addAttribute("check", 1);   // 정상 전달
		//			s.tempPw(u_id, tempPassword)  ;// db에 비밀번호를 임시비밀번호로 업데이트
				} catch (Exception e) {
					System.out.println(e);
					model.addAttribute("check", 2);  // 메일 전달 실패
				}
				return "mailResult";
			}

			
			
			
			
			
			
	//  Procedure Cursor Test 입력화면 
	  @RequestMapping(value = "writeDeptCursor", method = RequestMethod.GET)
	  public String writeDeptCursor(Model model) {
			  System.out.println("writeDeptCursor Start");
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("sDeptno", 10);
				map.put("eDeptno", 80);
				es.SelListDept(map);
				List<Dept> deptList = (List<Dept>) map.get("dept");
				System.out.println("deptList.dname[0].getDname->"+deptList.get(0).getDname());
				System.out.println("deptList.dname[0].getLoc->"+deptList.get(0).getLoc());
				System.out.println("deptList Size->"+ deptList.size());
				model.addAttribute("deptList", deptList);
		  return "writeDeptCursor";
		}

	  
	  
	  /*
	  */
	  
	  
	  
	  
	  
}