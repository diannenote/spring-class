package oracle.java.springMVCBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import oracle.java.springMVCBoard.command.BCommand;

@Controller
public class BController {
	
	BCommand command = null;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");
//		command = new BListCommand();
//		command.execute(model);
		return "list";
	}
	
	
}
