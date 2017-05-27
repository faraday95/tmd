<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page import="com.twitter.model.AJAXResponse"%>
<%@page import="com.twitter.model.Message"%>

<%@page import="com.twitter.constants.TwitterConstants"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HashTagSubmit</title>
<script type="text/javascript">

validatestopword=function()
{
	var stopWord=document.getElementById('hashTag').value;

	if(stopWord<=0)
	{
		alert("Please Enter a Value for Hash Tag");
		return false;
	}
}
</script>
</head>
<body>
<body background="<%=request.getContextPath()%>/images/background.jpg">
<jsp:include page="/jsp/customer.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>

<%
	AJAXResponse ajaxResponse=(AJAXResponse) request.getAttribute(TwitterConstants.Keys.OBJ);
if(null==ajaxResponse)
{
	
}
else
{
	boolean status=ajaxResponse.isStatus();
%>
<%
	if(!status)
	{
		List<Message> msg=ajaxResponse.getEbErrors();
%>
<%
		for(int i=0;i<msg.size();i++)
		{
			Message tempMsg=msg.get(i);
	%>
	
	<div class="errMsg"><%= tempMsg.getMsg()%></div>
	
<%		}
	
	}
}
%>


<form action="<%=request.getContextPath()%>/tweet/submitHashTag.do" method="post"  >


	<table>
		<tr>
			<td><label>Enter Hash Tag:</label> </td>
			<td><input name="hashTag" id="hashTag" type="text"  size="50" maxlength="50" /></td>
		</tr>
			<tr>
			<td><input  type="submit" value="Add HashTag" onclick="validateHashTag()"/></td>
		</tr>
			
	</table>
</form>
</body>
</html>