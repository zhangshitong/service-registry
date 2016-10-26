package org.spring.casapp.web;

import org.spring.casapp.NullSessionException;
import org.spring.casapp.SessionValidateException;
import org.spring.casapp.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;


@Controller("indexPage")
public class IndexPageController extends HttpClientController {
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest request, HttpServletResponse  response, HttpSession session) throws NullSessionException, SessionValidateException, IOException {
		if(!checkValidLoginedSession(session))  throw new SessionValidateException();
		Map model = new HashMap();
		request.setAttribute("ctx", request.getContextPath());
		UserInfo info = loadUserInfo(session);
		log.info("user info: " + info.getAccount());
		model.put("user", info);

		return new ModelAndView("index", model);
	}
	/**
	 * 加载学生信息
	 * @param session
	 * @param
	 */
	private UserInfo loadUserInfo(HttpSession session ){
		UserInfo userInfo =  null;
		try {
			userInfo = this.getValidLoginedInfo(session);
		} catch (NullSessionException e) {
			log.error("NullSessionException", e);
		}

		return  userInfo;

	}






}
