package controller.onlab.msc.bme.hu;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import interf.service.onlab.msc.bme.hu.ILdapService;
import interf.service.onlab.msc.bme.hu.IUserService;
import sql.model.onlab.msc.bme.hu.User;

@Controller
public class RegistrationController extends BaseController {
	@Autowired
	private ILdapService ldapService;
	
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signupUser(@Valid @ModelAttribute(value = "user") User user, BindingResult bindingResult) {
		ModelAndView view = null;

		if (bindingResult.hasErrors()) {
			LOGGER.info("Generating sign up error page");
			view = new ModelAndView("signupError");
		} else {
			LOGGER.info("Adsing registration date to user");
			userService.createRegistrationDate(user);
			
			LOGGER.info("Registering User to LDAP server");
			ldapService.registerLdapEntry(user);

			LOGGER.info("Registering User to MySQL server");

			LOGGER.info("Generating login page");
			view = new ModelAndView("login");
		}

		return view;
	}
}
