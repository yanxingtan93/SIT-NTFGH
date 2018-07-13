<%@tag description="User Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
<t:pageTemplate>
    <jsp:attribute name="header">

         <div class="row" style="margin: 0px 0px 0px 92%;">
             <div class="pull-right" name="logout" id="logout">
                 <button type="button" style="background-color: #3FBEBB" class="btn btn-primary pull-right" value="Logout">Logout
                     <i class="fa fa-lock"></i></button>
             </div>
         </div>
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
                  <a class="nav-item nav-link" href="/patient/pillboxOverview.jsp"><b><i class="fa fa-medkit"></i> Pillbox</b></a>
                  <a class="nav-item nav-link" href="/patient/history.jsp"><b><i class="fa fa-history"></i> History</b></a>
                  <a class="nav-item nav-link" href="/patient/preorder.jsp"><b><i class="fa fa-money"></i> Preorder</b></a>
                  <a class="nav-item nav-link" href="/patient/profile.jsp"><b><i class="fa fa-user"></i> Profile</b></a>
                  <a class="nav-item nav-link" href="/patient/help.jsp"><b><i class="fa fa-question-circle"></i> Help</b></a>
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
        userWelcome.html("<b>Patient</b><br>Hello, " + validAcc);

    });

</script>