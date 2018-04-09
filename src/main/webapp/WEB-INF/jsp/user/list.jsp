<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>用户列表</h1>
<div>
	<table>
		<thead>
			<tr>
				<td>用户ID</td>
				<td>用户姓名</td>
				<td>注册时间</td>
				<td>状态</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.username}</td>
					<td>${user.registTime}</td>
					<td>${user.status}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>