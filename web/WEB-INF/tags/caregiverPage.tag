<%@tag description="User Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate>
    <jsp:attribute name="header">
        <div class="float-right" style="margin: -60px 130px 0px 0px;">
            <img style="border-radius: 50%; margin: 0 10px 0 0" width="50" src="https://pbs.twimg.com/profile_images/997074101620559872/AHWWdl5J_200x200.jpg">
            <span class="float-right">Patient<br>Hello, Umar Malik</span>
        </div>


      <nav class="navbar navbar-expand-lg navbar-light">
          <a class="navbar-brand" href="#"></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                  <a class="nav-item nav-link" href="/caregiver/patientOverview.jsp">My Patients</a>
                  <a class="nav-item nav-link" href="/patient/pillboxOverview.jsp">Patient Pillbox</a>
                  <a class="nav-item nav-link" href="/patient/history.jsp">History</a>
                  <a class="nav-item nav-link" href="/patient/preorder.jsp">Preorder</a>
                  <a class="nav-item nav-link" href="/patient/profile.jsp">Patient Profile</a>
                  <a class="nav-item nav-link" href="/caregiver/profile.jsp">My Profile</a>
                  <a class="nav-item nav-link" href="/caregiver/help.jsp">Help</a>
              </div>
          </div>
      </nav>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:pageTemplate>