<%-- 
    Document   : paypalJS
    Created on : 18-Dec-2017, 19:08:28
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>paypalJS Page</title>
    <script src="https://www.paypalobjects.com/api/checkout.js"></script>
</head>

<body>
    <div id="paypal-button-container"></div>
    <%
        double param = (Double) session.getAttribute("total");
    %>
    
    <h1>Alba Airways</h1>
    <h2>Booking Confirmation</h2>
    <p>${message}</p>
    <p>${confirmationNo}</p>
    <script>
        var overallTotal = '<%=param%>';


        paypal.Button.render({

            env: 'sandbox', // sandbox | production

            // PayPal Client IDs - replace with your own
            // Create a PayPal app: https://developer.paypal.com/developer/applications/create
            client: {
                sandbox: 'AdGnAifGJ_HJZ_5EXe9J2Fd1KeBMZu-7eF0kCmv3w800uQn7YNYuAMuL9phRIMVI_ye7CAnVCU4lU66v',
                production: '<insert production client id>'
            },

            // Show the buyer a 'Pay Now' button in the checkout flow
            commit: true,

            // payment() is called when the button is clicked
            payment: function (data, actions) {

                // Make a call to the REST api to create the payment
                return actions.payment.create({
                    payment: {
                        transactions: [
                            {
                                amount: {total: overallTotal, currency: 'GBP'}
                            }
                        ]
                    }
                });
            },

            // onAuthorize() is called when the buyer approves the payment
            onAuthorize: function (data, actions) {
                // Make a call to the REST api to execute the payment
                return actions.payment.execute().then(function () {
                    window.alert('Payment Complete!');
                    //callAjax();
                });
            }

        }, '#paypal-button-container');

        //function callAjax(url, callback) {
        //var authorized = true;
        //var xmlhttp;
        //// compatible with IE7+, Firefox, Chrome, Opera, Safari
        //xmlhttp = new XMLHttpRequest();
        //xmlhttp.onreadystatechange = function () {
        //if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
        //callback(xmlhttp.responseText);
        //}
        //};
        //xmlhttp.open("POST", CustomerServlet, true);
        //xmlhttp.send(authorized);
        //}
    </script>
</body>
