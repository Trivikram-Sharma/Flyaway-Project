<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.flyaway.util.HibernateUtil" %>
    <%@ page import="org.hibernate.*" %>
    <%@ page import="java.util.List" %>
    <%@ page import = "com.flyaway.entity.*" %>
    <%@ page import="com.flyaway.dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	SessionFactory sf = HibernateUtil.buildSessionFactory();
	Session s = sf.openSession();
	List<Place> places = PlaceDao.listPlaces();
	List<Airline> airlines = AirlineDao.listAirlines();
	request.setAttribute("session",request.getSession(false));
	request.setAttribute("userName",request.getSession(false).getAttribute("userName"));
	request.setAttribute("password",request.getSession(false).getAttribute("password"));
%>
	<fieldset>
		<legend>Change Flight Details</legend>
		<form action="ChangeFlight" method="post">
			Flight ID: <input type="text" name="flightId" id="flightId" readonly value="<%=request.getParameter("fid") %>"><br />
			Seats: <input type="number" name="seats" id="seats" max="120"><br />
			Airline Name: 
			<select name="airlineName">
				<%
				for(Airline a:airlines){
					out.println("<option value='"+a.getAirlineId()+"'>"+a.getAirlineName()+"</option>");
					}
			%>
			</select><br />
			Source:
			<select name="source">
			<%
				for(Place p:places){
					out.println("<option value='"+p.getPlaceId()+"'>"+p.getCity()+"</option>");
				}
			%>
		</select> <br />
		Destination:
		<select name="destination">
			<%
				for(Place p:places){
					out.println("<option value='"+p.getPlaceId()+"'>"+p.getCity()+"</option>");
				}
			%>
		</select> <br />
		Ticket Price:
		<input type="number" name="ticketPrice" id="ticketPrice" min="0" max="3500"><br />
		<input type="submit" name="submit" value="Modify Flight">
		</form>
	</fieldset>
</body>
</html>