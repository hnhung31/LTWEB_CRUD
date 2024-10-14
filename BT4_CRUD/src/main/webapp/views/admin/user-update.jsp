<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<form action="${pageContext.request.contextPath}/admin/user/update"
      method="post" enctype="multipart/form-data">
	<input type="text"
		id="id" name="id" value="${use.id}" hidden="hidden"><br>
	<label for="fname">Username:</label> <br> 
	<input type="text"
		id="username" name="username" value="${use.username}"><br>
		
	<label for="fname">Fullname:</label> <br> 
	<input type="text"
		id="fullname" name="fullname" value="${use.fullname}"><br>	
		
	<label for="fname">Password:</label> <br> 
	<input type="text"
		id="password" name="password" value="${use.password}"><br>
			
	<label for="lname">Images name:</label> <br>
	<c:if test="${use.images.substring(0,5)  != 'https'}">
		<c:url value="/image?fname=${use.images}" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${use.images.substring(0,5)  == 'https'}">
		<c:url value="${use.images}" var="imgUrl"></c:url>
	</c:if>
	<img height="150" width="200" src="${imgUrl}" id="imagess" />
	<input
		type="file" onchange="chooseFile(this)" id="imagess" name="images"><br>
		<input type="submit" value="Update">
</form>
