<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pharmacistPage>
  <h1>User's Profile</h1>


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
            <div class="row">

             <div> <h3 class="panel-title">Profile</h3></div>

            </div>

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
                      <div class="pull-right">

                              <button type="button" style="background-color: #3FBEBB" class="btn btn-primary pull-right" onclick="viewPillbox()"  name="btnLog" value="Logout"><i class="fas fa-medkit"></i>
                                  Patient Pillbox
                                  </button>
                              <br>

                          <br>

                      </div>

                  </tr>
                  <tr><br></tr>
                  <tr>
                    <td>Name:</td>
                    <td><input type="text" style="font-weight: bolder" class="form-control" name="user_name" id="user_name"placeholder="Enter Name" disabled></td>
                  </tr>
                  <tr>
                    <td>NRIC:</td>
                    <td><input type="text" style="font-weight: bolder" class="form-control" name="user_NRIC" id="user_NRIC"  placeholder="Enter NRIC" disabled></td>
                  </tr>
                  <tr>
                      <td>Email: </td>
                      <td><input type="email" style="font-weight: bolder" class="form-control" name="user_email" id="user_email" placeholder="Enter Email" disabled></td>
                  </tr>
                  <tr>
                      <td>Contact: </td>
                      <td><input type="text" style="font-weight: bolder" class="form-control" name="user_contact" id="user_contact"  placeholder="Enter Contact" disabled></td>
                  </tr>
                  <tr>
                    <td>Address: </td>
                    <td><input type="text" style="font-weight: bolder" class="form-control" name="user_address" id="user_address"  placeholder="Enter Address" disabled></td>
                  </tr>
                  <tr>

                  <tr>
                    <td>Date of Birth: </td>
                    <td><input type="text" style="font-weight: bolder" class="form-control" name="user_dob" id="user_dob"  placeholder="Enter DOB" disabled></td>
                  </tr>

                  <tr><td>Caregivers: </td><td>

                      <table id="myMainTable" class="table table-striped table-bordered" style="width:100%">
                          <thead class="thead-dark">
                          <tr><th>Name</th><th>Contact</th><th>Email</th><th>Address</th> </tr>
                          </thead>

                          <tbody>

                          </tbody>

                      </table>

                  </td></tr>




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

</t:pharmacistPage>
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
        var id = getParameterByName("userID");

        $.get("/UserServlet?mode=patient&id="+id, function(responseJson) {


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


                mytable.row.add([value.name.toUpperCase(), value.contact, value.email, value.address]);
                }


            });
            mytable.draw();
        });

    });


    function getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }
function viewPillbox() {

    var id = getParameterByName("userID");
    location.replace("/pharmacist/patientPillboxOverview.jsp?userID="+id);


}

</script>