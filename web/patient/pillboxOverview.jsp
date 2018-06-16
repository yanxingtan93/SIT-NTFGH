<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<t:patientPage>
    <html>
    <head>
        <h1>Pillbox overview</h1>
    </head>
    <body>
    <table>
        <tbody>

        <c:forEach items="${getList}" var="getLists">
            <tr>
                <td>${getLists.inventory_id}</td>
                <td>${getLists.drugintake_id}</td>
                <td>${getLists.drugphase_id}</td>
                <td>${getLists.inventory_balance}</td>
                <td>${getLists.inventory_days}</td>
                <td>${getLists.inventory_startdate}</td>
                <td>${getLists.inventory_status}</td>
                <td>${getLists.drug_ID}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </body>
    </html>
</t:patientPage>
