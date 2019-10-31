<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--shiro标签使用-->
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<h2>主页</h2>
<shiro:user>
    <h3>欢迎您,<shiro:principal/> <a href="${pageContext.request.contextPath}/user/logout">退出登录</a></h3>
    <img style="width: 100px;height: 100px" src="${pageContext.request.contextPath}/user/img?username=<shiro:principal/>"/><br/>
    <shiro:hasPermission name="修改信息">
        <a href="${pageContext.request.contextPath}/user/modify">修改用户信息</a>
    </shiro:hasPermission>
</shiro:user>
<shiro:guest>
    <h3>请<a href="${pageContext.request.contextPath}/user/login">登录</a>或<a href="regist.jsp">注册</a></h3>
</shiro:guest>
</body>
</html>
