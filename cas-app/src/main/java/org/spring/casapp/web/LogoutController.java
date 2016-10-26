package org.spring.casapp.web;

import org.apache.log4j.Logger;
import org.spring.casapp.NullSessionException;
import org.spring.casapp.SessionValidateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller("logout")
public class LogoutController extends HttpClientController{
	private Logger log = Logger.getLogger(this.getClass());
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView loginSubmit(HttpSession session) throws NullSessionException, SessionValidateException, IOException {
		return new ModelAndView("redirect:/logout.html");
	}

}
