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
<title>SUCCESS</title>
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
	
	}else
	{
%>
		
		<div class="sucessMsg"><%=ajaxResponse.getMessage()%></div>
		<%
	}
}
%>



</body>
</html>