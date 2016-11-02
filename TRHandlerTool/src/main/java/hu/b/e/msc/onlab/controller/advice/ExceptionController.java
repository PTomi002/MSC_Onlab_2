package hu.b.e.msc.onlab.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	private static final String ERROR_PAGE_NAME = "error";
	
	@ExceptionHandler({ NoHandlerFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ModelAndView pageNotFoundException(NoHandlerFoundException exception, HttpServletRequest request) {
		return handleError(exception, request, HttpStatus.NOT_FOUND);
	}
	
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
