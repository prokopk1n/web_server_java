<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>Создания нового театра</title>
</head>

<div>
    <%@ include file="../../WEB-INF/views/menu.jsp" %>
</div>

<body>
<div>
    <form action="/addTheater" method="post">
        <ul>
            <li>
                <label for="name">Название</label>
                <input type="text" id="name" name="name">
            </li>
            <li>
                <label for="site">Сайт</label>
                <input type="text" id="site" name="site">
            </li>
            <li>
                <label for="address">Адрес</label>
                <input type="text" id="address" name="address">
            </li>
            <li>
                <label for="phone">Телефон в формате 8...</label>
                <input type="tel" id="phone" name="phone" pattern="8[0-9]{10}">
            </li>
            <li>
                <button type="submit">Сохранить</button>
            </li>
        </ul>
    </form>
</div>
</body>

</html>