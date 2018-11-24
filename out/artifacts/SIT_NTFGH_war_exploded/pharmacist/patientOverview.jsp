<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pharmacistPage>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        .dataTables_wrapper .FilterStuff .dataTables_filter {float:right}
    </style>

    <div class="container">
    <div class="page-header">
        <h2>User List<span class="pull-right label label-default"></span></h2>
    </div>
    <div class="row">
    <div class="col-lg-11">

    <!-- tabs -->
    <div class="tabbable">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#one" data-toggle="tab"><i class="fa fa-users"></i> Patients</a></li>
        <li><a href="#two" data-toggle="tab"><i class="fas fa-user-md"></i> NTFGH Pharmacists</a></li>
    </ul>
    <div class="tab-content">
    <div class="tab-pane active" id="one">
        <h4>Search For Patients</h4>
        <table id="myMainTable" class="table table-striped table-bordered" style="width:100%">
            <thead class="thead-dark">
            <tr><th>NRIC</th><th>Name</th><th>Date Of Birth</th><th>Contact Number</th><th>Email</th><th>Address</th><th>Special Condition</th><th>Functions</th></tr>
            </thead>

            <tbody>

            </tbody>

        </table>

    </div>

    <div class="tab-pane" id="two">
        <h4>Search My Pharmacists</h4>
        <table id="myMainTable2" class="table table-striped table-bordered" style="width:100%">
            <thead class="thead-dark">
            <tr><th>Name</th><th>Contact Number</th><th>Email</th><th>Address</th></tr>
            </thead>

            <tbody>

            </tbody>

        </table>

    </div>


    </div>
    </div>
        <!-- /tabs -->
    </div>
    </div>

    </div>



</t:pharmacistPage>


<script>


    var mytable = $('#myMainTable').DataTable({
        "aLengthMenu": [[5, 50, 75, -1], [5, 50, 75, "All"]],
        "iDisplayLength": 5,
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "sDom": 'lfrtip'

    });

    var mytable2 = $('#myMainTable2').DataTable({
        "aLengthMenu": [[5, 50, 75, -1], [5, 50, 75, "All"]],
        "iDisplayLength": 5,
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "sDom": 'lfrtip'

    });


    $(document).ready(function() {
        $.get("/UserServlet?mode=pharmacistAllPatients", function(responseJson) {


            $.each(responseJson, function(key,value) {

                var button = "\n" +
                    "  <form method=\"post\" action=\"/pharmacist/patientProfile.jsp?userID="+value.NRIC+" \">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"mode\"  value=\"View\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"userID\"  value="+value.NRIC+">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-success\">View</button>\n" +
                    "                    </form>";


                mytable.row.add([value.NRIC, value.name.toUpperCase(), value.dob, value.contact, value.email, value.address, value.specialCondition,button]);


            });
            mytable.draw();
        });



        $.get("/UserServlet?mode=pharmacistAllPharmacists", function(responseJson1) {


            $.each(responseJson1, function(key,value) {



                mytable2.row.add([value.name.toUpperCase(), value.contact, value.email,value.address]);


            });
            mytable2.draw();
        });





    });




</script>