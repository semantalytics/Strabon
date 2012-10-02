<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="eu.earthobservatory.org.StrabonEndpoint.StrabonBeanWrapper"%>
<%@page import="eu.earthobservatory.org.StrabonEndpoint.StrabonBeanWrapperConfiguration"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<jsp:directive.page import="eu.earthobservatory.org.StrabonEndpoint.Common"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="style.css" type="text/css" />
	<title>TELEIOS: Strabon Endpoint</title>
</head>
<body topmargin="0" leftmargin="0" link="#FFFFFF" vlink="#FFFFFF" alink="#FFFFFF">
<TABLE width="80%" BORDER=0 CELLPADDING=0 CELLSPACING=0>  
	<TR>
      <TD height="1"></TD>
	</TR>		
	<TR>
      <TD height="60" background="images/nav2_bg.gif"> 
        <table width="100%" border=0>
         <tr>
            <td width="1"><img src="images/nav2_bg.gif" width="1" height="60"></td>
            <td valign="top" width="50px"><a href="query.jsp"><img border="0" src="images/teleios_	logo.png" /></a></td>
            <td valign="top" align="left">
            <span id="logo"><a href="query.jsp">&nbsp;stSPARQL Endpoint</a></span><br><span class="style4"></span></td>
          </tr>
        </table>
      </TD>
	</TR>
</TABLE>
	<h1>About: </h1>
	<a href=""><%=request.getAttribute("resource")%></a>
	<div id="response">
	<!-- Response -->
<% if (request.getAttribute("response") != null) {
	if (Common.getHTMLFormat().equals(request.getParameter("format"))) {%>
		<%=request.getAttribute("response")%>
	<%} else { %>
	<PRE><%=request.getAttribute("response") %></PRE>
	<%}%>
<%}%>
</div>
</body>
</html>