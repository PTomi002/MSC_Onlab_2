package hu.bme.msc.onlab.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import hu.bme.msc.onlab.controller.SignupController;
import hu.bme.msc.onlab.enums.SignUpResult;
import hu.bme.msc.onlab.exception.LdapEntryExistsException;
import hu.bme.msc.onlab.exception.LdapUnknownRegistrationException;
import hu.bme.msc.onlab.exception.MySqlRegisrationException;
import hu.bme.msc.onlab.exception.RegistrationException;
import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.service.interf.ILdapService;
import hu.bme.msc.onlab.util.LoggerUtil;

@ControllerAdvice
public class ExceptionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	protected static final LoggerUtil LOGGER_UTIL = LoggerUtil.getLoggerUtil(LOGGER);

	private static final String ERROR_PAGE_NAME = "error";

	// ----------------------------------------------
	// ----------------------------------------------
	// || Specific exceptions ||
	// ----------------------------------------------
	// ----------------------------------------------

	@ExceptionHandler({ NoHandlerFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ModelAndView pageNotFoundException(NoHandlerFoundException exception, HttpServletRequest request) {
		return handleError(exception, request, HttpStatus.NOT_FOUND);
	}

	@Autowired
	private ILdapService ldapService;

	@ExceptionHandler({ LdapEntryExistsException.class })
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView ldapEntryExistsException(LdapEntryExistsException exception) {
		final ModelAndView model = new ModelAndView(SignupController.SIGNUP_PAGE);

		try {
			final User user = exception.getUser() == null ? new User() : exception.getUser();
			LOGGER.info("Generating sign up error page");

			model.addObject("user", user);
			model.addObject(SignupController.SIGNUP_MESSAGE,
					"User already exists with username: " + user.getUsernameId());
			model.addObject(SignupController.SIGNUP_RESULT, SignUpResult.FAILED);
		} catch (Exception e) {
			LOGGER.warn("User is null?", e);
		}

		return model;
	}

	@ExceptionHandler({ LdapUnknownRegistrationException.class , MySqlRegisrationException.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView ldapRegistrationException(RegistrationException exception,
			HttpServletRequest request) {
		try {
			final User user = exception.getUser();
			if (user != null) {
				LOGGER.info("Trying to unregister existing User from LDAP database: " + LOGGER_UTIL.getValue(user));
				ldapService.unRegister(user);
			} else {
				LOGGER.error("User object is null, can not unregister!");
			}
		} catch (Exception e) {
			LOGGER.warn("Could not unregister User LDAP database!", e);
		}
		return handleError(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// ----------------------------------------------
	// ----------------------------------------------
	// || Every not specific exception ||
	// ----------------------------------------------
	// ----------------------------------------------
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView unhandledException(Exception exception, HttpServletRequest request) {
		return handleError(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ModelAndView handleError(Exception exception, HttpServletRequest request, HttpStatus httpStatus) {
		final String redirectedUrl = request.getRequestURL().toString();
		final String httpMessage = exception.getLocalizedMessage();
		final int httpCode = httpStatus.value();
		final ModelAndView model = new ModelAndView(ERROR_PAGE_NAME);

		String errorMessage = "Request URL: " + redirectedUrl + " raised status code: " + httpCode + " ,message: "
				+ httpMessage;
		LOGGER.error(errorMessage, exception);

		model.addObject("httpCode", httpCode);
		model.addObject("httpMessage", httpMessage);
		model.addObject("redirectedUrl", redirectedUrl);

		return model;
	}
}
