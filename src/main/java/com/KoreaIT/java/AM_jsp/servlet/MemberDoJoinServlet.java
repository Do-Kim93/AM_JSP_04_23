package com.KoreaIT.java.AM_jsp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/doJoin")
public class MemberDoJoinServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String url = "jdbc:mysql://127.0.0.1:3306/AM_JSP_25_04?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);

            String loginId = request.getParameter("loginId");
            String loginPw = request.getParameter("loginPw");
            String name = request.getParameter("name");

            // 비어있으면 실패처리 가능 (추가 가능)
            if (loginId == null || loginPw == null || name == null || 
                loginId.trim().isEmpty() || loginPw.trim().isEmpty() || name.trim().isEmpty()) {
                response.getWriter().append("<script>alert('모든 값을 입력해주세요.'); history.back();</script>");
                return;
            }

            SecSql sql = SecSql.from("INSERT INTO member");
            sql.append("SET regDate = NOW(),");
            sql.append("loginId = ?,", loginId);
            sql.append("loginPw = ?,", loginPw);
            sql.append("name = ?", name);

            int id = DBUtil.insert(conn, sql);

            response.getWriter()
                    .append(String.format("<script>alert('회원가입 완료! [%d번 회원]'); location.replace('../home/main');</script>", id));

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().append("<script>alert('회원가입 실패!'); history.back();</script>");
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("<script>alert('잘못된 접근입니다.'); history.back();</script>");
    }
}
