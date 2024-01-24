package com.seonwoo.web;

import java.io.IOException;

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
import com.seonwoo.util.Util;

/**
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(Util.checkInt(request.getParameter("no")) && session.getAttribute("mid") != null) {
			int no = Util.str2Int(request.getParameter("no"));
			
			BoardDAO dao = new BoardDAO();

			dao.write(Util.getIP(request),getServletName(),null);
			BoardDTO dto = dao.detailBoard(no);
			
//			System.out.println(dto.getMid().equals(session.getAttribute("mid")));
//			System.out.println(session.getAttribute("mid").equals(dto.getMid()));
//			System.out.println(session.getAttribute("mid") == dto.getMid());
//			System.out.println(((String)session.getAttribute("mid")).equals(dto.getMid()));
			
			
			if(dto.getMid().equals((String)session.getAttribute("mid"))) {
				request.setAttribute("update", dto);
				RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("./error.jsp");
			}
			
		} else {
			response.sendRedirect("./error.jsp");
		}
			
//			if(no==0 || dto.getBoard_content() == null) {
//				response.sendRedirect("./error.jsp");
//			} else {
//				request.setAttribute("update", dto);
//				RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
//				rd.forward(request, response);
//			}
//			
//		} else {
//			response.sendRedirect("./error.jsp");
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(request.getParameter("title")!=null || request.getParameter("content")!=null
				|| Util.checkInt(request.getParameter("no")) && session.getAttribute("mid") != null) {
			
			
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = new BoardDTO();

			dto.setBoard_title(request.getParameter("title"));
			dto.setBoard_content(request.getParameter("content"));
			dto.setBoard_no(Integer.parseInt(request.getParameter("no")));
			dto.setMid((String)session.getAttribute("mid"));
			
			
			int result = dao.updateBoard(dto);
			
			if(result==0) {
				response.sendRedirect("./error.jsp");
			} else {
				response.sendRedirect("./detail?no="+request.getParameter("no"));
			}
		} else {
			response.sendRedirect("./error.jsp");
		}
	}

}






