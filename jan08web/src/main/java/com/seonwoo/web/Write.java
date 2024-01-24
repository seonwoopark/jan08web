package com.seonwoo.web;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.tag.el.fmt.RequestEncodingTag;

import com.seonwoo.dao.BoardDAO;
import com.seonwoo.dao.LogDAO;
import com.seonwoo.dto.BoardDTO;
import com.seonwoo.util.Util;

/**
 * Servlet implementation class Write
 */
@WebServlet("/write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Write() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		LogDAO log = new LogDAO();
		log.write(Util.getIP(request),getServletName(),null);
		if(session.getAttribute("mname") ==null) {
			response.sendRedirect("./login");	// url까지 변경해서 화면 보여줍니다.
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("write.jsp"); // url 고정, 화면만 변경
			rd.forward(request, response);	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("mid") == null || session.getAttribute("mname")==null) {
			//로그인 하지 않았다면 login
			response.sendRedirect("./login?login=nologin");
		} else {
			//로그인 했다면
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String mno =request.getParameter("mno");
			
			title = Util.removeTag(title);
			
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = new BoardDTO();

			dto.setBoard_title(title);
			dto.setBoard_content(content);
			dto.setMno(mno);
			dto.setIp(Util.getIP(request));
			
			int result = dao.write(dto);
			
			if(result == 1) {
				response.sendRedirect("./board");
				
			} else {
				response.sendRedirect("./error");
				
			}
		}
		
		
		
	}

}
