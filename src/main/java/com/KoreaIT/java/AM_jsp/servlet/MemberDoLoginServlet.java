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

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");

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

			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");

			SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt FROM `member`");
			sql.append("WHERE loginId = ? and loginPw = ?;", loginId, loginPw);

			boolean isJoinableLoginId = DBUtil.selectRowIntValue(conn, sql) == 0;

			if (isJoinableLoginId) {
				response.getWriter().append(String
						.format("<script>alert('잘못된 정보를 입력하셨습니다'); location.replace('../member/login');</script>"));
				return;
			}


			response.getWriter().append(
					String.format("<script>alert('%s 회원님 안녕하세요'); location.replace('../article/list');</script>", loginId));
			sql = SecSql.from("SELECT * FROM `member`");
			sql.append("WHERE loginId = ? and loginPw = ?;", loginId, loginPw);
			
			Map<String, Object> memberRow = DBUtil.selectRow(conn, sql);
			String logId = (String) memberRow.get("id");

			session.setAttribute("memberRow", memberRow);
			session.setAttribute("logId", logId);

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}