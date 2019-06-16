<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/11
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</head>
<script>
    function checkAdd() {
        var sno = $("#sno").val();
        var sname = $("#sname").val();
        var sex = $("#sex").val();
        if (sno==""||sname==""||sex==""){
            $("#message").text("学号或姓名或性别不能为空！");
            return false;
        }
        return true;
    }
</script>
<body>
<div>
    <div>
        <img src="${requestScope.request.contextPath}/img/add.jpg" style="width: 100%;height: 1000px">
    </div>
    <div class="table-bordered table col-lg-offset-5" style="margin-top: -600px">
        <span id="message"></span>
        <form:form onsubmit="return checkAdd()" action="${pageContext.request.contextPath}/student/B_insert" method="post" modelAttribute="student">
            <div id="div2">
                <c:if test="${student.id!=null}">
                    学号：${student.sno}<br><br>
                    <form:hidden path="id"/>
                    <input type="hidden" name="_method" value="PUT">
                </c:if>
                <c:if test="${student.id==null}">
                    学号：<form:input path="sno"/><form:errors path="sno"/><br><br>
                </c:if>
                学生姓名：<form:input path="sname"/><form:errors path="sname"/><br><br>
                性别：<form:radiobuttons path="sex" items="${requestScope.sex}"/>   <form:errors path="sex"/><br><br>
                <input type="submit" value="保存">
            </div>

        </form:form>
    </div>
</div>

</body>
</html>
