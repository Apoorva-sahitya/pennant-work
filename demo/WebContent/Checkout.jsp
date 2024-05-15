<%@ page import="demo.*, java.util.List, java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" type="text/css" href="style.css"> 
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
      
     <style>
       table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid white;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #20639B;
            color: white;
        }
        .total-price {
            margin-top: 20px;
            font-weight: bold;
        }
        form {
            margin-top: 20px;
        }
        .pay{
        	color: white;
        	background-color: #20639B;
        	padding: 10px 20px;
    		font-weight: bold;
    		border-radius: 5px;
    		border:none;
        }
        .tp{
        	background-color: #34495E;
        	color: white;
        	text-align:center;
        	padding: 5px;
        }
        .paybtn{ text-align:center; }
    </style>
</head>
<body>
    <h2>Checkout Page</h2>
    
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

    <table>
        <thead>
            <tr>
            	<th>Product Id</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Discount</th>
                
            </tr>
        </thead>
        <tbody>
            <%
                List<String> cart = (List<String>) session.getAttribute("cart");
                if (cart == null) {
                    cart = new ArrayList<>();
                }
                CartDAL cartDAL = new CartDAL();
                List<products> cartProducts = cartDAL.getCartProducts(cart);
                int totalPrice = 0; // Initialize total price

                for (products product : cartProducts) {
                    totalPrice += product.getprice(); // Calculate total price
            %>
            <tr>
                <td><%= product.getpid() %></td>   	
                <td><%= product.getpname() %></td>
                <td><%= product.getprice() %></td>
                <td><%= product.getdiscount() %></td>
                
            </tr>
            <%
                }
            %>
            <tr>
                <td>Total</td>
                <td><%= totalPrice %></td>
            </tr>
        </tbody>
    </table>
        <p class="tp">Total Price: <%= totalPrice %></p>
   <div class="paybtn"> <button id="rzp-button1" class="pay"><i class="fas fa-money-bill"></i> Proceed to Payment</button></div>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script>
  var options = {
    "key": "rzp_test_dLaNDY0zz27PMY", // Enter the Key ID generated from the Dashboard
    "amount": "<%= totalPrice * 100 %>",
    "currency": "INR",
    "description": "Acme Corp",
    "image": "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg",
    "prefill":
    {
      "email": "gaurav.kumar@example.com",
      "contact": +919900000000,
    },
    config: {
      display: {
        blocks: {
          utib: { //name for Axis block
            name: "Pay using Axis Bank",
            instruments: [
              {
                method: "card",
                issuers: ["UTIB"]
              },
              {
                method: "netbanking",
                banks: ["UTIB"]
              },
            ]
          },
          other: { //  name for other block
            name: "Other Payment modes",
            instruments: [
              {
                method: "card",
                issuers: ["ICIC"]
              },
              {
                method: 'netbanking',
              }
            ]
          }
        },
        hide: [
          {
          method: "upi"
          }
        ],
        sequence: ["block.utib", "block.other"],
        preferences: {
          show_default_blocks: false // Should Checkout show its default blocks?
        }
      }
    },
    "handler": function (response) {
      alert(response.razorpay_payment_id);
    },
    "modal": {
      "ondismiss": function () {
        if (confirm("Are you sure, you want to close the form?")) {
          txt = "You pressed OK!";
          console.log("Checkout form closed by the user");
        } else {
          txt = "You pressed Cancel!";
          console.log("Complete the Payment")
        }
      }
    }
  };
  var rzp1 = new Razorpay(options);
  document.getElementById('rzp-button1').onclick = function (e) {
    rzp1.open();
    e.preventDefault();
  }
</script>

    <!-- Other content or styling as needed -->

</body>
</html>
