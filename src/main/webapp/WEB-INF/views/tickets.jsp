<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>Билеты</title>
</head>

<div>
    <%@ include file="../../WEB-INF/views/menu.jsp" %>
</div>

<body>
<p align="center">Билеты на ${event.getPerformances().getName()}</p>
<p align="center">Дата: ${event.getDate()}</p>
<table>
    <tr>
        <th>Тип места</th>
        <th>Билеты</th>
    </tr>
    <c:forEach items="${type_of_seats}" var="type_of_seat">
    <tr>
        <td>${type_of_seat.getName()}</td>
        <td>
            <ul>
                <li>Ряд Место Стоимость</li>
                <c:forEach items="${types[type_of_seat.getType_id()]}" var="ticket">
                    <c:if test="${ticket.isIn_stock()}">
                        <li>${ticket.getSeats().getRow()} ${ticket.getSeats().getSeat()} ${ticket.getCost()}
                        <form>
                            <button formaction="/buyTicket?ticket_id=${ticket.getTicket_id()}" formmethod="post">Купить</button>
                        </form>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </td>
    </tr>
    </c:forEach>
</table>

<form>
    <div style="position: relative">
        <a class="mybutton" href="${pageContext.request.contextPath}/changeTickets?event_id=${event.getEvent_id()}">Изменить билеты</a>
    </div>
</form>

</body>