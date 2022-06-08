<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/8/2022
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<a href="/products?action=findname"></a>--%>
<form action="/products">
    <input type="hidden" name="action" value="search">
    <input type="text" name="name" placeholder="search">
    <button>Search</button>
</form>
<h2>Search by price range</h2>
<form action="/products">
    <input type="hidden" name="action" value="searchprice">
    <input type="number" name="begin" placeholder="begin price">
    <input type="number" name="end" placeholder="end price">
    <button>Search</button>
</form>
<c:forEach items="${products}" var="pro">
<h2>${pro.id},${pro.name},${pro.price},${pro.quantity}</h2>
    <c:if test="${pro.price >200}">Sale 10%</c:if>
    <c:if test="${pro.price <200}">Sale 20%</c:if>
</c:forEach>
</body>
</html>
