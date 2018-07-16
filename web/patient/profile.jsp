<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:patientPage>
  <h1>Manage personal details</h1>


    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>
    <style>
        .dataTables_wrapper .FilterStuff .dataTables_filter {float:right}
    </style>

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
                <img alt="User Pic" style="width: 250px;height: 250px" src="https://cdn.iconscout.com/public/images/icon/premium/png-512/doctor-patient-medical-healthcare-35fe41fd943e134b-512x512.png" class="img-circle img-responsive">
              </div>

              <form method="post" action="/UserServlet">
              <div class=" col-md-8 col-lg-8 ">
                <table class="table table-user-information">
                  <tbody>
                  <tr>
                    <td><input type="hidden" style="font-weight: bolder" class="form-control" name="route" id="route"placeholder="" value="edit"></td>
                    <td><input type="hidden" style="font-weight: bolder" class="form-control" name="roleA" id="roleA"placeholder="" value="patient"></td>
                  </tr>
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

                  <tr><td>Caregivers: </td><td>

                      <table id="myMainTable" class="table table-striped table-bordered" style="width:100%">
                          <thead class="thead-dark">
                          <tr><th>Name</th><th>Contact</th><th>Email</th><th>Address</th><th>Functions</th></tr>
                          </thead>

                          <tbody>

                          </tbody>

                      </table>

                  </td></tr>

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

</t:patientPage>
<script>


    var mytable = $('#myMainTable').DataTable({
        "aLengthMenu": [[15, 50, 75, -1], [15, 50, 75, "All"]],
        "iDisplayLength": 10,
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "sDom": 'lfrtip'

    });

    $(document).ready(function() {

        var name = $('#user_name');
        var ic = $('#user_NRIC');
        var email = $('#user_email');
        var address = $('#user_address');
        var contact = $('#user_contact');
        var dob = $('#user_dob');
        var validAcc = "${sessionScope.userID}";
        var x = 0;

        $.get("/UserServlet?mode=patient&id="+validAcc, function(responseJson) {


            $.each(responseJson, function(key,value) {

                if(x==0){
                    name.val(value.name.toUpperCase());
                    ic.val(value.NRIC.toUpperCase());
                    email.val(value.email);
                    contact.val(value.contact);
                    address.val(value.address);
                    dob.val(value.dob);

                    x++;
                }

                else{
                var button = "\n" +
                    "                    <form method=\"post\" action=\"/UserServlet\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"route\"  value=\"caregiverRemove\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"caregiver_NRIC\"  value="+value.NRIC+">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-danger\">Remove</button>\n" +
                    "                    </form>";


                mytable.row.add([value.name.toUpperCase(), value.contact, value.email, value.address,button]);
                }


            });
            mytable.draw();
        });

    });

</script>