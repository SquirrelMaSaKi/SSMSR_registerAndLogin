<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 海马哥
  Date: 2019/10/11
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/login" method="post">
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
            <td>验证码:</td>
            <td>
                <input type="text" name="code">
                <img src="${pageContext.request.contextPath}/code/getCode" onclick="click1(this)"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                记住我<input type="checkbox" name="auto" value="1">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登录">
            </td>
        </tr>
    </table>
</form>
<div style="color: red">${msg}</div>
<script type="text/javascript">
    function click1(img) {
        img.src="${pageContext.request.contextPath}/code/getCode?" + new Date().getTime();
    }
</script>
</body>
</html>
