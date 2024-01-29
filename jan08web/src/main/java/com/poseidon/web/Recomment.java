package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.CommentDAO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;


@WebServlet("/recomment")
public class Recomment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Recomment() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int result =0;
		if(session.getAttribute("mid") != null &&
				Util.intCheck2(request.getParameter("cno")) && request.getParameter("recomment") != null) {
			int cno = Util.str2Int2(request.getParameter("cno"));
			String recomment = Util.addBR(request.getParameter("recomment"));
			
			CommentDTO dto = new CommentDTO();
			dto.setCno(cno);
			dto.setComment(recomment);
			dto.setMid((String)session.getAttribute("mid"));
			
			CommentDAO dao = new CommentDAO();
			result = dao.commentUpdate(dto);
			
			System.out.println(result);
		
		}
		
		PrintWriter pw = response.getWriter();
		
		pw.print(result);

		
	}

}
