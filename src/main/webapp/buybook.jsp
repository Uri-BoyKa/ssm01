<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<script type="text/javascript" src="js/jquery-1.8.3.js" ></script>
<script type="text/javascript">
	/* showbalance is not defined
 	   at HTMLSelectElement.onchange 没有找到这个showbalance函数,
    	是因为在引入的js写了代码,应该重新在写一个script在写函数代码才行   */
function showbalance(accid) {
	/* data是接收后台返回的数据,返回的一般都是json和string(看尾部指定的类型) */
	$.post("ac/findbalance",{"accid":accid},function(data){
		console.log(data);
		$("#mybalance").html(data);
	},"json");
}
	
$(function(){
	$.post("ac/fa",{},function(data){
		console.log(data);
		$.each(data,function(i,n){
			$("#accid").append("<option value='"+n.accid+"'>"+n.accid+"</option>");
		});
	},"json");
});	

function ispay() {
	if(confirm("是否确认支付?")){
		/* form表单的id名+提交方式 */
		paybook.submit();
	}
}

</script>

</head>
<body>
	<h3>这里是buybook.jsp页面</h3>
	<form id="paybook" action="sbc/buybook" method="post" >
		<input type="hidden" value="${book.bookid }" name="bookid" id="bookid" />
		书名:${book.bookName }<br/>
		价格:${book.bookPrice }元<br/>
		请选择扣款账户:
			<select id="accid" name="accid" onchange="showbalance(this.value);">
				<option value="0" >---请选择扣款账户---</option>
				<%-- <c:forEach items="${aclist }" var="al" >
					<option value="${al.accid}" >${al.accid}</option>
				</c:forEach> --%>
			</select>
			
			余额:<span id="mybalance" ></span><br/>
			<input type="button" value="支付" onclick="ispay()" />
	</form>
</body>
</html>