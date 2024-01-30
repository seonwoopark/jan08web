package com.poseidon.admin;

import java.io.IOException;
import java.io.PrintWriter;
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
		request.setCharacterEncoding("UTF-8");
		
		BoardDAO dao = new BoardDAO();
		
		int page = 1;
		List<BoardDTO> list = null;
		if(request.getParameter("page") !=null && !request.getParameter("page").equals("")) {
			page = Util.str2Int2(request.getParameter("page"));
		}

		if(request.getParameter("search") ==null) {
			list = dao.boardAdminList(page);
		} else {
			list = dao.boardAdminList(request.getParameter("search"));			
		}
		
		int totalCount = dao.totalCount();
		
		
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/board.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String del = request.getParameter("del");
		String board_no = request.getParameter("board_no");
		
//		System.out.println(del +" : "+ board_no);
		BoardDTO dto = new BoardDTO();
		
		
		dto.setDel(Util.str2Int(del) ==1 ? 0 : 1);
		dto.setNo(Util.str2Int(board_no));
		
		BoardDAO dao = new BoardDAO();
		int result = dao.updateDel(dto);
		
		PrintWriter pw = response.getWriter();
		pw.print(result);
		
	}

}
