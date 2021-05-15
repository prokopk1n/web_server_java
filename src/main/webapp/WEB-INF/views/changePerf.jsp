<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../WEB-INF/views/include.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>Изменение спектакля ${performances.getName()}</title>
</head>

<div>
    <%@ include file="../../WEB-INF/views/menu.jsp" %>
</div>

<body>
<div>
    <form action="/changePerformance?perf_id=${performances.getPerformance_id()}" method="post">
        <ul>
            <li>
                <label for="name">Название</label>
                <input type="text" id="name" name="name" value="${performances.getName()}">
            </li>
            <li>
                <label for="duration">Длительность</label>
                <input type="time" id="duration" name="duration" value="${performances.getDuration()}">
            </li>
            <li>
                <label for="start">Дата начала</label>
                <input type="date" id="start" name="start" value="${performances.getStart()}">
            </li>
            <li>
                <label for="finish">Дата окончания</label>
                <input type="date" id="finish" name="finish" value="${performances.getFinish()}">
            </li>
            <li>
                <label for="description">Описание</label>
                <textarea id="description" name="description" cols="75" rows="30">${performances.getDescription()}</textarea>
            </li>
            <li>
                Актеры
                <c:forEach  items="${performances.getPerf_persons()}" var ="person">
                    <ul>
                        <li>
                                ${person.getPeople().getName()}
                                <button formaction="${pageContext.request.contextPath}/deleteMember?member_id=${person.getPerson_id()}" formmethod="post">
                                    Удалить
                                </button>
                        </li>
                    </ul>
                </c:forEach>
            </li>
            <li>
                <label for="member">Добавить актёра</label>
                <select id="member" name="member">
                    <option selected>Пусто</option>
                    <c:forEach  items="${people}" var ="man">
                        <option>${man.getName()}</option>
                    </c:forEach>
                </select>
            </li>
            Если не нашли подходящий вариант, то можно:
            <button formaction="addPerson" formmethod="get">Добавить нового человека</button>
            <li>
                <label for="role">Роль</label>
                <input type="text" id="role" name="role">
            </li>

            <li>
                <button type="submit">Сохранить</button>
            </li>
        </ul>
    </form>
</div>

<br>
<a class="mybutton" href="/addEvent?perf_id=${performances.getPerformance_id()}">
        Добавить сеанс
</a>


</body>
</html>