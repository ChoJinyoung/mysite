<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.bit2015.mysite.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  //MemberVo vo =(MemberVo)session.getAttribute("authUser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="/mysite/board">
					<input type = "hidden" name = "a" value="write">
					<%-- <%
					if(vo!=null){
					%> --%>
					<c:if test="${not empty authUser}">
					<input type = "hidden" name = "mno" value="${authUser.no}">
					</c:if>
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="/mysite/board">취소</a>
						<input type="submit" value="등록">
					</div>
				</form>				
			</div>
		</div>
		<c:import url="/views/include/navigation.jsp"/>
		<c:import url="/views/include/footer.jsp" />
	</div>
</body>
</html>