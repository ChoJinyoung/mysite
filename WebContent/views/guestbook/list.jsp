<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.bit2015.mysite.vo.GuestBookVo"%>
<%@page
	import="com.bit2015.mysite.web.action.guestbook.GuestBookActionFactory"%>

<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<%
	//List<GuestBookVo> list = (List<GuestBookVo>) request.getAttribute("list");
	pageContext.setAttribute("newLine", "\n");
%>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/views/include/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form action="/mysite/gb" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>

				<ul>
					<li><c:set var='count' value='${fn:length(list) }'></c:set> <c:forEach
							items='${list}' var="list" varStatus="status">
							<table>
								<tr>
									<td>[${count-status.index }]</td>
									<td>${list.name}</td>
									<td>${list.regDate}</td>
									<td><a href="/mysite/gb?a=delform&no=${list.no}">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>${fn:replace(list.message,newLine,'<br>')}</td>
								</tr>
								
							</table>
							</c:forEach>
							<br></li>
				</ul>
			</div>
		</div>
		<c:import url="/views/include/navigation.jsp" />
		<c:import url="/views/include/footer.jsp" />
	</div>
</body>
</html>