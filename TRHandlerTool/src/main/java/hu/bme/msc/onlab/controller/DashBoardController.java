package hu.bme.msc.onlab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/dashboard")
public class DashBoardController extends BaseController{

	@RequestMapping(value = { "/worklist" }, method = RequestMethod.GET)
	public String worklist() {
		LOGGER.info("Generating wok list page");
		return "worklist";
	}
	
	
}
