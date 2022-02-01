<%-- 
    Document   : ListAllAccounts.jsp
    Created on : Dec 2, 2021, 2:18:18 PM
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
            <h1>Overview of all Accounts</h1>
            <table>
                ${message.message}
            </table>
            <div class="form">
                <form method="post" action="BankingControl">
                    <input type="hidden" value="menu" name="action">
                    <input type="submit" name="action" value="Return to Main Menu">
                    <br><br>
                </form>
            </div>
        </div>
</html>

