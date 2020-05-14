package oracle.java.omyBatis3.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oracle.java.omyBatis3.model.Dept;
import oracle.java.omyBatis3.model.Emp;
import oracle.java.omyBatis3.service.EmpService;
import oracle.java.omyBatis3.service.Paging;

@Controller
public class EmpController {
    @Autowired
	private EmpService es;
    
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
	
}



