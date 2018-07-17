<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:caregiverPage>

    <!--
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
  <h1>History</h1>

  <table id="myMainTable" class="table table-striped table-bordered">
    <thead class="thead-dark">
    <tr>
      <th style="width:20%">Date</th>
      <th style="width:20%">Medication</th>
      <th style="width:20%">Scheduled Time</th>
      <th style="width:20%">Time Taken</th>
      <th style="width:20%">Adherence Status</th>
    </tr>
    </thead>

    <tbody>
    <tr>
      <td>12/02/1891</td>
      <td>Alprazolam</td>
      <td>10:30</td>
      <td>11:00</td>
      <td>On Time</td>
    </tr>
    <tr>
      <td>12/02/2000</td>
      <td>Ibuprofen</td>
      <td>14:30</td>
      <td>16:00</td>
      <td>Late</td>
    </tr>
    <tr>
      <td>12/02/1891</td>
      <td>Ibuprofen</td>
      <td>22:30</td>
      <td>22:00</td>
      <td>On Time</td>
    </tr>

    </tbody>

  </table>-->

    <h1>History</h1>
    <div class="row">

        <table id="historyTable" class="table table-striped table-bordered">

            <tbody>


            </tbody>
        </table>
    </div>
    <script>
        //================= UMAR TAKE NOTE =================
        var validAcc1 = "${sessionScope.patientID}";
        var userID = validAcc1;
        $('#userID').val(userID);
    </script>
    <script src ="../JS/patientHistory.js"></script>
</t:caregiverPage>
