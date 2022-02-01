<%-- 
    Document   : NewCustomer
    Created on : Nov 29, 2021, 11:04:53 AM
    Author     : frostmaine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Customer</title>
        <style>
            .content {
                max-width: 500px;
                margin: auto;
                text-align: center;
            }
            .form {
                max-width: 500px;
                margin: auto;
            }
        </style>
    </head>
    <body>
        <div class="content">
            <h1>Add a New Customer</h1>
            <div class="form">
                <form method="post" action="BankingControl">
                    <input type="hidden" value="menu" name="action">
                    <input type="submit" name="action" value="Return to Main Menu">
                    <br><br>
                </form>
                <form method="post" action="BankingControl">
                    <input type="hidden" value="add customer" name="action">
                    First name: <input type="text" id="first_name" name="first_name" required>
                    <br>
                    Last name: <input type="text" id="last_name" name="last_name" required>
                    <br>
                    <br>
                    Street: <input type="text" id="street_addr" name="street_addr" required>
                    City: <input type="text" id="city_addr" name="city_addr" size="8" required><br>
                    State: <input type="text" id="state_addr" name="state_addr" size="2" required>
                    Zip: <input type="text" id="zip_addr" name="zip_addr" size="5" required>
                    <br><br>
                    Birthday: <input type="date" id="birthday" name="birthday" value="1997-03-31" required>
                    <br><br>
                    <input type="submit" name="action" value="Add New Customer">
                    <input type="reset" value="clear">
                </form>
            </div>
        </div>
    </body>
</html>
