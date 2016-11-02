package hu.b.e.msc.onlab.controller.advice;

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

import hu.bme.msc.onlab.exception.LdapRegistrationException;
import hu.bme.msc.onlab.model.ldap.LdapUserEntry;
import hu.bme.msc.onlab.service.interf.ILdapService;
import hu.bme.msc.onlab.util.LoggerUtil;

@ControllerAdvice
public class ExceptionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	protected static final LoggerUtil LOGGER_UTIL = LoggerUtil.getLoggerUtil(LOGGER);
	
	private static final String ERROR_PAGE_NAME = "error";
	
	//	----------------------------------------------
	//	----------------------------------------------
	//	||		Specific exceptions					||
	//	----------------------------------------------		
	//	----------------------------------------------
	
	@ExceptionHandler({ NoHandlerFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ModelAndView pageNotFoundException(NoHandlerFoundException exception, HttpServletRequest request) {
		return handleError(exception, request, HttpStatus.NOT_FOUND);
	}
	
	@Autowired
	private ILdapService ldapService;
	
	@ExceptionHandler({ LdapRegistrationException.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView ldapRegistrationException(LdapRegistrationException exception, HttpServletRequest request) {
		try {
			final LdapUserEntry ldapUserEntry = exception.getLdapUserEntry();
			LOGGER.info("Trying to delete existing LdapUserEntry: " + LOGGER_UTIL.getValue(ldapUserEntry));
			if (ldapService.delete(exception.getLdapUserEntry()).isSuccess()) {
				LOGGER.info("LdapuserEntry has been deleted: " + LOGGER_UTIL.getValue(ldapUserEntry) + "!");
			}
		} catch (Exception e) {
			LOGGER.error("Could not delete LDAP entry!", e);
		}
		
		return handleError(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//	----------------------------------------------
	//	----------------------------------------------
	//	||		Every not specific exception		||
	//	----------------------------------------------		
	//	----------------------------------------------
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
