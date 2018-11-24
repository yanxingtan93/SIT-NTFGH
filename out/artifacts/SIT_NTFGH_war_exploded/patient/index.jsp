<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 15/7/18
  Time: 11:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:patientPage>
    <div class="flex-container">
        <div class="flex-item card">
            <a href="/patient/pillboxOverview.jsp" style="position: absolute;top: 0; left: 0;height: 100%; width: 100%;"></a>
            <div class="card-body dashboard-item">
                <i class="fa fa-medkit" ></i>
                <h5 class="card-title" >Medication</h5>
            </div>
        </div>
        <div class="flex-item card">
            <a href="/patient/history.jsp" style="position: absolute;top: 0; left: 0;height: 100%; width: 100%;"></a>
            <div class="card-body dashboard-item">
                <i class="fa fa-history" ></i>
                <h5 class="card-title" >History</h5>
            </div>
        </div>
        <div class="flex-item card">
            <a href="/patient/preorder.jsp" style="position: absolute;top: 0; left: 0;height: 100%; width: 100%;"></a>
            <div class="card-body dashboard-item">
                <i class="fa fa-money" ></i>
                <h5 class="card-title" >Preorder</h5>
            </div>
        </div>
        <div class="flex-item card">
            <a href="/patient/profile.jsp" style="position: absolute;top: 0; left: 0;height: 100%; width: 100%;"></a>
            <div class="card-body dashboard-item">
                <i class="fa fa-user" ></i>
                <h5 class="card-title" >Profile</h5>
            </div>
        </div>
        <div class="flex-item card">
            <a href="/patient/help.jsp" style="position: absolute;top: 0; left: 0;height: 100%; width: 100%;"></a>
            <div class="card-body dashboard-item">
                <i class="fa fa-question-circle" ></i>
                <h5 class="card-title" >Help</h5>
            </div>
        </div>
    </div>
</t:patientPage>