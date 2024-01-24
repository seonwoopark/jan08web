package com.seonwoo.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seonwoo.dao.MemberDAO;
import com.seonwoo.dto.BoardDTO;
import com.seonwoo.dto.MemberDTO;

@WebServlet("/myInfo")
public class MyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		if(session.getAttribute("mid") != null) {
			// mid 데이터베이스에 질의
			MemberDAO dao = new MemberDAO();
			
			// dto에 담아서
			MemberDTO dto = dao.myInfo((String)session.getAttribute("mid"));
			// myInfo.jsp에 찍어주도록
			request.setAttribute("dto", dto);
			
			List<Map<String, Object>> list = dao.readData(dto);
			request.setAttribute("readData", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("myInfo.jsp");
			rd.forward(request, response);
			
		} else {
			response.sendRedirect("./login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("newPw") != null && session.getAttribute("mid") !=null) {

			String pw = request.getParameter("newPw");
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = new MemberDTO();
			dto.setMpw(pw);
			dto.setMid((String)session.getAttribute("mid"));
			
			dao.updatePw(dto);
			
			response.sendRedirect("./index");
			
		}else {
			response.sendRedirect("./error.jsp");
		}
	}

}
