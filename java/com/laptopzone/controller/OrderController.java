package com.laptopzone.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptopzone.service.CartService;
import com.laptopzone.service.MemberService;
import com.laptopzone.service.OrderService;
import com.laptopzone.service.ProductService;

@WebServlet(urlPatterns = {"/order", "/insertOrder", "/orderComplete", "/orderCart", "/insertCartOrder", "/orderList"})
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrderController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String view = null;
		String uri = request.getRequestURI();
		String conPath = request.getContextPath(); 
		String com = uri.substring(conPath.length());
		
		//주문하기
		if(com.equals("/order")) {
			String memberId = request.getParameter("memberId");
			int orderAmount = Integer.parseInt(request.getParameter("amount"));
			int productNum = Integer.parseInt(request.getParameter("productNum"));
			request.setAttribute("productDetail", new ProductService().getProductDetail(productNum));
			request.setAttribute("orderAmount", orderAmount);
			request.setAttribute("memberInfo", new MemberService().getMemberInfo(memberId));
			
			view = "orderPage.jsp";
			
		//장바구니 품목들 주문
		}else if(com.equals("/orderCart")) {
			String memberId = request.getParameter("memberId");
			request.setAttribute("cartList", new CartService().getCartList(memberId));
			request.setAttribute("sum", new CartService().getSumPrice(memberId));
			request.setAttribute("memberInfo", new MemberService().getMemberInfo(memberId));
			
			view = "cartOrderPage.jsp";
		
		//주문정보 입력
		}else if(com.equals("/insertOrder")) {
			String memberId = request.getParameter("memberId");
			int productNum = Integer.parseInt(request.getParameter("productNum"));
			String productName = request.getParameter("productName");
			int productPrice = Integer.parseInt(request.getParameter("orderPrice"));
			int amount = Integer.parseInt(request.getParameter("orderAmount"));
			int totalPrice = Integer.parseInt(request.getParameter("orderPrice"));
			String receiverName = request.getParameter("receiverName");
			String receiverPhone = request.getParameter("receiverPhone");
			String zipcode = request.getParameter("zipcode");
			String address = request.getParameter("address");
			String addressDetail = request.getParameter("addressDetail");
			String addressEtc = request.getParameter("addressEtc");
			
			new OrderService().getInsertOrder(memberId, totalPrice, receiverName, 
					receiverPhone, zipcode, address, addressDetail, addressEtc, productNum, productName, productPrice, amount);

			view = "redirect:orderComplete?memberId="+memberId;
		
		//장바구니 주문정보 입력
		}else if(com.equals("/insertCartOrder")) {
			String memberId = request.getParameter("memberId");
			int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
			String receiverName = request.getParameter("receiverName");
			String receiverPhone = request.getParameter("receiverPhone");
			String zipcode = request.getParameter("zipcode");
			String address = request.getParameter("address");
			String addressDetail = request.getParameter("addressDetail");
			String addressEtc = request.getParameter("addressEtc");
			
			new OrderService().getInsertCartOrder(memberId, totalPrice, receiverName, 
					receiverPhone, zipcode, address, addressDetail, addressEtc);
			
			new CartService().getDeleteCartAll(memberId);
		
			view = "redirect:orderComplete?memberId="+memberId;
		
		//주문완료 페이지
		}else if(com.equals("/orderComplete")) {
			String memberId = request.getParameter("memberId");
			request.setAttribute("orderDetail", new OrderService().getOrderInfo(memberId));
			
			view = "orderComplete.jsp";
		
		//주문목록	
		}else if(com.equals("/orderList")) {
			String memberId = request.getParameter("memberId");
			
			String tmp = request.getParameter("page");

			int pageNum;
			
			if(tmp != null && tmp.length() > 0) {
				pageNum = Integer.parseInt(tmp);
			}else {
				pageNum = 1;
			}
			request.setAttribute("pagination", new OrderService().getPagination(pageNum, memberId));
			request.setAttribute("orderList", new OrderService().getOrderList(memberId , pageNum));
			
			view = "orderList.jsp";
	
		}
		
		
		if(view.startsWith("redirect:")) {
			response.sendRedirect(view.substring(9));
		}else{
			request.getRequestDispatcher(view).forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
