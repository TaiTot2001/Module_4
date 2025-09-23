<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22/09/2025
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chi tiết sinh viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h2>Thông tin chi tiết sinh viên</h2>

<table class="table table-bordered">
    <tr>
        <th>ID</th>
        <td><c:out value="${student.id}"/></td>
    </tr>
    <tr>
        <th>Họ tên</th>
        <td><c:out value="${student.name}"/></td>
    </tr>
    <tr>
        <th>Điểm GPA</th>
        <td><c:out value="${student.score}"/></td>
    </tr>
</table>

<a href="${pageContext.request.contextPath}/students" class="btn btn-secondary">Quay lại danh sách</a>

</body>
</html>
