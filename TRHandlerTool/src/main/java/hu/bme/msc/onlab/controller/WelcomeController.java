package hu.bme.msc.onlab.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController extends BaseController {
	// ~ Fields
	// ================================================================================================
	private static final String SIGNIN_MESSAGE = "signin_message";
	private static final String INVALID_SIGNIN = "invalid";

	// ~ HTTP Methods
	// ================================================================================================
	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView welcome() {
		final ModelAndView model = new ModelAndView("welcome");
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			model.addObject("user", getCurrentUser());
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
