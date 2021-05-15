<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>${performances.getName()}</title>
</head>

<div>
    <%@ include file="../../WEB-INF/views/menu.jsp" %>
</div>

<body>
<div>
    <table>
        <tr>
            <td>Название</td>
            <td>${performances.getName()}</td>
        </tr>
        <tr>
            <td>Описание</td>
            <td>${performances.getDescription()}</td>
        </tr>
        <tr>
            <td>Даты</td>
            <td>
                <ul>
                <c:forEach  items="${performances.getSchedule()}" var ="schedule">
                    <li><a href="/schedule?id=${schedule.getEvent_id()}">${schedule.getDate()}</a></li>
                </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <td>Участники</td>
            <td>
                <c:forEach  items="${performances.getPerf_persons()}" var ="person">
                    <ul>
                        <li>
                                ${person.getPeople().getName()} - ${person.getRole()}
                        </li>
                    </ul>
                </c:forEach>
            </td>
        </tr>
    </table>
</div>
<br>

<form>
<div style="position: relative">
    <a class="mybutton" href="${pageContext.request.contextPath}/changePerformance?perf_id=${performances.getPerformance_id()}">Изменить спектакль</a>
        <button formaction="${pageContext.request.contextPath}/deletePerformance?perf_id=${performances.getPerformance_id()}" formmethod="post">
            Удалить спектакль
        </button>
</div>
</form>
</body>

</html>