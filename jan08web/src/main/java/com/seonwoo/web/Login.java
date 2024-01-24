package com.seonwoo.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seonwoo.dao.LogDAO;
import com.seonwoo.dao.MemberDAO;
import com.seonwoo.dto.MemberDTO;
import com.seonwoo.util.Util;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String url = "";
		if(session.getAttribute("mname") != null) {
			url ="check.jsp";
		} else {
			url="login.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2024-01-12
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		
		if(request.getParameter("id") != null && request.getParameter("pw") !=null) {
			MemberDTO dto = new MemberDTO();
			dto.setMid(request.getParameter("id"));
			dto.setMpw(request.getParameter("pw"));
			
			MemberDAO dao = new MemberDAO();
			dto = dao.login(dto);
			//아이피도 저장하기
			Map<String, Object> log = new HashMap<String, Object>();
			log.put("ip", Util.getIP(request));
			log.put("url", "./login");
			log.put("data", "id :"+dto.getMid() + ", pw :"+dto.getMpw() + ", 결과 : " + dto.getCount());
			
			LogDAO logDAO = new LogDAO();
			logDAO.write(log);
			
			if(dto.getCount()==1) {
				System.out.println("정상 로그인");
				//세션 만들기
				HttpSession session = request.getSession();
				session.setAttribute("mname", dto.getMname());
				session.setAttribute("mid", dto.getMid());
				session.setAttribute("mno", dto.getMno());
				//페이지 이동 board
				response.sendRedirect("./board");
				
			}else {
				System.out.println("헬로우 사만다 = 안녕하세요 ");
				//페이지 이동 = login
				response.sendRedirect("./login?error=4567");
			}
		}
		
		
		
	}

}
