<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 3/6/18
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../JS/medicineForm.js"></script>

<t:pharmacistPage>
    <title>Manage Drug Information</title>

    <form action="/drugServlet" method="post">



        <br>
        <div class="container">
            <div class="row">

                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >


                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">Add Drug</h3>
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
                                            <td>Medicine Name:</td>
                                            <td><input type="text" class="form-control" name="medicineName"  placeholder="Enter Medicine Name"></td>
                                        </tr>
                                        <tr>
                                            <td>Brand:</td>
                                            <td><input type="text" class="form-control" name="brand"  placeholder="Enter Brand"></td>
                                        </tr>
                                        <tr>
                                            <td>Price</td>
                                            <td><input type="text" class="form-control" name="price"  placeholder="Enter Price"></td>
                                        </tr>

                                        <tr>
                                        <tr>
                                            <td>Medicine Form</td>
                                            <td>

                                                <select class="form-control" id="medicine-form" name="medicineForm">

                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Description</td>
                                            <td><textarea class="form-control" name="drugDescription" id="drugDescription" rows="4"></textarea></td>
                                        </tr>
                                        <tr>
                                            <td>Side Effects</td>
                                            <td><textarea class="form-control" name="drugSideEffect" id="drugSideEffect" rows="4"></textarea></td>
                                        </tr>

                                        </tr>

                                        </tbody>
                                    </table>

                                    <div class="pull-right">
                                        <button type="submit" class="btn btn-primary">Add To Catalogue</button>
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

</t:pharmacistPage>