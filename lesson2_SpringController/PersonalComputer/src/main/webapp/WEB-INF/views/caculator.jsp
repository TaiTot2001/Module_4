<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25/09/2025
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Caculator</title>
</head>
<body>
<h1>
    Caculator
</h1>
<form action="${pageContext.request.contextPath}/caculator" method="post">
    <input type="text" name="numberOne" value="${numberOne}" required>
    <input type="text" name="numberTwo" value="${numberTwo}" required>
    <br><br>
    <button type="submit" name="operator" value="add">Addition(+)</button>
    <button type="submit" name="operator" value="sub">Subtraction(-)</button>
    <button type="submit" name="operator" value="mul">Multiplication(*)</button>
    <button type="submit" name="operator" value="div">Division(/)</button>
</form>

<h4>Result ${resultMessage}</h4>

</body>
</html>
