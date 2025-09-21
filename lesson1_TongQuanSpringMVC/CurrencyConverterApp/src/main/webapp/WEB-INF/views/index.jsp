<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 21/09/2025
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Currency Converter</title>
</head>
<body>
<h2>Chuyển đổi USD sang VNĐ</h2>
<form action="convert" method="post">
    Tỉ giá (VNĐ/USD): <input type="text" name="rate"><br>
    Số USD: <input type="text" name="usd"><br>
    <input type="submit" value="Chuyển đổi">
</form>
</body>
</html>
