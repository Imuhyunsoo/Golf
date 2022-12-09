
<%--
  Created by IntelliJ IDEA.
  User: hyunsoolim
  Date: 2022/12/08
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        html, body { margin: 0; padding: 0; height: 100%;}
        header { width: 100%; height: 7%; background-color: dimgray; color: white;}
        nav { width: 100%; height: 3%; float: left; background-color: darkgray; color: white;}
        section { width: 100%; height: 85%; float: left; background-color: gainsboro;}
        footer { width: 100%; height: 5%; clear: both; background-color: dimgray; color: white; text-align: center;}
        h1 { text-align: center; }
        h2 { text-align: center; }
        a { color: white; text-decoration: none; }
    </style>
</head>
<body>
<header>
    <h1>골프연습장 회원관리 프로그램 ver 1.0</h1>
</header>
<nav>
    <a href="teacher.do">강사조회</a>
    <a href="apply.do">수강신청</a>
    <a href="member.do">회원정보조회</a>
    <a href="sales.do">강사매출현환</a>
    <a href="index.jsp">홈으로</a>
</nav>
<section>
    <h2>수강신청</h2>
    <table width="800" border="1">
        <form action="insert.do" method="post">
            <tr>
                <td>수강월</td>
                <td><input type="text" name="regist_month" size="20">2022년03월 예)202203</td>
            </tr>
            <tr>
                <td>회원명</td>
                <td>
                    <select name="c_name">
                        <option value="회원명" selected="selected">회원명</option>
                        <c:forEach var="name" items="${list}">
                            <option value="${name.c_name}">${name.c_name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>회원번호</td>
                <td><input type="text" name="c_no" size="30">예)10001</td>
            </tr>
            <tr>
                <td>강의장소</td>
                <td>
                    <select name="class_area">
                        <option value="강의장소" selected="selected">강의장소</option>
                        <c:forEach var="area" items="${list}">
                            <option value="${area.class_area}">${area.class_area}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>강의명</td>
                <td>
                    <select name="class_name">
                        <option value="강의신청" selected="selected">강의신청</option>
                        <c:forEach var="name" items="${classNames}">
                            <option value="${name.classname}">${name.classname}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>수강료</td>
                <td><input type="text" name="tuition" size="20">원</td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="수강신청">
                    <input type="button" value="다시쓰기"></td>
            </tr>
        </form>
    </table>
</section>
<footer>
    <h3>HRD KOREA Copyright © 2015 All rights reserved Human Resources Development Service of Korea.</h3>
</footer>
</body>
</html>
