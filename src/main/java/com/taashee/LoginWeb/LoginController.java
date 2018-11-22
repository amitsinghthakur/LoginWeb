package com.taashee.LoginWeb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println("uname:++++++++++" + uname);
		System.out.println("pass:++++++++++" + pass);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql:3306/sampledb", "userYOP",
					"4yNuy85aIcIfdhLU");
			PreparedStatement ps = con
					.prepareStatement("select userName,pass from student where userName=? and pass=?");
			ps.setString(1, uname);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				response.sendRedirect("success.html");
				return;
			}
			response.sendRedirect("error.html");
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
