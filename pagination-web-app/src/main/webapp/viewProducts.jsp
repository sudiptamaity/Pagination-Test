<%@page import="com.lti.training.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product List</title>
</head>
<body>
	<table align="center" border="1" bgcolor="wheat">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Price</th>
				<th>Quantity</th>
			</tr>
		</thead>
		<tbody>
			<%
			//<!--gets the list of products in the session-->
		List<Product>  products = (List<Product>) request.getAttribute("currentProducts");
		for(Product product : products)
		{
			%>
			<tr>
				<!-- displaying the contents in the table from the product object -->
				<td><%=product.getId() %></td>
				<td><%=product.getName() %></td>
				<td><%=product.getPrice() %></td>
				<td><%=product.getQuantity() %></td>
			</tr>
			<%
			}
			%>

			<tr>
				<th colspan="4">
					<button onclick="go('prev')">&lt;&lt;</button>
					<button onclick="go('next')">&gt;&gt;</button>
					</th>
			</tr>
		</tbody>
	</table>
<script>

function go(page)
{
	window.location.href="ProductControllerServlet?go="+page;
	}
</script>
</body>
</html>