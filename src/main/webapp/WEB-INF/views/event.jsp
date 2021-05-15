<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>${event.getPerformances().getName()}</title>
</head>

<div>
    <%@ include file="../../WEB-INF/views/menu.jsp" %>
</div>

<body>
<div>
    <table>
        <tr>
            <td>Название</td>
            <td>${event.getPerformances().getName()}</td>
        </tr>
        <tr>
            <td>Театр</td>
            <td>${event.getPerformances().getTheater().getName()}</td>
        </tr>
        <tr>
            <td>Зал</td>
            <td>${event.getConcert_halls().getName()}</td>
        </tr>
        <tr>
            <td>Время</td>
            <td>${event.getDate()}</td>
        </tr>
    </table>
</div>
<br>

<form>
    <div style="position: absolute; left: 40%">
        <a class="mybutton" href="${pageContext.request.contextPath}/changeEvent?event_id=${event.getEvent_id()}">Изменить сеанс</a>
        <button formaction="${pageContext.request.contextPath}/deleteEvent?event_id=${event.getEvent_id()}" formmethod="post">
            Удалить сеанс
        </button>
    </div>
    <div style="position: absolute; right: 15%">
        <a class="mybutton" href="/tickets?event_id=${event.getEvent_id()}">Билеты</a>
    </div>
</form>

</body>

</html>