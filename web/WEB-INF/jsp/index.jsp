<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/2 0002
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>login</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  </head>
  <body>
  <form action="loginCheck" method="post">
    <table>
      <tr>
        <td><label>登录名：</label></td>
        <td><input type="text" id="loginname" name="loginname"></td>
      </tr>
      <tr>
        <td><label>密码：</label></td>
        <td><input type="password" id="password" name="password"></td>
      </tr>
      <tr>
        <td><button id="submit" type="submit" value="登录">登陆</button></td>
      </tr>
    </table>
  </form>
  </body>
</html>
