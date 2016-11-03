package hu.bme.msc.onlab.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hu.bme.msc.onlab.enums.SignUpResult;
import hu.bme.msc.onlab.exception.RegistrationException;
import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.service.interf.ILdapService;
import hu.bme.msc.onlab.service.interf.IUserService;

@Controller
public class SignupController extends BaseController {
	public static final String SIGNUP_PAGE = "signup";

	public static final String SIGNUP_MESSAGE = "signup_message";

	public static final String SIGNUP_RESULT = "signup_result";

	@Autowired
	private ILdapService ldapService;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView getSignup(@ModelAttribute(value = "user") User user) {
		final ModelAndView model;
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// TODO Just for development!
		user.setFirstname("aaaaa");
		user.setLastname("aaaaa");
		user.setEmail("aaa@aaa.aaa");
		user.setPassword("aA0+++");
		user.setUsername("aaaaa");

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("Forwarding welcome page");
			model = new ModelAndView("forward:/welcome");
		} else {
			LOGGER.info("Generating to the signup page");
			model = new ModelAndView(SIGNUP_PAGE);
			model.addObject(SIGNUP_RESULT, SignUpResult.NOT_SIGNUP);
		}

		return model;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup(@Valid @ModelAttribute(value = "user") User user, final BindingResult bindingResult) throws RegistrationException {
		final ModelAndView model;

		if (bindingResult.hasErrors()) {
			LOGGER.info("Generating sign up error page");
			model = new ModelAndView(SIGNUP_PAGE);

			model.addObject(SIGNUP_RESULT, SignUpResult.FAILED);
			model.addObject(SIGNUP_MESSAGE, "Invalid sign up!");
			model.setStatus(HttpStatus.BAD_REQUEST);
		} else {
			LOGGER.info("Registering User to LDAP server");
			ldapService.register(user);

			LOGGER.info("Registering User to MySQL server");
			userService.register(user);

			LOGGER.info("Generating login page");
			model = new ModelAndView(SIGNUP_PAGE);
			model.addObject("user", new User());
			model.addObject(SIGNUP_RESULT, SignUpResult.PASSED);
			model.addObject(SIGNUP_MESSAGE, "Successful sign up!");
		}

		return model;
	}
}
