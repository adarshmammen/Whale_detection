package Whale_dec;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Whale_dec_servlet_registration
 */
@WebServlet("/Whale_dec_servlet_registration")
public class Whale_dec_servlet_registration extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Whale_dec_servlet_registration() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubString r;
	    boolean redirect=false;
	    String r = "";
	    String username = request.getParameter("usr");
		String email = request.getParameter("inputEmail");
		String password = request.getParameter("pwd");
		//check session has email?
		SessionInfo info = new SessionInfo();
		if(username!="" && email!="" && password!="" && info.isEmailExisted(request)=="yes"){
			r = "you have signed in! Please logout and try again";
		}else{	
			//try to have a first check on the data received
			if(username.length()<=16&&email.length()<=16 && password.length()<=20){
				//check duplication
				//put in DB
				//sign in (set email session and name session)
				TestDB db = new TestDB();
				info.setEmailInSession(email, request);
				info.setUsernameInSession(username, request);
				ServletContext context = this.getServletConfig().getServletContext();
				context.setAttribute("db1", db);  
				//redirect to cover
				//redirect=true;

			}else{
				r = "Information you input is not in correct form";
			}
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
	}//end of doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean redirect=false;
	    String r = "";
	    String username = request.getParameter("usr");
		String email = request.getParameter("inputEmail");
		String password = request.getParameter("pwd");
		//check session has email?
		SessionInfo info = new SessionInfo();
	    TestDB db = new TestDB();
		if(info.isEmailExisted(request).equals(new String("yes"))){
			r = "You have signed in! Please logout and try again";
		}else{	
			//try to have a first check on the data received
			if(username!="" && email!="" && password!=""){ //&& username.length()<=16&&email.length()<=16 && password.length()<=20){
				//check duplication
				//put in DB
				//sign in (set email session and name session)
				//TestDB db = new TestDB();
				if(db.addEmailRecord(email, password).equals(new String("0"))){
					r = "Email address used! Pleas try another one!";
				}else{ //if(db.addEmailRecord(email, password).equals(new String("1"))){
					info.setEmailInSession(email, request);
					info.setUsernameInSession(username, request);
					r="sign up and log in successfully!";
					ServletContext context = this.getServletConfig().getServletContext();
					context.setAttribute("db1", db);  
				//redirect to cover
				//redirect=true;
				//}else{
					//r = "unknown problem, server bad response";
				}
			}else{
				r = "Information you input is not in correct form";
			}
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
	}//end of do post
	
}//end of class

