<%-- 
    Document   : Menu
    Created on : Nov 29, 2021, 11:04:31 AM
    Author     : frostmaine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
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
            <h1>Banking by Matthew Yackiel</h1>
            <h1>Welcomes!</h1>
            <h2>${user.firstName} ${user.lastName}</h2><br>
            <h2>Please Make a Selection from the Following Choices.</h2><br>
        </div>
        <div class="form">
            <form method="post" action="BankingControl">
                
                New Customer <input type="radio" name="action" value="New Customer"><br>
                New Account <input type="radio" name="action" value="New Account"><br>
                Account Transaction <input type="radio" name="action" value="Account Transaction"><br>
                Logout <input type="radio" name="action" value="Logout"><br><br>
                <input type="submit" value="Submit">
                <input type="reset">
            </form>
        </div>
    </body>
</html>
