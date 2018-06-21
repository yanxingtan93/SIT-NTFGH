<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 3/6/18
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<t:pharmacistPage>
    <div>
        <h1>Manage Catalog of Drug Information</h1>
        <span class="pull-right"> <a href="/pharmacist/medicationAdd.jsp" class="btn btn-primary">+ Add New Drug</a></span>
    </div>
<br>

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
        <tr><th>No</th><th>Drug Name</th><th>Brand</th><th>Price</th><th>Type</th><th>Description</th><th>Side Effect</th><th>Functions</th></tr>
        </thead>

        <tbody>
        <c:forEach items="${list}" var="Medicine">
        <tr>
            <td>${Medicine.id}</td>
            <td>${Medicine.medicineName}</td>
            <td>${Medicine.brand}</td>
            <td>${Medicine.price}</td>
            <td>${Medicine.medicineForm}</td>
            <td>${Medicine.description}</td>
            <td>${Medicine.sideEffect}</td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">

                <form method="post" action="/drugCatalogueServlet">
                    <input type="hidden" class="form-control" name="mode" value="Edit">
                    <input type="hidden" class="form-control" name="drugid"  value=${Medicine.id}>
                    <button type="submit" class="btn btn-success">Edit</button>
                </form>
                    <form method="post" action="/drugCatalogueServlet">
                        <input type="hidden" class="form-control" name="mode"  value="Delete">
                        <input type="hidden" class="form-control" name="drugid"  value=${Medicine.id}>
                    <button type="submit" class="btn btn-danger">Delete</button>
                    </form>

                </div>
            </td>


        </tr>
        </c:forEach>
        </tbody>
    </table>

</t:pharmacistPage>

<script>

    function editDrug(s) {
            document.location.href = "/pharmacist/medicationEdit.jsp?drug=" + s;
    }
</script>