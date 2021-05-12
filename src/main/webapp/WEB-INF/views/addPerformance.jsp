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
        <form action="/addPerformance" modelAttribute="performance" method="post">
            <ul>
                <li>
                    <label for="name">Название</label>
                    <input type="text" id="name" name="name">
                </li>
                <li>
                    <label for="duration">Длительность</label>
                    <input type="time" id="duration" name="duration">
                </li>
                <li>
                    <label for="start">Дата начала</label>
                    <input type="date" id="start" name="start">
                </li>
                <li>
                    <label for="finish">Дата окончания</label>
                    <input type="date" id="finish" name="finish">
                </li>
                <li>
                    <label for="description">Описание</label>
                    <textarea id="description" name="description" cols="50" rows="15"></textarea>
                </li>
                <li>
                    <label for="theater">Театр</label>
                    <select id="theater" name="theater_name">
                        <c:forEach  items="${theaters}" var ="theater">
                            <option>${theater.getName()}</option>
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