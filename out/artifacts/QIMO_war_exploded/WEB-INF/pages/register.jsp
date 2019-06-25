<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/11
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <style>
        #div1{
            border: solid 2px silver;
            margin-top: -600px;
            margin-left: 700px;
            text-align: center;
        }

    </style>
    <script>
        // 判断是登录账号和密码是否为空
        function check() {
            var usercode = $("#user_code").val();
            var password = $("#user_password").val();
            if (usercode==""||password==""){
                $("#message").text("账号或密码不能为空！");
                return false;
            }
            return true;
        }

        function cancel(date) {
            var c= document.getElementById("cancel");
            c.action = date;
            c.submit();
            return false;
        }

    </script>
</head>
<body>
    <form:form id="cancel" method="get"></form:form>

    <div>
        <img src="${requestScope.request.contextPath}/img/backgrount.jpg" style="width: 100%;height: auto;">
        <div id="div1" class="table-bordered col-lg-2 " >
            <div id="message">${requestScope.insertResult}</div>
                <form:form onsubmit="return check()" cssStyle="margin-top: 30px" action="${pageContext.request.contextPath}/user/B_insert" method="post" modelAttribute="user">
                    账号：<form:input path="user_code"/><form:errors path="user_code"/> <br><br>
                    用户名：<form:input path="user_name"/><br><br>
                    密码：<form:password path="user_password"/><form:errors path="user_password"/><br><br>
                    <input type="submit" value="注册">
                    <input onclick="cancel('/user/login')" type="button" value="取消">
                </form:form>
            </div>
        </div>
    </div>

</body>
</html>
