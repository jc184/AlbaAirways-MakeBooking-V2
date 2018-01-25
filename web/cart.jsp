<%-- 
    Document   : cart
    Created on : 29-Nov-2017, 21:08:37
    Author     : james
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="enums.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AlbaAirways-MakeBooking</title>
    </head>
    <body>
        <h1>Alba Airways</h1>
        <h2>Booking Cart - Outbound</h2>
        <p>${outboundMessage}</p>
        <table border="1">
            <tr>
                <th>Passenger No</th>
                <th>Passenger Type</th>
                <th>Seat Type</th>
                <th>Seat Number</th>
                <th>Flight</th>
                <th>Amount</th>
                <th>Select</th>
            </tr>
            <c:forEach var="item" items="${cart.persons}" varStatus="loop">
                <tr>
                    <td><c:out value='${loop.index}'/></td>
                    <td><c:out value='${item.seatOwner}'/></td>
                    <td><select name="seatType" id="seatType" value="">
                            <option selected><c:out value='${item.seatType}'/></option>
                            <option><c:out value='ECONOMY'/></option>
                            <option><c:out value='FIRSTCLASS'/></option>
                        </select></td>
                    <td><c:out value='${item.seat.seatPK.seatNo}'/></td>
                    <td><c:out value='${sessionScope.outboundFlightId}'/></td>
                    <td><c:out value='${item.totalCurrencyFormat}'/></td>
                    <td><form action="BookingCartServlet" method="post">
                            <input type="hidden" name="flightId" value="${sessionScope.outboundFlightId}">
                            <input type="hidden" name="passengerNo" value="${loop.index}">
                            <input type="submit" value="Choose Seat" name="submit">
                        </form></td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="submit" value="Continue" name="submit"></td>
            </tr>
        </table>
        <br>
        <br>
        <h2>Booking Cart - Return</h2>
        <p>${returnMessage}</p>
        <table border="1">
            <tr>
                <th>Passenger No</th>
                <th>Passenger Type</th>
                <th>Seat Type</th>
                <th>Seat Number</th>
                <th>Flight</th>
                <th>Amount</th>
                <th>Select</th>
            </tr>
            <c:forEach var="item" items="${cart.returnPersons}" varStatus="loop">
                <tr>
                    <td><c:out value='${loop.index}'/></td>
                    <td><c:out value='${item.seatOwner}'/></td>
                    <td><select name="seatType" id="seatType" value="">
                            <option selected><c:out value='${item.seatType}'/></option>
                            <option><c:out value='ECONOMY'/></option>
                            <option><c:out value='FIRSTCLASS'/></option>
                        </select></td>
                    <td><c:out value='${item.seat.seatPK.seatNo}'/></td>
                    <td><c:out value='${sessionScope.returnFlightId}'/></td>
                    <td><c:out value='${item.totalCurrencyFormat}'/></td>
                    <td><form action="BookingCartServlet" method="post">
                            <input type="hidden" name="flightId" value="${sessionScope.returnFlightId}">
                            <input type="hidden" name="returnPassengerNo" value="${loop.index}">
                            <input type="submit" value="Choose Return Seat" name="submit">
                        </form></td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="submit" value="Continue" name="submit"></td>
            </tr>
        </table>
        <p><b>Overall cost:</b>${sessionScope.totalCost}</p>

            <form action="CustomerServlet" method="post">
                
                <input type="submit" value="Confirm Booking" name="submit">
            </form>
        

    </body>
</html>
