package com.seonwoo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seonwoo.dao.BoardDAO;
import com.seonwoo.dao.MemberDAO;

/**
 * Servlet implementation class IDCheck
 */
@WebServlet("/idCheck")
public class IDCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IDCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		MemberDAO dao = new MemberDAO();
		
		int result= dao.checkId(id);
		
		PrintWriter pw = response.getWriter();
		
		pw.print(result);
		
	}

}
