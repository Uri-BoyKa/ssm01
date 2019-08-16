<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basepath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Refresh" content="5;url=<%=basepath %>bc/fid" />
<title>Insert title here</title>
</head>
<body>
<h3>${msg }</h3>
即将跳转...... 
<a href="bc/fid" >没跳转,请点击</a>
</body>
</html>