<%-- 
    Document   : GetBalance.jsp
    Created on : Dec 2, 2021, 2:18:07 PM
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
            th, td {
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <div class="content">
            <h1>The Balance of:</h1>
            <div class="form">
                ${message.message}
                <form method="post" action="BankingControl">
                    <input type="hidden" value="menu" name="action">
                    <input type="submit" name="action" value="Return to Main Menu">
                    <br><br>
                </form>
            </div>
        </div>
</html>

