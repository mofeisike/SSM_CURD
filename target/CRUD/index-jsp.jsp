<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="${ctx}/static/css/bootstrap/3.3.6/bootstrap.css" rel="stylesheet">
</head>
<body>

<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<%--

web路径

不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准（http://localhost：3306)；需要加上项目名|
http://localhost：3306/crud

--%>


<script src="${ctx}/static/js/jquery/2.0.0/jquery.min.js"></script>
<script>
    window.location = '${ctx}/emps';
    // window.location = '/jsp/list.jsp';
</script>

</body>
</html>
