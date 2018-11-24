<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<t:adminPage>
    <h1>Account Creation</h1>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>

    <form action="/UserServlet?route=adminAdd" method="post">
    <br>
    <div class="container">
        <div class="row">

            <div class="col-xs-12 col-sm-12 col-md-11 col-lg-11 toppad" >


                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">User Details</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">

                            <div class="col-md-3 col-lg-3 " align="center">
                                <br>
                                <img alt="User Pic" style="width: 50px;height: 50px" src="http://cdn.onlinewebfonts.com/svg/img_382491.png" class="img-circle img-responsive">
                            </div>


                            <div class=" col-md-9 col-lg-9 ">
                                <table class="table table-user-information">
                                    <tbody>
                                    <tr>
                                        <td>Name:</td>
                                        <td><input type="text" class="form-control" name="user_name" value="" placeholder="Enter Name" required></td>
                                    </tr>
                                    <tr>
                                        <td>NRIC:</td>
                                        <td><input type="text" class="form-control" name="user_NRIC" value="" placeholder="Enter NRIC" required></td>
                                    </tr>
                                    <tr>
                                    <td>Password:</td>
                                       <td>


                                            <input type="password" id="password_input" name="user_password" class="form-control" placeholder="Enter Password" aria-label="Username" aria-describedby="password_visibility" required>

                                    </td>
                                        <td>

                                            <button type="button" style="width:40px" class="input-group-addon fa fa-eye btn btn-primary btn-md bg-primary text-white" name="password_visibility" id="password_visibility"></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td><input type="email" class="form-control" name="user_email" value="" placeholder="Enter Email" required></td>
                                    </tr>
                                    <tr>
                                        <td>Contact Number</td>
                                        <td><input type="text" class="form-control" name="user_contact" value="" placeholder="Enter Contact Number" required></td>
                                    </tr>
                                    <tr>
                                        <td>Address</td>
                                        <td><input type="text" class="form-control" name="user_address" value="" placeholder="Enter Address" required></td>
                                    </tr>
                                    <tr>

                                    <tr>
                                        <td>Date of Birth</td>
                                        <td><input type="text" class="form-control" name="user_dob" value="" placeholder="Enter DOB" required></td>
                                    </tr>
                                    <tr>
                                    <div class="form-group">
                                        <label for="sel1"><b>Role In System:</b></label>
                                        <select class="form-control" style="height:50px;background-color: beige;font-weight: bolder" id="sel1" name="role" required>
                                            <option name="Admin">Admin</option>
                                            <option name="Caregiver">Caregiver</option>
                                            <option name="Patient">Patient</option>
                                            <option name="Pharmacist">Pharmacist</option>
                                        </select>
                                    </div>
                                    </tr>



                                    </tr>

                                    </tbody>
                                </table>
                                <div class="pull-right">
                                    <button type="submit" class="btn btn-primary">Add User</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                        <span class="pull-right">
                            <a href="" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                            <a data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
                        </span>
                    </div>

                </div>
            </div>
        </div>
    </div>
    </form>

</t:adminPage>

<script>

    $(function() {
        $("#password_visibility").click(function(){
            var pass_input = document.getElementById("password_input");
            if (pass_input.type === "password") {
                pass_input.type = "text";
                $(this).removeClass("fa-eye").addClass("fa-eye-slash")
            } else {
                pass_input.type = "password";
                $(this).removeClass("fa-eye-slash").addClass("fa-eye")
            }
        });
    });


</script>