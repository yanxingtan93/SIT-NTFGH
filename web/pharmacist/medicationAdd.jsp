<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 3/6/18
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../JS/medicineForm.js"></script>

<t:pharmacistPage>
    <title>Manage Drug Information</title>
    <h1>Manage Catalog of Drug Information</h1>
    <form action="/drugServlet" method="post">
        Medicine Name: <input type="text" name="medicineName" placeholder="Enter Medicine Name"/><br/>
        Brand: <input type="text" name="brand" placeholder="Enter Brand"/><br/>
        Price: <input type="text" name="price" placeholder="Enter Price"/><br/>
        Medicine Form:<select name="medicineForm" id="medicine-form">
        <option>Select a Medicine Form</option>

    </select><br/>
        Description:<textarea name="drugDescription" placeholder="Enter Description of the medicine"  cols="30" rows="10"></textarea><br/>
        Possible Side Effects:<textarea name="drugSideEffect" placeholder="Enter Possible Side Effect"  cols="30" rows="10"></textarea><br/>
        <input type="submit"/>

    </form>

</t:pharmacistPage>