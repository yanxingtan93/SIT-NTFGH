<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:patientPage>


    <h1>History</h1>
    <div class="row">

        <table id="historyTable" class="table table-striped table-bordered">

            <tbody>


            </tbody>
        </table>
    </div>
    <script>

        $('#userID').val(userID);

        var userID = "${sessionScope.userID}";
    </script>
    <script src ="../JS/patientHistory.js"></script>
</t:patientPage>
