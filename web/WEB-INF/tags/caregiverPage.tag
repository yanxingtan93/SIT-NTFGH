<%@tag description="User Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">

<t:pageTemplate>
    <jsp:attribute name="header">
<!--
<div class="row" style="position: absolute;padding-top:0px; text-align:center;background-color: black" >

    <div class="form-group" style="margin: 0px 0px 10px 8%;">
       <span name="patient" id="patient" style="color:white;"></span>
    </div>

</div>
        -->
         <div class="row" style="margin: 10px 0px 0px 92%;">
             <div class="pull-right" name="logout" id="logout">
                 <form method="post" action="/UserServlet">
                     <input type="hidden" class="form-control" name="route" id="route"  placeholder="" value="logout" >

                     <button type="submit" style="background-color: #3FBEBB" class="btn btn-primary pull-right"  name="btnLog" value="Logout">Logout
                         <i class="fa fa-lock"></i></button>
                 </form>
             </div>
         </div>



        <div class="float-right" style="margin: -60px 130px 0px 0px;">
            <img style="border-radius: 50%; margin: 0 10px 0 0" width="50" src="https://d30y9cdsu7xlg0.cloudfront.net/png/5832-200.png">
            <span class="float-right" name="welcome" id="welcome"></span>
        </div>



<nav class="navbar navbar-expand-lg navbar-dark" id="navbarAdmin">
    <a class="navbar-brand" href="#"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" >
        <div class="navbar-nav">

            <div class="nav-item nav-link" style="width:100%;">
                Patient
                <select name="caregiverPatientSelect" id="addDrugFrequency" style="    color: black;">
                    <option value="1">Kok Leong</option>
                    <option value="2">Denise</option>
                </select>
            </div>
            <a class="nav-item nav-link" href="#">Manage Patients</a>
            <a class="nav-item nav-link" href="#">Settings</a>
        </div>
    </div>
</nav>

      <nav class="navbar navbar-expand-lg navbar-light">
          <a class="navbar-brand" href="#"></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                  <a class="nav-item nav-link" href="/caregiver/patientOverview.jsp"><b><i class="fas fa-wheelchair"></i> My Patients</b></a>
                  <a class="nav-item nav-link" href="/caregiver/pillboxOverview.jsp"><b><i class="fas fa-medkit"></i> Pillbox</b></a>
                  <a class="nav-item nav-link" href="/caregiver/history.jsp"><b><i class="fa fa-history"></i> History</b></a>
                  <a class="nav-item nav-link" href="/patient/preorder.jsp"><b><i class="fa fa-money"></i> Preorder</b></a>
                <!--  <a class="nav-item nav-link" href="/patient/profile.jsp"><b><i class="fas fa-heartbeat"></i> Patient-Profile</b></a>-->
                  <a class="nav-item nav-link" href="/caregiver/profile.jsp"><b><i class="fa fa-user"></i> My Profile</b></a>
                  <a class="nav-item nav-link" href="/caregiver/help.jsp"><b><i class="fa fa-question-circle"></i> Help</b></a>
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
        userWelcome.html("<b>CareGiver</b><br>Hello, " + validAcc);

        var currentPatient = $('#patient');
        var validPatientName = "${sessionScope.patientName}";
        currentPatient.html("<b> My Current Patient: </b>" + validPatientName)




    });


    function check(){
        var validAcc1 = "${sessionScope.userID}";
        $.get("/UserServlet?mode=validate&role=caregiver&NRIC="+validAcc1, function(responseJson) {

            var access = responseJson.toString();
            if(access=="false"){
                window.history.back();
            }


        });
    }

    window.onload(check());

</script>