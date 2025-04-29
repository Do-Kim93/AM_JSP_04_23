package com.KoreaIT.java.AM_jsp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member/logout")
public class MemberLogOutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();

		Map<String, Object> memberRow = (Map<String, Object>) session.getAttribute("memberRow");
		try {
			if (memberRow.get("id") == null) {
				response.getWriter().append(String
						.format("<script>alert('로그인 되어있지 않습니다.'); location.replace('../member/login');</script>"));
			}
		} catch (Exception e) {
			response.getWriter().append(
					String.format("<script>alert('로그인 되어있지 않습니다.'); location.replace('../member/login');</script>"));
		}

		response.getWriter().append(String.format("<script>alert('로그아웃 되었스비다'); location.replace('../home/main');</script>"));
		session.invalidate();

	}

}