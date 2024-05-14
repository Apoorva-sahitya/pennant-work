<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="demo.products" %>
<!DOCTYPE html>
<html>
<head>
    <title>Online Store</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="style.css">   
     <style>
        input.add-to-cart-btn { 
			background-color: #0056b3; 
			color: white; 
			border: none; 
			padding: 10px 20px; 
			font-weight: bold;
			border-radius: 5px; 
		 }
		 input.add-to-cart-btn :hover {
		    background-color: green; /* Darker blue on hover */
		    box-shadow: 0 2px 5px 0 rgba(0,0,0,0.24), 0 2px 10px 0 rgba(0,0,0,0.19); /* Add shadow on hover */
		}
    </style>
</head>
<body>
<h2>Online Store</h2>

<div class="navbar">
    <a href="productservlet">Home</a>
    <a href="#login">Login/Register</a>
    <a href="#cart">
	<a href="cart.jsp">
	    <i class="fas fa-shopping-cart" style="vertical-align: middle; margin-right: 5px;"></i>
	    Cart
	</a>
	
    
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

<div class="cards-container">
   <%
    List<products> productList = (List<products>) request.getAttribute("productList");
    for (products product : productList) {
%>
    <div class="card">
        <img src="<%= product.getpimg() %>" alt="Product Image">
        <div class="card-container">
            <h3><%= product.getpname() %></h3>
            <p>Price: <%= product.getprice() %></p>
            <p>HSN: <%= product.gethsn_code() %></p>
            <p>Category ID: <%= product.getcat_id() %></p>
            <!-- Add to Cart Form -->
			<form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
                <input type="hidden" name="productId" value="<%= product.getpid() %>" />
                <input type="submit" class="add-to-cart-btn" value="Add To Cart" />
            </form>
        </div>
    </div>
<%
    }
%>

</div>

</body>
</html>
