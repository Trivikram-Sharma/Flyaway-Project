<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="com.flyaway.entity.*"%>
<%@ page import="com.flyaway.dao.*"%>
<%@ page import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Places</title>
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
	<a href="add-place.html">Add Place</a>
	<a href="LogoutServlet">Logout</a>
	<a href="admin-homepage.jsp">Admin Home Page</a>
	<br />
	<%
		List<Place> places = PlaceDao.listPlaces();
	request.setAttribute("places",places);
	%>
	<table align="center" style="border : 4px solid green">
		<thead>
			<tr>
				<th>PlaceID</th>
				<th>City</th>
				<th>State</th>
				<th>Departing Flights</th>
				<th>Arriving flights</th>
				<!--  <th>Modify/Delete</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${places}" var="place">
				<tr>
					<td>${place.getPlaceId()}</td>
					<td>${place.getCity()}</td>
					<td>${place.getState()}</td>
					<td>${place.getDeparting(place.getDepartingFlights())}</td>
					<td>${place.getArriving(place.getArrivingFlights()) }</td>		
					<!--  <td colspan="2"><a href="changeplace.jsp?pid=${place.getPlaceId() }">Modify</a>/<a href="DeletePlace?pid=${place.getPlaceId() }">Delete</a></td>
					-->
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>