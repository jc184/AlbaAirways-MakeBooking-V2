<%-- 
    Document   : flightpage
    Created on : 29-Jun-2017, 11:35:51
    Alba Airways application AlbaAirways-MakeBooking
    @author james chalmers F6418079
    @version 1.0
--%>

<%@page import="java.util.List"%>
<%@page import="entities.Flight"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flight Info Page</title>
    </head>
    <body>
        <div class="outer">
            <div class="header"><h1>Alba Airways</h1></div>
            <div class="box"><h2>View all flights</h2>
                <% List<Flight> flightStore = (List<Flight>) request.getAttribute("FlightStore");%>
                <table border="1" id="table" class="sortable">
                    <!-- UNCOMMENT HTML WITH THIS -->      
                    <thead>
                        <tr>
                            <th><h3>Flight Id</h3></th>
                            <th><h3>Leave Datetime</h3></th>
                            <th><h3>Arrival DateTime</h3></th>
                            <th><h3>Route Id</h3></th>
                            <th><h3>Select</h3></th>
                            <th><h3>Select</h3></th>
                        </tr>
                    </thead>
                    <tbody>

                        <% for (Flight flight : flightStore) {%>
                        <tr>
                            <td><%= flight.getId()%></td>
                            <td><%= flight.getDepartureDateTime()%></td>
                            <td><%= flight.getArrivalDateTime()%></td>
                            <td><%= flight.getRouteId()%></td>
                            <td><form name="selectOutboundFlight" action="BookingCartServlet">
                                    <input type="hidden" value="<%= flight.getId()%>" name="outboundFlightId" />
                                    <input type="submit" value="select outbound" name="submit" /></form></td>
                            <td><form name="selectReturnFlight" action="BookingCartServlet">
                                    <input type="hidden" value="<%= flight.getId()%>" name="returnFlightId" />
                                    <input type="submit" value="select return" name="submit" /></form></td>
                        </tr>
                        <%}%>
                        <c:forEach var="flight" items="${flightStore}">

                        </c:forEach>
                    </tbody>

            </div>
        </div>
    </body>
</html>
