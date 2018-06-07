<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:patientPage>
    <html>
    <head>
        <h1>Pillbox overview</h1>
    </head>
    <body>
    <table>
        <tbody>
        <c:forEach items="${pillboxList}" var="pillbox">
            <tr>
                <td><c:out value="${pillbox.inventory_id}"/></td>
                <td><c:out value="${pillbox.drugintake_id}"/></td>
                <td><c:out value="${pillbox.drugphase_id}"/></td>
                <td><c:out value="${pillbox.inventory_balance}"/></td>
                <td><c:out value="${pillbox.inventory_days}"/></td>
                <td><c:out value="${pillbox.inventory_startdate}"/></td>
                <td><c:out value="${pillbox.inventory_status}"/></td>
                <td><c:out value="${pillbox.drug_ID}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </body>
    </html>
</t:patientPage>
