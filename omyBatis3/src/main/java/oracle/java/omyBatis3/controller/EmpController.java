package oracle.java.omyBatis3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import oracle.java.omyBatis3.model.Dept;
import oracle.java.omyBatis3.model.DeptVo;
import oracle.java.omyBatis3.model.Emp;
import oracle.java.omyBatis3.model.EmpDept;
import oracle.java.omyBatis3.model.Member1;
import oracle.java.omyBatis3.service.EmpService;
import oracle.java.omyBatis3.service.Paging;

@Controller
public class EmpController {
    @Autowired
	private EmpService es;
    @Autowired
    private JavaMailSender mailSender;
    
	@RequestMapping(value="list")
	public String list(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController list Start...");
		// Emp TBL Count Get
		int total = es.total();
		System.out.println("EmpController list total->"+total);
		System.out.println("EmpController list currentPage->"+currentPage);
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart());   // 시작
		emp.setEnd(pg.getEnd());
		List<Emp> list = es.list(emp);
		model.addAttribute("list", list);
		model.addAttribute("pg", pg);
		
		return "list";
		
	}
	@RequestMapping(value="detail")
	public String detail (HttpServletRequest request, int empno, Model model) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		Emp emp = es.detail(empno);
		model.addAttribute("emp",emp);
		return "detail";
	}
	
	@RequestMapping(value="updateForm")
	public String updateFrom (int empno, Model model) {
		
		Emp emp = es.detail(empno);
		model.addAttribute("emp",emp);
		return "updateForm";
		
	}
	
	@RequestMapping(value="update", method = RequestMethod.POST)
	public String update (Emp emp, Model model) {
		int k = es.update(emp);
		return "redirect:list.do";
	}
	
	
	@RequestMapping(value="writeForm")
	public String writeForm(Model model) {
		List<Emp> list = es.listManager();
		List<Dept> deptList = es.select();
		model.addAttribute("empManagerList", list);
		model.addAttribute("deptList", deptList);
		
		return "writeForm";
				
	}
	
	@RequestMapping(value="write", method = RequestMethod.POST)
	public String write(Emp emp, Model model) {
		int result = es.insert(emp);
		if (result > 0) return "redirect:list.do";
		else {
			model.addAttribute("msg", "입력 실패! 확인해보세요.");
			return "redirect:writeForm.do";
		}
	}
	@RequestMapping(value="delete")
	public String delete(int empno, Model model) {
		
		es.delete(empno);
		return "redirect:list.do";
	}
	
	@RequestMapping(value="confirm")
	public String confirm(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("empno", empno);
		if (emp != null) {
			model.addAttribute("msg","중복된 사번입니다.");
			return "forward:writeForm.do";
		} else {
			model.addAttribute("msg","사용가능한 사번입니다.");
			return "forward:writeForm.do";
		}
	}
	
	@RequestMapping(value="listEmp")
	public String listEmp(Model model) {
		System.out.println("controller listEmp start..");
		EmpDept empDept = null;
		List<EmpDept> listEmp = es.listEmp(empDept);
		model.addAttribute("listEmp", listEmp);
		
		return "listEmp";
	}
	//mailTransport 코드
	@RequestMapping(value="mailTransport")
	public String mailTransport(HttpServletRequest request, Model model) {
		System.out.println("mailTransport");
		String tomail = "564486_v@naver.com";
		
		System.out.println(tomail);
		String setfrom = "k01058318991@gmail.com";
		String title = "mailTransport 입니다.";
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom);
			messageHelper.setTo(tomail);
			messageHelper.setSubject(title);
		
			String tempPassword = (int) (Math.random() * 999999) + 1 + "";
			messageHelper.setText("임시 비밀번호입니다: ", tempPassword);
			System.out.println("임시 비밀번호입니다: " + tempPassword);
			DataSource dataSource = new FileDataSource("c:\\log\\cat.jpg");
			messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), dataSource);
			mailSender.send(message);
			model.addAttribute("check", 1);
			
		} catch(Exception e) {
			model.addAttribute("check", 2);
		}
		return "mailResult";
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
	
	//Procedure VO Test
	@RequestMapping(value="writeDeptIn", method= RequestMethod.GET)
	public String writeDeptIn(Locale locale, Model model) {
		System.out.println("writeDeptIn start");
		return "writeDept3";
	}
	
	//Procedure test 입력 후 VO 전달 test
	@RequestMapping(value="writeDept", method=RequestMethod.POST)
	public String writeDept(DeptVo deptVO, Model model) {
		es.insertDdept(deptVO);
		if (deptVO == null) {
			System.out.println("deptVO null");
		} else {
			System.out.println();
			System.out.println();
			System.out.println();
			model.addAttribute("msg", "정상입력되었습니다.");
			model.addAttribute("dept", deptVO);
		}
		return "writeDept3";
	}
	
	@RequestMapping(value="writeDeptCursor", method=RequestMethod.GET)
	public String writeDeptCursor(Model model) {
		System.out.println("writeDeptCursor start");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sDeptno", 10);
		map.put("eDeptno", 80);
		es.SelListDept(map);
		List<Dept> deptList = (List<Dept>) map.get("dept");
		System.out.println("deptList.get[0].getDname()->" + deptList.get(0).getDname());
		System.out.println("deptList.get[0].getLoc()->" + deptList.get(0).getLoc());
		System.out.println("depsize->" + deptList.size());
		
		model.addAttribute("deptList", deptList);
		return "writeDeptCursor";
	}
	
	// interCeptor 시작 화면 
		@RequestMapping(value = "interCeptorForm", method = RequestMethod.GET)
		public String interCepterForm(Model model) {
			  System.out.println("interCepterForm Start");
		    return "interCeptorForm";
		}
		
		// interCepter 진행 Test
		@RequestMapping(value="interCeptor")
		public String interCepter(String id, Model model) {
			System.out.println("interCeptor  Test Start");
			System.out.println("interCeptor id->"+id);
			//int memCnt = 1;
			int memCnt = es.memCount(id);

			System.out.println("memCnt ->"+ memCnt);
		//	List<EmpDept> listEmp = es.listEmp(empDept); User 가져오는 Service
		//   member1의   Count가져오는  Service 수행 
		//	member.setId("kkk");

			model.addAttribute("id",id);
			model.addAttribute("memCnt",memCnt);
			System.out.println("interCeptor  Test End");
			return "interCeptor";   // User 존재하면  User 이용 조회 Page
		}
		
		// interCeptor 진행 Test
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

	
}



