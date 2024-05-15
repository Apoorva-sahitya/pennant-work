package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PincodeDAL {
	private Connection con;
	private DBconnection db = new DBconnection();

	public int isServiceable(String pincode, List<String> productIds) {
		try {
			con = db.loadProperties();
			String query = "SELECT COUNT(*) FROM i186_pincodes WHERE p_id = ? AND pin = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			System.out.println("first");
			int nonServiceableProductId = 0;

			for (String productId : productIds) {
				stmt.setInt(1, Integer.parseInt(productId));
				stmt.setInt(2, Integer.parseInt(pincode));
				ResultSet rs = stmt.executeQuery();
				System.out.println("executed");
				if (rs.next() && rs.getInt(1) > 0) {
					// Product ID and pincode match in the table
					System.out.println("Match found for product ID " + productId + " and pincode " + pincode);

				} else {
					// Return the product ID when pincode is not serviceable
					nonServiceableProductId = Integer.parseInt(productId);
					break;

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// Handle empty or invalid pincode (e.g., empty string)
			System.err.println("Invalid pincode: " + pincode);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// No match found, return 0
		return 0;
	}
}
