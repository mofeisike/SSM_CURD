
//总记录数,来到最后一页,分页信息里面有赋值
var totalRecod;

$(function () {
    //去首页
    toPage(1);
});

function build_emps_table(result){
    //清空table表格
    // 注意每次构建前都要清空分页
    $("#emps_table tbody").empty();
    var emp = result.data.pageinfo.list;
    $.each( emp, function(index, item){
        /*
        $("<td></td>") 创建元素
        */
        // 构建数据
        var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>")
        var empIdTd = $("<td></td>").append(item.empId);
        var empNameTd = $("<td></td>").append(item.empName);
        var empGenderTd = $("<td></td>").append(item.gender == 'M' ? '男' : '女');
        var empEmailTd = $("<td></td>").append(item.email);
        var deptNameTd = $("<td></td>").append(item.department.deptName);

        // 构建按钮  .attr("edit-id", item.empId) 表示自定义按钮:员工id
        var editBtn = $("<button></button>")
            .addClass("edit_btn btn btn-info ")
            .attr("edit-id", item.empId)
            .append($("<span></span>")
                .addClass("glyphicon glyphicon-pencil"))
            .append("编辑");

        var deleteBtn = $("<button></button>")
            .addClass("delete_btn btn btn-primary ")
            .attr("delete-id", item.empId)
            .append($("<span></span>")
                .addClass("glyphicon glyphicon-remove"))
            .append("删除")
            .attr("deleteName", item.empName);

        /*添加和删除组合添加*/
        var btnTd = $("<td></td>")
            .append(editBtn)
            .append(" ")
            .append(deleteBtn);
        /*.append(" ")  有一个小间隔 */


        $("<tr></tr>")
            .append(checkBoxTd)
            .append(empIdTd)
            .append(empNameTd)
            .append(empGenderTd)
            .append(empEmailTd)
            .append(deptNameTd)
            .append(btnTd)
            .appendTo("#emps_table tbody");
        /*appendTo整体(前面的append的元素)添加到那个元素*/
    });
};

//解析分页信息
function build_page_info(result){
    // 注意每次构建前都要清空分页
    $("#page_info_area").empty();

    $("#page_info_area")
        .append("当前"+result.data.pageinfo.pageNum+"页 总"
            +result.data.pageinfo.pages +"页 总"
            +result.data.pageinfo.total+"数量");
    //总记录数
    totalRecod = result.data.pageinfo.total;
};

//解析分页条
function build_page_nav(result){
    // 注意每次构建前都要清空分页
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");

    // 首页
    firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));

    // 前一页
    prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href", "#"));

    // 如果当前页是第一页(是否有前一页,没有就是第一页),禁止点击
    if (result.data.pageinfo.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    }else {
        //禁用的情况,不用绑定事件
        // 跳转首页
        firstPageLi.click(function() {
            toPage(1);
        });

        // 跳转前一页(注意前面虽然禁止了首页跳转,但是只有禁止点击标志,还是可以点击)
        prePageLi.click(function() {
            toPage(result.data.pageinfo.pageNum == 1 ? 1 : result.data.pageinfo.pageNum - 1)
        })
    }

    ul.append(firstPageLi).append(prePageLi);

    // 下一页
    nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href", "#"));

    // 末页
    lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));

    // 如果当前页是最后一页禁止点击
    if (result.data.pageinfo.hasNextPage == false) {
        lastPageLi.addClass("disabled");
        nextPageLi.addClass("disabled");
    }else {
        //禁用的情况,不用绑定事件
        // 跳转最后一页
        lastPageLi.click(function() {
            toPage(result.data.pageinfo.pages);
        });

        // 跳转下一页(注意前面虽然禁止了末页跳转,但是只有禁止点击标志,还是可以点击,或者在pagehelper的配置中设置reasonable属性)
        nextPageLi.click(function() {
            var pn = result.data.pageinfo.pageNum;
            var pg = result.data.pageinfo.pages;
            toPage(pn == pg ? pg : pn+ 1)
        })
    }

    // 页数的生成与跳转
    $.each(result.data.pageinfo.navigatepageNums, function(index, item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item).attr("href", "#"));

        /*当前页 == 遍历的值 添加选中class*/
        if (result.data.pageinfo.pageNum == item) {
            numLi.addClass("active");
        }

        numLi.click(function() {
            toPage(item);
        })

        ul.append(numLi);
    })

    ul.append(nextPageLi).append(lastPageLi);

    //把ul添加到nav
    var nav = $("<nav></nav>").append(ul);

    $("#page_nav_area").append(nav);
    //一样的效果
    /*nav.appendTo("#page_nav_area");*/

};

// 页面跳转
function toPage(pn) {
    $.ajax({
        url : '/emps',
        data : 'pn=' + pn,
        type : 'get',
        success : function(result) {
            // console.log(result);
            //1,解析并显示员工数据
            build_emps_table(result);
            //2,解析并显示分页信息
            build_page_info(result);
            //3,解析分页条
            build_page_nav(result);
        }
    })
}

/*添加的模态框*/

/*--------------------------------------------------添加js-------------------------------------------------------------*/
/*模态框*/
$("#emp_add").click(function(){
    //每次点击,清除表单(内容和样式),重置数据
    reset_from("#empMyModal form")

    //ajax,查出部分信息
    getDepts("#empMyModal select");
    //弹出
    $("#empMyModal").modal();
});


//重置表单(内容 和 样式)
function reset_from(ele) {
    //1 清空表单内容
    // reset() 是DOM(js对象)下面的方法 ,Jquery没有这个方法
    $(ele)[0].reset();

    //2 清空表单样式
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help-block").text();
}

//查询部分信息
function getDepts(ele) {
    $(ele).empty();
    $.ajax({
        url : '/depts',
        type : 'get',
        success : function(result) {
            // console.log(result);
            $.each(result.data.dept,function () {
                //this 表示 result ,如果不用this , 在方法带入result
                var optionEle = $("<option></option>")
                    .append(this.deptName)
                    .attr("value",this.deptId);
                // $("#dept_add_select")
                $(ele).append(optionEle);
            })
        }
    })
}

//保存
$("#emp_save_btn").click(function () {
    //1、模态框中填写的表单数据提交给服务器进行保存
    //2. 校验
    if (!validate()) {
        alert("validate fail")
        return false;
    }

    //判断ajax的用户名是否检验成功
    //这个很特俗,返回是字符串
    if( $(this).attr("ajax-value") == true ){
        // alert("insert fail");
        return false;
    }else{
        // alert("insert success");
    }


    //ajax请求
    $.ajax({
        url : '/emp',
        type : 'post',
        data : $("#form1").serialize(),
        success : function(result) {
            //result的结果可能是两个
            console.log(result);
            if(result.code == 200){
                //关闭模态框
                $("#empMyModal").modal('hide');
                //2、来到最后一页，显示刚才保存的数据
                //发送ajax请求显示最后一页数据即可
                toPage(totalRecod);
            }else {
                //后台校验

                //显示失败信息
                if(result.data.errorFields.empName != undefined){
                    //显示邮箱信息
                    show_validate_message("#empinput_add_input","error","用户名可以是2-5位中文或者6-16位英文和数字的组合")
                }else if (result.data.errorFields.email != undefined){
                    //显示名字信息
                    show_validate_message("#email", "error", "邮箱格式不正确")
                }
            }
        }
    })
});


//校验表单
function validate() {
    //1.校验数据使用正则表达式
    var empName = $("#empinput_add_input").val();

    /*中文的 A-Z a-z 0-9 的任意字符 如果加上 - _ 就可以添加- _*/
    var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u4E00-\u9FA5]{2,5})/;
    if (!regName.test(empName)) {
        // alert("name fail")
        show_validate_message("#empinput_add_input","error","用户名可以是2-5位中文或者6-16位英文和数字的组合")
        return false;
    }else {
        // alert("name success")
        show_validate_message("#empinput_add_input","success","")
    }

    //2.校验邮箱
    var email= $("#email").val();
    var regEmail = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    if (!regEmail.test(email)) {
        //alert("email fail")
        show_validate_message("#email", "error", "邮箱格式不正确")
        return false;
    } else {
        //alert("email success")
        show_validate_message("#email", "success", "")
        return true;
    }

}

//显示校验的结果和信息
function show_validate_message(element,status,msg) {
    // 每次显示前要清空
    $(element).parent().removeClass("has-success");
    $(element).parent().removeClass("has-error");
    $(element).next("span").empty();
    if (status == "success") {
        $(element).parent().addClass("has-success");
        $(element).next("span").text("");
    }else {
        $(element).parent().addClass("has-error");
        $(element).next("span").text(msg);
    }
}

//校验是否有重复的名称,但是没有任何的阻止的行为
$("#empinput_add_input").change(function () {
    //发送ajax请求校验用户名
    //就是这个输入框的值this 表示#empinput_add_input
    var empName = this.value;
    $.ajax({
        url : '/checkuser',
        type : 'post',
        data : "empName="+empName,
        success : function(result) {
            if (result.code == 200) {
                // alert("ajax校验success");
                show_validate_message("#empinput_add_input", "success", "用户名可用");
                //保存ajax的校验信息
                $("#emp_save_btn").attr("ajax-value", true);
            } else {
                // alert("ajax校验fail");
                show_validate_message("#empinput_add_input", "error", "用户名可以是2-5位中文或者6-16位英文和数字的组合");
                $("#emp_save_btn").attr("ajax-value", false);
            }
        }
    })
})
/*--------------------------------------------------添加js-------------------------------------------------------------*/
