

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
            <div class="form-login">

                <img src="https://www.gov.sg/sgdi/~/media/sgdi/ntfgh.png" alt="logo_ntfgh" width="291">
                <h4>NTFGH Pharmacy Application</h4>
                <table>

                    <tr>
                        <td>
                    <label for="userName">&nbsp;NRIC: </label>
                        </td>
                        <td>
                    <input type="text" id="userName" class="form-control input-sm chat-input" placeholder="NRIC" /> <br>
                        </td>



                    </tr>

                <br>

                    <tr>
                        <td>
                    <label for="userPassword">&nbsp;Password: </label>
                        </td>
                        <td>
                    <input type="password" id="userPassword" class="form-control input-sm chat-input" placeholder="Password" /> <br>
                        </td>
                    </tr>

                <br>
                <div class="row">
                    <tr>
                        <td>
                    <label for="userEmail">&nbsp;Email: </label>
                        </td>
                        <td>
                    <input type="email" id="userEmail" class="form-control input-sm chat-input" placeholder="Email" /> <br>
                        </td>
                    </tr>
                </div>
                <br>
                <div class="row">
                    <tr>
                        <td>
                    <label for="userContact">&nbsp;Contact: </label>
                        </td>
                        <td>
                    <input type="number" id="userContact" class="form-control input-sm chat-input" placeholder="Contact Number" /> <br>
                        </td>
                    </tr>
                </div>
                <br>
                <div class="row">
                    <tr>
                        <td>
                    <label for="userAddress">&nbsp;Address: </label>
                        </td>
                        <td>
                    <input type="text" id="userAddress" class="form-control input-sm chat-input" placeholder="Address" /> <br>
                        </td>
                    </tr>
                </div>

                <div class="row">
                    <tr>
                        <td >
                    <label for="userCondition">&nbsp;My Conditions: &nbsp </label>
                        </td>
                        <td>
                    <textarea class="form-control" name="userCondition" id="userCondition" rows="4"></textarea> <br>
                        </td>
                    </tr>
                </div>
                </table>

                <br>
                <span class="label label-success">&nbsp; My Role: &nbsp; &nbsp;</span>
                &nbsp;&nbsp;<label class="radio-inline"><input type="radio" id="role" name="optradio">Patient</label>
                <label class="radio-inline"><input type="radio" name="optradio">Caregiver</label>
                <div class="wrapper">
                    <br><br>
            <span class="group-btn">
                <a href="registration.jsp" class="btn btn-primary btn-md">Register <i class="fa fa-user-plus"></i></a>
            </span>
                </div>
            </div>

        </div>
    </div>
</div>
</body>