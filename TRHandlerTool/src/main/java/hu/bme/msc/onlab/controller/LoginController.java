package hu.bme.msc.onlab.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController{
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView getUsersView() {
		final ModelAndView view;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("Forwarding to the dashboard");
			view = new ModelAndView("forward:/dashboard");
		} else {
			LOGGER.info("Generating login page");
			view = new ModelAndView("login");
		}
		return view;
	}
}
