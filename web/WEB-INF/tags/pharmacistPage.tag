<%@tag description="User Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate>
    <jsp:attribute name="header">
        <div class="float-right" style="margin: -60px 130px 0px 0px;">
            <img style="border-radius: 50%; margin: 0 10px 0 0" width="50" src="https://pbs.twimg.com/profile_images/997074101620559872/AHWWdl5J_200x200.jpg">
            <span class="float-right" name="welcome" id="welcome"></span>
        </div>


      <nav class="navbar navbar-expand-lg navbar-light">
          <a class="navbar-brand" href="#"></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                  <a class="nav-item nav-link" href="/pharmacist/patientOverview.jsp">Patients</a>
                  <a class="nav-item nav-link" href="/pharmacist/medicationOverview.jsp">Medication</a>
                  <a class="nav-item nav-link" href="/pharmacist/contentOverview.jsp">Content</a>
                  <a class="nav-item nav-link" href="/pharmacist/profile.jsp">Profile</a>
              </div>
          </div>
      </nav>
    </jsp:attribute>
    <jsp:body>

        <jsp:doBody/>
    </jsp:body>
</t:pageTemplate>

<script>

    $(document).ready(function(){
        var userWelcome = $('#welcome');
        var validAcc = "${sessionScope.userID}";
        userWelcome.html("Pharmacist<br>Hello, " + validAcc);

    });

</script>