package com.laptopzone.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptopzone.service.CartService;
import com.laptopzone.service.ProductService;

@WebServlet(urlPatterns = {"/addCart", "/cartList", "/deleteCart", "/deleteCartAll"})
public class CartController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public CartController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view = null;
		String uri = request.getRequestURI();
		String conPath = request.getContextPath(); 
		String com = uri.substring(conPath.length());
		
		//장바구니 담기
		if(com.equals("/addCart")) {
			int productNum = Integer.parseInt(request.getParameter("productNum"));
			String memberId = request.getParameter("memberId");
			int amount = Integer.parseInt(request.getParameter("amount"));
			new CartService().getAddCart(memberId, productNum, amount);
			
			view = "redirect:productDetail?productNum=" + productNum;
		
		//장바구니 목록
		}else if(com.equals("/cartList")) {
			String memberId = request.getParameter("memberId");
			
			String tmp = request.getParameter("page");

			int pageNum;
			
			if(tmp != null && tmp.length() > 0) {
				pageNum = Integer.parseInt(tmp);
			}else {
				pageNum = 1;
			}

			request.setAttribute("pagination", new CartService().getPagination(pageNum, memberId));
			request.setAttribute("cartList", new CartService().getCartList(memberId, pageNum));
			request.setAttribute("sum", new CartService().getSumPrice(memberId));
			
			view = "cart.jsp";
			
		//장바구니 삭제	
		}else if(com.equals("/deleteCart")) {
			String memberId = request.getParameter("memberId");
			int cartNum = Integer.parseInt(request.getParameter("cartNum"));
			new CartService().getDeleteCart(cartNum);
			
			view = "redirect:cartList?memberId="+memberId;
		
		//장바구니 전체삭제
		}else if(com.equals("/deleteCartAll")) {
			String memberId = request.getParameter("memberId");
			new CartService().getDeleteCartAll(memberId);
			
			view = "redirect:cartList?memberId="+memberId;
			
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
