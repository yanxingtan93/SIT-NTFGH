<%--
  Created by IntelliJ IDEA.
  User: MacBookPro
  Date: 13/7/18
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>



<t:patientPage>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>



    <style>
        .dataTables_wrapper .FilterStuff .dataTables_filter {float:right}
    </style>



    <div class="container" id="myTable">

        <table id="myMainTable" class="table table-striped table-bordered" style="width:100%">
            <thead class="thead-dark">
            <tr><th>No</th><th>Content Title</th><th>Content Category</th><th>Functions</th></tr>
            </thead>
        </table>

    </div>
    <script src ="/JS/PatientEducation.js"></script>
</t:patientPage>