package com.laptopzone.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptopzone.service.ProductService;
import com.laptopzone.service.QnAService;

@WebServlet(urlPatterns = {"/qnaList", "/writeQnA", "/insertQnA", "/updateQnA", "/selectQnA", "/deleteQnA", "/replyQnA"})
public class QnAController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QnAController() {
        super();    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view = null;
		String uri = request.getRequestURI();
		String conPath = request.getContextPath(); 
		String com = uri.substring(conPath.length());
		
		//QnA 목록
		if(com.equals("/qnaList")) {
			String tmp = request.getParameter("page");

			int pageNum;
			
			if(tmp != null && tmp.length() > 0) {
				pageNum = Integer.parseInt(tmp);
			}else {
				pageNum = 1;
			}
			request.setAttribute("pagination", new QnAService().getPagination(pageNum));
			request.setAttribute("qnaList", new QnAService().getQnaList(pageNum));
			
			view = "qna.jsp";
		
		//QnA 보기
		}else if(com.equals("/selectQnA")) {
			int qnaNum = Integer.parseInt(request.getParameter("qnaNum"));
			request.setAttribute("selectQnA", new QnAService().getSelectQnA(qnaNum));
			
			view = "selectQnA.jsp";
		
		//QnA 작성 페이지	
		}else if(com.equals("/writeQnA")) {
			//새 글쓰기
			String action = "insertQnA?";
			int qnaNum = 0;
			
			//글 수정
			String tmp = request.getParameter("qnaNum");

			if(tmp != null && tmp.length() > 0) {
				qnaNum = Integer.parseInt(tmp);
			}	

			if(qnaNum > 0) {
				action = "updateQnA?qnaNum="+qnaNum+"&";
				request.setAttribute("selectQnA", new QnAService().getSelectQnaUpate(qnaNum));
			}
			
			//답글
			String tmp2 = request.getParameter("parentNum");
			int parentNum = 0;
			
			if(tmp2 != null && tmp2.length() > 0) {
				parentNum = Integer.parseInt(tmp2);
			}
			
			if(parentNum > 0) {
				action = "replyQnA?parentNum="+parentNum+"&";
				request.setAttribute("reply", "reply");
			}
			
			request.setAttribute("action", action);
			
			view = "writeQnA.jsp";
		
		//QnA 등록
		}else if(com.equals("/insertQnA")) {
			String qnaTitle = request.getParameter("qnaTitle");
			String qnaWriter = request.getParameter("qnaWriter");
			String qnaContent = request.getParameter("qnaContent");
			
			new QnAService().getInsertQnA(qnaTitle, qnaWriter, qnaContent);
			
			view = "redirect:qnaList";
			
		//QnA 수정	
		}else if(com.equals("/updateQnA")) {
			int qnaNum = Integer.parseInt(request.getParameter("qnaNum"));
			String qnaTitle = request.getParameter("qnaTitle");
			String qnaContent = request.getParameter("qnaContent");
			
			new QnAService().getUpdateQnA(qnaNum, qnaTitle, qnaContent);
			
			view = "redirect:selectQnA?qnaNum="+qnaNum;
		
		//QnA 삭제	
		}else if(com.equals("/deleteQnA")) {
			int qnaNum = Integer.parseInt(request.getParameter("qnaNum"));
			new QnAService().getDeleteQnA(qnaNum);
			
			view = "redirect:qnaList";
		
		//QnA 답글
		}else if(com.equals("/replyQnA")) {
			int parentNum = Integer.parseInt(request.getParameter("parentNum"));
			String qnaTitle = request.getParameter("qnaTitle");
			String qnaWriter = request.getParameter("qnaWriter");
			String qnaContent = request.getParameter("qnaContent");
			
			new QnAService().getInsertReply(parentNum, qnaTitle, qnaWriter, qnaContent);
			
			view = "redirect:qnaList";
		}
		
			
			
		
		if(view.startsWith("redirect:")) {
			response.sendRedirect(view.substring(9));
		}else{
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
