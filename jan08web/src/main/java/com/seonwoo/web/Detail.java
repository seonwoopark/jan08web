package com.seonwoo.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seonwoo.dao.BoardDAO;
import com.seonwoo.dao.LogDAO;
import com.seonwoo.dto.BoardDTO;
import com.seonwoo.dto.CommentDTO;
import com.seonwoo.util.Util;

@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Detail() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		int no = Util.str2Int(request.getParameter("no"));
		
		//읽음수 올리기 2024-01-19
		//bno,mno
		HttpSession session = request.getSession();
		
		
		dao.write(Util.getIP(request),getServletName(),"no ="+no);
		
		
		if(session.getAttribute("mid") != null) {
			dao.countUp( no, (String) session.getAttribute("mid") );
		}
		
		
		BoardDTO dto = dao.detailBoard(no);
		if(dto.getBoard_title()==null || no==0) {
			response.sendRedirect("error.jsp");
		} else {
			
			
			
			request.setAttribute("detail", dto);
			List<CommentDTO> list = dao.selectComment(no);
			
			
			if(list.size()>0) {
				request.setAttribute("list", list);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
			rd.forward(request, response);
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
