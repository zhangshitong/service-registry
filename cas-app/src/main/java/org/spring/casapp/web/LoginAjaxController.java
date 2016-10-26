package org.spring.casapp.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.spring.casapp.NullSessionException;
import org.spring.casapp.SessionValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;


@Controller("LoginAjax")
@RequestMapping("/login")
public class LoginAjaxController  extends HttpClientController{
	private Logger log = Logger.getLogger(this.getClass());

	
	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
	public void loginSubmit(HttpServletRequest request, HttpServletResponse  response) throws NullSessionException, SessionValidateException, IOException {
		HttpSession session = request.getSession(true);
		JSONObject jo = new JSONObject();
		String username = request.getParameter("username");
		String userPassword = request.getParameter("password");
		String verifyCode = request.getParameter("verifyCode");
		if(StringUtils.isEmpty(username)){
			jo.put("ErrorMsg", "用户名不能为空");
			jo.put("success", false);
		}
		if(StringUtils.isEmpty(userPassword)){
			jo.put("ErrorMsg", "密码不能为空");
			jo.put("success", false);
		}

		//输出验证结果
		jo.put("success", true);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		jo.write(response.getWriter());

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView loginSubmit(HttpSession session) throws NullSessionException, SessionValidateException, IOException {
		return new ModelAndView("redirect:/logout.html");
	}

}
