<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:patientPage>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/base/jquery-ui.css"/>

    <html>
    <head>
        <title>Title</title>
        <h2>View Preorder</h2>
    </head>
    <body>
    <div class="preorder">
        <label class="col-sm-4 col-form-label">Preorder ID:</label>
        <input type="text" id="preorderID" name="preorderID" value ="" readonly >

        <table>
            <c:forEach items="${list}" var="item">
                <tr>
                    <td><c:out value="${item.drug_name}" /></td>
                    <td><c:out value="${item.quantity}" /></td>
                </tr>
            </c:forEach>
        </table>

    </div>


    </body>
    </html>
</t:patientPage>

<script>
    $(document).ready(function() {
        $.get("/preorderViewServlet?mode=get", function (responseJson) {

        });
    });
</script>
