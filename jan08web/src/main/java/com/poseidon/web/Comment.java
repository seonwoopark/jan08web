package com.poseidon.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.CommentDAO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Comment() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// 한글처리
		HttpSession session = request.getSession();

		if (request.getParameter("commentcontent") != null && Util.intCheck(request.getParameter("bno"))
				&& session.getAttribute("mid") != null) {
			// 오는 값 받기
			String commentcontent = request.getParameter("commentcontent");// 댓글내용
			
			
			
			//HTML에서 태그를 특수기호로 변경하기
			commentcontent = Util.removeTag(commentcontent);
			
			//엔터처리 /r /n /nr
			commentcontent = Util.addBR(commentcontent);
			
			
			
			
			
			String bno = request.getParameter("bno"); // 글번호
			// System.out.println(commentcontent + " : " + bno);

			// 저장해주세요.
			CommentDTO dto = new CommentDTO();
			dto.setComment(commentcontent);
			dto.setBoard_no(Util.str2Int(bno));
			dto.setMid((String) session.getAttribute("mid"));
			dto.setIp(Util.getIP(request));

			CommentDAO dao = new CommentDAO();
			int result = dao.commentWrite(dto);
			// System.out.println("처리결과 : " + result);
			// 이동해주세요.
			if (result == 1) {
				response.sendRedirect("./detail?no=" + bno);
			} else {
				response.sendRedirect("./error.jsp");
			}
		} else {
			response.sendRedirect("./error.jsp");
		}
	}

}
