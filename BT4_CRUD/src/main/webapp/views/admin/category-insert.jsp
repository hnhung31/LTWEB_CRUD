<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath }/admin/category/insert"
	method="post" enctype="multipart/form-data">
   
	<label for="categoryname">Category name:</label><br>
	<input type="text" id="categoryname" name="categoryname" value=""><br> <label for="images">Images:</label><br>
	
	<img id="imagess" height="150" width="200" src="" /> <input
		type="file" onchange="chooseFile(this)" id="images" name="images"
		value=""><br>
		
	<p>Status:</p>
	<input type = "radio" id="ston" name="status" value="1" checked>
	<label for="html">Đang hoạt động</label><br>
	<input type = "radio" id="stoff" name="status" value="0">
	<label for="css">Khoá</label><br>
	 
	<input type="submit" value="insert">
</form>