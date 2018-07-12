<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 3/6/18
  Time: 1:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pharmacistPage>
    <style>
        .dataTables_wrapper .FilterStuff .dataTables_filter {float:right}
    </style>
    <h1>Display PDF uploads here</h1>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>


    <span class="pull-right"> <a href="/pharmacist/contentAdd.jsp" class="btn btn-primary">+ Add New Content</a></span>
    <br/>
    <div class="container" id="myTable">
        <table id = "myMainTable"class="table table-striped table-bordered" style="width:100%">
            <thead class="thead-dark">
                <tr><th>No</th><th>Content Title</th><th>Content Category</th><th>Functions</th></tr>
            </thead>
        </table>

    </div>


</t:pharmacistPage>

