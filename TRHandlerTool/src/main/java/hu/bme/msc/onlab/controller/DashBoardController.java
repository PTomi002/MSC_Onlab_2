package hu.bme.msc.onlab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashBoardController extends BaseController{

	@RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
	public String welcome() {
		LOGGER.info("Generating dashboard page");
		return "dashboard";
	}
	
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String admin() {
		LOGGER.info("Generating dashboard page");
		return "dashboard";
	}

}
