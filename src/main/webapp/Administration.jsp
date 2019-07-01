<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Administration - ${sessionScope.supervisor==null ? "log in" : "choose action"}</title>
</head>
<body>
<c:if test="${sessionScope.supervisor==null}">
    <p>Log in please</p>
</c:if>
<c:if test="${sessionScope.supervisor!=null}">
    <p>Logged in as ${sessionScope.supervisor.login} - ${sessionScope.supervisor.role}</p>
</c:if>
<div style="display: ${sessionScope.supervisor==null ? "block" : "none"}">
    <form method="post">
        <input type="text" name="login" placeholder="login">
        <input type="password" name="pass" placeholder="password">
        <input type="reset" value="clear">
        <input type="submit" name="submit" value="submit">
    </form>
    <form action="main" method="get">
        <button type="submit">main page</button>
    </form>
</div>
<p>${requestScope.message}</p>
<div style="display: ${sessionScope.supervisor==null ? "none" : "block"}">

    <form action="add-update" method="get">
        <button type="submit">Add new book</button>
    </form>

    <form action="${pageContext.request.contextPath}/" method="get">
        <button type="submit">Manage books DB</button>
    </form>

    <form action="${pageContext.request.contextPath}/" method="get"
          style="display: ${sessionScope.supervisor.role.equals("ROOT") ? "block" : "none"} ">
        <button type="submit">Manage supervisor DB</button>
    </form>

    <form action="${pageContext.request.contextPath}/administration" method="post">
        <button type="submit" name="logout">Logout</button>
    </form>
</div>
</body>
</html>
