<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
     <%@ page import = "com.flyaway.entity.*" %>
    <%@ page import="com.flyaway.servlet.*" %>
    <%@ page import="java.util.*" %>
    <%@ page import="com.flyaway.dao.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flights</title>
<style>
table,th,td {
	border: 2px solid green;
	padding: 2px;
	text-align: center;
	cell-spacing: 2 px;
}

td {
	border: 2 px;
}

body {
	background-color: royal-blue;
	text-align: center
}
</style>
</head>
<body>
	<a href="add-flight.jsp">Add Flight</a>
	<a href="LogoutServlet">Logout</a>
	<a href="admin-homepage.jsp">Admin Home Page</a>
	<br />
	<%
		List<Flight> flights = FlightDao.listFlights();
	request.setAttribute("flights",flights);
	StringBuilder sb = new StringBuilder();
	%>
	<table align="center" style="border : 4px solid green">
		<thead>
			<tr>
				<th>FlightID</th>
				<th>Airline Name</th>
				<th>Seats</th>
				<th>Source</th>
				<th>Destination</th>
				<th>Ticket Price</th>
				<th>Modify/Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${flights}" var="flight">
				<tr>
					<td>${flight.getFlightId()}</td>
					<td>${flight.getAirline().getAirlineName()}</td>
					<td>${flight.getSeats() }</td>
					<td>${flight.getSource().getCity()}</td>
					<td>${flight.getDestination().getCity()}</td>
					<td>Rs.${flight.getTicketPrice()}/-</td>
					<td colspan="2"><a href="changeflight.jsp?fid=${flight.getFlightId() }">Modify</a>/<a href="DeleteFlight?fid=${flight.getFlightId() }">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>