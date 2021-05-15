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
    <form action="/addPerson" method="post">
        <ul>
            <li>
                <label for="name">ФИО</label>
                <input type="text" id="name" name="name" required>
            </li>
            <li>
                <label for="description">Описание</label>
                <textarea id="description" name="description" cols="50" rows="15" required></textarea>
            </li>
            <button>Сохранить</button>
        </ul>
    </form>
</div>
</body>

</html>