package internshipproject;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Savedata
 */
@WebServlet("/Savedata")
public class Savedata extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("username");
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		String cpass=request.getParameter("confirmPassword");
		PrintWriter pw=response.getWriter();
		pw.println(uname);
		if(pass.equals(cpass)) {
			pw.println("match");
			try {
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","root");
				String insert="insert into users values(?,?,?,?)";
				PreparedStatement pre=con.prepareStatement(insert);
				pre.setString(1, uname);
				pre.setString(2, email);
				pre.setString(3, pass);
				pre.setString(4, cpass);
				
				pre.executeUpdate();
				pw.println("successfully registred");
				response.sendRedirect("login_page.html");
				
			}
			catch(SQLException e) {
				pw.println(e);
			}
		}
		else {
			
			pw.println("password and confirm password mismatch");
			response.sendRedirect("regestration.html");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
