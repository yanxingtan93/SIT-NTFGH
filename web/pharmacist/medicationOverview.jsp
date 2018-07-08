<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 3/6/18
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<t:pharmacistPage>
    <div>
        <h2>Manage Catalog of Drug Information
        <span class="pull-right"> <a href="/pharmacist/medicationAdd.jsp" class="btn btn-primary">+ Add New Drug</a></span>
        </h2>
    </div>
<br>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>


    <style>
        .dataTables_wrapper .FilterStuff .dataTables_filter {float:right}
    </style>


<div class="container" id="myTable">

    <table id="myMainTable" class="table table-striped table-bordered" style="width:100%">
        <thead class="thead-dark">
        <tr><th>No</th><th>Drug Name</th><th>Brand</th><th>Type</th><th>Description</th><th>Side Effect</th><th>Functions</th></tr>
        </thead>
    </table>

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
            $(document).ready(function() {
                $.get("/drugCatalogueServlet?route=all", function(responseJson) {


                    $.each(responseJson, function(key,value) {
                        var button = "\n" +

                             "  <form method=\"post\" action=\"/pharmacist/medicationEdit.jsp?xmedID="+value.id+" \">\n" +
                            "                    <input type=\"hidden\" class=\"form-control\" name=\"mode\" value=\"Edit\">\n" +
                            "                    <input type=\"hidden\" class=\"form-control\" name=\"xmedID\"  value="+value.id+">\n" +
                            "                    <button type=\"submit\" class=\"btn btn-success\">Edit</button>\n" +
                            "                </form>\n" +
                            "                    <form method=\"post\" action=\"/drugCatalogueServlet\">\n" +
                            "                        <input type=\"hidden\" class=\"form-control\" name=\"mode\"  value=\"Delete\">\n" +
                            "                        <input type=\"hidden\" class=\"form-control\" name=\"drugid\"  value="+value.id+">\n" +
                            "                    <button type=\"submit\" class=\"btn btn-danger\">Delete</button>\n" +
                            "                    </form>";


        mytable.row.add([value.id, value.medicineName, value.brand, value.medicineForm, value.description, value.sideEffect,button]);


                    });
                    mytable.draw();
                });

            });

</script>

<!--
<script type="text/javascript" charset="utf-8">

    (function($) {

        $(document).ready(function() {

            var table = $('#myMainTable').dataTable();


            var myTables = document.getElementById("myMainTable");
            $.get("/drugCatalogueServlet", function(responseJson) {


                $.each(responseJson, function(key,value) {

                    var button = "\n" +
                        "                <form method=\"post\" action=\"/drugCatalogueServlet\">\n" +
                        "                    <input type=\"hidden\" class=\"form-control\" name=\"mode\" value=\"Edit\">\n" +
                        "                    <input type=\"hidden\" class=\"form-control\" name=\"drugid\"  value="+value.id+">\n" +
                        "                    <button type=\"submit\" class=\"btn btn-success\">Edit</button>\n" +
                        "                </form>\n" +
                        "                    <form method=\"post\" action=\"/drugCatalogueServlet\">\n" +
                        "                        <input type=\"hidden\" class=\"form-control\" name=\"mode\"  value=\"Delete\">\n" +
                        "                        <input type=\"hidden\" class=\"form-control\" name=\"drugid\"  value="+value.id+">\n" +
                        "                    <button type=\"submit\" class=\"btn btn-danger\">Delete</button>\n" +
                        "                    </form>";

// <tr><th>No</th><th>Drug Name</th><th>Brand</th><th>Price</th><th>Type</th><th>Description</th><th>Side Effect</th><th>Functions</th></tr>

                    table.row.add({
                        "No": value.id,
                        "Drug Name": value.medicineName,
                        "Brand": value.brand,
                        "Price": value.price,
                        "Type": value.medicineForm,
                        "Description": value.description,
                        "Side Effect": value.sideEffect,
                        "Functions": button
                    }).draw();


                    /*

                    var row = myTables.insertRow(-1);

                    var cell1 = row.insertCell(0);
                    var cell2 = row.insertCell(1);
                    var cell3 = row.insertCell(2);
                    var cell4 = row.insertCell(3);
                    var cell5 = row.insertCell(4);
                    var cell6 = row.insertCell(5);
                    var cell7 = row.insertCell(6);
                    var cell8 = row.insertCell(7);

                    cell1.innerHTML = value.id;
                    cell2.innerHTML = value.medicineName;
                    cell3.innerHTML = value.brand;
                    cell4.innerHTML = value.price;
                    cell5.innerHTML = value.medicineForm;
                    cell6.innerHTML = value.description;
                    cell7.innerHTML = value.sideEffect;
                    cell8.innerHTML = button;
    /*

                        x+= "<tr><td>"+value.id+"</td><td>"+value.medicineName+"</td><td>"+value.brand+"</td><td>"+value.price+"</td><td>"
                            +value.medicineForm+"</td><td>"+value.description+"</td><td>"+value.sideEffect+"</td><td>"+button+"</td></tr>";

    */

                    });

                });




            });



        })(jQuery)

</script>


<script type="text/javascript" charset="utf-8">

    $(document).ready(function() {
        $('#myMainTable').dataTable({
            "aLengthMenu": [[5, 50, 75, -1], [5, 50, 75, "All"]],
            "iDisplayLength": 5,
            destroy:true
        });
    } );


</script>
-->