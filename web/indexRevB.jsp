<%-- 
    Document   : indexRevA.jsp
    Created on : 11-Jul-2017, 11:37:31
    Author     : james chalmers Open University F6418079
    Project    : Alba Airways application AlbaAirways-MakeBooking
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="database.SeatDB" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="styles/styles.css" type="text/css" rel="stylesheet">
        <style>
            table {
                width:70px; float:left;
            }
            th,td {
                padding: 0px;
            }
            radio {
                float: bottom;
            }
            .SeatingClassInfo {
                float:left;
                padding: 0px;
            }
        </style>
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript" >
            $(document).ready(function () {
                $.ajax({
                    type: "get",
                    url: "SeatingServlet",
                    dataType: 'json',
                    error: function () {
                        alert("Error Occurred");
                    },
                    success: function (data) {
                        var receivedData = [];
                        $.each(data.jsonArray, function (index) {
                            $.each(data.jsonArray[index], function (key, value) {
                                var point = [];
                                point.push(key);
                                point.push(value);
                                receivedData.push(point);
                                document.getElementById("output").innerHTML = point;
                                $.each(document.getElementsByClassName("btn"), function (index) {
                                    $.each(point[1], function (index, value) {
                                        if (value === true) {
                                            document.getElementsByClassName("btn")[index].style.color = "red";
                                        }
                                    });
                                }
                                );
                            });
                        });

                    }
                });
            });
        </script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="SeatingLayout"> 
            <form name="chooseSeat" action="SeatingServlet" method="POST">
                <div class="tables">
                    <table border="0" class="tbl">
                        <tbody>
                            <tr>
                                <td><input type="submit" value="seat01F" name="submit" id="1" class="btn" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat02F" name="submit" id="2" class="btn"/></td>
                            </tr>
                            <tr>
                                <th><h3></h3></th>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat03F" name="submit" id="3" class="btn"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <table border="0" class="tbl">
                        <tbody>
                            <tr>
                                <td><input type="submit" value="seat04F" name="submit" id="4" class="btn" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat05F" name="submit" id="5" class="btn"/></td>
                            </tr>
                            <tr>
                                <th><h3></h3></th>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat06F" name="submit" id="6" class="btn"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <table border="0" class="tbl">
                        <tbody>
                            <tr>
                                <td><input type="submit" value="seat07F" name="submit" id="7" class="btn" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat08F" name="submit" id="8" class="btn"/></td>
                            </tr>
                            <tr>
                                <th><h3></h3></th>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat09F" name="submit" id="9" class="btn"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <table border="0" class="tbl">
                        <tbody>
                            <tr>
                                <td><input type="submit" value="seat10F" name="submit" id="10" class="btn" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat11F" name="submit" id="11" class="btn"/></td>
                            </tr>
                            <tr>
                                <th><h3></h3></th>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat12F" name="submit" id="12" class="btn"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <table border="0" class="tbl">
                        <tbody>
                            <tr>
                                <td><input type="submit" value="seat13E" name="submit" id="13" class="btn" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat14E" name="submit" id="14" class="btn"/></td>
                            </tr>
                            <tr>
                                <th><h3></h3></th>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat15E" name="submit" id="15" class="btn"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <table border="0" class="tbl">
                        <tbody>
                            <tr>
                                <td><input type="submit" value="seat16E" name="submit" id="16" class="btn" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat17E" name="submit" id="17" class="btn"/></td>
                            </tr>
                            <tr>
                                <th><h3></h3></th>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat18E" name="submit" id="18" class="btn"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <table border="0" class="tbl">
                        <tbody>
                            <tr>
                                <td><input type="submit" value="seat19E" name="submit" id="19" class="btn" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat20E" name="submit" id="20" class="btn"/></td>
                            </tr>
                            <tr>
                                <th><h3></h3></th>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat21E" name="submit" id="21" class="btn"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <table border="0" class="tbl">
                        <tbody>
                            <tr>
                                <td><input type="submit" value="seat22E" name="submit" id="22" class="btn" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat23E" name="submit" id="23" class="btn"/></td>
                            </tr>
                            <tr>
                                <th><h3></h3></th>
                            </tr>
                            <tr>
                                <td><input type="submit" value="seat24E" name="submit" id="24" class="btn"/></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </form>
            <br /><br /><br />
            <div class="SeatingClassInfo"><p>1st Class Seats numbered from "seat01F" to "seat12F"</p></div><br /><br /><br />
            <div class="SeatingClassInfo"><p>Economy Class Seats numbered from "seat13E" to "seat24E"</p></div>
            <form name="Seats" action="SeatingServlet" method="POST"> <p>Seating Layout</p>
                <input type="submit" value="seats" name="submit" style="width:75px" />
            </form>
            <div>
                <p id="output"></p>
            </div>
        </div>
        
        <form name="chooseEconomy" action="SeatingServlet" method="POST">
            <p><input type="submit" value="Economy" name="submit" /> Select if you want an Economy Seat</p>
        </form><br><br>
        <form name="Cart" action="BookingCartServlet" method="POST">
            <input type="hidden" value="${seatNumber}" name="seatNumber" />
            <input type="submit" value="back to cart" name="submit" style="width:75px" />
        </form>
    </body>
</html>
