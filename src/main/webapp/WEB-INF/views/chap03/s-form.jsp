<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Web study</title>
</head>
<body>
    <h1>로그인 하기</h1>
    <form action="/hw/s-login-check" method="Post">
        <input type="text" name="id">
        <input type="text" name="password">
        <button>로그인하기</button>
    </form>

</body>
</html>