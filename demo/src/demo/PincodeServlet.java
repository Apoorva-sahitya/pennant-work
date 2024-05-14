package demo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// PincodeServlet.java
@WebServlet("/PincodeServlet")
public class PincodeServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pincode = request.getParameter("pincode"); // Get the entered pincode
		List<String> cartProductIds = (List<String>) request.getSession().getAttribute("cart");

		// Validate pincode against the i186_pincodes table
		PincodeDAL pincodeDAL = new PincodeDAL();
		boolean isServiceable = pincodeDAL.isServiceable(pincode, cartProductIds);
		System.out.println("method call");
		if (isServiceable) {
			// Proceed to checkout
			System.out.println("Pincode is not serviceable. Showing alert.");
			response.getWriter().write("false");

		} else {
			// Display alert and stay on cart page
			System.out.println("Pincode is serviceable. Redirecting to checkout.");
			response.sendRedirect("Checkout.jsp");

		}
	}
}
