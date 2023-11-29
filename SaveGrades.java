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
 * Servlet implementation class SaveGrades
 */
@WebServlet("/SaveGrades")
public class SaveGrades extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Student_Name=request.getParameter("studentName");
		String Subject=request.getParameter("subject");
		String Score=request.getParameter("score");
		PrintWriter pw=response.getWriter();
		
			try {
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","root");
				String insert="insert into stud_grades values(?,?,?)";
				PreparedStatement pre = con.prepareStatement(insert);
				pre.setString(1,Student_Name);
				pre.setString(2,Subject);
				pre.setString(3,Score);
				
				pre.executeUpdate();
				pw.println("successfully grades added");
				
			}catch(SQLException e) {
				pw.println(e);
			}
		}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
