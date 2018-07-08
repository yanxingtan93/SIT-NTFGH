

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

        <img src="https://www.gov.sg/sgdi/~/media/sgdi/ntfgh.png" alt="logo_ntfgh" width="291">
        <h4><b>NTFGH Pharmacy Application</b></h4>

        <input type="hidden" id="route" name="route" class="form-control input-sm chat-input" value="login" />
        <input type="text" name="userName" id="userName"  class="form-control input-sm chat-input" placeholder="NRIC" value="S1234567B"/>
        </br>
        <input type="password" name="userPassword" id="userPassword" class="form-control input-sm chat-input" placeholder="Password" />
        </br>
        <span class="label label-success">&nbsp; My Role: &nbsp; &nbsp;</span>
        &nbsp;&nbsp;<label class="radio-inline"><input type="radio" id="role" name="optradio" value="Patient" checked="checked">Patient</label>
        <label class="radio-inline"><input type="radio" name="optradio" value="Caregiver">Caregiver</label>
        <div class="wrapper">
          <br><br>
          <span class="group-btn">

              <button type="submit" class="btn btn-primary"><b>Login</b> <i class="fa fa-sign-in"></i></button>
                <a href="registration.jsp" class="btn btn-primary btn-md"><b>Register</b> <i class="fa fa-user-plus"></i></a>
            </span>
        </div>

      </div>
      </form>

    </div>
  </div>
</div>
</body>