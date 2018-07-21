

<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

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
                <h4>NTFGH Pharmacy Application</h4>
                <table>

                    <tr>
                        <td>
                            <label for="userName">&nbsp;Name: </label>
                        </td>
                        <td>
                            <input type="text" id="userName" name="user_name" class="form-control input-sm chat-input" placeholder="Name" /> <br>
                        </td>
                    </tr>

                    <br>


                    <tr>
                        <td>
                            <input type="hidden" id="route" name="route" class="form-control input-sm chat-input" value="userAdd" />
                    <label for="userName">&nbsp;NRIC: </label>
                        </td>
                        <td>
                    <input type="text" id="userNRIC" name="user_NRIC" class="form-control input-sm chat-input" placeholder="NRIC" /> <br>
                        </td>



                    </tr>

                <br>

                    <tr>
                        <td>
                    <label for="userPassword">&nbsp;Password: </label>
                        </td>
                        <td>
                    <input type="password" id="userPassword" name="user_password" class="form-control input-sm chat-input" placeholder="Password" /> <br>
                        </td>
                    </tr>

                <br>
                <div class="row">
                    <tr>
                        <td>
                    <label for="userEmail">&nbsp;Email: </label>
                        </td>
                        <td>
                    <input type="email" id="userEmail" name="user_email" class="form-control input-sm chat-input" placeholder="Email" /> <br>
                        </td>
                    </tr>
                </div>
                <br>
                    <tr>
                        <td>
                            <label for="userDOB">&nbsp;Date Of Birth: </label>
                        </td>
                        <td>
                            <input type="text" id="userDOB" name="user_dob" class="form-control input-sm chat-input" placeholder="Date of Birth" /> <br>
                        </td>
                    </tr>

                <br>


                <div class="row">
                    <tr>
                        <td>
                    <label for="userContact">&nbsp;Contact: </label>
                        </td>
                        <td>
                    <input type="number" id="userContact" name="user_contact" class="form-control input-sm chat-input" placeholder="Contact Number" /> <br>
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
                    <input type="text" id="userAddress" name= "user_address" class="form-control input-sm chat-input" placeholder="Address" /> <br>
                        </td>
                    </tr>
                </div>

                <div class="row">
                    <tr>
                        <td >
                    <label for="userCondition">&nbsp;My Conditions: &nbsp </label>
                        </td>
                        <td>
                    <textarea class="form-control" name="user_condition" id="userCondition" rows="4"></textarea> <br>
                        </td>
                    </tr>
                </div>
                </table>

                <br>
                <span class="label label-success">&nbsp; My Role: &nbsp; &nbsp;</span>
                &nbsp;&nbsp;
                <label class="radio-inline"><input type="radio" name="roleA" value="Patient" checked="checked">Patient</label>
                <label class="radio-inline"><input type="radio" name="roleA" value="Caregiver">Caregiver</label>

                <div class="wrapper">
                    <br><br>
            <span class="group-btn">
                 <a href="index.jsp" style="background-color: black" class="btn btn-primary btn-md"><b>Back</b> <i class="fa fa-close"></i></a>
                 <%--<button type="submit" style="background-color: black" class="btn btn-primary"><b>Register</b> <i class="fa fa-group"></i></button>--%>
                <button type="button" onclick="openPDPAModal()" style="background-color: black" class="btn btn-primary"><b>Register</b> <i class="fa fa-group"></i></button>

            </span>
                </div>
            </div>

                <div id="PDPAModal" class="modal">
                    <div class="modal-content">
                        <h2>PDPA Agreement</h2><br>
                        <p>
                            Note that by using this website you are agreeing to allow NTFGH employees to view your data as in order to help you verify
                            your medications are taken correctly. All neccessary steps under PDPA have been taken to ensure that your data is protected.
                        </p>
                        <div class="row">
                            <div class="col-sm-6">
                                <button type="submit" class="btn btn-success btn-block btn-lg">Confirm</button>
                            </div>
                            <div class="col-sm-6">
                                <button type="button" class="btn btn-default btn-block btn-lg"  onclick="closePDPAModal()">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<script>
    var PDPAModal = document.getElementById('PDPAModal');
    function openPDPAModal() {
        PDPAModal.style.display = "block";
    }
    function closePDPAModal() {
        PDPAModal.style.display = "none";
    }
</script>
</body>