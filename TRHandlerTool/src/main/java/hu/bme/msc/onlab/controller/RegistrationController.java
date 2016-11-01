package hu.bme.msc.onlab.controller;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hu.bme.msc.onlab.exception.RegistrationException;
import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.service.interf.ILdapService;
import hu.bme.msc.onlab.service.interf.IUserService;

@Controller
public class RegistrationController extends BaseController {
	private static final String INVALID = "invalid";

	@Autowired
	private ILdapService ldapService;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView getSignup(@ModelAttribute(value = "user") User user, @RequestParam(name = INVALID, required = false) boolean invalid) {
		final ModelAndView view;
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("Forwarding welcome page");
			view = new ModelAndView("forward:/welcome");
		} else {
			LOGGER.info("Generating to the signup page");
			view = new ModelAndView("signup");
			view.addObject(INVALID, invalid);
		}
		
		return view;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView signup(@Valid @ModelAttribute(value = "user") User user, final BindingResult bindingResult, HttpServletRequest request)
			throws RegistrationException {
		final ModelAndView view;

		if (bindingResult.hasErrors()) {
			LOGGER.info("Generating sign up error page");
			view = new ModelAndView("signup");
			
			request.setAttribute("invalid", true);
			view.setStatus(HttpStatus.BAD_REQUEST);
		} else {
			LOGGER.info("Registering User to LDAP server");
			ldapService.register(user);

			LOGGER.info("Registering User to MySQL server");
			userService.register(user);
			
			LOGGER.info("Generating login page");
			view = new ModelAndView("login");
		}

		return view;
	}
}
