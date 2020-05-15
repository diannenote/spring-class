package oracle.java.omyBatis3.controller;

import java.util.List;

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
import oracle.java.omyBatis3.model.Emp;
import oracle.java.omyBatis3.model.EmpDept;
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
		
	
}



