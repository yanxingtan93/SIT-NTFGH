<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pharmacistPage>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/base/jquery-ui.css"/>

    <head>
    <h2>Edit Preorder</h2>
    </head>
    <br>
    <body>

    <label class="col-sm-2 col-form-label">Preorder ID:</label>
    <input type="text" id="preorder_ID" name="preorderID"  readonly >
    <br>
    <label class="col-sm-2 col-form-label">User NRIC:</label>
    <input type="text" id="NRIC" name="preorderNRIC" readonly >
    <br>
    <label class="col-sm-2 col-form-label">Preorder Mode:</label>
    <input type="text" id="Mode" name="preorderMode" readonly >
    <br>
    <label class="col-sm-2 col-form-label">Preorder Date:</label>
    <input type="text" id="Date" name="preorderDate" readonly >
    <br>
    <label class="col-sm-2 col-form-label">Preorder Status:</label>
    <input type="text" id="Status" name="preorderStatus" readonly >
    <br><br>

    <table id="myMainTable" class="dailyMedTable table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th style="width:50%">Drug Name</th>
            <th style="width:50%">Quantity</th>
        </tr>
        </thead>
    </table>

    </body>
</t:pharmacistPage>

<script>
    $(document).ready(function() {
        var id = getParameterByName("preorderID");
        $.get("/pharmacistPreorderServlet?mode=viewByID&id="+id, function(responseJson) {
            var $table = $('<tbody>').appendTo($('#myMainTable'));
            $.each(responseJson, function(key,value) {
                console.log(key);
                $('<tr>').appendTo($table)
                    .append($('<td>').text(value.drug_name))
                    .append($('<td>').text(value.quantity))
            });
        });

    });

    $(document).ready(function() {
        var preorderid = $('#preorder_ID');
        var nric = $('#NRIC');
        var mode = $('#Mode');
        var date = $('#Date');
        var status = $('#Status');

        var id = getParameterByName("preorderID");
        console.log("id is:" + id);

        $.get("/pharmacistPreorderServlet?mode=Edit&id="+id, function(responseJson) {
            $.each(responseJson, function(key,value) {

                preorderid.val(value.preorder_ID);
                nric.val(value.user_NRIC);
                mode.val(value.preorder_mode);
                date.val(value.collection_date);
                status.val(value.status);

            });

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


</script>
