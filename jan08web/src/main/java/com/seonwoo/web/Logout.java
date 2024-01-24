package com.seonwoo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 세션 종료
		HttpSession session = request.getSession();
		
		/*
		 * 세션 쿠키
		 * 세션 : 서버에 저장됩니다.            쿠키 : 클라이어언트에 저장 (브라우저)
		 *        로그인 정보                          쇼핑정보, 장바구니, 방문내역
		 *        자바                                 스크립트
		 *        
		 * JWT 
		 * 
		 */
		
		
		if(session.getAttribute("mname") != null) {
//			session.setMaxInactiveInterval(3600); //세션 시간 연장
//			System.out.println("세션 유효시간 : " + session.getMaxInactiveInterval());
//			System.out.println("mname : " + session.getAttribute("mname"));
			session.removeAttribute("mname");
		}
		
		if(session.getAttribute("mid") != null) {
//			System.out.println("mid : " + session.getAttribute("mid"));
			session.removeAttribute("mid");
		}
		
		/*
		 * invalidate()는 세션 자체를 무효화하고 제거하고
		 * removeAttribute()는 현재 세션에서 특정 key-value만 제거를 한다
		 * removeAttribute()로 키만 제거를 하면 httpSession 인스턴스가 남아있어
		 * invalidate()해주는것이 좋습니다.
		 */
		
		session.invalidate();
		// login 페이지로 보내기
		//response.sendRedirect("./logout.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("logout.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
