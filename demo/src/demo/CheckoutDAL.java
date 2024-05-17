package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckoutDAL implements CategoryDAO {
	private Connection con;
	private DBconnection db = new DBconnection();

	@Override
	public List<products> getProductsByCategory(String categoryId) {
		// Implementation for retrieving products by category (if needed)
		return null;
	}

	@Override
	public List<products> getProductsByCategoryAndPrice(String categoryId, String sortDirection) {
		// Implementation for retrieving products by category and price (if needed)
		return null;
	}

	@Override
	public List<products> getProductsSortedByPrice(String sortDirection) {
		// Implementation for retrieving products sorted by price (if needed)
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
							rs.getString("image"), rs.getInt("hsn"), rs.getInt("cat_id"), rs.getInt("discount"));
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

	public int getGstRateByHsnCode(int hsnCode) {
		try {
			Connection con = db.loadProperties(); // Replace with your actual connection setup
			String sql = "SELECT gst FROM hsn_gst_186 WHERE hsn_code = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hsnCode);

			System.out.println("Executing query: " + sql); // Print the query
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int gstRate = rs.getInt("gst");
				System.out.println("GST rate for HSN code " + hsnCode + ": " + gstRate);
				return gstRate;
			} else {
				System.out.println("No GST rate found for HSN code " + hsnCode);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0; // Default GST rate (you can handle this differently)
	}

	public int getShippingChargesByPrice(int p) {
		try {
			System.out.println("dal start"); // Print the query
			Connection con = db.loadProperties(); // Replace with your actual connection setup
			System.out.println(con);
			String sql = "SELECT charges FROM shipping_186 WHERE ? between p_start and p_end";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p);

			System.out.println("Executing query: " + sql); // Print the query
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int ship = rs.getInt("charges");
				System.out.println("Shipping rate for price " + p + ": " + ship);
				return ship;
			} else {
				System.out.println("No ship charges are found for given price " + p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
