package com.laptopzone.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.laptopzone.service.MemberService;

@WebServlet(urlPatterns = {"/memberLogin", "/memberJoin", "/memberIdCheck", "/memberLogout", "/idCheck", "/memberInfo", "/updateInfo",
		"/memberUpdate", "/memberDelete", "/searchId", "/searchPwd", "/memberList"})
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberController() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view = null;
		String uri = request.getRequestURI();
		String conPath = request.getContextPath(); 
		String com = uri.substring(conPath.length());
		
		//로그인
		if(com.equals("/memberLogin")) {
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			
			int result = new MemberService().getMemberLogin(memberId, memberPwd);
			
			if(result == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("memberId", memberId);
				view = "redirect:index";		
			}else {
				request.setAttribute("failed", result);
				view = "login.jsp";
			}
		
		//로그아웃
		}else if(com.equals("/memberLogout")) {
			HttpSession session = request.getSession();
			session.removeAttribute("memberId");
			
			request.setAttribute("logout", 1);
			
			view = "index";
		
		//아이디 중복체크
		}else if(com.equals("/idCheck")) {
			String memberId = request.getParameter("memberId");
			request.setAttribute("memberId", memberId);
			request.setAttribute("idCheck", new MemberService().getIdCheck(memberId));

			view = "idCheck.jsp";
		
		//회원가입
		}else if(com.equals("/memberJoin")) {
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			String memberName = request.getParameter("memberName");
			String memberPhone = request.getParameter("memberPhone");
			String memberZipcode = request.getParameter("memberZipcode");
			String memberAddress = request.getParameter("memberAddress");
			String memberAddressDetail = request.getParameter("memberAddressDetail");
			String memberAddressEtc = request.getParameter("memberAddressEtc");
			
			new MemberService().getMemberJoin(memberId, memberPwd, memberName, memberPhone, memberZipcode, 
					memberAddress, memberAddressDetail, memberAddressEtc);
			
			view = "redirect:login.jsp";
			
		//회원정보
		}else if(com.equals("/memberInfo")) {
			String memberId = request.getParameter("memberId");
			request.setAttribute("memberInfo", new MemberService().getMemberInfo(memberId));
			
			view = "memberInfo.jsp";
			
		//회원정보수정	
		}else if(com.equals("/updateInfo")) {
			String memberId = request.getParameter("memberId");
			request.setAttribute("updateCheck", 1);
			request.setAttribute("updateInfo", new MemberService().getMemberInfo(memberId));
			
			view = "memberInfo.jsp";
		
		}else if(com.equals("/memberUpdate")) {
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			String memberName = request.getParameter("memberName");
			String memberPhone = request.getParameter("memberPhone");
			String memberZipcode = request.getParameter("memberZipcode");
			String memberAddress = request.getParameter("memberAddress");
			String memberAddressDetail = request.getParameter("memberAddressDetail");
			String memberAddressEtc = request.getParameter("memberAddressEtc");
			
			new MemberService().getMemberUpdate(memberId, memberPwd, memberName, memberPhone, 
					memberZipcode, memberAddress, memberAddressDetail, memberAddressEtc);
	
			
			view = "redirect:memberInfo?memberId="+memberId;
		
		//회원탈퇴
		}else if(com.equals("/memberDelete")) {
			String memberId = request.getParameter("memberId");
			new MemberService().getMemberDelete(memberId);
			
			view = "redirect:memberLogout";
			
		//아이디 찾기	
		}else if(com.equals("/searchId")) {
			String memberName = request.getParameter("memberName");
			String memberPhone = request.getParameter("memberPhone");
			
			String memberId = new MemberService().getSearchId(memberName, memberPhone);
			
			if(memberId != null) {
				request.setAttribute("memberId", memberId);
				request.setAttribute("findId", 1);
				view = "search.jsp";
			}else {
				request.setAttribute("findId", 0);
				view = "search.jsp";
			}
			
			
		//비밀번호 찾기	
		}else if(com.equals("/searchPwd")) {
			String memberId = request.getParameter("memberId");
			String memberPhone = request.getParameter("memberPhone");
			
			String memberPwd = new MemberService().getSearchPwd(memberId, memberPhone);
			
			if(memberPwd != null) {
				request.setAttribute("memberPwd", memberPwd);
				request.setAttribute("findPwd", 1);
				view = "search.jsp";
			}else {
				request.setAttribute("findPwd", 0);
				view = "search.jsp";
			}
		
		//회원 목록	
		}else if(com.equals("/memberList")) {
			request.setAttribute("memberList", new MemberService().getMemberList());
			
			view = "memberList.jsp";
		}
		
		
			
		
		
		if(view.startsWith("redirect:")) {
			response.sendRedirect(view.substring(9));
		}else{
			request.getRequestDispatcher(view).forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
