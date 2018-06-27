<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../JS/medicineForm.js"></script>

<t:adminPage>
    <title>Manage Drug Information</title>

    <form action="/GeneralSettingsServlet?route=phase" method="post">



        <br>
        <div class="container">
            <div class="row">

                <div class="col-xs-12 col-sm-12 col-md-9 col-lg-7 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >


                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">Add Medication Phases</h3>
                        </div>


                        <div class="panel-body">
                            <div class="row">

                                <div class="col-md-3 col-lg-3 " align="center">
                                    <br>
                                    <img alt="User Pic" style="width: 50px;height: 50px" src="http://cdn.onlinewebfonts.com/svg/img_382491.png" class="img-circle img-responsive">
                                </div>


                                <div class=" col-md-9 col-lg-9 ">
                                    <table class="table table-user-information">
                                        <tbody>
                                        <tr>

                                            <td>Medication Phases Term:</td>
                                            <td><input type="text" class="form-control" name="name"  placeholder="Enter Phases"></td>
                                        </tr>


                                        </tbody>
                                    </table>

                                    <div class="pull-right">
                                        <button type="submit" class="btn btn-primary">Add To Database</button>
                                    </div>
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




    </form>

</t:adminPage>