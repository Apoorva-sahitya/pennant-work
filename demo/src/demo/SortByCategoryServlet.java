package demo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SortByCategory")
public class SortByCategoryServlet extends HttpServlet {
	private CategoryDAO categoryDAO = new CategoryDAL();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = request.getParameter("category");
		String sortDirection = request.getParameter("sort"); // 'asc' or 'desc'
		List<products> productList;

		System.out
				.println("SortByCategoryServlet: Category ID - " + categoryId + ", Sort Direction - " + sortDirection);

		// Check if both category and sort direction are provided
		if (categoryId != null && !categoryId.isEmpty() && sortDirection != null && !sortDirection.isEmpty()) {
			System.out.println("Sorting by category and price.");
			productList = categoryDAO.getProductsByCategoryAndPrice(categoryId, sortDirection);
		}
		// If only category is provided
		else if (categoryId != null && !categoryId.isEmpty()) {
			System.out.println("Sorting by category only.");
			productList = categoryDAO.getProductsByCategory(categoryId);
		}
		// If only sort direction is provided
		else if (sortDirection != null && !sortDirection.isEmpty()) {
			System.out.println("Sorting by price only.");
			productList = categoryDAO.getProductsSortedByPrice(sortDirection);
		}
		// If neither is provided, default to sorting by price in ascending order
		else {
			System.out.println("Default sorting by price in ascending order.");
			productList = categoryDAO.getProductsSortedByPrice("asc");
		}

		if (productList == null) {
			System.out.println("Product list is null.");
		} else {
			System.out.println("Product list size: " + productList.size());
		}

		request.setAttribute("productList", productList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");
		dispatcher.forward(request, response);
	}
}
