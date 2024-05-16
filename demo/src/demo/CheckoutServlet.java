package demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> cart = (List<String>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}

		CategoryDAO checkoutDAL = new CheckoutDAL(); // Using the interface pointer
		List<products> checkoutProducts = checkoutDAL.getCartProducts(cart);
		for (products product : checkoutProducts) {
			int hsnCode = product.gethsn_code(); // Replace with your actual method
			int p = product.getprice();
			int gstRate = checkoutDAL.getGstRateByHsnCode(hsnCode);
			System.out.println("gst by hsn called");
			product.setGstRate(gstRate);
			int ship = checkoutDAL.getShippingChargesByPrice(p);
			System.out.println("Product ID: " + product.getpid() + ", HSN Code: " + hsnCode + ", GST Rate: "
					+ product.getGstRate() + ", price " + p);
		}

		int totalPrice = calculateTotalPrice(checkoutProducts);

		request.setAttribute("checkoutProducts", checkoutProducts);
		request.setAttribute("totalPrice", totalPrice);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Checkout.jsp");
		dispatcher.forward(request, response);
	}

	private int calculateTotalPrice(List<products> products) {
		int total = 0;
		for (products product : products) {
			total += product.getprice();
		}
		return total;
	}
}
