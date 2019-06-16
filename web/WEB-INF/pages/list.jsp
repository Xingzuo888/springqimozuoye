<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/11
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script>
        function deleteStudent(data) {
            var formDelete = document.getElementById("formDelete");
            formDelete.action = data;
            formDelete.submit();
            return false;
        }
    </script>
    <style>
        #table1{
            background-color: silver;
        }
    </style>
</head>
<body>
<div>
    <img src="${pageContext.request.contextPath}/img/list.jpg" style="width: 100%;height: auto;">
    <div class="col-lg-8 col-lg-offset-2" style="margin-top: -800px">
        <div class="col-lg-offset-4 label label-info col-lg-3" style="font-size: 30px">学生信息管理</div>
        <c:if test="${empty requestScope.student_list}">
        <div style="margin: 100px">
            <h1>没有学生记录</h1>
        </div>
        </c:if>
        <c:if test="${!empty requestScope.student_list}">
            <div style="margin: 100px">
            <table class="table table-bordered text-center " id="table1">
                <tr class="list-group list-group-item-info">
                    <th class="text-center">ID</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">学号</th>
                    <th class="text-center">性别</th>
                    <th class="text-center" colspan="2">操作</th>
                </tr>
                <c:forEach items="${requestScope.student_list}" var="student">
                    <tr class="list-group nav-tabs">
                        <td>${student.id}</td>
                        <td>${student.sname}</td>
                        <td>${student.sno}</td>
                        <td>${student.sex}</td>
                        <td><a href="/student/B_insert/${student.id}">编辑</a> </td>
                        <td><a href="javascript:void(0);" onclick="deleteStudent('/student/B_delete/${student.id}') ">删除</a> </td>
                    </tr>
                </c:forEach>
            </table>
            </div>
        </c:if>
        <nav style="margin-top: -90px">
            <ul class="pager">
                <li>共有${requestScope.count}条数据</li>
                <li><a href="${pageContext.request.contextPath}/student/B_queryUp">上一页</a> </li>
                <li><a href="${pageContext.request.contextPath}/student/B_queryDown">下一页</a> </li>
            </ul>
        </nav>
        <div style="text-align: center">
            <a class="h4 list-group-item col-lg-8 col-lg-offset-2" href="${pageContext.request.contextPath}/student/B_insert">新增学生</a>
        </div>
    </div>
</div>
<form:form id="formDelete" action="" method="delete"></form:form>
</body>
</html>
