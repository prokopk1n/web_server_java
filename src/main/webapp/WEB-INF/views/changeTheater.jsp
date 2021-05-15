<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>Изменение спектакля ${theater.getName()}</title>
</head>

<div>
    <%@ include file="../../WEB-INF/views/menu.jsp" %>
</div>

<body>
<div>
    <form action="/changeTheater?theater_id=${theater.getTheater_id()}" method="post">
        <ul>
            <li>
                <label for="name">Название</label>
                <input type="text" id="name" name="name" value="${theater.getName()}">
            </li>
            <li>
                <label for="phone">Телефон</label>
                <input type="tel" id="phone" name="phone" value="${theater.getPhone()}" pattern="8[0-9]{10}">
            </li>
            <li>
                <label for="address">Адрес</label>
                <input type="text" id="address" name="address" value="${theater.getAddress()}">
            </li>
            <li>
                <label for="site">Сайт</label>
                <input type="text" id="site" name="site" value="${theater.getEmail()}">
            </li>
            <li>
                <button type="submit">Сохранить</button>
            </li>
        </ul>
    </form>
</div>
</body>
</html>