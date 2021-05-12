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
                <label for="theater">Театр</label>
                <select id="theater" name="theater_name">
                    <option selected>${performances.getTheater().getName()}</option>
                    <c:forEach  items="${theaters}" var ="theater">
                        <option>${theater.getName()}</option>
                    </c:forEach>
                </select>
            </li>
            <li>
                Актеры
                <c:forEach  items="${performances.getPerf_persons()}" var ="person">
                    <ul>
                        <li>
                                ${person.getPeople().getName()}
                                <button formaction="${pageContext.request.contextPath}/deleteMember?perf_id=${performances.getPerformance_id()}&member_id=${person.getPerson_id()}" formmethod="post">
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


</body>