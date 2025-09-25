<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25/09/2025
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
  SanWich Condiments
</h1>
<form action="${pageContext.request.contextPath}/save" method="post">
<div class="condiments">
    <label><input type="checkbox" name="condiment" value="Lettuce"> Lettuce</label>
    <label><input type="checkbox" name="condiment" value="Tomato"> Tomato</label>
    <label><input type="checkbox" name="condiment" value="Mustard"> Mustard</label>
    <label><input type="checkbox" name="condiment" value="Sprouts"> Sprouts</label>
</div>

<hr>

<button type="submit">Save</button>
</form>
</body>
</html>
