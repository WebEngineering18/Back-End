package rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Routes {
	
	@RequestMapping("/")
	public String index() {
		return "forward:/index.html";
	}
}