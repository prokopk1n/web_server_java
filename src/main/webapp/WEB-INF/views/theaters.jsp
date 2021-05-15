<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>Театры</title>
</head>

<div>
    <%@ include file="../../WEB-INF/views/menu.jsp" %>
</div>

<body>
<div>
    <table>
        <tr>
            <th>Название</th>
            <th>Контакты</th>
        </tr>
        <c:forEach  items="${theaters}" var ="theater">
            <tr>
                <td><a href="${pageContext.request.contextPath}/theater?theater_id=${theater.getTheater_id()}">${theater.getName()}</a></td>
                <td>
                        <ul>
                            <li><a href="http://${theater.getEmail()}">${theater.getEmail()}</a></li>
                            <li>${theater.getAddress()}</li>
                            <li>${theater.getPhone()}</li>
                        </ul>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>


</html>