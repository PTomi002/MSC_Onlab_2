package hu.b.e.msc.onlab.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController extends BaseController {

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
}
