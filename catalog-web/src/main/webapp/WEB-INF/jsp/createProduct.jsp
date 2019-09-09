<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Product Catalog</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Product Catalog</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>					
				</ul>
				<ul class="nav navbar-nav">
                					<li class="nav-link"><a href="#">Logout</a></li>
                </ul>
			</div>
		</div>
	</nav>

	<div class="container">
	<form:form action="catalogAdd" method="post" modelAttribute="productForm">
		<div class="starter-template">
		<h2> Add Product</h2>
		   <table class="table ">
		   <thead >
		   <tr class="warning">
		   <th>Product Name:<form:input  path ="productName"/></th>
		   <th align="left"><form:errors path="productName" /></th>
		   <th>USOC:<form:input   path ="usoc" /></th>
		   <th align="left"><form:errors path="usoc" /></th>
		   <th>State Code:<form:input   path ="stateCode"/> </th>
		   <th align="left"><form:errors path="stateCode" /></th>
		   <th> Region Code:<form:input   path ="regionCode" /></th>
		   <th align="left"><form:errors path="regionCode" /></th>
		   <th>Availablity:<form:select  path="available">
		   <option value="Y">Y</option>
		   <option value="N">N</option>
		   </form:select>
		   <th><input type="submit"  class=".btn-danger" value="Add Product" /></th>
		   </tr>
		   </thead>
		   <tbody>
		   </table>
		   </div>
		<div class="starter-template">
		<table class = " table ">
		<thead>
		<tr class ="warning">
		<td>Product Name</td>
		<td>State Code</td>
		<td>Region Code </td>
		<td>USOC </td>
		<td>Availablity</td>
		<td><img src="/images/delete.png" width="30" height="20" alt="delete"></td>
		</tr>
		</thead>
		<tbody>
<c:forEach var="product" items="${products}">
<tr class="active">
	<td><c:out value="${product.productName}"></c:out></td>
	<td><c:out value="${product.stateCode}"></c:out></td>
	<td><c:out value="${product.regionCode}"></c:out></td>
	<td><c:out value="${product.usoc}"></c:out></td>
	<td><c:out value="${product.available}"></c:out></td>
	<td><a href="/catalogDelete?id=${product.id}"><img src="/images/delete.png" width="30" height="20" alt="delete"></a></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</form:form>	
</div>
<!-- /.container -->

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>