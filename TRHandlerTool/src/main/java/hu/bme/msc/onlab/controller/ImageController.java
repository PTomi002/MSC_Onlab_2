package hu.bme.msc.onlab.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImageController extends BaseController {

	private static final String AVATAR_CONTENT_TYPE = "image/jpeg, image/png, image/gif, image/jpg";

	private static final String DEFAULT_USER_AVATAR = "/public/image/icon-user-default.png";

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/default_avatar", method = RequestMethod.GET)
	public void defaultUserAvatar(HttpServletResponse response) {
		LOGGER.info("Fetch default user avatar");
		final File defaultUserAvatar;
		final byte[] buffer;

		try {
			defaultUserAvatar = new File(servletContext.getRealPath(DEFAULT_USER_AVATAR));
			buffer = Files.readAllBytes(defaultUserAvatar.toPath());
			
			response.setContentType(AVATAR_CONTENT_TYPE);
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			
			LOGGER.info("Writing out image to ServletOutputStream");
			response.getOutputStream().write(buffer);
			response.getOutputStream().flush();
		} catch (Exception e) {
			LOGGER.error("Exception happened can not get default user image!", e);
		} finally {
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				LOGGER.error("Can not close HttpServletResponse outputstream!");
			}
		}
	}
}
