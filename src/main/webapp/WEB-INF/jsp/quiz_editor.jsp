<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактор</title>

    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>

    <script type="text/javascript" src="js/quiz_editor.js"></script>
    <link rel="stylesheet" href="css/quiz_editor.css" type="text/css">
</head>
<body>
<form action="Upload" method="post" enctype="multipart/form-data">
    <div class="quiz_header">
        <span class="header-content"><p>Название викторины:</p><input id="i1" class="quiz_name_input" name="quiz_name" required></span>
        <span class="header-content"><p>Количество вопросов:</p><select id="s1" name="question_amount" onchange="selectOnChange()" required>
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
                <option>10</option>
            </select></span><br/>
        <span class="header-content"><input type="file" name="image_path"></span>
    </div>

    <div class="creator_block">
        <script>selectOnChange()</script>
    </div>
    <input type="hidden" name="command" value="createquiz">
    <input type="submit" value="Submit">
</form>
</body>
</html>
