<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <nav>
	<ul class="pagination">
		<li><a href="#">&laquo;</a></li>
		<c:forEach begin="${page.startPageNumber}" end="${page.endPageNumber}"
			step="1" var="i">
			<c:if test="${ page.currentPageNumber == i }">
				<li class="active"><span >${ i }</span> </li>
			</c:if>
			<c:if test="${ page.currentPageNumber != i }">
				<li><a href="/rouse/user?currentPageNumber=${i}">${i}</a></li>
			</c:if>
		</c:forEach>
		<li><a href="#">&raquo;</a></li>
	</ul>
	</nav>
  </body>
</html>
