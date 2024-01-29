package com.poseidon.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;
import com.poseidon.util.Util;

/**
 * Servlet implementation class Members
 */
@WebServlet("/admin/members")
public class Members extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Members() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> list = null;
		if(request.getParameter("grade") == null || request.getParameter("grade").equals("")) {
			list = dao.selectMember();
		
		} else {
			list = dao.selectMember(Util.str2Int2(request.getParameter("grade")));
		}
		
		
		request.setAttribute("list", list);
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/members.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDTO dto = new MemberDTO();
		dto.setMno(Integer.parseInt(request.getParameter("mno")));
		dto.setMgrade(Integer.parseInt(request.getParameter("grade")));
		
		
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.updateGrade(dto);
		
		if(result ==1) {
			if(request.getParameter("prevGrade") != null) {				
				response.sendRedirect("./members?grade="+request.getParameter("prevGrade"));
			} else {
				response.sendRedirect("./members");
			}
			
			
		}else {
			response.sendRedirect("../error.jsp");
		}
		
	}

}
