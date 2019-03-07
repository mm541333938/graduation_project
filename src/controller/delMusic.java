package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import model.*;

@Controller
class delMusic {
	int label = 0;

	// 收藏音乐
	@RequestMapping(value = "delMusic/mid={mid}uid={uid}", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public void returnColMusic(@PathVariable("mid") String mid, @PathVariable("uid") String uid) {
		label = 80;
		MusicModel.delColMusic(uid, mid);
	}
}
