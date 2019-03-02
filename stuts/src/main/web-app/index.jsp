<%--
  Created by IntelliJ IDEA.
  User: huifer
  Date: 2019/2/19
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>hello</h1>
<h3><a href="${pageContext.request.contextPath}/hello.action">struts</a></h3>

<h1> method </h1>
<h3><a href="${pageContext.request.contextPath}/userFind.action">查询user</a></h3>
<h3><a href="${pageContext.request.contextPath}/userUpdate.action">修改user</a></h3>
<h3><a href="${pageContext.request.contextPath}/userDelete.action">删除user</a></h3>
<h3><a href="${pageContext.request.contextPath}/userSave.action">保存user</a></h3>

<h1> 通配符</h1>
<h3><a href="${pageContext.request.contextPath}/tpf_find.action">通配符查询</a></h3>
<h3><a href="${pageContext.request.contextPath}/tpf_update.action">通配符修改</a></h3>
</body>
</html>
