<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
      
<form action="${pageContext.request.contextPath}/admin/video/search" method="post">
    <label for="search">Tìm kiếm theo tiêu đề:</label>
    <input type="text" id="search" name="search" value="${param.search}">
    <button type="submit">Tìm kiếm</button>
</form>

<a href="${pageContext.request.contextPath}/admin/video/insert">Add video</a>
<table border="1" , width=100%>
	<tr>
		<th>STT</th>
		<th>Video Id</th>
		<th>Poster</th>
		<th>Title</th>
		<th>Description</th>
		<th>Views</th>
		<th>Category</th>
		<th>Status</th>
		<th>Action</th>
	</tr>
	
	<c:forEach items="${listvideo}" var="vi" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			<td>${vi.videoId}</td>
			<td><c:if test="${vi.poster.substring(0,5) != 'https' }">
					<c:url value="/image?fname=${vi.poster }" var="imgUrl"></c:url>
				</c:if> <c:if test="${vi.poster.substring(0,5) == 'https' }">
					<c:url value="${vi.poster }" var="imgUrl"></c:url>
				</c:if> <img height="150" width="200" src="${imgUrl}" /></td>

			<td>${vi.title}</td>
			<td>${vi.description}</td>
			<td>${vi.views}</td>
			<td>${vi.category.categoryId}</td>


			<td><c:if test="${vi.active == true}">
					<span>Đang hoạt động</span>
				</c:if> <c:if test="${vi.active != true}">
					<span>Khoá</span>
				</c:if></td>
			<td>
			<a href="<c:url value="/admin/video/update?id=${vi.videoId }"/>"
				class="center">Sửa </a> |
			<a href="<c:url value="/admin/video/delete?id=${vi.videoId }"/>"
				class="center">Xóa </a>
				
		</tr>
	</c:forEach>

</table>