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

<h2>Danh sách sinh viên</h2>

<table class="table table-bordered table-striped mt-3">
    <thead>
    <tr>
        <th>Mã số</th>
        <th>Họ tên</th>
        <th>Điểm tổng kết</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="s" items="${students}">
        <tr>
            <td><c:out value="${s.id}"/></td>
            <td><c:out value="${s.name}"/></td>
            <td><c:out value="${s.score}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/students/add" class="btn btn-primary">➕ Thêm sinh viên</a>

</body>
</html>

