<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.flyaway.entity.*" %>
    <%@ page import="com.flyaway.dao.*" %>
    <%@ page import="com.flyaway.util.*" %>
    <%@ page import="org.hibernate.*" %>
    <%@ page import="java.util.*" %>
    <%@ page import="javax.persistence.Query" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	Double amount = Double.parseDouble(request.getParameter("amount"));
	String fid = (String)session.getAttribute("fid");
	int np = (Integer)session.getAttribute("np");
	String pname=(String)session.getAttribute("pname");
	long phoneNumber = (Long)session.getAttribute("phoneNumber");
	String emailId = (String)session.getAttribute("emailId");
	Flight f = FlightDao.getFlight(fid);
	
	if(np*f.getTicketPrice()==amount) {
		int booked = FlightDao.modifyFlight(np, f);
		if(booked==np){
		out.println("<h1>Payment done successfully! "+np+" seats booked.</h1>");
		out.println("<a href='index.html'>Click Here for Homepage</a><br>");}
		else{
			out.println("<h4>Payment Not successful!! Please check details and try again.</h4>");
			out.println("<a href='index.html'>Homepage</a><br>");
		}
	}
	else {
		out.println("<h4 color='red'>Payment Not successful!! Please check details and try again.</h4>");
		out.println("<a href='index.html'>Homepage</a><br>");
	}
	%>
	Flight No : <%=f.getFlightId()%><br />
	Flight starts at: <%=f.getSource().getCity()%><br />
	Flight Lands at: <%=f.getDestination().getCity()%> <br />
	Airline : <%=f.getAirline().getAirlineName()%><br />
	Ticket Price per seat :<%=f.getTicketPrice() %><br />
	--------------------------------------------------------<br />
	Name : <%=pname %><br />
	Phone Number: <%=phoneNumber%><br />
	Email-ID: <%=emailId%> <br />
	Number of Persons travelling/ Seats booked: <%=np%><br />
	Amount : <%=amount %>
</body>
</html>