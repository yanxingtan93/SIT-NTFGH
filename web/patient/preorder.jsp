<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../JS/preorderForm.js"></script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">

<t:patientPage>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


    <div class="row">
        <div class="col-sm-6">
            <h2>Self-Collection</h2>
            <p>
                Venue:<br>
                Ng Teng Fong General Hospital<br>
                Tower A, Level 2, Outpatient & Retail<br>
                Pharmacy<br><br>
                Operating Hours:<br>
                Monday - Friday: 8.30 am - 7 pm<br>
                Saturday: 8.30 am - 1.30 pm<br>
                Closed on Sundays / Public Holiday<br>
            </p>
        </div>
        <div class="col-sm-6">
            <h2>Delivery</h2>
            <p>
                Delivery Charge: $10.70<br><br>
                Delivery Time:<br>
                Monday - Friday: 9 am - 5pm <br>(excluding public holiday & eve of public holiday)<br><br><br>
                Pharmacy will contact you to arrange time.<br><br>
            </p>

        </div>
    </div>
    <br><h1>Current Preorder(s)</h1><br>
    <div class="row">
        <table id="myMainTable" class="dailyMedTable table table-striped table-bordered">
            <thead class="thead-dark">
            <tr>
                <th style="width:60%">Medication</th>
                <th style="width:15%">Quantity</th>
                <th style="width:25%">Collection Mode</th>
            </tr>
            </thead>
            <div id ="pillbox-table" class="pillbox">

            </div>
        </table>
    </div>
    <br><h1>New Preorder</h1><br>
    <form action="/preorderServlet" method="post">
        <div id="collectionMode" class="form-group row">
            <label class="col-sm-2 col-form-label">Preferred Collection Method</label>
            <div class="col-sm-3">
                <label class="radio-inline"><input type="radio" name="method" id="collection" value="Self-Collection"> Self-Collection</label><br>
                <label class="radio-inline"><input type="radio" name="method" id="delivery" value="Home/ Office Delivery"> Home/ Office Delivery</label><br>
            </div>
            <div id="toshow" class="col-sm-6" style="display:none">
                <label class="col-sm-4 col-form-label">Collection Date</label>
                <input type="text" id="datepicker">
            </div>
        </div>
        <br>
        <div id= "medicationRow" class="form-group row">
            <label class="col-sm-2 col-form-label">Medication</label>
            <div class="col-sm-4">
                <input type="text" id="search" name="search" class="search" placeholder="search here"/>
                    <%--<select class="form-control" id="medication-Preorder" name="medicationPreorder">--%>

                    <%--</select>--%>
            </div>
            <label class="col-sm-2 col-form-label">Total Quantity</label>
            <div class="col-sm-4">
                <input type="number" name="quantity" min="1" max="5">
            </div>
        </div>
        <br>
        <div class="col-sm-2">
            <button id="addRow" type="button" class="btn btn-infok">Add more medication</button>
        </div>
        <br>

        <div class="form-group row">
            <div class="col-sm-12">
                <button type="button" class="btn btn-success btn-block btn-lg" onclick="openConfirmModal(0)">Submit</button>
            </div>
        </div>


        <div id="confirmModal" class="modal">
            <div class="modal-content">
                <h2>Please confirm.</h2><br>
                <div class="row">
                    <div class="col-sm-6">
                        <button type="submit" class="btn btn-success btn-block btn-lg" onclick="">Confirm</button>
                    </div>
                    <div class="col-sm-6">
                        <button type="button" class="btn btn-default btn-block btn-lg"  onclick="closeConfirmModal()">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <script>
        $("#addRow").click(function () {
            $("<div id= \"medicationRow\" class=\"form-group row\">\n" +
                "          <label class=\"col-sm-2 col-form-label\">Medication</label>\n" +
                "          <div class=\"col-sm-4\">\n" +
                "              <select class=\"form-control\" id=\"medication-Preorder\" name=\"medicationPreorder\">\n" +
                "\n" +
                "              </select>\n" +
                "          </div>\n" +
                "          <label class=\"col-sm-2 col-form-label\">Total Quantity</label>\n" +
                "          <div class=\"col-sm-4\">\n" +
                "              <input type=\"number\" name=\"quantity\" min=\"1\" max=\"5\">\n" +
                "          </div>").insertAfter('#medicationRow')
            // $("#medicationRow").append("");
        });



        $(document).ready(function(){
            $("input[type='radio']").click(function(){
                var collection = $("input[id='collection']:checked").val();
                if(collection){
                    $("#toshow").show();
                }
                else {
                    $("#toshow").hide();
                }
            });
        });

        $(function() {
            $( "#datepicker" ).datepicker();
        } );


        $(document).ready(function() {
            $.get("/preorderServlet?mode=get", function(responseJson) {
                var $table = $('<tbody>').appendTo($('#myMainTable'));
                $.each(responseJson, function(key,value) {
                    $('<tr>').appendTo($table)
                        .append($('<td>').text(value.drugname))
                        .append($('<td>').text(value.quantity))
                        .append($('<td>').text(value.mode))

                });
            });

        });

        // Get the modal
        var confirmModal = document.getElementById('confirmModal');

        // Get the button that opens the modal
        function openConfirmModal(id) {
            confirmModal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        function closeConfirmModal() {
            confirmModal.style.display = "none";
        }
        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == confirmModal) {
                confirmModal.style.display = "none";
            }
        }
        //var selectedOption = $("input:radio[name=method]:checked").val()

        // $(function() {
        //     $( "#search" ).autocomplete({
        //         source: function (request, response) {
        //             $.ajax({
        //                 url: "preorderFormServlet",
        //                 dataType: 'json',
        //                 data: request,
        //                 success: function (data) {
        //                     if (typeof Array.prototype.forEach != 'function') {
        //                         Array.prototype.forEach = function(callback){
        //                             for (var i = 0; i < this.length; i++){
        //                                 callback.apply(this, [this[i], i, this]);
        //                             }
        //                         };
        //                     }
        //
        //                     var values = data;
        //                     var newArray = new Array(values.length);
        //                     var i = 0;
        //                     values.forEach(function (entry) {
        //                         var newObject = {
        //                             label: entry.name
        //                         };
        //                         newArray[i] = newObject;
        //                         i++;
        //                     });
        //
        //                     response(newArray);
        //                 }
        //             });
        //         },
        //         minLength: 1
        //     });
        // });

    </script>
</t:patientPage>
