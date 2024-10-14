<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath }/admin/user/insert"
	method="post" enctype="multipart/form-data">
   
	<label for="username">Username:</label><br>
	<input type="text" id="username" name="username" value=""> <br>
	
	<label for="fullname">Fullname:</label><br>
	<input type="text" id="fullname" name="fullname" value=""> <br>
	
	<label for="password">Password:</label><br>
	<input type="text" id="password" name="password" value=""> <br>
	
	<br> <label for="images">Images:</label><br>
	
	<img id="imagess" height="150" width="200" src="" /> <input
		type="file" onchange="chooseFile(this)" id="images" name="images"
		value=""><br>
		
	 
	<input type="submit" value="insert">
</form>