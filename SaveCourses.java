package internshipproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveCourses
 */
@WebServlet("/SaveCourses")
public class SaveCourses extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Course_name=request.getParameter("coursename");
		String Course_code=request.getParameter("coursecode");
		PrintWriter pw=response.getWriter();
			try {
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","root");
				String insert="insert into courses values(?,?)";
				PreparedStatement pre=con.prepareStatement(insert);
				pre.setString(1,Course_name);
				pre.setString(2,Course_code);
				
				pre.executeUpdate();
				pw.println("successfully courses added");
				response.sendRedirect("Grades.html");
				
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
