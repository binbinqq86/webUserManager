<%--
  Created by IntelliJ IDEA.
  User: tb
  Date: 2018/8/14
  Time: 下午2:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理系统</title>
</head>
<body>
<div>
    <span style="float:right;">
        <a name="login" href="./user/login.jsp">登录</a>
        <a name="register" href="user/register.jsp">注册</a>
    </span>
    <span style="float:left;">欢迎${welcome}来到</span>
</div>
<h1 align="center">用户管理系统</h1>
<div align="center">
    <a name="addUser" href="user/addUser.jsp">增加用户</a>
    <p></p>
    <table width="" border="1" cellpadding="10" cellspacing="1" bgcolor="#ffffff" align="center">
        <tr>
            <th align="left" width="50">id</th>
            <th align="left" width="150">用户名</th>
            <th align="left" width="150">昵称</th>
            <th align="left" width="100">用户角色</th>
            <th align="left" width="80">操作</th>
        </tr>
    </table>
</div>
</body>
</html>
