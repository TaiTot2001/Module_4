<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 23/09/2025
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sửa sinh viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h2>Sửa thông tin sinh viên</h2>

<form action="${pageContext.request.contextPath}/students/edit" method="post" class="mt-3">
    <div class="mb-3">
        <label for="id" class="form-label">Mã số</label>
        <input type="text" class="form-control" id="id" name="id"
               value="${student.id}" readonly>
    </div>

    <div class="mb-3">
        <label for="name" class="form-label">Họ tên</label>
        <input type="text" class="form-control" id="name" name="name"
               value="${student.name}" required>
        <span class="text-danger">${errorName}</span>
    </div>

    <div class="mb-3">
        <label for="score" class="form-label">Điểm tổng kết</label>
        <input type="number" step="0.1" class="form-control" id="score" name="score"
               value="${student.score}" required>
        <span class="text-danger">${errorScore}</span>
    </div>

    <button type="submit" class="btn btn-primary">Cập nhật</button>
    <a href="${pageContext.request.contextPath}/students" class="btn btn-secondary">Hủy</a>
</form>

</body>
</html>