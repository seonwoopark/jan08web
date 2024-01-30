package com.poseidon.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.CommentDAO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

/**
 * Servlet implementation class Comments
 */
@WebServlet("/admin/comments")
public class Comments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CommentDAO dao = new CommentDAO();
		
		int page= 0;
		
		if(request.getParameter("page") == null) {
			page = 1;
		} else {
			page =  Util.str2Int2(request.getParameter("page"));
		}
		
		List<CommentDTO> list = dao.selectComment(page);
		int totalcount = dao.totalCount();
		
		request.setAttribute("list", list);
		request.setAttribute("totalcount", totalcount);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/comments.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
