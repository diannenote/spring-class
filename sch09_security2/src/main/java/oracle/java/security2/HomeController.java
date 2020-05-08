package oracle.java.security2;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/login.html")
	public String login(Locale locale, Model model) {
		System.out.println("login.html start...");
		return "security/login";
	}
	
	@RequestMapping("/welcome.html")
	public String welcome(Locale locale, Model model) {
		
		System.out.println("welcome.html start...");
		return "security/welcome";
	}
	
	@RequestMapping("/loginForm.html")
	public String loginForm(Locale locale, Model model) {
		System.out.println("loginForm.html start...");
		return "security/loginForm";
	}
	
}
