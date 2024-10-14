<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<a href="${pageContext.request.contextPath}/admin/user/insert">Add
	User</a>
<table border="1" width=100%>
	<tr>
		<th>STT</th>
		<th>UserId</th>
		<th>UserName</th>
		<th>FullName</th>
		<th>Password</th>
		<th>Images</th>
	</tr>
	
	<c:forEach items="${listuse}" var="use" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			<td>${use.id}</td>
			<td>${use.username}</td>
			<td>${use.fullname}</td>
			<td>${use.password}</td>
			<td><c:if test="${use.images.substring(0,5) != 'https' }">
					<c:url value="/image?fname=${use.images }" var="imgUrl"></c:url>
				</c:if> <c:if test="${use.images.substring(0,5) == 'https' }">
					<c:url value="${use.images }" var="imgUrl"></c:url>
				</c:if> <img height="150" width="200" src="${imgUrl}" /></td>

			<td><a
				href="<c:url value="/admin/user/update?id=${use.id }"/>"
				class="center">Sửa </a> | <a
				href="<c:url value="/admin/user/delete?id=${use.id }"/>"
				class="center">Xóa </a></
		</tr>
	</c:forEach>

</table>