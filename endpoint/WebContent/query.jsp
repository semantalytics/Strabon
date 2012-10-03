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
	<script type="text/javascript" src="js/more_link.js"></script>
	<link rel="stylesheet" href="style.css" type="text/css" />
	<script type="text/javascript">
		function toggleMe(a) {
			var e = document.getElementById(a);
			if (!e) {
				return true;
			}
			if (e.style.display == "none") {
				e.style.display = "block";
			} else {
				e.style.display = "none";
			}
			return true;
		}
	</script>
	<script>
		$(document).ready(function() {
		var showChar = 100;
		var ellipsestext = "...";
		var moretext = "more";
		var lesstext = "less";
		$('.more').each(function() {
			var content = $(this).html();
	
			if(content.length > showChar) {
	
				var c = content.substr(0, showChar);
				var h = content.substr(showChar-1, content.length - showChar);
	
				var html = c + '<span class="moreelipses">'+ellipsestext+'</span>&nbsp;<span class="morecontent"><span>' + h + '</span>&nbsp;&nbsp;<a href="" class="morelink">'+moretext+'</a></span>';
	
				$(this).html(html);
			}
	
		});
	
		$(".morelink").click(function(){
			if($(this).hasClass("less")) {
				$(this).removeClass("less");
				$(this).html(moretext);
			} else {
				$(this).addClass("less");
				$(this).html(lesstext);
			}
			$(this).parent().prev().toggle();
			$(this).prev().toggle();
			return false;
		});
	});
</script>
<%
	if (request.getAttribute("pathToKML") != null) {
	if ("map_local".equals(request.getAttribute("handle"))) {
%>
	<script type="text/javascript" src="js/geoxml3-kmz.js"></script>
	<script type="text/javascript" src="js/ProjectedOverlay.js"></script>	
	<%
			}
		%>
	<link href="http://code.google.com/apis/maps/documentation/javascript/examples/default.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
	<script type="text/javascript">
		function initialize() {
			// center at Brahames
			var brahames = new google.maps.LatLng(37.92253, 23.72275);
			var myOptions = {
				zoom: 11,
				center: brahames,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			
			// get KML filename
			var kml = '<%=request.getAttribute("pathToKML")%>';
			// create map
			var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
		
			// display using geoxml3
		<%if ("map_local".equals(request.getAttribute("handle"))) {%>
			var myParser = new geoXML3.parser({map: map});
			myParser.parse(kml);
			
		<%} else {%>
			var ctaLayer = new google.maps.KmlLayer(kml);
			ctaLayer.setMap(map);
		<%}%>
		}
	</script> 
<%
 	}
 %>
 	<!-- jQuery start  -->
	<link type="text/css" href="style-menu.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.23.custom.min.js"></script>
	<script type="text/javascript">
	$(function(){
			// Accordion
			$("#accordion").accordion({ 
				header: "h3",
				fillSpace: true
			});
			//hover states on the static widgets
			$('#dialog_link, ul#icons li').hover(
				function() { $(this).addClass('ui-state-hover'); },
				function() { $(this).removeClass('ui-state-hover'); }
			);
	});
	</script>
	<style type="text/css">
		/*demo page css*/
		body{ font: 90% "Trebuchet MS", sans-serif; margin: 50px;}
		.container { height:700px; width:165px;}
		.demoHeaders { margin-top: 1em;}
		#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
		#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
		ul#icons {margin: 0; padding: 0;}
		ul#icons li {margin: 1px; position: relative; padding: 1px 0; cursor: pointer; float: left;  list-style: none;}
		ul#icons span.ui-icon {float: left; margin: 0 1px;}
	</style>
 	<!-- jQuery end -->
 
	<title>TELEIOS: Strabon Endpoint</title>
</head>
<body topmargin="0" leftmargin="0" link="#FFFFFF" vlink="#FFFFFF" alink="#FFFFFF" onload="initialize()">

<!-- include TELEIOS header and description -->
<%@ include file="teleios-header.html"%>
<!-- include TELEIOS header and description -->

<FORM enctype="UTF-8" accept-charset="UTF-8" method="post" action="Query">
<INPUT type=hidden name="view" value="HTML"/>

<table border="0" width="100%">
<tr> 
	<td width="90" valign="top" bgcolor="#dfe8f0"> 
		<table border="0" cellspacing="0" cellpadding="0" width="165">  
		<tr><td id="twidth">
		<div class="container">
		<div id="accordion">
		<%
					StrabonBeanWrapper strabonWrapper;
							ServletContext context;
							context = getServletContext();
							WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
							strabonWrapper=(StrabonBeanWrapper) applicationContext.getBean("strabonBean");
							
							Iterator <StrabonBeanWrapperConfiguration> entryListIterator = strabonWrapper.getEntries().iterator();
							boolean first = true;
							while(entryListIterator.hasNext())
							{
								StrabonBeanWrapperConfiguration entry = entryListIterator.next();
								
								if (entry.isHeader()) {
									if (!first) {
										%>
										</div></div>
										<%
									} else {
										first = false;
									}
									
									String label=entry.getLabel();
									String bean=entry.getBean();
									String style = "";
									if (bean == null) {
										bean = "#";
									} else {
										style = "class=\"navText\"";
									}
									%>
									<div><h3><a <%=style%> href="<%=bean%>"><%=label%></a></h3><div>
									<%
								} else {
									String href="\""+URLEncoder.encode(entry.getBean(),"utf-8")+"?view=HTML&handle="+entry.getHandle()+"&query="+URLEncoder.encode(entry.getStatement(),"utf-8")+"&format="+URLEncoder.encode(entry.getFormat(),"utf-8")+"\"";
									String title="\""+entry.getTitle()+"\"";
									String label=entry.getLabel();
				%>
					<b>&middot;</b>&nbsp;<a class="linkText" href=<%=href%> title=<%=title%>><%=label%></a><br/>
		<%
			
								}
							}
		%>
		</div>
		</div>
	</td>
</tr>
<!-- 
<tr><td width="90" class="style4"><a href="describe.jsp" class="navText">Describe</a></td></tr>
<tr><td width="90" class="style4"><a href="store.jsp" class="navText" title="Store triples">Store</a></td></tr>
 --> 
</table>
</td>
<td width="*" valign="top" >
<table cellspacing="5">
<%if (request.getAttribute("info") != null) { %>
	<!-- Info Message -->
  		<TR><TD colspan=2>
  		<CENTER><P><%=request.getAttribute("info") %></P></CENTER>
  		</TD></TR>
	<!-- Info Message -->
<%}%>
<tr>
<td id="output">stSPARQL Query:</td>
<%
	// get query parameter or attribute (the attribute comes from ConnectionBean)
	String query = "";
	if (request.getParameter("query") != null) {
		query = request.getParameter("query");
		
	} else if (request.getAttribute("query") != null) {
		query = (String) request.getAttribute("query");
		
	}
	
	if ("null".equals(query)) {
		query = "";
	}
	
	// get format parameter or attribute (the attribute comes from ConnectionBean)
	String selFormat = "HTML";
	if (request.getParameter("format") != null) {
		selFormat = request.getParameter("format");
		
	} else if (request.getAttribute("format") != null) {
		selFormat = (String) request.getAttribute("format");
		
	}
		
	// get handle parameter or attribute (the attribute comes from ConnectionBean)
	String handle = "";
	if (request.getParameter("handle") != null) {
		handle = request.getParameter("handle");
		
	} else if (request.getAttribute("handle") != null) {
		handle = (String) request.getAttribute("handle");
		
	}
%>
<td id="output"><textarea name="query" title="pose your query/update here" rows="15" cols="100"><%=query%></textarea></td>
</tr>
<tr>
	<td id="output"><center>Output Format:<br/>
		<select name="format" title="select one of the following output format types">
		<% 
		for (String format : Common.registeredQueryResultsFormatNames) {%>
				<OPTION value="<%=format%>"<%=format.equals(selFormat) ? "selected":""%>><%=format%></OPTION>
		<%}%>
		</select></center>
	</td>
<td colspan=2><br/><center>
<input type="submit" title="execute query" value="Query" name="submit" /><br/>
<input type="submit" title="execute update" value="Update" name="submit" style="width: 400px"/></center><br/></td>
</tr>
<tr>
	<td id="output"><center>View Result:<br/>
	<SELECT name="handle" title="select how you would like to view the result">
		<OPTION value="plain"<%= ("plain".equals(handle)) ? "selected":""%>>Plain</OPTION>
		<OPTION value="download"<%= ("download".equals(handle)) ? "selected":""%>>Download</OPTION>
		<OPTION value="map"<%= ("map".equals(handle)) ? "selected":""%>>On a map</OPTION>
		<OPTION value="map_local"<%= ("map_local".equals(handle)) ? "selected":""%>>On a map (localhost)</OPTION>
	</SELECT></center>
	</td>
	<td colspan=2>&nbsp;</td>
</tr>
<% if (request.getAttribute("error") != null) {%>
	<!-- Error Message -->
	<TR>
		<TD id="output">Result: </TD><TD id="output"><%=request.getAttribute("error") %></TD>
	</TR>
	<!-- Error Message -->	
<%}%>
</table></td></tr></table><br/><br/>
</form>
	<!-- Response -->
<% if (request.getAttribute("response") != null) {
	if (Common.getHTMLFormat().equals(request.getParameter("format"))) {%>
		<%=request.getAttribute("response")%>
	<%} else { %>
	<PRE><%=request.getAttribute("response") %></PRE>
	<%}%>
<%}%>
	<!-- Response -->
<% if (request.getAttribute("pathToKML") != null) { %>
	<div id="map_canvas"></div>
<%}%>
</body>
</html>
