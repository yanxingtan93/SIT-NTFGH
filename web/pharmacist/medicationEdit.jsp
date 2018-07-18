<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 3/6/18
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../JS/medicineForm.js"></script>


<t:pharmacistPage>

<br>
    <div class="container">
        <div class="row">

            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >


                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">Manage Drug</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">

                            <div class="col-md-3 col-lg-3 " align="center">
                                <br>
                                <img alt="User Pic" style="width: 50px;height: 50px" src="http://cdn.onlinewebfonts.com/svg/img_382491.png" class="img-circle img-responsive">
                            </div>


                            <div class=" col-md-9 col-lg-9 ">
                                <form method="post" action="/drugCatalogueServlet">
                                    <input type="hidden" class="form-control" name="route" id="route" value="Save" >
                                    <input type="hidden" class="form-control" name="drugid" id="drugid" value="" >
                                <table class="table table-user-information">


                                    <tbody>
                                    <tr>
                                        <td>Medicine Name:</td>
                                        <td><input type="text" class="form-control" name="drug_name" id="drug_name"  placeholder="Enter Medicine Name" required></td>
                                    </tr>
                                    <tr>
                                        <td>Brand:</td>
                                        <td><input type="text" class="form-control" name="drug_brand" id="drug_brand"  placeholder="Enter Brand" required></td>
                                    </tr>


                                    <tr>
                                    <tr>
                                        <td>Medicine Form</td>
                                        <td>
                                            <select class="form-control" id="medicine-form" name="medicineForm" required>

                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Description</td>
                                        <td><textarea class="form-control" name="drug_desc" id="drug_desc" rows="4" required></textarea></td>
                                    </tr>
                                    <tr>
                                        <td>Side Effects</td>
                                        <td><textarea class="form-control" name="drug_sideEffects" id="drug_sideEffects"  rows="4" required></textarea></td>
                                    </tr>

                                    </tr>

                                    </tbody>

                                </table>

                                <div class="pull-right">
                                    <button type="submit" class="btn btn-success">Save Changes</button>
                                </div>
                                </form>
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
</t:pharmacistPage>




<script>



    $(document).ready(function() {

        var drugid = $('#drugid');
        var name = $('#drug_name');
        var brand = $('#drug_brand');
        var desc = $('#drug_desc');
        var side = $('#drug_sideEffects');

        var id = getParameterByName("xmedID");
        console.log("id is:" + id);

        $.get("/drugCatalogueServlet?route=individual&id="+id, function(responseJson) {


            $.each(responseJson, function(key,value) {

                drugid.val(value.id)
               name.val(value.medicineName).trigger('change');
               brand.val(value.brand);
               desc.val(value.description);
                side.val(value.sideEffect);

            });

        });

    });

    function getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }
</script>
