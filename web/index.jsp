<%@ page import="com.tb.system.bean.User" %>
<%@ page import="com.tb.system.utils.JdbcUtil" %>
<%@ page import="com.tb.system.dao.UserDaoImpl" %>
<%@ page import="java.util.List" %>
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
    <%
        User user = (User) session.getAttribute("user");
        String getRole = "";
        if (user != null && !"".equals(user)) {
    %>
    <script type="text/javascript">
        alert("<%=user.userName%>操作成功");
    </script>
    <%
            if (user.role == -1)
                getRole = "管理员:";
            else
                getRole = "普通用户:";
        }

        String userName = (String) session.getAttribute("userName");
        if (userName != null && !"".equals(userName)) {
    %>
    <script type="text/javascript">
        alert("删除<%=userName%>成功");
    </script>
    <%
        }
    %>
</head>
<body>

<div>
    <span style="float:right;">
        <a name="login" href="user/login.jsp">登录</a>
        <a name="register" href="user/addOrRegister.jsp">注册</a>
    </span>
    <a style="float:left;">欢迎<%=getRole%>${welcome}来到</a>
</div>
<br>
<h1 align="center">用户管理系统</h1>
<div align="center">
    <a name="addUser" href="user/addOrRegister.jsp">增加用户</a>
    <p></p>
    <table width="" border="1" cellpadding="10" cellspacing="1" bgcolor="#ffffff" align="center">
        <tr>
            <th align="left" width="50">id</th>
            <th align="left" width="150">用户名</th>
            <th align="left" width="150">昵称</th>
            <th align="left" width="100">用户角色</th>
            <th align="left" width="80">操作</th>
        </tr>
        <%
            List<User> allUser = UserDaoImpl.getInstance().read(null);
            for (User u : allUser) {
                String role = "普通用户";
                if (u.role == -1) {
                    role = "管理员";
                }
                if (u.nickName == null || "".equals(u.nickName)) {
                    u.nickName = u.userName;
                }
//                if (user == null) {
//                    //未登录禁止查看
//                    return;
//                }
        %>
        <tr>
            <td align="left" width="50"><%=u.userId%>
            </td>
            <td align="left" width="150"><%=u.userName%>
            </td>
            <td align="left" width="150"><%=u.nickName%>
            </td>
            <td align="left" width="100"><%=role%>
            </td>
            <td align="left" width="80">
                <a name="update" href="user/updateUser.jsp?userName=<%=u.userName%>">修改</a>
                <a name="delete" href="<%=request.getContextPath()%>/DeleteUserServlet?userName=<%=u.userName%>">删除</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>
