<%--
  Created by IntelliJ IDEA.
  User: hyunsoolim
  Date: 2022/12/07
  Time: 8:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>index</title>
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
  <h2>강사 조회</h2>
  <table width="700" border="1">
    <thead>
      <tr>
        <td>강사코드</td>
        <td>강사명</td>
        <td>강의명</td>
        <td>수강료</td>
        <td>강사자격취득일</td>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="board" items="${teacherList}">
        <tr>
          <td>${board.teacher_code}</td>
          <td>${board.teacher_name}</td>
          <td>${board.class_name}</td>
          <td>${board.class_price}</td>
          <td>${board.teacher_regist_date}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</section>
<footer>
  <h3>HRD KOREA Copyright © 2015 All rights reserved Human Resources Development Service of Korea.</h3>
</footer>
</body>
</html>
