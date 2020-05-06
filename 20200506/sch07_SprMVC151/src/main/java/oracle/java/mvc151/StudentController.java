package oracle.java.mvc151;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

	@RequestMapping("/studentForm")
	public String studentForm() {
		return "createPage";
	}
	
	@RequestMapping("/student/create")
	public String studentCreate( HttpServletRequest request, Student stu1, BindingResult result ,Model model) {
	//	public String studentCreate( HttpServletRequest request,  @ModelAttribute("st3") Student stu1, BindingResult result ,Model model) {
		String context =  request.getContextPath();
		String page = "createDonePage";
		System.out.println("/student/create Start");
		StudentValidator validator = new StudentValidator();
		validator.validate(stu1, result);
		System.out.println("result Message->"+ result.toString());
		System.out.println("result Message getFieldError->"+ result.getFieldErrors("name"));
		
		if(result.hasErrors()) {
			result.getFieldError("name");
			if (result.hasFieldErrors("name")) {
				System.out.println("result.hasErrors1->"+result.getFieldError("name"));
				FieldError fieldError1 = result.getFieldError("name");
				String name = fieldError1.getCode();
				model.addAttribute("name", name);
				} 
			if (result.hasFieldErrors("id")) {
				FieldError fieldError2 = result.getFieldError("id");
				System.out.println( "fieldError.hasErrors2()-->"+ fieldError2.getCode());
				String name = fieldError2.getCode();
				model.addAttribute("name", name);
			}
			page = "createPage";
		}
		System.out.println("result Message->End");
		System.out.println("result page->"+page);
		return page;
	}
}
