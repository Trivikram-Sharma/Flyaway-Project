

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ page import="com.flyaway.entity.*" %>
    <%@ page import="com.flyaway.dao.*" %>
    <%@ page import="com.flyaway.util.*" %>
    <%@ page import="org.hibernate.*" %>
    <%@ page import="java.util.*" %>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Available Flights</title>
</head>
<body>
	<%
		Place source = PlaceDao.getPlace(Integer.valueOf(request.getParameter("source")));
		Place destination = PlaceDao.getPlace(Integer.valueOf(request.getParameter("destination")));
		int travelling_persons = Integer.parseInt(request.getParameter("numpersons"));
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session s = sf.openSession();
		Query q = s.createQuery("from Flight where source=:source and destination=:destination and seats>=:seats");
		q.setParameter("source",source);
		q.setParameter("destination",destination);
		q.setParameter("seats",travelling_persons);
		List<Flight> available_flights = q.getResultList();
		request.setAttribute("flights",available_flights);
		request.setAttribute("numpersons",travelling_persons);
	%>
	<table>
	<tr>
		<th>Flight No.</th>
		<th>Source</th>
		<th>Destination</th>
		<th>Ticket Price</th>
	</tr>
	<c:forEach items="${flights}" var="flight">
		<tr>
			<td>${flight.getFlightId() }</td>
			<td>${flight.getSource().getCity() }</td>
			<td>${flight.getDestination().getCity()}</td>
			<td>${flight.getTicketPrice()}</td>
			<td><a href='flight-booking.jsp?fid=${flight.getFlightId()}&npersons=${numpersons}'>Book This flight</a></td>
		</tr>
	</c:forEach>
	
	</table>
</body>
</html>