<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/result.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous"/>
</head>
<body>

<c:set var="qz" value="${requestScope.quiz}"/>
<c:set var="res" value="${requestScope.result}"/>

<div class="main">
    <span class="header">${qz.title}</span>
    <hr>
    <c:forEach var="q" items="${qz.questionList}">
        <div class="question">
            ${qz.questionList.indexOf(q) + 1})
            <c:choose>
                <c:when test="${q.correct}">
                    <strong style="color: lawngreen; font-size: 24px">&#10003;</strong>
                </c:when>
                <c:otherwise>
                    <strong style="color: red; font-size: 24px">&times;</strong>
                </c:otherwise>
            </c:choose>
            <span class="question_title">${q.title}</span>: <span class="user_answer">${q.userAnswer}</span>
        </div>
    </c:forEach>
    <hr>
    <div class="result">
        <span class="result">Your result: <fmt:formatNumber value="${res}" pattern="" type="percent" maxIntegerDigits="3"/></span>
    </div>
    <span class="to_main"><form action="Controller?command=gotomainpage" method="post"><input class="button" type="submit" value="To main"></form></span>
</div>
</body>
</html>
