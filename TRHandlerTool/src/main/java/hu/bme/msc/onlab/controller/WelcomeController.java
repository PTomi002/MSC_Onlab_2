package hu.bme.msc.onlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.service.interf.IUserService;
import hu.bme.msc.onlab.util.ResponseDto;

@Controller
public class WelcomeController extends BaseController {

	private static final String SIGNIN_MESSAGE = "signin_message";
	private static final String INVALID_SIGNIN = "invalid";

	@Autowired
	private IUserService userService;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView welcome() {
		final ModelAndView model = new ModelAndView("welcome");
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String usernameId = auth.getName();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ResponseDto<User> getResponse = userService.get(usernameId); 
			model.addObject("user", getResponse.isSuccess() ? getResponse.getValue() : new User());
		}
		
		LOGGER.info("Generating welcome page");
		return model;
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public ModelAndView signin(@RequestParam(name = INVALID_SIGNIN, required = false) final boolean invalid) {
		final ModelAndView model;
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("Forwarding to the dashboard");
			model = new ModelAndView("forward:/dashboard");
		} else {
			LOGGER.info("Generating sign in page");
			model = new ModelAndView("signin");
			model.addObject(INVALID_SIGNIN, invalid);
			model.addObject(SIGNIN_MESSAGE, "Invalid username or password!");
		}
		return model;
	}
}
