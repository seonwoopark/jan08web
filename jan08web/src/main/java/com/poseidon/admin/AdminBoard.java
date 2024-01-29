package com.poseidon.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;


@WebServlet("/admin/adminboard")
public class AdminBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminBoard() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		int page = 1;
		
		if(request.getParameter("page") !=null && request.getParameter("page") != "") {
			page = Util.str2Int2(request.getParameter("page"));
		}
		
		List<BoardDTO> list = dao.boardAdminList(page);
		int totalCount = dao.totalCount();
		
		
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/board.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		
		
	}

}
