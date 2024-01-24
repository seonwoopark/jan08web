package com.seonwoo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seonwoo.dao.BoardDAO;
import com.seonwoo.dto.BoardDTO;
import com.seonwoo.util.Util;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		HttpSession session = request.getSession();
		//int no = Util.str2Int(request.getParameter("no"));
		// no가 숫자야 ? + 로그인 했어 ?
		if(Util.checkInt(request.getParameter("no")) && session.getAttribute("mid") != null) {
			int no = Util.str2Int(request.getParameter("no"));
			
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(no);
			dto.setMid((String)session.getAttribute("mid"));
			
			int value = dao.deleteBoard(dto);
			
			if(value==1) {
				response.sendRedirect("./board");
			}else {
				response.sendRedirect("./error.jsp");
			}
			
		} else {
			response.sendRedirect("./error.jsp");
		}
		
		
		//int value = dao.deleteBoard(no);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
