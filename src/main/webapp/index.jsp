<%--
  Created by IntelliJ IDEA.
  User: userf
  Date: 10/23/2020
  Time: 7:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="voteServlet" method="POST">

    <input type="text" id="userId" name="userId" placeholder="userid"><br><br>
    <input type="text" id="movieId" name="movieId" placeholder="movieid"><br><br>
    <input type="text" id="score"  name="score" placeholder="score"><br><br>
    <input type="submit" value="Submit">
</form>


</body>
</html>
