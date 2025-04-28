package com.KoreaIT.java.AM_jsp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/checkLoginIdDup")
public class MemberCheckLoginIdDupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");

        String loginId = request.getParameter("loginId");
        boolean isDup = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://127.0.0.1:3306/AM_JSP_25_04?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            String user = "root";
            String password = "";

            Connection conn = DriverManager.getConnection(url, user, password);

            SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
            sql.append("FROM member WHERE loginId = ?;", loginId);
            Map<String, Object> row = DBUtil.selectRow(conn, sql);
            int count = (int) row.get("cnt");

            isDup = count > 0;

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.getWriter().write(isDup ? "Y" : "N");
    }
}
