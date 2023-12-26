<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Web study</title>
    <%@ include file="include/static-head.jsp"%>
    <style>
        h1{
            margin: 200px auto;
            width: 40%;
            font-size: 40px;
            font-weight: 700;
            color: orange;
            text-align: center;
        }
    </style>
</head>
<body>

    <%

        String userName = "방문자";
        //클라이언트에게 쿠키를 검사
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("login")){
                userName = cookie.getValue();
            }
        }
    %>

    <%@ include file="include/header.jsp"%>

<%--    <h1><%= userName %> 안녕하세요~~~~~</h1>--%>

<%--    서버에서 model이나 redirectAttributes에 담아 놓은 데이터는
        ${requestScope.aaa} 로 참조 가능하고 requestScrope는 생략 가능

        그런데 세션에 담은 데이터는
        ${sessionScope.aaaa}로 참조가능하고 model에 같은 이름의 데이터가 있다면
        sessionScope를 생략가능하다.--%>
    <c:if test="${login == null}">
        <h1>방문자님 안눙하세요 !!!!</h1>
    </c:if>

    <c:if test="${login != null}">
        <h1>${login.nickName}님 하리룰</h1>
    </c:if>

</body>
</html>