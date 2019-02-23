<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <link href="/static/css/bootstrap/3.3.6/bootstrap.css" rel="stylesheet">
</head>
<body>

<!-- 这是使用一个小技巧,在外部的js中使用el的值可以先把这个值放到隐藏域中,然后通过element获取 -->
<input type="text" id="APP_PATH" value="${APP_PATH}" />
<input type="text" id="ctx" value="${ctx}" />

<div class="container">

    <%--标题--%>
    <div class="row">
        <div class="col-xs-12 ">
            <h1><a href="" class="text-primary">SSM_CURD</a></h1>

        </div>
    </div>

    <%--添加删除--%>
    <div class="row">
        <div class="col-xs-4  col-xs-offset-8">
            <button type="button" class="btn btn-primary" id="emp_add">新增</button>
            <button type="button" class="btn btn-danger" id="emp_delete">删除</button>
        </div>
    </div>

    <%--表格--%>
    <div class="row">
        <div class="col-xs-12 ">
            <table class="table table-hover" id="emps_table">
                <thead>
                    <tr>
                        <th><input type='checkbox' id='check_all'/></th>
                        <th>ID</th>
                        <th>empName</th>
                        <th>gender</th>
                        <th>email</th>
                        <th>deptName</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>

    <%--分页--%>
    <div class="row">
        <%--分页信息--%>
        <div class="col-xs-5" id="page_info_area"></div>
        <%--分页条--%>
        <div class="col-xs-7" id="page_nav_area"></div>
    </div>

</div>

<%--新添模态框--%>
<div class="modal fade" id="empMyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button">
                    <span aria-hidden="true">×</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">员工添加</h4>
            </div>

            <div class="modal-body">

                <form class="form-horizontal" id="form1">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="empName" id="empinput_add_input" placeholder="empName">
                            <span class="help-block"></span>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" name="email" id="email" placeholder="email@163.com">
                            <span class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_add_input" value="M" checked> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_add_input" value="F"> 女
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="dId" id="dept_add_select">
                                <%--<option>1</option>
                                <option>2</option>--%>
                            </select>
                        </div>
                    </div>

                </form>

            </div>

            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button" id="emp_add_close_btn">关闭</button>
                <button class="btn btn-primary"  type="button"  id="emp_save_btn">保存</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<%--修改模态框--%>
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button">
                    <span aria-hidden="true">×</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">员工修改</h4>
            </div>

            <div class="modal-body">

                <form class="form-horizontal" id="form2">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="empName_update_static"></p>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" name="email" id="email_update_input" placeholder="email@163.com">
                            <span class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_update_input" value="M" checked> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_update_input" value="F"> 女
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="dId" id="dept_update_select">
                                <%--<option>1</option>
                                <option>2</option>--%>
                            </select>
                        </div>
                    </div>

                </form>

            </div>

            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button" id="emp_update_close_btn">关闭</button>
                <button class="btn btn-primary"  type="button"  id="emp_update_btn">更新</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>


<script src="static/js/jquery/2.0.0/jquery.min.js"></script>
<script src="static/js/bootstrap/3.3.6/bootstrap.js"></script>
<script src="static/js/index.js"></script>

<script>

    //1、我们是按钮创建之前就绑定了click，所以绑定不上。
    //1）、可以在创建按钮的时候绑定。2）、绑定点击.live（）
    //jquery新版没有1ive，使用on进行替代

    //单个删除
    $(document).on("click",".delete_btn",function () {

        //eq(3) : 匹配一个给定索引值的元素
        var empName = $(this).parent("tr").find("td").eq(3).text();
        var empId = $(this).attr("delete-id");

        //confirm 有确认的alret HTMLDOM
        if (confirm("确认删除"+empName+"吗?")){
            //确认，发送ajax请求删除即可
            $.ajax({
                url:"/emp/delete/"+empId,
                type: "delete",
                success :function (result) {
                    alert(result.msg);
                    toPage(currenPage)
                }

            })
        }

    })

    //全选
    $("#check_all").click(function () {
        $(".check_item").prop("checked",$(this).prop("checked"))
    })


    //单选
    $(document).on("click",".check_item",function () {
        //判断当前选择中的元素是否当前页数的选择框个
        var falg = $(".check_item:checked").length == $(".check_item").length;
        //:checked 匹配所有选中的被选中元素(复选框、单选框等)
        $("#check_all").prop("checked",falg);
    })


    //点击全部删除，就批量删除
    $("#emp_delete").click(function () {
        var empName = "";
        var del_idstr = "";

        //$(".check_item:checked") 被选中的复选框
        $.each($(".check_item:checked"),function () {
            //组装name
            empName += $(this).parents("tr").find("td").eq(2).text()+",";
            //组装id字符串
            del_idstr += $(this).parents("tr").find("td").eq(1).text()+"-";
        })

        //去除多余的后面的符号
        empName=empName.substring(0, empName.length-1);
        del_idstr=del_idstr.substring(0, del_idstr.length-1);

        if (confirm("确认删除"+empName+"吗?")) {
            $.ajax({
                url: "/emp/deletes/"+del_idstr,
                type: "delete",
                success :function (result) {
                    alert(result.msg);
                    toPage(currenPage)
                }

            })
        }
    })


</script>

</body>
</html>
