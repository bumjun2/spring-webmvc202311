<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Web study</title>
</head>
<body>
    <form action="/main/join/ok" method="post">
        id : <input type="text" name="id"> <br>
        password : <input type="text" name="password"> <br>
        userName : <input type="text" name="userName">
        <button type="submit">가입하기</button>
    </form>
</body>
</html>