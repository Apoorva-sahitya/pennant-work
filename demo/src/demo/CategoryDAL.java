package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAL implements CategoryDAO {
	private Connection con;
	DBconnection db = new DBconnection();

	public List<products> getProductsByCategory(String categoryId) {
		System.out.println("CategoryDAL: getProductsByCategory() called with categoryId: " + categoryId);
		List<products> productList = new ArrayList<>();
		String sql = "SELECT * FROM i186_products WHERE cat_id = ? ORDER BY cat_id";
		try {
			con = db.loadProperties();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(categoryId));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				products product = new products(rs.getInt("p_id"), rs.getString("name"), rs.getInt("price"),
						rs.getString("image"), rs.getInt("hsn"), rs.getInt("cat_id"), rs.getInt("discount"));
				productList.add(product);
				System.out.println("Product added: " + product);
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					System.out.println("Database connection closed");
				} catch (SQLException e) {
					System.out.println("SQL Exception on close: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return productList;
	}

	public List<products> getProductsSortedByPrice(String sortDirection) {
		List<products> productList = new ArrayList<>();
		String sql = "SELECT * FROM i186_products ";
		if ("asc".equalsIgnoreCase(sortDirection)) {
			sql += "ORDER BY price ASC";
		} else if ("desc".equalsIgnoreCase(sortDirection)) {
			sql += "ORDER BY price DESC";
		}

		try {
			con = db.loadProperties();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				products product = new products(rs.getInt("p_id"), rs.getString("name"), rs.getInt("price"),
						rs.getString("image"), rs.getInt("hsn"), rs.getInt("cat_id"), rs.getInt("discount"));
				productList.add(product);
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
		return productList;
	}

	public List<products> getProductsByCategoryAndPrice(String categoryId, String sortDirection) {
		List<products> productList = new ArrayList<>();
		String sql = "SELECT * FROM i186_products WHERE cat_id = ? ";
		if ("asc".equalsIgnoreCase(sortDirection)) {
			sql += "ORDER BY price ASC";
		} else if ("desc".equalsIgnoreCase(sortDirection)) {
			sql += "ORDER BY price DESC";
		} else {
			sql += "ORDER BY cat_id"; // Default sorting
		}

		System.out.println("CategoryDAL: SQL Query - " + sql);

		try {
			con = db.loadProperties();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(categoryId));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				products product = new products(rs.getInt("p_id"), rs.getString("name"), rs.getInt("price"),
						rs.getString("image"), rs.getInt("hsn"), rs.getInt("cat_id"), rs.getInt("discount"));
				productList.add(product);
				System.out.println("Product added: " + product.getpname());
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					System.out.println("Database connection closed");
				} catch (SQLException e) {
					System.out.println("SQL Exception on close: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return productList;
	}

	@Override
	public List<products> getCartProducts(List<String> productIds) {
		// TODO Auto-generated method stub
		return null;
	}

}
