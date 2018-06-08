<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pharmacistPage>
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
        <tr>
            <td>S1230932X</td>
            <td>Kok Leong</td>
            <td>17/02/1891</td>
            <td>92891231</td>
            <td>kokleongKL@xxx.com</td>
            <td>Sungei Garden</td>
            <td>-</td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">View</button>
                </div>
            </td>
        </tr>

        <tr>
            <td>S1515532X</td>
            <td>Liew Shui Peng</td>
            <td>17/02/1891</td>
            <td>92891231</td>
            <td>kokleongKL@xxx.com</td>
            <td>Dover</td>
            <td>-</td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">View</button>
                </div>
            </td>
        </tr>

        <tr>
            <td>S140932X</td>
            <td>Kok Leong</td>
            <td>12/02/1891</td>
            <td>92111231</td>
            <td>kokleongKL2@xxx.com</td>
            <td>Kranji</td>
            <td>-</td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">View</button>
                </div>
            </td>
        </tr>

        <tr>
            <td>S1230331X</td>
            <td>Jolie Tan</td>
            <td>17/02/1891</td>
            <td>92891231</td>
            <td>kjolie@xxx.com</td>
            <td>Chinese Garden</td>
            <td>-</td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">View</button>
                </div>
            </td>
        </tr>

        <tr>
            <td>S1977932X</td>
            <td>Swisler</td>
            <td>20/02/1891</td>
            <td>91111231</td>
            <td>kowrec@xxx.com</td>
            <td>Sungei Garden</td>
            <td>-</td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">View</button>
                </div>
            </td>
        </tr>
        <tr>
            <td>S1510932X</td>
            <td>Donnie</td>
            <td>17/05/1993</td>
            <td>92891231</td>
            <td>AAle1g41gKL@xxx.com</td>
            <td>Tampines</td>
            <td>-</td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">View</button>
                </div>
            </td>
        </tr>
        <tr>
            <td>S1239832X</td>
            <td>Joey Chew</td>
            <td>15/02/1991</td>
            <td>92134711</td>
            <td>koDaoiKL@xxx.com</td>
            <td>Bedok</td>
            <td>-</td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary">View</button>
                </div>
            </td>
        </tr>


        </tbody>

    </table>

</t:pharmacistPage>
