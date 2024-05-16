<%@ page import="demo.*, java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" type="text/css" href="style.css">   
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <style>
    	.rem, .po{
			color: white;
        	background-color: #20639B;
        	padding: 10px 20px;
    		font-weight: bold;
    		border-radius: 5px;
    		border:none;
		} 
    </style>
    <script>
    function enterPincode() {
        var pincode = prompt("Enter your pincode:");
        if (pincode !== null) {
            console.log("Entered pincode: " + pincode);
            // Call the servlet to validate pincode and product IDs
            fetch('PincodeServlet', {
                method: 'POST',
                body: 'pincode=' + encodeURIComponent(pincode),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            })
            .then(response => response.text())
            .then(result => {
                if (result === 'false') {
                	console.log("js Pincode is serviceable. Redirecting to checkout.");
                    // Proceed to checkout
                    window.location.href = 'Checkout.jsp';
                } else {
                	 console.log("Pincode is not serviceable. Showing alert.");
                     // Display alert and stay on cart page
                     alert('Product are not serviceable region. Kindly remove the frock from the cart.');
                }
            });
        }
    }

    </script>
</head>
<body>
<h2>Shopping Cart</h2>

<div class="navbar">
    <a href="productservlet">Home</a>
    <a href="#login">Login/Register</a>
    <a href="#cart">
	<a href="cart.jsp">
	    <i class="fas fa-shopping-cart" style="vertical-align: middle; margin-right: 5px;"></i>
	    Cart
	</a>
	<a href="CheckoutServlet">Checkout</a>
	
    >
    <div class="dropdown">
        <button class="dropbtn">Sort By 
            <i class="fa fa-caret-down"></i>
        </button>
	<div class="dropdown-content">
	    <a href="SortByCategory?sort=asc">Price Low to High</a>
	    <a href="SortByCategory?sort=desc">Price High to Low</a>
	</div>
    </div> 
    <div class="dropdown">
        <button class="dropbtn">Category 
            <i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown-content">
		    <a href="SortByCategory?category=1">Electronics</a>
		    <a href="SortByCategory?category=2">Apparel</a>
		    <a href="SortByCategory?category=3">Books</a>
		</div>
    </div> 
</div>
<div class="pobtn">
    <button onclick="enterPincode()" class=".po">Place Order</button>
</div>
<%
    List<String> cart = (List<String>) session.getAttribute("cart");
    if (cart == null) {
        cart = new ArrayList<>();
    }
    CartDAL cartDAL = new CartDAL();
    List<products> cartProducts = cartDAL.getCartProducts(cart);
    for (products product : cartProducts) {
%>
    <div class="cards">
        <img src="<%= product.getpimg() %>" alt="Product Image">
        <p><%= product.getpname() %></p>
        <p>Price: <%= product.getprice() %></p>
        <!-- Remove from Cart Form -->
			<form action="${pageContext.request.contextPath}/RemoveFromCartServlet" method="post">
            <input type="hidden" name="productId" value="<%= product.getpid() %>" />
            <input type="submit" value="Remove from Cart" class="rem" />
        </form>
    </div>
<%
    }
%>
</body>
</html>
