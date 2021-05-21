<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/main.css" type="text/css">
</head>
<body>
    <h1>Викторины по фильмам</h1>
    <div class="main_container">
        <c:forEach var="n" items="${sessionScope.quizList}">
            <div class="quiz_block">
                <div class="quiz_image">
                    <img src="${n.imagePath}">
                </div>
                <div class="quiz_title">
                    <h4>${n.title}</h4>
                    <c:if test="${n.done}">
                        <p>Пройден!</p>
                    </c:if>
                </div>
                <div class="quiz_block_panel">
                    <a href="Controller?command=gotoquizpage&data_id=${n.id}">Начать!</a>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
