<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>${theater.getName()}</title>
</head>

<div>
    <%@ include file="../../WEB-INF/views/menu.jsp" %>
</div>

<body>
<div>
    <table>
        <tr>
            <td>Название</td>
            <td>${theater.getName()}</td>
        </tr>
        <tr>
            <td>Контакты</td>
            <td>
                <ul>
                    <li>${theater.getPhone()}</li>
                    <li>${theater.getEmail()}</li>
                    <li>${theater.getAddress()}</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td>Залы</td>
            <td>
                <ul>
                <c:forEach  items="${theater.getConcert_halls()}" var ="concert_hall">
                    <li><a href="/Concert_halls?id=${concert_hall.getHall_id()}">${concert_hall.getName()}</a></li>
                </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <td>Спектакли</td>
            <td>
                <c:forEach  items="${theater.getPerformances()}" var ="performance">
                    <ul>
                        <li>
                            <a href="/performance?id=${performance.getPerformance_id()}">${performance.getName()}</a>
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
        <a class="mybutton" href="${pageContext.request.contextPath}/changeTheater?theater_id=${theater.getTheater_id()}">Изменить театр</a>
        <button formaction="${pageContext.request.contextPath}/deleteTheater?theater_id=${theater.getTheater_id()}" formmethod="post">
            Удалить театр
        </button>
    </div>
</form>
</body>

</html>