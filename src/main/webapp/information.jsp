<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	System.out.println(basepath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basepath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
function buybook(bookid){
	location = "ac/fau?bookid="+bookid;
}
</script>
</head>
<body>
 <h3>书籍详细信息</h3>
 
 <table>
 	<tr><td><strong>编号:</strong>&nbsp;&nbsp;${boo.bookid }</td></tr>
 	
 	<tr><td><strong>名字:</strong>&nbsp;&nbsp;${boo.bookName }</td></tr>
 	
 	<tr><td><strong>出版社:</strong>&nbsp;&nbsp;${boo.publicDept }</td></tr>
 	
 	<tr><td><strong>价格:</strong>&nbsp;&nbsp;${boo.bookPrice }元</td></tr>
 	
 	<tr><td><strong>出版日期:</strong>&nbsp;&nbsp;
 	<fmt:formatDate value="${boo.publicDate }" pattern="yyyy-MM-dd" /></td></tr>
 	
 	<tr><td><strong>作者:</strong>&nbsp;&nbsp;${boo.bookAuth }</td></tr>
 	
 	<tr><td><strong>简介:</strong>&nbsp;&nbsp;${boo.summary }</td></tr>
 	
 	<tr><td><strong>封面:</strong>&nbsp;&nbsp;<img src="${boo.imgPath }" width="200px" ></td></tr>
 	
 </table>
 	<input type="button" value="直接购买" onclick="buybook('${boo.bookid}')" />
 	<!-- 这里，点击购买的时候：
		1.要求后续操作必须得登录之后才可以操作；
		2.还需要提供一个页面给用户，让用户选择使用哪个账户来扣款(因为一个用户，有多个账户)
 -->
</body>
</html>