<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:patientPage>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/base/jquery-ui.css"/>

    <head>
        <title>Title</title>
        <h2>View Preorder #${id}</h2>
    </head>

    <br>
    <body>
        <label class="col-sm-4 col-form-label">Preorder Mode:</label>
        <input type="text" id="Mode" name="preorderMode" value =" ${mode}" readonly >
        <br>

        <label class="col-sm-4 col-form-label">Preorder Date:</label>
        <input type="text" id="Date" name="preorderDate" value =" ${date}" readonly >
        <br>

        <label class="col-sm-4 col-form-label">Preorder Status:</label>
        <input type="text" id="Status" name="preorderStatus" value =" ${status}" readonly >
        <br><br>

    <table id="myMainTable" class="dailyMedTable table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th style="width:50%">Drug Name</th>
            <th style="width:50%">Quantity</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="item">
            <tr>
                <td><c:out value="${item.drug_name}"/></td>
                <td><c:out value="${item.quantity}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </body>






</t:patientPage>

<script>
    // $(document).ready(function() {
    //     var $list = $('<tbody>').appendTo($('#preorderView'));
    //     $.each($list, function(key,value) {
    //         console.log(key)
    //         $('<tr>').appendTo($table)
    //             .append($('<td>').text(value.preorder_ID))
    //             .append($('<td>').text(value.preorder_mode))
    //     });
    //
    // });


    <%--$(document).ready(function() {--%>
        <%--$.get("/preorderViewServlet?mode=get", function (responseJson) {--%>

        <%--});--%>
    <%--});--%>
</script>
