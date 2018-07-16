<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:caregiverPage>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>


    <style>
        .dataTables_wrapper .FilterStuff .dataTables_filter {float:right}
    </style>

    <h2>My Patients
        <span class="pull-right"> <a href="/caregiver/patientAdd.jsp" class="btn btn-primary">+ Add My Patient</a></span>
    </h2>
    <table id="myMainTable" class="table table-striped table-bordered" style="width:100%">
        <thead class="thead-dark">
        <tr><th>NRIC</th><th>Name</th><th>Date Of Birth</th><th>Contact Number</th><th>Email</th><th>Address</th><th>Functions</th></tr>
        </thead>

        <tbody>

        </tbody>

    </table>

</t:caregiverPage>


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
    $(document).ready(function() {
        $.get("/UserServlet?mode=caregiver", function(responseJson) {


            $.each(responseJson, function(key,value) {




                var button = "\n" +
                    "                    <form method=\"post\" action=\"/UserServlet\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"route\"  value=\"Access\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"userAID\"  id=\"userAID\" value="+value.NRIC+">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-warning  \">Access</button>\n" +
                    "                    </form>&nbsp" +
                    "" +
                    "                    <form method=\"post\" action=\"/UserServlet\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"route\"  value=\"DeletePatient\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"userID\"  value="+value.NRIC+">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-danger\">Remove</button>\n" +
                    "                    </form>&nbsp";


                mytable.row.add([value.NRIC, value.name, value.dob, value.contact, value.email, value.address, button]);


            });
            mytable.draw();
        });

    });




</script>