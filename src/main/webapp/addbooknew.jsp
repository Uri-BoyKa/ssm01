<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<h3>添加书籍信息</h3>
<%-- Current request is not a multipart request因为没在form变单里加 : enctype="multipart/form-data" --%>
<form action="bc/adbok" method="post" enctype="multipart/form-data" >
<table>
		<tr>
			<td>书名:</td>
			<td><input type="text" name="bookName" value="大圣智斗金蝉子" > </td>
		</tr>
		<tr>
			<td>出版社:</td>
			<td><input type="text" name="publicDept" value="西游出版社" > </td>
		</tr>
		<tr>
			<td>价格:</td>
			<td><input type="text" name="bookPrice" value="28" > </td>
		</tr>
		<tr>
			<td>出版日期:</td>
			<td><input type="text" name="publicDate" value="2019-08-10" > </td>
		</tr>
		<tr>
			<td>作者:</td>
			<td><input type="text" name="bookAuth" value="齐天大圣孙悟空" > </td>
		</tr>
		<tr>
			<td>图片:</td>
			<td><input type="file" name="pic" ></td>
		</tr>
		<tr>
			<td style="padding-top: -10px" >简介:</td>
			<td><textarea rows="5" cols="30" name="summary" >hello java</textarea></td>
		</tr>
</table>
		<input type="submit" value="增  加" >
	</form>
</body>
</html>