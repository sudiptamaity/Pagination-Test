package com.lti.training.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lti.training.dao.ProductDao;
import com.lti.training.model.Product;

public class ProductControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageSize = 3;// for 3 records to be displayed in the table

		// storing the current position in a session
		HttpSession session = request.getSession();
		Integer currentPosition = (Integer) session.getAttribute("cp");
		
		if (currentPosition == null) // if not
		{
			currentPosition = 1; 
		}
			
		// string to get whether it was a previous or next
		String go = request.getParameter("go");
		// logic for showing 3 products on the a web page
		if (go != null) {
			if (go.equals("next"))
				currentPosition += pageSize;
			else if (go.equals("prev"))
				currentPosition -=pageSize;
		} else
			currentPosition = 1;
session.setAttribute("cp", currentPosition);

ProductDao dao=new ProductDao();
List<Product> products=dao.fetchProducts(currentPosition,currentPosition+pageSize );

//this is an alternative to storing the products in a session
request.setAttribute("currentProducts",products);
//redirecting the page back to the JSP instead of redirecting the page directly
RequestDispatcher dispatcher=request.getRequestDispatcher("viewProducts.jsp");
dispatcher.forward(request,response);

//response.sendRedirect("viewProducts.jsp");
	}

}
