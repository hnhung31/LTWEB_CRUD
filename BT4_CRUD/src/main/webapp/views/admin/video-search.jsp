<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/admin/video/search" method="post" enctype="multipart/form-data">
<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>VideoId</th>
        <th>Poster</th>
        <th>Video Title</th>
        <th>Description</th>
        <th>Views</th>
        <th>Category</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listVideos}" var="video" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${video.videoId}</td>
            <td><c:if test="${video.poster.substring(0,5) != 'https' }">
					<c:url value="/image?fname=${video.poster }" var="imgUrl"></c:url>
				</c:if> <c:if test="${video.poster.substring(0,5) == 'https' }">
					<c:url value="${video.poster }" var="imgUrl"></c:url>
				</c:if> <img height="150" width="200" src="${imgUrl}" /></td>
				
            <td>${video.title}</td>
            <td>${video.description}</td>
            <td>${video.views}</td>
            <td>${video.category.categoryId}</td>
            <td>
                <c:choose>
                    <c:when test="${video.active}">
                        <span>Đang hoạt động</span>
                    </c:when>
                    <c:otherwise>
                        <span>Đã khóa</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="<c:url value='/admin/video/update?id=${video.videoId}'/>">Sửa</a> |
                <a href="<c:url value='/admin/video/delete?id=${video.videoId}'/>">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</form>