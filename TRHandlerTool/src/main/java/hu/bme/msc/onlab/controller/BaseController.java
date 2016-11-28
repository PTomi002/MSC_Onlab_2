package hu.bme.msc.onlab.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import hu.bme.msc.onlab.model.sql.User;

public abstract class BaseController {
	// ~ Fields
	// ================================================================================================
	protected static final String USER_SESSION_ATTRIBUTE = "user";
	
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	// ~ Wired fields
	// ================================================================================================
	@Autowired
	protected HttpSession session;
	
	// ~ Methods
	// ================================================================================================
	protected User getCurrentUser() {
		return (User) session.getAttribute(USER_SESSION_ATTRIBUTE);
	}
}
