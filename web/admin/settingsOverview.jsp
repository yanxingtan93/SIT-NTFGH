<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminPage>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        .dataTables_wrapper .FilterStuff .dataTables_filter {float:right}
    </style>




    <div class="container">
        <div class="page-header">
            <h2><i class="fa fa-gears"></i> General Database Settings<span class="pull-right label label-default"></span></h2>
        </div>
        <div class="row">
            <div class="col-lg-11">

                <!-- tabs -->
                <div class="tabbable">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#one" data-toggle="tab"><i class="fa fa-gear"></i> Medicine Forms</a></li>
                        <li><a href="#two" data-toggle="tab"><i class="fa fa-gear"></i> Medicine Phases</a></li>
                        <li><a href="#three" data-toggle="tab"><i class="fa fa-gear"></i> Medicine Intake Periods</a></li>

                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="one">

                            <h4>System's Available Medicine Forms
                                <span class="pull-right"> <a href="/admin/medicationFormAdd.jsp" class="btn btn-primary">+ Add New Form</a></span>
                            </h4>

                            <table id="myMainTable" class="table table-striped table-bordered" style="width:100%">

                                <thead class="thead-dark"><tr><th>ID</th><th>Form</th><th>Functions</th></tr></thead>
                                <tbody></tbody>
                            </table>


                        </div>
                        <div class="tab-pane" id="two">
                            <h4>System's Available Drug Phases
                                <span class="pull-right"> <a href="/admin/medicationPhaseAdd.jsp" class="btn btn-primary">+ Add New Phase</a></span>
                            </h4>
                            <table id="myMainTablePhase" class="table table-striped table-bordered" style="width:100%">
                                <thead class="thead-dark"><tr><th>ID</th><th>Phase</th><th>Functions</th></tr></thead>
                                <tbody></tbody>
                            </table>

                        </div>
                        <div class="tab-pane" id="three">
                            <h4>System's Available Drug Intake Periods
                                <span class="pull-right"> <a href="/admin/medicationIntakeAdd.jsp" class="btn btn-primary">+ Add New Intake Period</a></span>
                            </h4>
                            <table id="myMainTableIntake" class="table table-striped table-bordered" style="width:100%">
                                <thead class="thead-dark"><tr><th>ID</th><th>Intakes</th><th>Functions</th></tr></thead>
                                <tbody></tbody>
                            </table>

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

    var mytable2 = $('#myMainTablePhase').DataTable({
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



    var mytable3 = $('#myMainTableIntake').DataTable({
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

                var button = "\n"  +

                    "  <form method=\"post\" action=\"/admin/medicationFormEdit.jsp?xFormID="+value.id+" \">\n" +
                    "                    <input type=\"hidden\" class=\"form-control\" name=\"mode\" value=\"Edit\">\n" +
                    "                    <input type=\"hidden\" class=\"form-control\" name=\"medID\"  value="+value.id+">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-success\">Edit</button>\n" +
                    "                </form>\n" +
                    "                    <form method=\"post\" action=\"/GeneralSettingsServlet\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"route\"  value=\"DeleteForm\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"settingID\"  value="+value.id+">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-danger\">Delete</button>\n" +
                    "                    </form>";


                mytable.row.add([value.id, value.formName,button]);


            });
            mytable.draw();
        });


        $.get("/GeneralSettingsServlet?mode=phase", function(responseJson1) {


            $.each(responseJson1, function(key,value) {

                var button = "\n"  +

                    "  <form method=\"post\" action=\"/admin/medicationPhaseEdit.jsp?xFormID="+value.id+" \">\n" +
                    "                    <input type=\"hidden\" class=\"form-control\" name=\"mode\" value=\"Edit\">\n" +
                    "                    <input type=\"hidden\" class=\"form-control\" name=\"medID\"  value="+value.id+">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-success\" >Edit</button>\n" +
                    "                </form>\n" +
                    "                    <form method=\"post\" action=\"/GeneralSettingsServlet\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"route\"  value=\"DeletePhase\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"settingID\"  value="+value.id+">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-danger\" >Delete</button>\n" +
                    "                    </form>";


                mytable2.row.add([value.id, value.phaseTerm,button]);


            });
            mytable2.draw();
        });

        $.get("/GeneralSettingsServlet?mode=intake", function(responseJson2) {


            $.each(responseJson2, function(key,value) {

                var button = "\n"  +

                    "  <form method=\"post\" action=\"/admin/medicationIntakeEdit.jsp?xFormID="+value.id+" \">\n" +
                    "                    <input type=\"hidden\" class=\"form-control\" name=\"mode\" value=\"Edit\">\n" +
                    "                    <input type=\"hidden\" class=\"form-control\" name=\"medID\"  value="+value.id+">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-success\">Edit</button>\n" +
                    "                </form>\n" +
                    "                    <form method=\"post\" action=\"/GeneralSettingsServlet\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"route\"  value=\"DeleteIntake\">\n" +
                    "                        <input type=\"hidden\" class=\"form-control\" name=\"settingID\"  value="+value.id+">\n" +
                    "                    <button type=\"submit\" class=\"btn btn-danger\">Delete</button>\n" +
                    "                    </form>";


                mytable3.row.add([value.id, value.intakeTerm,button]);


            });
            mytable3.draw();
        });




    });



</script>