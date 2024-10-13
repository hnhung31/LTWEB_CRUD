<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<form action="${pageContext.request.contextPath}/admin/video/update"
      method="post" enctype="multipart/form-data">
	<input type="text"
		id="videoId" name="videoId" value="${vi.videoId}" hidden="hidden"><br>
	
	  <label for="fname">Title:</label> <br> 
	<input type="text"
		id="title" name="title" value="${vi.title}"><br>
	
	<label for="lname">Poster:</label> <br>
	<c:if test="${vi.poster.substring(0,5)  != 'https'}">
		<c:url value="/image?fname=${vi.poster}" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${vi.poster.substring(0,5)  == 'https'}">
		<c:url value="${vi.poster}" var="imgUrl"></c:url>
	</c:if>
	<img height="150" width="200" src="${imgUrl}" id="imagess" />
	<input
		type="file" onchange="chooseFile(this)" id="imagess" name="poster"><br>
		
	
		<label for="views">Views:</label> <br> 
	<input type="text"
		id="views" name="views" value="${vi.views}" ><br>
	
	   <label for="description">Description:</label><br>
    <textarea id="description" name="description"></textarea><br>

    <label for="categoryid">Category:</label><br>
    <select id="categoryid" name="categoryid">
    <c:forEach var="cate" items="${categoryList}">
        <option value="${cate.categoryId}">${cate.categoryname}</option> <!-- Sửa ở đây -->
    </c:forEach>
</select>
	
	
	<p>Action:</p>
	<input type = "radio" id="ston" name="active" value="true" checked>
	<label for="html">Đang hoạt động</label><br>
	<input type = "radio" id="stoff" name="active" value="false">
	<label for="css">Khoá</label><br>
		<input type="submit" value="Update">
</form>
