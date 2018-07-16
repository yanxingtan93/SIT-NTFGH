<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminPage>
    <h1>Manage personal details</h1>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>
    <br>
    <div class="container">
        <div class="row">

            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 toppad" >

                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">Profile</h3>
                    </div>
                    <div class="panel-body">
                        <div class="container">

                            <div class="col-md-3 col-lg-3 " align="center">
                                <br>
                                <img alt="User Pic" style="width: 250px;height: 250px" src="https://cdn0.iconfinder.com/data/icons/icostrike-characters/512/user_login-512.png" class="img-circle img-responsive">
                            </div>

<form action="/UserServlet" method="post">
                            <div class=" col-md-8 col-lg-8 ">
                                <input type="hidden" style="font-weight: bolder" class="form-control" name="route" id="route"placeholder="" value="edit">
                               <input type="hidden" style="font-weight: bolder" class="form-control" name="roleA" id="roleA"placeholder="" value="admin">
                                <table class="table table-user-information">
                                    <tbody>
                                    <tr>

                                        <td>Name:</td>
                                        <td><input type="text" style="font-weight: bolder" class="form-control" name="user_name" id="user_name"placeholder="Enter Name"></td>
                                    </tr>
                                    <tr>
                                        <td>NRIC:</td>
                                        <td><input type="text" style="font-weight: bolder" class="form-control" name="user_NRIC" id="user_NRIC"  placeholder="Enter NRIC"></td>
                                    </tr>
                                    <tr>
                                        <td>Email: </td>
                                        <td><input type="email" style="font-weight: bolder" class="form-control" name="user_email" id="user_email" placeholder="Enter Email"></td>
                                    </tr>
                                    <tr>
                                        <td>Contact: </td>
                                        <td><input type="text" style="font-weight: bolder" class="form-control" name="user_contact" id="user_contact"  placeholder="Enter Contact"></td>
                                    </tr>
                                    <tr>
                                        <td>Address: </td>
                                        <td><input type="text" style="font-weight: bolder" class="form-control" name="user_address" id="user_address"  placeholder="Enter Address"></td>
                                    </tr>
                                    <tr>

                                    <tr>
                                        <td>Date of Birth: </td>
                                        <td><input type="text" style="font-weight: bolder" class="form-control" name="user_dob" id="user_dob"  placeholder="Enter DOB"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <div class="pull-right">
                                                <button type="submit" class="btn btn-success">Save Changes</button>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>

                            </div>

                            </form>
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


</t:adminPage>


<script>


    $(document).ready(function() {

        var name = $('#user_name');
        var ic = $('#user_NRIC');
        var email = $('#user_email');
        var address = $('#user_address');
        var contact = $('#user_contact');
        var dob = $('#user_dob');
        var validAcc = "${sessionScope.userID}";
        var x = 0;

        $.get("/UserServlet?mode=myprofile&id="+validAcc, function(responseJson) {

            $.each(responseJson, function(key,value) {

                name.val(value.name.toUpperCase());
                ic.val(value.NRIC.toUpperCase());
                email.val(value.email);
                contact.val(value.contact);
                address.val(value.address);
                dob.val(value.dob);


            });
        });

    });

</script>