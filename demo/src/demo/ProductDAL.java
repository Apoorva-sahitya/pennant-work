package demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAL implements ProductDAO {
	private Connection con;
	DBconnection db = new DBconnection();

	@Override
	public List<products> getAllProducts() {
		System.out.println("hii-productDAL");
		List<products> products = new ArrayList<>();
		String query = "SELECT * FROM i186_products";
		try {
			con = db.loadProperties();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				products product = new products(rs.getInt("p_id"), rs.getString("name"), rs.getInt("price"),
						rs.getString("image"), rs.getInt("hsn"), rs.getInt("cat_id"));
				products.add(product);
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
		System.out.println(products);
		return products;
	}
}
