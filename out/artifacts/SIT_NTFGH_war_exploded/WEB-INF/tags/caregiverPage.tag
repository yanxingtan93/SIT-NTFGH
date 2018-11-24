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
        <div class="navbar-nav row"  style="margin:0px;">
            <div class="col-lg"  >
            </div>
            <div class="col-lg" style="background: #000000;">
                <div class="nav-item nav-link " style="width:100%;">

                    <b><i class="fa fa-child"></i> Patient</b>
                    <select name="caregiverPatientSelect" class="form-control" id="caregiverPatientSelect" style="color: black;background-color: antiquewhite;font-weight: bold;height: 29px">
                    </select>
                </div>
            </div>

            <div class="col-lg" style="background: #000000;">
                <a class="nav-item nav-link" href="/caregiver/patientOverview.jsp"><b> <i class="fas fa-wheelchair"></i> Manage Patients</b></a>
            </div>
            <div class="col-lg" style="background: #000000;">
                <a class="nav-item nav-link" href="/caregiver/profile.jsp"><b><i class="fa fa-User"></i> My Profile</b></a>
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
                  <a class="nav-item nav-link" href="/caregiver/pillboxOverview.jsp"><b><i class="fas fa-medkit"></i> Pillbox</b></a>
                  <a class="nav-item nav-link" href="/caregiver/history.jsp"><b><i class="fa fa-history"></i> History</b></a>
                  <a class="nav-item nav-link" href="/caregiver/preorder.jsp"><b><i class="fa fa-money"></i> Preorder</b></a>
                  <a class="nav-item nav-link" href="/caregiver/drugCatalog.jsp"><b><i class="fa fa-info"></i> Drug Info</b></a>
                <!--  <a class="nav-item nav-link" href="/patient/profile.jsp"><b><i class="fas fa-heartbeat"></i> Patient-Profile</b></a>-->
                  <a class="nav-item nav-link" href="/caregiver/patientProfile.jsp"><b><i class="fa fa-heartbeat"></i> Profile</b></a>
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

        var currentPatient = "${sessionScope.patientID}";

        if(currentPatient==""){

        }


        $('#caregiverPatientSelect').on('change', function() {

            $.post( "/UserServlet?route=selectPatient", {patientID: this.value})
            location.reload();
        });

        getPatients(validAcc);//change to session variables


    });

    function getPatients(caregiverID){

        var x = 0;
        var firstPatientID = "";
        console.log(caregiverID);
        $.post( "/UserServlet?route=listPatients", {caregiverID: caregiverID})
            .then(
                function(data,status) {

                    //SET session value to item.user_NRIC
                    $.each(JSON.parse(data), function (i, item) {
                        $('#caregiverPatientSelect').append($('<option>', {
                            value: item.user_NRIC,
                            text : item.user_name.toUpperCase()
                        }));
                        if(x==0){
                            firstPatientID = item.user_NRIC;
                        }
                        x++;
                    });

                    var validPatientID = "${sessionScope.patientID}";
                    $('#caregiverPatientSelect').val(validPatientID);
                }
            );





    }



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