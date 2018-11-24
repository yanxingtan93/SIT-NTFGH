
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../JS/medicineForm.js"></script>


<t:adminPage>

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
                                <form method="post" action="/GeneralSettingsServlet">
                                    <input type="hidden" class="form-control" name="route" id="route" value="SaveIntake" >
                                    <input type="hidden" class="form-control" name="formID" id="formID" value="" >
                                    <table class="table table-user-information">


                                        <tbody>
                                        <tr>
                                            <td>Medicine Intake:</td>
                                            <td><input type="text" class="form-control" name="form_name" id="form_name"  placeholder="Enter Term" required></td>
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
</t:adminPage>




<script>



    $(document).ready(function() {

        var formID = $('#formID');
        var name = $('#form_name');


        var id = getParameterByName("xFormID");
        console.log("id is:" + id);

        $.get("/GeneralSettingsServlet?mode=medForm&id="+id+"&type=3", function(responseJson) {

                formID.val(id)
                name.val(responseJson);



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
