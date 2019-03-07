package controller;

import model.UserModel;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class MyController {
	int label = 0;

	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public String requestHTML() {
		label = 10;
		return "my";
	}

	// 获取用户信息
	@RequestMapping(value = "My/getUInfo/uid={uid}", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String returnUserInfo(@PathVariable("uid") String uid, HttpSession session) {// uid为
																						// userId，在这加session
		label = 20;
		session.setAttribute("userId", uid);
		return UserModel.getUserInfo(uid);
	}

	// 获取用户收藏的音乐
	@RequestMapping(value = "My/myMusic/uid={uid}", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String returnMyMusic(@PathVariable("uid") String uid) {
		label = 30;
		return UserModel.getMyMusic(uid);
	}
}
