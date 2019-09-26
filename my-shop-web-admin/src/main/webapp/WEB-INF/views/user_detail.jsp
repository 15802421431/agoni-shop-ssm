<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>黑客世界 | 用户详情</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="box-body">
    <table id="dataTable" class="table">
        <tbody>
        <tr>
            <th>
                Username :
            </th>
            <td>
                ${tbUser.username}
            </td>
        </tr>
        <tr>
            <th>
                Phone :
            </th>
            <td>
                ${tbUser.phone}
            </td>
        </tr>
        <tr>
            <th>
                Email :
            </th>
            <td>
                ${tbUser.email}
            </td>
        </tr>
        <tr>
            <th>
                Created :
            </th>
            <td>
                <fmt:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
        </tr>
        <tr>
            <th>
                Updated :
            </th>
            <td>
                <fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<!-- /.box-body -->
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
