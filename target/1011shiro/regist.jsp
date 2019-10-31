<%--
  Created by IntelliJ IDEA.
  User: 海马哥
  Date: 2019/10/11
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/regist" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>用户名</td>
                <td>
                    <input type="text" name="username">
                </td>
            </tr>
            <tr>
                <td>密码</td>
                <td>
                    <input type="password" name="password">
                </td>
            </tr>
            <tr>
                <td>头像(png/jpg)</td>
                <td>
                    <input type="file" name="picture">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="注册">
                </td>
            </tr>
        </table>
    </form>
    <div style="color: red">${msg}</div>
</body>
</html>
