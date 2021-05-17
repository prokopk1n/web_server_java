<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>Изменение билетов</title>
</head>

<div>
    <%@ include file="../../WEB-INF/views/menu.jsp" %>
</div>

<body>
<div>
    <ul>
    <c:forEach items="${seats}" var="seat">
        <form action="/changeTicket?event_id=${event.getEvent_id()}&seat_id=${seat.getSeat_id()}" method="post">
                <li>
                    ${seat.getType_of_seats().getName()}
                    ${seat.getSection()}
                    <c:if test="${seat.getSide()}=='L' || ${seat.getSide()}=='R'}">
                        ${seat.getSide()}
                    </c:if>
                    ${seat.getRow()}
                    ${seat.getSeat()}
                        <label for="cost">Стоимость</label><input type="text" id="cost" name="cost" required value="1000">
                        <label for="in_stock">В продаже</label>
                        <select id="in_stock" name="in_stock">
                            <option>Да</option>
                            <option>Нет</option>
                        </select>
                        <button type="submit">Сохранить</button>
                </li>
        </form>
    </c:forEach>
    </ul>
</div>
</body>
</html>