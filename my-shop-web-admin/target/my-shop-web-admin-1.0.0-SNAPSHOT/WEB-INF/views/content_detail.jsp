<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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