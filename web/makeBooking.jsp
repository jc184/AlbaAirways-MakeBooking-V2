<%-- 
    Document   : makeBooking
    Created on : 09-Aug-2017, 16:02:53
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = LoginPage.jsp");
%>
<html>
    <head>
        <title>AlbaAirways</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <!-- <script type="text/javascript" src="scripts/bookingvalidation.js"></script>UNCOMMENT HTML WITH THIS -->
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script type="text/javascript">
            $(function () {
                $('.dateTxt').datepicker({dateFormat: "yy-mm-dd"}).datepicker("setDate", "0");
            });
        </script>
    </head>
    <body>

        <!-- UNCOMMENT HTML WITH THIS -->
        <div class="outer">
            <div class="header"><h1>AlbaAirways</h1></div>
            <p style="color: red">${msg}</p>
            <div class="box"><h2>New Booking</h2>
                <p>Enter your details in the form below and click 'add' to make a booking</p>
                <form name="form" action="BookingCartServlet" method="POST">
                    <table>
                        <!-- UNCOMMENT TO USE HTML BELOW -->
                        <tr>
                            <td align="left"><p><label for="noOfAdults">Number of Adults:</label></p></td>
                            <td><select name="noOfAdults" id="noOfAdults" value="">
                                    <option selected>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="noOfChildren">Number of Children:</label></p></td>
                            <td><select name="noOfChildren" id="noOfChildren" value="">
                                    <option selected>0</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="noOfInfants">Number of Infants:</label></p></td>
                            <td><select name="noOfInfants" id="noOfInfants" value="">
                                    <option selected>0</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="outboundFlightDate">Date of Travel (yyyy-mm-dd):</label></p></td>
                            <td><input type="text" name="outboundFlightDate" class="dateTxt" id="one" readonly="true"/></td>
                        </tr>
                        <tr>
                            <td align="left"><p>Show daily outbound flights:</p></td>
                            <td><input type="submit" value="outbound flights" name="submit" style="width:115px" onclick="validate(this.form);return false;"/></td>
                            <!--    -->
                        </tr>
                        <tr>
                            <td align="left"><p><label for="outboundFlightId">Outbound Flight Id: </label></p></td>
                            <td><input type="text" name="outboundFlightId" id="outboundFlightId" value='${sessionScope.outboundFlightId}' /></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="returnFlightDate">Date of Travel (yyyy-mm-dd):</label></p></td>
                            <td><input type="text" name="returnFlightDate" class="dateTxt" id="two" readonly="true"/></td>
                        </tr>
                        <tr>
                            <td align="left"><p>Show daily return flights:</p></td>
                            <td><input type="submit" value="return flights" name="submit" style="width:115px" onclick="validate(this.form);return false;"/></td>
                            <!--    -->
                        </tr>
                        <tr>
                            <td align="left"><p><label for="returnFlightId">Return Flight Id: </label></p></td>
                            <td><input type="text" name="returnFlightId" id="returnFlightId" value='${sessionScope.returnFlightId}' /></td>
                        </tr>
                        <tr>
                            <td><p>Your Cart</p></td>
                            <td><input type="submit" value="show cart" name="submit" style="width:75px" onclick="validate(this.form);return false;" /><br /></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
