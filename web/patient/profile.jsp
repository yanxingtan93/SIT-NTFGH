<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:patientPage>
  <h1>Manage personal details</h1>

  <br>
  <div class="container">
    <div class="row">

      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >


        <div class="panel panel-info">
          <div class="panel-heading">
            <h3 class="panel-title">Profile</h3>
          </div>
          <div class="panel-body">
            <div class="row">

              <div class="col-md-3 col-lg-3 " align="center">
                <br>
                <img alt="User Pic" style="width: 250px;height: 250px" src="data:image/jpeg;base64,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" class="img-circle img-responsive">
              </div>


              <div class=" col-md-9 col-lg-9 ">
                <table class="table table-user-information">
                  <tbody>
                  <tr>
                    <td>Name:</td>
                    <td><input type="text" class="form-control" name="user_name" value="Umar" placeholder="Enter Medicine Name"></td>
                  </tr>
                  <tr>
                    <td>NRIC:</td>
                    <td><input type="text" class="form-control" name="user_NRIC" value="S9318913X" placeholder="Enter Brand"></td>
                  </tr>
                  <tr>
                    <td>Email</td>
                    <td><input type="email" class="form-control" name="user_email" value="Juoae@gmail.com" placeholder="Enter Price"></td>
                  </tr>
                  <tr>
                    <td>Address</td>
                    <td><input type="email" class="form-control" name="user_address" value="Clementi Ave 2" placeholder="Enter Price"></td>
                  </tr>
                  <tr>

                  <tr>
                    <td>Date of Birth</td>
                    <td><input type="text" class="form-control" id="user_dob" value="16/12/1994" placeholder="Enter DOB"></td>
                  </tr>

                  </tr>

                  </tbody>
                </table>

              </div>
            </div>
          </div>
          <div class="panel-footer">
            <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
            <span class="pull-right">
                            <a href="" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                            <a data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
                        </span>
          </div>

        </div>
      </div>
    </div>
  </div>

</t:patientPage>
