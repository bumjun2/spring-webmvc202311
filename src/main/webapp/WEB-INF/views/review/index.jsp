<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Web study</title>
</head>
<body>
    <form action="/main/isLogin" method="Post">
        #id : <input type="text" name="id">
        #password : <input type="text" name="password">
        <button type="submit">로그인 하기</button>
    </form>
    <a href="/main/join">회원가입</a>
    <br>
    <c:forEach var="m" items="${mList}">
        ${m.id}
        ${m.shortUserName}
        <a href="/main/delete?id=${m.id}">x</a>
        <br>
    </c:forEach>
    <form action="/main/search" method="post">
        <input type="text" name="id">
        <button type="submit">회원 찾기</button>
    </form>

</body>
</html>