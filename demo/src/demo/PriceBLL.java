package demo;

import java.util.List;

public class PriceBLL implements CategoryDAO {
	public double getTotalPrice(List<products> checkoutProducts) {
		double basic_gst;
		double disc_price;
		double actual_price;
		double actual_gst;
		double real_price;
		for (products product : checkoutProducts) {
			int pr = product.getprice();
			int gt = product.getGstRate();
			int ds = product.getdiscount();
			real_price = pr / (1 + (gt / 100));
			basic_gst = pr - real_price;
			disc_price = (real_price * ds) / 100;
			actual_price = real_price - disc_price;
			actual_gst = (actual_price * basic_gst) / real_price;
			actual_price = actual_gst + actual_price;
			product.setActualPrice(actual_price);
			product.setActualGst(actual_gst);

		}
		return 0;

	}

	@Override
	public List<products> getProductsByCategory(String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<products> getProductsByCategoryAndPrice(String categoryId, String sortDirection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<products> getProductsSortedByPrice(String sortDirection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<products> getCartProducts(List<String> productIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGstRateByHsnCode(int hsnCode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getShippingChargesByPrice(int p) {
		// TODO Auto-generated method stub
		return 0;
	}
}
