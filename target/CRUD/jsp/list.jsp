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

<div class="container">

    <%--标题--%>
    <div class="row">
        <div class="col-xs-12 ">
            <h1><span class="text-primary">SSM_CURD</span></h1>
        </div>
    </div>

    <%--添加删除--%>
    <div class="row">
        <div class="col-xs-4  col-xs-offset-8">
            <button type="button" class="btn btn-primary">新增</button>
            <button type="button" class="btn btn-danger">删除</button>
        </div>
    </div>

    <%--表格--%>
    <div class="row">
        <div class="col-xs-12 ">
            <table class="table table-hover">

                <thead>
                <tr>
                    <th>ID</th>
                    <th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${pageinfo.list}" var="emp">
                    <tr>
                        <td>${emp.empId}</td>
                        <td>${emp.empName}</td>
                        <td>${emp.gender=="M"?"男":"女"}</td>
                        <td>${emp.email}</td>
                        <td>${emp.department.deptName}</td>
                        <td>
                            <a href="" type="button" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-edit"></span>
                                新增
                            </a>
                            <a href="" type="button" class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash"></span>
                                删除
                            </a>
                        </td>
                    </tr>

                </c:forEach>
                </tbody>

            </table>
        </div>
    </div>

    <%--分页--%>
    <div class="row">
        <%--分页信息--%>
        <div class="col-xs-5 ">
            当前${pageinfo.pageNum}页, 总${pageinfo.pages}页, 总${pageinfo.total}记录
        </div>
        <div class="clearfix visible-xs-block col-xs-1" ></div>
        <%--分页条--%>
        <div class="col-xs-7 ">
            <nav>
                <ul class="pagination pagination-lg">
                    <c:if test="${!pageinfo.isFirstPage}">
                        <li><a href="${ctx}/emps?pn=1">首页</a></li>
                    </c:if>

                    <c:if test="${pageinfo.hasPreviousPage}">
                        <li>
                            <a href="${ctx}/emps?pn=${pageinfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach items="${pageinfo.navigatepageNums}" var="page_Num">

                        <c:if test="${page_Num == pageinfo.pageNum}">
                            <li class="active disabled"><a href="#">${page_Num}</a></li>
                        </c:if>

                        <c:if test="${page_Num != pageinfo.pageNum}">
                            <li><a href="${ctx}/emps?pn=${page_Num}">${page_Num}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${pageinfo.hasNextPage}">
                        <li>
                            <a href="${ctx}/emps?pn=${pageinfo.pageNum+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${!pageinfo.isLastPage}">
                        <li><a href="${ctx}/emps?pn=${pageinfo.pages}">末页</a></li>
                    </c:if>

                </ul>
            </nav>
        </div>
    </div>

</div>

<script src="${ctx}/static/js/jquery/2.0.0/jquery.min.js"></script>
<script>
    // window.location = '${ctx}/emps';
    // window.location = '/jsp/list.jsp';
</script>

</body>
</html>
