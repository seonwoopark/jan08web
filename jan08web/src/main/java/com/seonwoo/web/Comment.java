package com.seonwoo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seonwoo.dao.CommentDAO;
import com.seonwoo.dto.CommentDTO;
import com.seonwoo.util.Util;

@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Comment() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String no = request.getParameter("no");
		String commentcontent = request.getParameter("commentcontent");
		
		
		//오는 값 특수기호 변경
		commentcontent = Util.removeTag(commentcontent);
		//엔터처리
		
		commentcontent = Util.addBR(commentcontent);
		// 줄바꿈 처리해주기
		
		
		if(session.getAttribute("mid") != null && no !=null &&commentcontent !=null) {
			//저장해주세요.
			CommentDTO dto = new CommentDTO();
			dto.setBoard_no(Integer.parseInt(no));
			dto.setCcomment(commentcontent);
			dto.setMid((String)session.getAttribute("mid"));
			dto.setIp(Util.getIP(request));
			CommentDAO dao = new CommentDAO();
			
			int result =dao.insertComment(dto);
			
			
			if(result == 1) {
				//이동해주세요.
				response.sendRedirect("./detail?no="+no);
			} else {
				response.sendRedirect("./error.jsp");
			}
			
		} else {
			response.sendRedirect("./error.jsp");
		}
		
	}

}











