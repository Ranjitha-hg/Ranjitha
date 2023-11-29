package internshipproject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Save_Login
 */
@WebServlet("/Save_Login")
public class Save_Login extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("username");
		String pass=request.getParameter("password");
		PrintWriter pw=response.getWriter();
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","root");
			String filter="select * from users where username=? and userpassword=?";
			PreparedStatement pre=con.prepareStatement(filter);
			pre.setString(1, uname);
			pre.setString(2, pass);
			ResultSet rs=pre.executeQuery();
		
			if(rs.next()) {
				String n=rs.getString("username");
				String p=rs.getString("userpassword");
				if(!uname.equals(n)) {
					pw.println("Login error");
					response.sendRedirect("login_page.html");
				}
				if(!pass.equals(p)) {
					pw.println("Login error");
					response.sendRedirect("login_page.html");
					
				}
				else {
					pw.println("login success");
					response.sendRedirect("admin_page.html");
				}
			}
			else {
				pw.println("Login error");
			}
//			pw.println("successfully login");
//			response.sendRedirect("admin_page.html");
			
		}
		catch(SQLException e) {
			pw.println(e);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
