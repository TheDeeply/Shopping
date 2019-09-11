<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/3 0003
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <title>登录页面</title>
</head>
<body>

<div class="login-main">
    <header class="layui-elip">登录</header>
    <form class="layui-form">
        <div class="layui-input-inline">
            <input type="text" id="loginname" name="account" required lay-verify="required" placeholder="用户名" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="password" id="password" name="password" required lay-verify="required" placeholder="密码" autocomplete="off"
                   class="layui-input">
        </div>
        <hr/>
        <div class="layui-input-inline login-btn">
            <button  type="button" class="layui-btn"value="登录" onclick="login()" onsubmit="return false";>登录</button>
        </div>
    </form>

</div>

<%--<h3>登录页面</h3>--%>
<%--<br>--%>
<%--<label>登录名：</label>--%>
<%--<input type="text" id="loginname" name="loginname">--%>
<%--<label>密码：</label>--%>
<%--<input type="password" id="password" name="password">--%>
<%--<button id="submit" type="submit" value="登录" onclick="login()">登陆</button>--%>

</body>
</html>
<style>
    .login-main {
        position: fixed;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        width: 350px;
        margin: 0 auto;
    }

    .login-main header {
        margin-top: 150px;
        height: 35px;
        line-height: 35px;
        font-size: 30px;
        font-weight: 100;
        text-align: center;
    }

    .login-main header, .login-main form, .login-main form .layui-input-inline {
        margin-bottom: 15px;
    }

    .login-main form .layui-input-inline, .login-main form .layui-input-inline input, .login-main form .layui-input-inline button {
        width: 100%;
    }

    .login-main form .login-btn {
        margin-bottom: 5px;
    }

</style>
<script>
    function login() {
        var loginname = document.getElementById("loginname").value;
        var password = document.getElementById("password").value;
        $.ajax({
            type: 'post',
            url: '/loginCheck',
            data: {"loginname":loginname,"password":password},
            dataType:'text',
            success: function (data) {
                if (data == 'false'){
                    layer.msg("用户名或密码错误");
                }else {
                    window.location.href = "/main"
                }
            },error:function () {
                layer.msg("炸了");
            }
        })
    }
</script>