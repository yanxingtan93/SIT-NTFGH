<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminPage>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>


    <style>
        .dataTables_wrapper .FilterStuff .dataTables_filter {float:right}
    </style>




    <div class="container">
        <div class="page-header">
            <h2>General Database Settings<span class="pull-right label label-default"></span></h2>
        </div>
        <div class="row">
            <div class="col-lg-10">

                <!-- tabs -->
                <div class="tabbable">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#one" data-toggle="tab">Medicine Forms</a></li>
                        <li><a href="#two" data-toggle="tab">Medicine Phases</a></li>
                        <li><a href="#three" data-toggle="tab">Medicine Intake Periods</a></li>
                        <li><a href="#four" data-toggle="tab">Allergies</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="one">

                            <h4>Systems Available Medicine Forms</h4>


                            <table id="myMainTable" class="table table-striped table-bordered" style="width:100%">
                                <thead class="thead-dark"><tr><th>ID</th><th>Form</th><th>Functions</th></tr></thead>
                                <tbody></tbody>
                            </table>


                        </div>
                        <div class="tab-pane" id="two">

                            <table id="myMainTable2" class="table table-striped table-bordered" style="width:100%">
                                <thead class="thead-dark"><tr><th>ID</th><th>Phase</th><th>Functions</th></tr></thead>
                                <tbody></tbody>
                            </table>

                        </div>
                        <div class="tab-pane" id="three">

                            Thirdamuno, ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra varius quam sit amet vulputate. Quisque mauris augue, molestie tincidunt condimentum vitae.


                        </div>
                        <div class="tab-pane" id="four">

                            Fouranjd, ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra varius quam sit amet vulputate. Quisque mauris augue, molestie tincidunt condimentum vitae.


                        </div>
                    </div>
                </div>
                <!-- /tabs -->
            </div>
        </div>

    </div>


</t:adminPage>


<script>


    var mytable = $('#myMainTable').DataTable({
        "aLengthMenu": [[10, 50, 75, -1], [10, 50, 75, "All"]],
        "iDisplayLength": 10,
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "sDom": 'lfrtip'

    });
    $(document).ready(function() {
        $.get("/GeneralSettingsServlet?mode=medForms", function(responseJson) {


            $.each(responseJson, function(key,value) {

                var button = "\n" +
                    "                    <form method=\"post\" action=\"/drugCatalogueServlet\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"mode\"  value=\"Delete\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"drugid\"  value="+value.id+">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-danger\">Delete</button>\n" +
                    "                    </form>";


                mytable.row.add([value.id, value.formName,button]);


            });
            mytable.draw();
        });

    });

    var mytable2 = $('#myMainTable2').DataTable({
        "aLengthMenu": [[10, 50, 75, -1], [10, 50, 75, "All"]],
        "iDisplayLength": 10,
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "sDom": 'lfrtip'

    });
    $(document).ready(function() {
        $.get("/GeneralSettingsServlet?mode=phase", function(responseJson) {


            $.each(responseJson, function(key,value) {

                var button = "\n" +
                    "                    <form method=\"post\" action=\"/drugCatalogueServlet\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"mode\"  value=\"Delete\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"drugid\"  value="+value.id+">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-danger\">Delete</button>\n" +
                    "                    </form>";


                mytable2.row.add([value.id, value.phaseTerm,button]);


            });
            mytable2.draw();
        });

    });


</script>