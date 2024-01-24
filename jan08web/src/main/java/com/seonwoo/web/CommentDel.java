package com.seonwoo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seonwoo.dao.BoardDAO;
import com.seonwoo.dto.CommentDTO;
import com.seonwoo.util.Util;

@WebServlet("/commentDel")
public class CommentDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentDel() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_no = request.getParameter("no");
		String cno = request.getParameter("cno");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("mid") != null && board_no != null && Util.checkInt2(cno)) {
			BoardDAO dao = new BoardDAO();
			CommentDTO dto = new CommentDTO();
			dto.setMid((String)session.getAttribute("mid"));
			dto.setCno(Integer.parseInt(cno));
			
			int result = dao.commentDel(dto);
			
			if(result == 1 ) {
				response.sendRedirect("./detail?no="+board_no);
			} else {
				response.sendRedirect("./error.jsp");
				
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		int result = 0;
		if(session.getAttribute("mid") != null && Util.checkInt2(request.getParameter("no"))) {
			BoardDAO dao = new BoardDAO();
			CommentDTO dto = new CommentDTO();
			
			String no = request.getParameter("no");
			
			dto.setMid((String)session.getAttribute("mid"));
			dto.setCno(Integer.parseInt(no));
			
			result = dao.commentDel(dto);
			
		}
		PrintWriter pw = response.getWriter();
		
		pw.print(result);
		
	}

}
