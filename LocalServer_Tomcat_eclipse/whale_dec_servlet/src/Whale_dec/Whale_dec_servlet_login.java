package Whale_dec;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Whale_dec_servlet_login
 */
@WebServlet("/Whale_dec_servlet_login")
public class Whale_dec_servlet_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Whale_dec_servlet_login() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String r="";
	    boolean redirect=false;	
		if(action.equals(new String("signinCheck"))){
			SessionInfo info = new SessionInfo();
			if(info.isEmailExisted(request)=="yes"){
				r = "1";
			}else{
				r = "0";
			}
		}else{		
			String email = request.getParameter("inputEmail");
			String password = request.getParameter("inputPassword");
			//check session has email?
			SessionInfo info = new SessionInfo();
			if(info.isEmailExisted(request)=="yes"){
				r = "you have signed in! Please logout and try again";
			}else{
				ServletContext context = this.getServletContext();
				TestDB db = (TestDB) context.getAttribute("db1");
			if(db.verifyLogIn(email,password).equals(new String("1"))){//verify	
				//try to have a first check on the data received
				int a = email.length();
				int b = password.length();
				if(a<=16 && b<=20){
					//this part should be replaced as mysql verification
					if(email.equals(new String("chen"))&& password.equals(new String("123"))){
						r = email + " welcome!";
						//add the current email into session
						info.setEmailInSession(email,request);
						//and try to redirect to cover
						//redirect = true;
					}else{
						r = email + " not existed!";
					}
				}else{
					r = "username or password is not in correct form";
				}
			}else{
				r="wrong email or password!";
			}//verify
			}//for email exist in session or not
		}
		//here we return the result to webpage with outputstream
		OutputStream outputStream = response.getOutputStream();//get outputstream
	    response.setHeader("content-type", "text/html;charset=UTF-8");//ensure the webpage decode in UTF-8
		byte[] dataByteArr = r.getBytes("UTF-8");//turn the string into bytes
	    outputStream.write(dataByteArr);
	    //if login successfully, we redirect the pages to cover/someplace else
	    if(redirect){
	        response.setHeader("Location", "index.html"); //need to be modified
	    }
	        //below is the code for output with printwriter
	        /*
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        response.setHeader("content-type", "text/html;charset=UTF-8");
	        out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'/>");
	        out.write(r);
	        */		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String r="";
	    boolean redirect=false;
		String email = request.getParameter("inputEmail");
		String password = request.getParameter("inputPassword");
		//check session has email?
		SessionInfo info = new SessionInfo();
		if(info.isEmailExisted(request)=="yes"){
			r = "you have signed in! Please logout and try again";
		}else{	
			//try to have a first check on the data received
			ServletContext context = this.getServletContext();
			TestDB db = (TestDB) context.getAttribute("db1");
			if(db.verifyLogIn(email,password).equals(new String("1"))){//verify	
				//try to have a first check on the data received
				//int a = email.length();
				//int b = password.length();
				//if(a<=16 && b<=20){
					//this part should be replaced as mysql verification
					/*
					if(email.equals(new String("chen"))&& password.equals(new String("123"))){
						r = email + " welcome!";
						//add the current email into session
						info.setEmailInSession(email,request);
						//and try to redirect to cover
						//redirect = true;
					}else{
						r = email + " not existed!";
					}
					*/
					info.setEmailInSession(email,request);
					r = email + " welcome!";			
				//}else{
				//	r = "username or password is not in correct form";
				//}
			}else{
				r="wrong email or password!";
			}//verify
		}//for email exist in session or not
		
		//here we return the result to webpage with outputstream
		OutputStream outputStream = response.getOutputStream();//get outputstream
        response.setHeader("content-type", "text/html;charset=UTF-8");//ensure the webpage decode in UTF-8
		byte[] dataByteArr = r.getBytes("UTF-8");//turn the string into bytes
        outputStream.write(dataByteArr);
        //if login successfully, we redirect the pages to cover/someplace else
        if(redirect){
        	response.setHeader("Location", "cover.html"); //need to be modified
        }
        //below is the code for output with printwriter
        /*
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        response.setHeader("content-type", "text/html;charset=UTF-8");
        out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'/>");
        out.write(r);
        */
	
	}

}
