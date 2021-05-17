<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>Участники</title>
</head>

<div>
    <%@ include file="../../WEB-INF/views/menu.jsp" %>
</div>

<body>
<div>
    <table>
        <tr>
            <th>Имя</th>
            <th>Описание</th>
            <th>Действие</th>
        </tr>
        <c:forEach  items="${members}" var ="man">
            <tr>
                <td>${man.getName()}</td>
                <td>${man.getDescription()}</td>
                <td>
                    <form>
                        <a href="changeMan?member_id=${man.getPeople_id()}">Изменить</a>
                        <button formaction="deleteMan?member_id=${man.getPeople_id()}" formmethod="post">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>


</html>