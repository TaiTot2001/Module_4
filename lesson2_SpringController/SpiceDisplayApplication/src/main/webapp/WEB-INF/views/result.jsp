<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25/09/2025
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<h2>
    Your choice of condiments with SanWich is:
</h2>

<form action="home" method="get">
    <ol>
        <c:forEach var="condiment" items="${condiments}">
            <li><c:out value="${condiment}"/></li>

        </c:forEach>
    </ol>
    <button type="submit">Back</button>
</form>
</body>
</html>
