package hu.b.e.msc.onlab.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

	protected static final String ERROR_PAGE_NAME = "error";

	protected ModelAndView handleError(Exception exception, HttpServletRequest request, HttpStatus httpStatus) {
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
