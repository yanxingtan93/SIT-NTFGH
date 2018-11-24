

<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
    <style>

        @import url(http://fonts.googleapis.com/css?family=Roboto:400);
        body {
            background-color:#54C5C3;
            -webkit-font-smoothing: antialiased;
            font: normal 14px Roboto,arial,sans-serif;
        }

        .container {

            padding: 10px;
            padding-top: 120px;
            padding-right: 80px;
            position: center;
            align-self: center;
        }

        .form-login {
            background-color: #EDEDED;
            padding-top: 10px;
            padding-bottom: 20px;
            padding-left: 20px;
            padding-right: 20px;
            border-radius: 15px;
            border-color:#d2d2d2;
            border-width: 5px;
            box-shadow:0 1px 0 #cfcfcf;
        }

        h4 {
            border:0 solid #fff;
            border-bottom-width:1px;
            padding-bottom:10px;
            text-align: center;
        }

        .form-control {
            border-radius: 10px;
        }

        .wrapper {
            text-align: center;
        }


        body {background-color: #54C5C3;}
    </style>



</head>
<body bgcolor="aqua">
<!--Pulling Awesome Font -->
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<div class="container">
    <div class="row">
        <div class="col-md-offset-5 col-md-4">
            <form method="post" action="/UserServlet">
            <div class="form-login">
                <div class="pull-right">  <a href="index.jsp" style="background-color: black" class="btn btn-primary btn-sm"><i class="fa fa-arrows-h"></i><b>&nbspPatient</b> </a></div>
                <img src="https://www.gov.sg/sgdi/~/media/sgdi/ntfgh.png" alt="logo_ntfgh" width="291">
                <h4><b>NTFGH Pharmacy Application</b></h4>

                    <input type="hidden" id="route" name="route" class="form-control input-sm chat-input" value="login" />
                <input type="text" style="font-weight: bold" id="userName" name="userName" class="form-control input-sm chat-input" placeholder="NRIC" />
                </br>
                <input type="password" style="font-weight: bold" id="userPassword" name="userPassword" class="form-control input-sm chat-input" placeholder="Password" />
                </br>
                <span class="label label-success">&nbsp; My Role: &nbsp; &nbsp;</span>
                &nbsp;&nbsp;<label class="radio-inline" style="font-weight: bolder"><input type="radio" id="role" name="optradio" value="Pharmacist" checked="checked">Pharmacist</label>
                <label class="radio-inline" style="font-weight: bolder"><input type="radio" name="optradio" value="Admin">Administrator</label>
                <div class="wrapper">
                    <br><br>
                    <p>${requestScope["message"]}</p>
            <span class="group-btn">

                 <button type="submit" style="background-color: black" class="btn btn-primary"><b>Login</b> <i class="fa fa-sign-in"></i></button>
            </span>
                </div>

            </div>
            </form>
        </div>
    </div>
</div>
</body>