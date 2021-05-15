<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>Изменить сеанс</title>
</head>

<div>
    <%@ include file="../../WEB-INF/views/menu.jsp" %>
</div>

<body>
<div>
    <form action="/changeEvent?event_id=${event.getEvent_id()}" method="post">
        <ul>
            <li>
                Спектакль "${event.getPerformances().getName()}"
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
                    <c:forEach  items="${event.getPerformances().getTheater().getConcert_halls()}" var ="hall">
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