package oracle.java.omyBatis3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
