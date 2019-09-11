<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/3 0003
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登陆成功页面</title>
</head>
<body>

<div>
    <%
        String name = (String)request.getAttribute("username");
        String pwd = (String)request.getAttribute("password");
    %>
    <p>用户名：<%=name %></p>
    <p>密码：<%=pwd %></p>
</div>


<br>
</body>
</html>
