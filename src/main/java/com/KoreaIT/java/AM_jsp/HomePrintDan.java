package com.KoreaIT.java.AM_jsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
//WebServlet(접속할 주소값)
@WebServlet("/home/printDan")
public class HomePrintDan extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Hello World!");
		
		request.getRequestDispatcher("/jsp/home/printDan.jsp").forward(request, response);
	}


}
