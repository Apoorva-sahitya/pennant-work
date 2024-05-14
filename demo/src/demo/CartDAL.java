// CartDAL.java
package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAL implements CategoryDAO {
	private Connection con;
	private DBconnection db = new DBconnection();

	// Implementing methods from CategoryDAO
	@Override
	public List<products> getProductsByCategory(String categoryId) {
		// Implementation here
		return null;
	}

	@Override
	public List<products> getProductsByCategoryAndPrice(String categoryId, String sortDirection) {
		// Implementation here
		return null;
	}

	@Override
	public List<products> getProductsSortedByPrice(String sortDirection) {
		// Implementation here
		return null;
	}

	// Cart-specific methods
	public List<products> getCartProducts(List<String> productIds) {
		List<products> cartProducts = new ArrayList<>();
		try {
			con = db.loadProperties();
			for (String id : productIds) {
				String query = "SELECT * FROM i186_products WHERE p_id = ?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(id));
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					products product = new products(rs.getInt("p_id"), rs.getString("name"), rs.getInt("price"),
							rs.getString("image"), rs.getInt("hsn"), rs.getInt("cat_id"));
					cartProducts.add(product);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cartProducts;
	}

}
