package com.KoreaIT.java.AM_jsp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/article/write")
public class ArticleWriteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if((boolean) session.getAttribute("logId")) {
			response.getWriter().append(String
					.format("<script>alert('로그인이 필요함'); location.replace('../member/login');</script>"));
		}
		request.getRequestDispatcher("/jsp/article/write.jsp").forward(request, response);
	}

}