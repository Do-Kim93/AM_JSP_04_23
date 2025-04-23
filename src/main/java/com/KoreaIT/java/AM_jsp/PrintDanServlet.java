package com.KoreaIT.java.AM_jsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
//WebServlet(접속할 주소값)
@WebServlet("/printDan")
public class PrintDanServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//html문법과 java문법을 혼용가능 
		response.setContentType("text/html;charset=UTF-8");
		
		String data = request.getParameter("dan");

		

//		response.getWriter().append("8 * 1 = 8\n");
//		response.getWriter().append(String.format("%d * %d = %d<br>", 8, 1, 8));
//		response.getWriter().append(String.format("%d * %d = %d<br>", 8, 2, 16));

		int d = Integer.parseInt(data);
		response.getWriter().append(String.format("==%d단==<br>",d));

		for (int i = 1; i <= 9; i++) {
			response.getWriter().append(String.format("%d * %d = %d<br>", d, i, d * i));
		}

	}

}