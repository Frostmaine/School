<%-- 
    Document   : NewAccount
    Created on : Nov 29, 2021, 11:05:08 AM
    Author     : frostmaine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Account</title>
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
            <h1>Add a New Account</h1>
            <div class="form">
                <form method="post" action="BankingControl">
                    <input type="hidden" value="menu" name="action">
                    <input type="submit" name="action" value="Return to Main Menu">
                    <br><br>
                </form>
                <form method="post" action="BankingControl">
                    <input type="hidden" value="add account" name="action">
                    Customer #: <input type="text" id="customer_num" name="customer_num" size="2" required> <br>
                    Account Balance: $<input type="text" id="account_balance" size="4" name="account_balance" required> <br>
                    <input type="submit" name="action" value="Add New Account">
                    <input type="reset" value="clear">
                </form>
            </div>
        </div>
    </body>
</html>
