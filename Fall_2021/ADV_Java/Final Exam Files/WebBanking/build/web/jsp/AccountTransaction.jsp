<%-- 
    Document   : Transaction
    Created on : Nov 29, 2021, 11:05:19 AM
    Author     : frostmaine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction</title><style>
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
            <h1>Enter your Transaction</h1>
            <div class="form">
                <form method="post" action="BankingControl">
                    <input type="hidden" value="menu" name="action">
                    <input type="submit" name="action" value="Return to Main Menu">
                    <br><br>
                </form>
                <form method="post" action="BankingControl">
                    Account #: <input type="text" id="account_num" name="account_num"> <br>
                    Account Balance: $<input type="text" id="account_balance" name="account_balance"><br>
                    Debit: <input type="radio" name="action" value="debit">
                    Credit: <input type="radio" name="action" value="credit"><br>
                    Get Balance: <input type="radio" name="action" value="Get Balance"><br>
                    List All Accounts: <input type="radio" name="action" value="List All Accounts"><br>
                    <input type="submit" value="Submit">
                    <input type="reset" value="Clear">
                </form>
            </div>
        </div>
</html>
