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
    <form action="/addEvent?perf_id=${performance.getPerformance_id()}" method="post">
        <ul>
            <li>
                Спектакль "${performance.getName()}"
            </li>
            <li>
                <label for="date">Дата</label>
                <input type="date" id="date" name="date" required>
            </li>
            <li>
                <label for="time">Время</label>
                <input type="time" id="time" name="time" required>
            </li>
            <li>
                <label for="hall">Зал</label>
                <select id="hall" name="hall_name" required>
                    <c:forEach  items="${theater.getConcert_halls()}" var ="hall">
                        <option>${hall.getName()}</option>
                    </c:forEach>
                </select>
            </li>
            <li>
                <button type="submit">Сохранить</button>
            </li>
        </ul>
    </form>
</div>
</body>

</html>