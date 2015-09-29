package Whale_dec;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInfo {
	public SessionInfo(){
		
	}
	
	public HttpServletRequest setUsernameInSession(String username, HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		return request;
	}
	
	public HttpServletRequest removeUsernameInSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		return request;
	}
	
	public HttpServletRequest setEmailInSession(String email, HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		return request;
	}
	
	public HttpServletRequest removeEmailInSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("email");
		return request;
	}
	
	public String getEmail(HttpServletRequest request){
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		if(email==null){
			return "";
		}else{
			return email;
		}
	}
	public String getSessionId(HttpServletRequest request){
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		return sessionId;
	}
	public long getSessionCreateTime(HttpServletRequest request){
		HttpSession session = request.getSession();
		long sessionCreationTime = session.getCreationTime();
		return sessionCreationTime;
	}
	
	public String sessionIsNew(HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session.isNew()) {
			return "new";
		}else{
			return "exist";
		}
	}
	
	public String isEmailExisted(HttpServletRequest request){
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		if(email==null){
			return "no";
		}else{
			return "yes";
		}
	}

}
