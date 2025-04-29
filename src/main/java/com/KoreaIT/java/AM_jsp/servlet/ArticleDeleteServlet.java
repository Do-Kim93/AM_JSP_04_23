package com.KoreaIT.java.AM_jsp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/doDelete")
public class ArticleDeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		

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
			response.getWriter().append("연결 성공!");

			int id = Integer.parseInt(request.getParameter("id"));
			int mid = Integer.parseInt(request.getParameter("mid"));
			int nowid = (int) session.getAttribute("loginedMemberId");
			if(mid == nowid) {
				SecSql sql = SecSql.from("DELETE");
				sql.append("FROM article");
				sql.append("WHERE id = ?;", id);

				DBUtil.delete(conn, sql);

				response.getWriter()
						.append(String.format("<script>alert('%d번 글이 삭제됨'); location.replace('list');</script>", id));
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