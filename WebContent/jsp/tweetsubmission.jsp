<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tweet Submission</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/extjs41/resources/css/ext-all.css" />
<script type="text/javascript" >
var contextPath='<%=request.getContextPath()%>';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/extjs41/bootstrap.js"></script>

</head>
<body background="<%=request.getContextPath()%>/images/tweet.jpg">
<jsp:include page="/jsp/customer.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>

<div id="content">

<div id="confirmationMessage" color="black" background="yellow" ></div>
<div id="container">
<form action="<%=request.getContextPath()%>/tweet/retriveTweets.do" method="post"  >


	<table>
		<tr>
			<td><input  type="submit" value="Retrive Tweet"/></td>
		</tr>
			
	</table>
</form>

<form>

</form>

</div>
</div>

</body>
</html>