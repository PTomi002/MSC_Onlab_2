package hu.bme.msc.onlab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/dashboard/tr")
public class TroubleReportController extends BaseController {

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createTroubleReport() {
		return new ModelAndView("create_tr");
	}
}
