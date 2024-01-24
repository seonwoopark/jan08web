package com.seonwoo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seonwoo.dao.LogDAO;
import com.seonwoo.dao.MemberDAO;
import com.seonwoo.dto.MemberDTO;
import com.seonwoo.util.Util;

@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Join() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LogDAO dao = new LogDAO();
		dao.write(Util.getIP(request),getServletName(),null);
		RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw1 = request.getParameter("pw1");
		
		MemberDAO dao = new MemberDAO();
		
		MemberDTO dto = new MemberDTO();
		dto.setMid(id);
		dto.setMname(name);
		dto.setMpw(pw1);
		
		int result = dao.insertMember(dto);
		
		if(result ==1) {
			response.sendRedirect("./login");
		} else {
			response.sendRedirect("./error.jsp");
		}
		
	}

}
