<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>Билеты в театр</title>
</head>

<div>
        <%@ include file="../../WEB-INF/views/menu.jsp" %>
</div>

<body>
<div>
    <table>
        <tr>
            <th>Спектакль</th>
            <th>Дата</th>
            <th>Театр</th>
        </tr>
        <c:forEach  items="${performances}" var ="performance">
            <tr>
                <td><a href="${pageContext.request.contextPath}/performance?id=${performance.getPerformance_id()}">${performance.getName()}</a></td>
                <td>${performance.getStart()} - ${performance.getFinish()}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/theater?id=${performance.getTheater().getTheater_id()}">
                        ${performance.getTheater().getName()}
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div style="position: absolute; left: 43%; bottom: -50px">
    <a class="mybutton" href="${pageContext.request.contextPath}/addPerformance">Добавить спектакль</a>
</div>
</body>


</html>