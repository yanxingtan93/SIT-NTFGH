<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>



<t:pharmacistPage>
    <div>
        <h2>View Patients' Preorder</h2>
    </div>
    <br>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>



    <style>
        .dataTables_wrapper .FilterStuff .dataTables_filter {float:right}
    </style>



    <div class="container" id="myTable">
        <br>
        <table id="myMainTable" class="table table-striped table-bordered" style="width:90%">
            <thead class="thead-dark">
            <tr>
                <th>Preorder ID</th>
                <th>Patient's NRIC</th>
                <th>Preorder Mode</th>
                <th>Collection Date</th>
                <th>Status</th>
                <th>Functions</th>
            </tr>
            </thead>
        </table>

    </div>
    <script src ="../JS/preorderView.js"></script>
</t:pharmacistPage>
