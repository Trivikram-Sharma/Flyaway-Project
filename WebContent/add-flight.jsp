
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
<title>Add Flight</title>
<style>
	body{
		text-align: center;
	}
</style>
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
		<legend align="center">ADD FLIGHT</legend>
		<form action="AddFlight" method="post">
			Flight Id:
			<input type="text" name="flightId" id="flightId"><br />
			Seats: <input type="number" name="seats" id="seats" max="120"><br />
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
		Airline: 
		<select name="airlineName">
			<%
				for(Airline a:airlines){
					out.println("<option value='"+a.getAirlineId()+"'>"+a.getAirlineName()+"</option>");
					}
			%>
		</select><br />
		Ticket Price:
		<input type="number" name="ticketPrice" id="ticketPrice" min="0" max="3500"><br />
		<input type="submit" name="submit" value="Add Flight">
		</form>
	</fieldset>
	
</body>
</html>