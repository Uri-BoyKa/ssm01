<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basepath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
	/* a:visited {color: red} 已被访问过的链接 红色 */
</style>
<script type="text/javascript">
function goAdd(){
	location.href="uc/tiao";
}

</script>
</head>
<body>

	<h3>这里是book_list.jsp页面</h3>
<table border="1" width="800" cellspacing="0"  >
	<tr>
		<td>编号</td>
		<td>名字</td>
		<td>出版日期</td>
		<td>作者</td>
		<td>简介</td>
	</tr>
	
	<c:forEach items="${booklist }" var="bl" >
	<tr>
	
		<td>${bl.bookid }</td>
		<td><a href="bc/infor?b=${bl.bookid }" style="text-decoration:none;color:blue " >
		  ${bl.bookName }</a></td>
		<td>
			<fmt:formatDate value="${bl.publicDate }" pattern="yyyy-MM-dd" />
		</td>
		<td>${bl.bookAuth }</td>
		<td>
			${fn:substring(bl.summary,0,20) }${fn:length(bl.summary)>20?"...":"" }
		</td>
	</tr>
	</c:forEach>

</table>

	<input type="button" onclick="goAdd();" value="添加书籍" >
	
	<c:if test="${page.isFirstPage==true}"><a>首页</a></c:if>
	<c:if test="${page.isFirstPage==false}">
		<a href="bc/fid?page=${page.firstPage}" >首页</a>
	</c:if>
	<c:if test="${page.hasPreviousPage==true}">
		<a href="bc/fid?page=${page.prePage}" >上一页</a>
	</c:if>
	<c:if test="${page.hasPreviousPage==false}"><a>上一页</a></c:if>
	<c:if test="${page.hasNextPage==true}">
		<a href="bc/fid?page=${page.nextPage}" >下一页</a>
	</c:if>
	<c:if test="${page.hasNextPage==false}"><a>下一页</a> </c:if>
	<c:if test="${page.isLastPage==true}"><a>末页</a></c:if>
	<c:if test="${page.isLastPage==false}">
		<a href="bc/fid?page=${page.lastPage}" >末页</a>
	</c:if>
	
	每页${page.pageSize }条 当前页${page.size}条数据${page.pageNum}/${page.pages}页
	
	共有:${page.total}条信息
	<%-- 导航页码数 : ${page.navigatePages}
	所有导航页号 : ${page.navigateNums[]}--%>

</body>
</html>