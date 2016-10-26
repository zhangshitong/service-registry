package org.spring.casapp.web;
import org.spring.casapp.NullSessionException;
import org.spring.casapp.UserInfo;

import javax.servlet.http.HttpSession;
import java.util.Date;

public abstract class HttpClientController {
	final static String SESSIONHTTPCLIENT  = "HTTPCLIENT_SESSION";
	final static String SESSIONUSERINFO  = "USERINFO_SESSION";

	/**
	 * 获取当前登录用户的UserInfo
	 * @param session
	 * @return
	 * @throws NullSessionException
	 */
	public UserInfo getValidLoginedInfo(HttpSession session) throws NullSessionException {
		if(session.getId() == null) {
			throw new NullSessionException();
		}
		if(session.getAttribute(SESSIONUSERINFO) != null){
			return (UserInfo) session.getAttribute(SESSIONUSERINFO) ;
		}
		return null;
	}
	/**
	 * 设置当前登录用户的UserInfo
	 * @param session
	 * @param userInfo
	 * @throws NullSessionException
	 */
	public void setValidLoginedInfo(HttpSession session, UserInfo userInfo) throws NullSessionException{
		if(session.getId() == null) {
			throw new NullSessionException();
		}
		if(session.getAttribute(SESSIONUSERINFO) != null){
			session.removeAttribute(SESSIONUSERINFO) ;
		}
		session.setAttribute(SESSIONUSERINFO, userInfo);
	}

	/**
	 * 校验当前用户是否有效 
	 * @param session
	 * @return
	 * @throws NullSessionException
	 */
	public boolean checkValidLoginedSession(HttpSession session) throws NullSessionException{
		UserInfo userInfo = getValidLoginedInfo(session);
		if(userInfo != null) return true;
		return false;
	}

	
	protected boolean checkDateBetween(Date beginDate, Date endDate, Date curentDate){
    	boolean f = false; //默认为不在范围内
    	long beginDteAsLong = 631123200000L;
    	long endDteAsLong= curentDate.getTime() + 100L;
    	if(beginDate != null){
    		beginDteAsLong= beginDate.getTime();
    	}
    	if(endDate != null){
    	   endDteAsLong= endDate.getTime();
    	}
    	long currDteAsLong= curentDate.getTime();
    	if(beginDteAsLong <=currDteAsLong && endDteAsLong>=currDteAsLong){
    		f = true;
    	}
    	
    	return f;
    }


}
