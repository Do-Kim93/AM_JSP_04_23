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
		String max = request.getParameter("limit");
		String co = request.getParameter("color");

		

//		response.getWriter().append("8 * 1 = 8\n");
//		response.getWriter().append(String.format("%d * %d = %d<br>", 8, 1, 8));
//		response.getWriter().append(String.format("%d * %d = %d<br>", 8, 2, 16));

		int d = Integer.parseInt(data);
		int maxx = Integer.parseInt(max);
		response.getWriter().append(String.format("==%d단==<br>",d));
//html 태그를 이용할수있다는 점을 이용해서 인라인 css문법으로 컬러를 줄수있다.
		for (int i = 1; i <= maxx; i++) {
			response.getWriter().append(String.format("<div style='color:%s'>%d * %d = %d</div>", co, d, i, d * i));
		}

	}

}