package controller.onlab.msc.bme.hu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController{
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView getUsersView() {
		LOGGER.info("Generating login page");
		return new ModelAndView("login");
	}
}
