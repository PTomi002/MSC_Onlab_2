package hu.bme.msc.onlab.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hu.bme.msc.onlab.exception.RegistrationException;
import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.service.interf.ILdapService;
import hu.bme.msc.onlab.service.interf.IUserService;

@Controller
public class RegistrationController extends BaseController {
	@Autowired
	private ILdapService ldapService;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signupUser(@Valid @ModelAttribute(value = "user") User user, final BindingResult bindingResult)
			throws RegistrationException {
		final ModelAndView view;

		if (bindingResult.hasErrors()) {
			LOGGER.info("Generating sign up error page");
			view = new ModelAndView("signupError");
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
