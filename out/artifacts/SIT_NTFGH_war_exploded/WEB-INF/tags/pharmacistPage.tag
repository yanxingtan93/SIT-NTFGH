<%@tag description="User Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
<t:pageTemplate>
    <jsp:attribute name="header">

        <div class="row" style="margin: 0px 0px 0px 92%;">
            <div class="pull-right" name="logout" id="logout">
                <form method="post" action="/UserServlet">
                    <input type="hidden" class="form-control" name="route" id="route"  placeholder="" value="logout" >

                    <button type="submit" style="background-color: #3FBEBB" class="btn btn-primary pull-right"  name="btnLog" value="Logout">Logout
                        <i class="fa fa-lock"></i></button>
                </form>
            </div>
        </div>

        <div class="float-right" style="margin: -60px 130px 0px 0px;">
            <img style="border-radius: 50%; margin: 0 10px 0 0" width="50" src="https://cdn.iconscout.com/public/images/icon/premium/png-512/pharmacist-consultation-medical-doctor-pill-37584c4c69a5525b-512x512.png">


            <span class="float-right" name="welcome" id="welcome"></span>
         </div>


      <nav class="navbar navbar-expand-lg navbar-light">
          <a class="navbar-brand" href="#"></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                  <a class="nav-item nav-link" href="/pharmacist/patientOverview.jsp"><b><i class="fa fa-users"></i> Patients</b></a>
                  <a class="nav-item nav-link" href="/pharmacist/medicationOverview.jsp"><b><i class="fas fa-medkit"></i> Medication</b></a>
                  <a class="nav-item nav-link" href="/pharmacist/preorder.jsp"><b><i class="fa fa-money"></i> Preorder</b></a>
                  <a class="nav-item nav-link" href="/pharmacist/contentOverview.jsp"><b><i class="fa fa-book"></i> Content</b></a>
                  <a class="nav-item nav-link" href="/pharmacist/profile.jsp"><b><i class="fa fa-user"></i> Profile</b></a>
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


        userWelcome.html("<b>Pharmacist</b><br>Hello, " + validAcc);

    });


    function check(){
        var validAcc1 = "${sessionScope.userID}";
        $.get("/UserServlet?mode=validate&role=pharmacist&NRIC="+validAcc1, function(responseJson) {

            var access = responseJson.toString();
            if(access=="false"){
                window.history.back();
            }


        });
    }

    window.onload(check());

</script>