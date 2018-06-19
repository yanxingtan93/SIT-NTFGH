<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminPage>
    <h1>Search for patients by Name or NRIC</h1>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>
    <script type="text/javascript" charset="utf-8">

        $(document).ready(function() {
            $('#myMainTable').dataTable({
                "aLengthMenu": [[5, 50, 75, -1], [5, 50, 75, "All"]],
                "iDisplayLength": 5
            });
        } );


    </script>

    <style>
        .dataTables_wrapper .FilterStuff .dataTables_filter {float:right}
    </style>


    <table id="myMainTable" class="table table-striped table-bordered" style="width:100%">
        <thead class="thead-dark">
        <tr><th>NRIC</th><th>Name</th><th>Date Of Birth</th><th>Contact Number</th><th>Email</th><th>Address</th><th>Special Condition</th><th>Functions</th></tr>
        </thead>

        <tbody>
        <c:forEach items="${list}" var="User">
            <tr>
                <td>${User.NRIC}</td>
                <td>${User.name}</td>
                <td>${User.dob}</td>
                <td>${User.contact}</td>
                <td>${User.email}</td>
                <td>${User.address}</td>
                <td>${User.address}</td>
                <td>
                    <div class="btn-group" role="group" aria-label="Basic example">

                        <form method="post" action="/drugCatalogueServlet">
                            <input type="hidden" class="form-control" name="mode" value="Edit">
                            <input type="hidden" class="form-control" name="userid"  value=${User.NRIC}>
                            <button type="submit" class="btn btn-info">View</button>
                        </form>


                    </div>
                </td>


            </tr>
        </c:forEach>
        </tbody>

    </table>

</t:adminPage>
