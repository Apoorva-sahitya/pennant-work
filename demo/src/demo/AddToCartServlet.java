package demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// AddToCartServlet.java
// @WebServlet("/AddToCartServlet")
// public class AddToCartServlet extends HttpServlet {
// protected void doPost(HttpServletRequest request, HttpServletResponse response)
// throws ServletException, IOException {
// HttpSession session = request.getSession();
// List<String> cart = (List<String>) session.getAttribute("cart");
// if (cart == null) {
// cart = new ArrayList<>();
// }
// String productId = request.getParameter("productId");
// cart.add(productId);
// session.setAttribute("cart", cart);
// response.sendRedirect(request.getContextPath() + "/product.jsp");
// }
// }

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> cart = (List<String>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}
		String productId = request.getParameter("productId");
		cart.add(productId);
		session.setAttribute("cart", cart);

		// Redirect back to the referring page
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}
}
