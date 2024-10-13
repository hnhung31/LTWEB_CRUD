<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/admin/video/insert"
    method="post" enctype="multipart/form-data">
   
    <label for="videoId">Video ID:</label><br>
    <input type="text" id="videoId" name="videoId" value=""><br>

    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title" value=""><br>

    <label for="poster">Poster:</label><br>
    <img id="imagess" height="150" width="200" src="" /> 
    <input type="file" onchange="chooseFile(this)" id="images" name="poster"><br>

    <label for="views">Views:</label><br>
    <input type="text" id="views" name="views" value=""><br>

    <label for="description">Description:</label><br>
    <textarea id="description" name="description"></textarea><br>

    <label for="categoryid">Category:</label><br>
    <select id="categoryid" name="categoryid">
    <c:forEach var="cate" items="${categoryList}">
        <option value="${cate.categoryId}">${cate.categoryname}</option> <!-- Sửa ở đây -->
    </c:forEach>
</select>


    <p>Action:</p>
    <input type="radio" id="ston" name="active" value="true" checked>
    <label for="ston">Đang hoạt động</label><br>

    <input type="radio" id="stoff" name="active" value="false">
    <label for="stoff">Khóa</label><br>

    <input type="submit" value="Insert">
</form>