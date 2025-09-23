<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19/09/2025
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Danh sách sinh viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
<c:if test="${not empty message}">
    <div style="color:green">${message}</div>
</c:if>
<div style="color: green;">${massage}</div>

<h2>Danh sách sinh viên</h2>

<form method="get" action="${pageContext.request.contextPath}/students" class="row g-2 mb-3">
    <div class="col-auto">
        <input type="text" class="form-control" name="q" placeholder="Tìm theo mã số / họ tên" value="${q}">
    </div>
    <div class="col-auto">
        <select name="sort" class="form-select">
            <option value="id" <c:if test="${sort == 'id'}">selected</c:if>>Mã số</option>
            <option value="name" <c:if test="${sort == 'name'}">selected</c:if>>Họ tên</option>
            <option value="score" <c:if test="${sort == 'score'}">selected</c:if>>Điểm</option>
        </select>
    </div>
    <div class="col-auto">
        <select name="dir" class="form-select">
            <option value="asc" <c:if test="${dir == 'asc'}">selected</c:if>>Tăng dần</option>
            <option value="desc" <c:if test="${dir == 'desc'}">selected</c:if>>Giảm dần</option>
        </select>
    </div>
    <div class="col-auto">
        <button type="submit" class="btn btn-primary">Lọc</button>
    </div>
    <div class="col-auto ms-auto">
        <a href="${pageContext.request.contextPath}/students/add" class="btn btn-success">➕ Thêm sinh viên</a>
    </div>
</form>

<table class="table table-bordered table-striped align-middle ">
    <colgroup>
        <col style="width: 10%;">
        <col style="width: 35%;">
        <col style="width: 15%;">
        <col style="width: 15%;">
        <col style="width: 25%;">
    </colgroup>
    <thead>
    <tr>
        <th class="text-center">Mã số</th>
        <th class="text-center">Họ tên</th>
        <th class="text-center">Điểm</th>
        <th class="text-center">Thứ hạng</th>
        <th class="text-center">Chức năng</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="s" items="${students}">
        <tr>
            <td class="text-center"><c:out value="${s.id}"/></td>
            <td class="text-center align-middle"><c:out value="${s.name}"/></td>
            <td class="text-center"><c:out value="${s.score}"/></td>
            <td class="text-center"><c:out value="${s.rank}"/></td>
            <td class="text-center">
                <a href="students/detail?id=${s.id}" class="btn btn-info btn-sm">
                    Chi tiết
                </a>

                <a href="students/edit?id=${s.id}" class="btn btn-warning btn-sm">
                    Sửa
                </a>

                <a href="students/delete?id=${s.id}" class="btn btn-danger btn-sm"
                   onclick="return confirm('Bạn có chắc muốn xoá sinh viên ${student.name}?');">
                    Xóa
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<nav>
    <ul class="pagination">
        <c:forEach begin="1" end="${totalPages}" var="p">
            <c:choose>
                <c:when test="${p == page}">
                    <li class="page-item active">
                        <span class="page-link">
                            <c:out value="${p}"/>
                        </span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link"
                           href="?q=${q}&sort=${sort}&dir=${dir}&size=${size}
                            <c:if test='${p != 1}'>&page=${p}</c:if>">
                            <c:out value="${p}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</nav>

</body>
</html>
