package demo;

import java.util.List;

public interface CategoryDAO {
	List<products> getProductsByCategory(String categoryId);

	List<products> getProductsByCategoryAndPrice(String categoryId, String sortDirection);

	List<products> getProductsSortedByPrice(String sortDirection);

	List<products> getCartProducts(List<String> productIds);

	int getGstRateByHsnCode(int hsnCode);

	int getShippingChargesByPrice(int p);

}
