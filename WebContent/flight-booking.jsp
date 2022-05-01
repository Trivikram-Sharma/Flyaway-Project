<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Booking</title>
</head>
<body>
<%String np = request.getParameter("npersons");
	String fid = request.getParameter("fid");
	session.setAttribute("np",np);
	session.setAttribute("fid",fid);%>
<form action="payment.jsp" method="post">
	Name: <input type="text" placeholder="Please Enter your Name" name="pname" id="pname"><br />
	Mobile Num: <input type="number" name="phoneNumber" ><br />
	Email : <input type="email" name="emailId" id="emailId"><br />
	No.of persons booked: <%=np %><br />
	<input type="submit" name="Submit" value="Proceed To Payment">
	</form>
</body>
</html>