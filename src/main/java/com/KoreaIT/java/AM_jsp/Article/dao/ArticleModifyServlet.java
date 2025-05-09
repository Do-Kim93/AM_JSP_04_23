package com.KoreaIT.java.AM_jsp.Article.dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

@WebServlet("/article/modify")
public class ArticleModifyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			response.getWriter()
					.append(String.format("<script>alert('로그인 하고와'); location.replace('../member/login');</script>"));
			return;
		}

		// DB 연결
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 x");
			e.printStackTrace();

		}

		String url = "jdbc:mysql://127.0.0.1:3306/AM_JSP_25_04?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);

			int id = Integer.parseInt(request.getParameter("id"));
			int mid = Integer.parseInt(request.getParameter("mid"));
			int nowid = (int) session.getAttribute("loginedMemberId");
			if(mid == nowid) {
				SecSql sql = SecSql.from("SELECT *");
				sql.append("FROM article inner join");
				sql.append("member on article.mid = member.id");
				sql.append("WHERE article.id = ?;", id);

				Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);

				request.setAttribute("articleRow", articleRow);
				
				if(articleRow == null) {
					// todo
				}

				request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);
			}else{response.getWriter()
			.append(String.format("<script>alert('니가 쓴거아니야'); location.replace('list');</script>"));}

			

		} catch (SQLException e) {
			System.out.println("에러 1 : " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}