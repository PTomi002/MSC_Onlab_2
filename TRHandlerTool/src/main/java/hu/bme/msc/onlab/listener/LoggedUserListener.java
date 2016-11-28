package hu.bme.msc.onlab.listener;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.service.interf.IUserService;
import hu.bme.msc.onlab.util.ResponseDto;

@Component
public class LoggedUserListener extends BaseListener {

	private static final String USER_SESSION_ATTRIBUTE = "user";

	@Autowired
	private HttpSession session;

	@Autowired
	private IUserService userService;

	@EventListener
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		// fetching user object from database and storing it to the current
		// session
		try {
			LOGGER.info("Trying to add current user to the session, event: " + event.toString());
			final String usernameId = event.getAuthentication().getName();
			ResponseDto<User> userResponse = userService.get(usernameId);
			if (userResponse.isSuccess()) {
				LOGGER.info("User has been added to the session: " + userResponse.getValue().toString());
				session.setAttribute(USER_SESSION_ATTRIBUTE, userResponse.getValue());
			} else {
				LOGGER_UTIL.error(userResponse);
			}
		} catch (Exception e) {
			LOGGER.error("Exception happened during setting user object tot he session!", e);
		}
	}

}
