package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.UserModel;

@Controller
class upUserController {
	int label = 0;

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView requestHTML() {
		label = 10;
		ModelAndView modelAndView = new ModelAndView("update", "user", new UserModel());
		return modelAndView;
	}

	@RequestMapping(value = "/userUpdate", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String userUpdate(@ModelAttribute("user") UserModel user, HttpSession session, HttpServletResponse response)
			throws IOException {
		String userId = session.getAttribute("userId").toString();
		user.upUserInfo(user.getName(), userId);
		// System.out.println(user.getName());
		response.sendRedirect("./my");
		return "my";
	}

}
