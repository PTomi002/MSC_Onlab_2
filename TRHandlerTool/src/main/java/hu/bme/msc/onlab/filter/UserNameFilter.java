package hu.bme.msc.onlab.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.MDC;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserNameFilter implements Filter {

	public static final String DEFAULT_FILTER_NAME = "userNameFilter";

	@Override
	public void destroy() {
		// Implementation is not needed.
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			MDC.put("user", SecurityContextHolder.getContext().getAuthentication().getName());
			chain.doFilter(request, response);
		} catch (Throwable t) {
		} finally {
			MDC.remove("user");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Implementation is not needed.
	}

}
