<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Question</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous"/>
</head>
<body>

<c:set var="qn" value="${requestScope.question}"/>
<c:set var="amount" value="${sessionScope.quiz.questionList.size()}"/>
<c:set var="id" value="${sessionScope.quiz.id}"/>

    <div class="question">
        ${sessionScope.quiz.questionList[qn - 1]}
    </div>

<c:if test="${amount > 1}">
    <ul class="pagination">
        <c:if test="${qn != 1}">
            <li class="page-item"><a class="page-link"
                                     href="Controller?command=gotoquizpage&question=${qn-1}&data_id=${id}">Prev</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${amount}" var="i">
            <c:choose>
                <c:when test="${qn eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="Controller?command=gotoquizpage&question=${i}&data_id=${id}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${qn lt amount}">
            <li class="page-item"><a class="page-link"
                                     href="Controller?command=gotoquizpage&question=${qn+1}&data_id=${id}">next</a>
            </li>
        </c:if>
    </ul>
</c:if>
</body>
</html>
