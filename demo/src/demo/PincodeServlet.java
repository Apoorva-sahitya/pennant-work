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
		String pincode = request.getParameter("pincode");
		List<String> cartProductIds = (List<String>) request.getSession().getAttribute("cart");

		PincodeDAL pincodeDAL = new PincodeDAL();
		int nonServiceableProductId = pincodeDAL.isServiceable(pincode, cartProductIds);
		if (nonServiceableProductId != 0) {
			// Return the non-serviceable product ID as response
			response.getWriter().write(String.valueOf(nonServiceableProductId));
		} else {
			// Return '0' when pincode is serviceable
			response.getWriter().write("0");
		}
	}

}
