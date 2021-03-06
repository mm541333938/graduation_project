package controller;

import model.UserModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
class LoginController {
	int label = 0;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView requestHTML() {
		label = 10;
		ModelAndView modelAndView = new ModelAndView("login", "log", new UserModel());
		return modelAndView;
	}

	// 用户登录
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String userRegister(@ModelAttribute("log") UserModel user) {
		label = 20;
		String status = user.login();
		return status;
	}
}
