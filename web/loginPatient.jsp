

<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<style>

    @import url(http://fonts.googleapis.com/css?family=Roboto:400);
    body {
        background-color:#fff;
        -webkit-font-smoothing: antialiased;
        font: normal 14px Roboto,arial,sans-serif;
    }

    .container {
        padding: 25px;
        position: fixed;
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



</style>



</head>
<body bgcolor="aqua">
<!--Pulling Awesome Font -->
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<div class="container">
    <div class="row">
        <div class="col-md-offset-5 col-md-4">
            <div class="form-login">

                <img src="https://www.gov.sg/sgdi/~/media/sgdi/ntfgh.png" alt="logo_ntfgh" width="291">
                <h4>NTFGH Pharmacy Application</h4>
                <input type="text" id="userName" class="form-control input-sm chat-input" placeholder="Username" />
                </br>
                <input type="text" id="userPassword" class="form-control input-sm chat-input" placeholder="Password" />
                </br>
                <span class="label label-success">&nbsp; My Role: &nbsp; &nbsp;</span>
                &nbsp;&nbsp;<label class="radio-inline"><input type="radio" id="role" name="optradio">Patient</label>
                <label class="radio-inline"><input type="radio" name="optradio">Caregiver</label>
                <div class="wrapper">
                    <br><br>
            <span class="group-btn">
                <a href="#" class="btn btn-primary btn-md">Login <i class="fa fa-sign-in"></i></a>
                <a href="#" class="btn btn-primary btn-md">Register <i class="fa fa-user-plus"></i></a>
            </span>
                </div>
            </div>

        </div>
    </div>
</div>
</body>