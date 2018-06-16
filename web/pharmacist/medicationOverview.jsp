<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 3/6/18
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>



<t:pharmacistPage>
    <h1>Manage Catalog of Drug Information</h1>

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
        <tr>
            <td>1</td>
            <td>Paracetamol</td>
            <td>Gucci</td>
            <td>$4.15</td>
            <td>Pill</td>
            <td>For Headache</td>
            <td>For Headache</td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">Edit</button>
                    <button type="button" class="btn btn-secondary">Delete</button>

                </div>
            </td>
        </tr>

        <tr>
            <td>2</td>
            <td>Lipsium</td>
            <td>Exec</td>
            <td>$5.15</td>
            <td>Inhaler</td>
            <td>Cholestrol</td>
            <td>Cholestrol</td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">Edit</button>
                    <button type="button" class="btn btn-secondary">Delete</button>

                </div>
            </td>
        </tr>

        <tr>
            <td>3</td>
            <td>Ardisan</td>
            <td>Exec</td>
            <td>$2.15</td>
            <td>Inhaler</td>
            <td>Skin problems</td>
            <td>-</td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">Edit</button>
                    <button type="button" class="btn btn-secondary">Delete</button>

                </div>
            </td>
        </tr>

        <tr>
            <td>4</td>
            <td>Chloromine</td>
            <td>-</td>
            <td>$3.75</td>
            <td>Pill</td>
            <td>For Running nose</td>
            <td></td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">Edit</button>
                    <button type="button" class="btn btn-secondary">Delete</button>

                </div>
            </td>
        </tr>

        <tr>
            <td>5</td>
            <td>A-Chloromine</td>
            <td>-</td>
            <td>$3.85</td>
            <td>Pill</td>
            <td>For Running nose</td>
            <td></td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">Edit</button>
                    <button type="button" class="btn btn-secondary">Delete</button>

                </div>
            </td>
        </tr>
        <tr>
            <td>6</td>
            <td>Ax-Chloromine</td>
            <td>-</td>
            <td>$4.23</td>
            <td>Pill</td>
            <td>For Running nose</td>
            <td></td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">Edit</button>
                    <button type="button" class="btn btn-secondary">Delete</button>

                </div>
            </td>
        </tr>
        <tr>
            <td>7</td>
            <td>Chloromine - Adult</td>
            <td>-</td>
            <td>$5.55</td>
            <td>Pill</td>
            <td>For Running nose</td>
            <td></td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">Edit</button>
                    <button type="button" class="btn btn-secondary">Delete</button>

                </div>
            </td>
        </tr>


        </tbody>

    </table>



        <table>
            <th>Test</th>
            <c:forEach items="${list}" var="record">
                <tr>
                    <td>${record.id }</td>

                </tr>
            </c:forEach>
        </table>


</t:pharmacistPage>