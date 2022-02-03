<!DOCTYPE html>
<%--LoginChoice.jsp--%>

<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/generalStyles.css" type="text/css">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <noscript>
            <meta http-equiv="refresh" content="0; URL=html/javascriptDisabled.html">
        </noscript>
    </head>
    <body>
        <header class="title_bar_container"> 
            <div id="HeaderText">Sample Web Application Login Page</div>
        </header>
        <img id="backPhoto" src="images/BackgroundPhoto.jpg">
        
        <%--Container for the login menu--%>
        <section class = "content_container" id = "small_container">

            <header class = "content_title_bar" id="login_header"> 
                <div class = "title" >
                    Login
                </div> 
            </header>

            <form id="login_form" action="Options" method = "POST">              
                <div >
                    <img id="huskyIcon" src="images/husky.png">
                </div>
                <div id = "login_text_container">

                    <input type ="submit" name = "options" value="Display Error Logs" class="btn"/>
                    <input type ="submit" name = "options" value="Display Properties" class="btn"/>
                </form>
                <br>
                <br>

                <br>
                <br>
            </form>
                    
        </section>      
    </body>
</html>
