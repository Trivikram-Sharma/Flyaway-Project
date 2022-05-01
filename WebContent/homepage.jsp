<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.flyaway.util.HibernateUtil" %>
    <%@ page import="org.hibernate.*" %>
    <%@ page import="java.util.List" %>
    <%@ page import = "com.flyaway.entity.Place" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HomePage</title>
<style> 
h3{background-color : green;color:white}
header,nav{background-color: green; color:white}
a{color:blue; background-color:silver}
body{background-color : royal-blue;text-align: center}
</style>
</head>
<body>
<%
	SessionFactory sf = HibernateUtil.buildSessionFactory();
	Session s = sf.openSession();
	List<Place> places = s.createQuery("from Place").list();
%>
	<fieldset>
	<legend align="center">Flight Search</legend>
	<form action="availableflights.jsp" method="post">
		Date of travel:
		<input type="date" name="travel-date" id="travel-date" /><br />
		Source(Please enter the place of start of the travel):
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
		Number of persons(Please enter the number of persons travelling(No of seats needed)):
		<input type="number" name="numpersons" id="numpersons" min="1" max="10"/> <br />
		<input type="submit" name="Submit" value="Search Flights"> 
	</form>
	</fieldset>
</body>
</html>