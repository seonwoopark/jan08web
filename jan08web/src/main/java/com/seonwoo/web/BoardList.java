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

import com.seonwoo.dao.BoardDAO;
import com.seonwoo.dao.LogDAO;
import com.seonwoo.dto.BoardDTO;
import com.seonwoo.util.Util;

@WebServlet("/board")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//int page = Util.str2Int2(request.getParameter("page"));
		
		int page = 1;
		
		if(request.getParameter("page") != null && request.getParameter("page") != "") {
			page = Util.str2Int(request.getParameter("page"));
		}
		
			Map<String, Object> log = new HashMap<String, Object>();
			log.put("ip", Util.getIP(request));
			log.put("url", "./board");
			log.put("data", "page="+page);
			
			LogDAO logDAO = new LogDAO();
			logDAO.write(log);
			
			BoardDAO dao = new BoardDAO();
			List<BoardDTO> list = dao.selectBoard(page);
			int totalCount = dao.totalCount();
			
			request.setAttribute("page", page);
			request.setAttribute("list", list);
			request.setAttribute("total", totalCount);
			RequestDispatcher rd = request.getRequestDispatcher("board.jsp");
			rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
