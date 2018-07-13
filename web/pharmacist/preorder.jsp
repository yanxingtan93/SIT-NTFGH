<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../JS/preorderForm.js"></script>

<%--<script src="https://code.jquery.com/jquery-1.12.4.js"></script>--%>

<t:pharmacistPage>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/base/jquery-ui.css"/>

<html>
<head>
    <h2>View Patients' Preorders</h2>
</head>
<br>
<body>
<div class="container">
    <form method="post" action="/pharmacistPreorderServlet">
        <div class="col-sm-12">
            <div class="row">
                <input type="hidden" class="form-control" name="action" value="selectPatient"/>
                    <label class="col-sm-2 col-form-label">Patient: </label>
                    <select class="col-sm-4 form-control" id="patientList" name="patientList">

                    </select>
                    <button type='submit' class='col-sm-2 btn btn-info btn-sm' name='viewPatientPreorder'>Submit</button>

            </div>
        </div>

    </form>
</div>

<div class="row">


    <br>
    <table id="myMainTable" class="dailyMedTable table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>

            <th style="width:10%">Preorder ID</th>
            <th style="width:15%">Method of Collection</th>
            <th style="width:15%">Collection Date</th>
            <th style="width:15%">Status</th>
            <th style="width:15%">View</th>
        </tr>
        </thead>
        <div id ="pillbox-table" class="pillbox">

        </div>
    </table>
</div>
</body>
</html>
</t:pharmacistPage>

<script>
    $(document).ready(function() {               // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
        $.get("/patientFormServlet", function(responseJson) {                 // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
            var $select = $("#patientList");                           // Locate HTML DOM element with ID "someselect"
            $.each(responseJson, function(key, value) {               // Iterate over the JSON object.
                $("<option>").val(value).text(value).appendTo($select); // Create HTML <option> element, set its value with currently iterated key and its text content with currently iterated item and finally append it to the <select>.
            });
        });
    });

    $(function() {
        $.get("/pharmacistPreorderServlet", function(responseJson) {
            var $table = $('<tbody>').appendTo($('#myMainTable'));
            $.each(responseJson, function(key,value) {
                console.log(key)
                $('<tr>').appendTo($table)
                    .append($('<td>').text(value.preorder_ID))
                    .append($('<td>').text(value.preorder_mode))
                    .append($('<td>').text(value.collection_date))
                    .append($('<td>').text(value.status))

                    .append($('<td>')
                            .append("<form method=\"post\" action=\"/pharmacistPreorderServlet\">\n" +
                                "                <input type=\"hidden\" class=\"form-control\" name=\"action\" value=\"pharmView\">\n" +
                                "                <input type=\"hidden\" class=\"form-control\" name=\"preorder_ID\" value="+value.preorder_ID+">\n" +
                                "                <button type=\"submit\" class=\"btn btn-info btn-block\">View</button>\n" +
                                "                <form>")
                        // .append("<button type='submit' class='btn btn-info btn-block btn-lg' name='viewPreorder' onclick='submit'>View</button>")
                    )
            });
        });
    });

</script>