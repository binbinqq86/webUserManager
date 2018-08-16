<%--
  Created by IntelliJ IDEA.
  User: tb
  Date: 2018/8/15
  Time: 下午3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户</title>
    <%
        String userName = request.getParameter("userName");
        String msg= (String) request.getAttribute("msg");
        if(msg!=null&&!"".equals(msg)){
            %>

    <script type="text/javascript">
        alert("<%=msg%>");
    </script>
    <%
        }
    %>

</head>
<body>
<form action="<%=request.getContextPath()%>/UpdateUserServlet" method="post">
    <input style="display: none" name="userName" value="<%=userName%>">
    <table align="center">
        <tr>
            <td width="80" align="right">昵称：</td>
            <td>
                <input id="nickName" name="nickName" type="text" placeholder="请输入昵称"/>
            </td>
        </tr>
    </table>
    <p></p>
    <div align="center">
        <input name="login" value="修改" type="submit" class="inputText" style="color: #000000">

    </div>
</form>
</body>
</html>
