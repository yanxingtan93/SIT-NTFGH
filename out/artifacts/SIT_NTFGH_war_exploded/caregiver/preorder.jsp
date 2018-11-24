<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<script src="../JS/preorderForm.js"></script>


<t:caregiverPage>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/base/jquery-ui.css"/>


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
    <br><h1><i class="fa fa-money"></i> Current Preorder(s)</h1><br>
    <div class="row">
        <table id="myMainTable" class="dailyMedTable table table-striped table-bordered">
            <thead class="thead-dark">
            <tr>

                <th style="width:10%">Preorder ID</th>
                <th style="width:15%">Method of Collection</th>
                <th style="width:15%">Collection Date</th>
                <th style="width:15%">Status</th>
                <th style="width:15%">View</th>
            </tr>
            </thead>
            <div id ="pillbox-table" class="pillbox">

            </div>
        </table>
    </div>
    <br><h1><i class="fa fa-plus-square"></i> New Preorder</h1><br>
    <form action="/preorderServlet" method="post">
        <div id="collectionMode" class="form-group row">
            <label class="col-sm-2 col-form-label">Preferred Collection Method</label>
            <div class="col-sm-3" id="radiobtn">
                <label class="radio-inline"><input type="radio" name="method" id="collection" value="Self-Collection"> Self-Collection</label><br>
                <label class="radio-inline"><input type="radio" name="method" id="delivery" value="Home/ Office Delivery" checked="checked"> Home/ Office Delivery</label><br>
            </div>

        </div>
        <br>
        <div id= "medicationRow" class="form-group row">
            <div class="col-sm-12">
                <div class="input_fields_wrap">
                    <div>
                        <input type="hidden" class="form-control" name="action" value="Form">
                        <input type="hidden" id="role" name="role" value="Caregiver">
                        <div class="row">
                            <label class="col-sm-2 col-form-label">Medication</label>
                            <div class="col-sm-4">
                                <input type="text" id='medication-Preorder' name="medicationPreorder" autocomplete="off" required>
                            </div>
                            <label class="col-sm-2 col-form-label">Total Quantity</label>
                            <div class="col-sm-2">
                                <input type="number" name="quantity" min="1" max="999" required>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <br>
        <div class="col-sm-2">
            <button class="add_field_button">Add Medication</button>
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
        $(document).ready(function () {
            $("#medication-Preorder").autocomplete({
                source: function(request, response) {
                    $.ajax({
                        url: "/preorderFormServlet?mode=getPatientSelect",
                        dataType: "json",
                        data: { term: request.term },
                        success: function (data) {
                            var tag_val = $("#medication-Preorder").val().toLowerCase();
                            response($.map(data, function (item) {

                                //filtering results....
                                if (item.drugname.toLowerCase().indexOf(tag_val) != -1) {
                                    return {
                                        label: item.drugname,
                                        name: item.drugname,
                                    };
                                }
                            }));
                        }
                    });
                }
            });
        });

        $(document).ready(function() {               // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
            $.get("/preorderFormServlet?mode=getPatientSelect", function(responseJson) {                 // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
                var $select = $("#medication-Preorder");                           // Locate HTML DOM element with ID "someselect"
                $.each(responseJson, function(key, value) {               // Iterate over the JSON object.
                    $("<option>").val(key).text(value).appendTo($select); // Create HTML <option> element, set its value with currently iterated key and its text content with currently iterated item and finally append it to the <select>.
                });
            });
        });


        $(document).ready(function() {
            var max_fields      = 10; //maximum input boxes allowed
            var wrapper         = $(".input_fields_wrap"); //Fields wrapper
            var add_button      = $(".add_field_button"); //Add button ID

            var x = 1; //initlal text box count
            $(add_button).click(function(e){ //on add input button click

                e.preventDefault();
                if(x < max_fields){ //max input box allowed
                    x++; //text box increment
                    $(wrapper).append('<div class="row">\n' +
                    '                            <label class="col-sm-2 col-form-label">Medication</label>\n' +
                    '                            <div class="col-sm-4">\n' +
                    '                                <input type="text" id="medication-Preorder'+x+'" name="medicationPreorder" autocomplete="off" required>\n' +
                    '                            </div>\n' +
                    '                            <label class="col-sm-2 col-form-label">Total Quantity</label>\n' +
                    '                            <div class="col-sm-2">\n' +
                    '                                <input type="number" name="quantity" min="1" max="999" required>\n' +
                    '                            </div>\n' +
                    '                            <a href="#" class="remove_field"> Remove</a></div>'); //add input box
                }

                $("#medication-Preorder"+x).autocomplete({
                    source: function(request, response) {
                        $.ajax({
                            url: "/preorderFormServlet?mode=getPatientSelect",
                            dataType: "json",
                            data: { term: request.term },
                            success: function (data) {
                                var tag_val = $("#medication-Preorder"+x).val().toLowerCase();
                                response($.map(data, function (item) {

                                    //filtering results....
                                    if (item.drugname.toLowerCase().indexOf(tag_val) != -1) {
                                        return {
                                            label: item.drugname,
                                            name: item.drugname,
                                        };
                                    }
                                }));
                            }
                        });
                    }
                });
            });

            $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
                e.preventDefault(); $(this).parent('div').remove(); x--;
            })

        });

        $(document).ready(function(){
            $("input[type='radio']").change(function(){
                var collection = $("input[id='collection']:checked").val();
                if(collection){
                    $("<div id=\"toshow\" class=\"col-sm-6\">\n" +
                        "                <label class=\"col-sm-4 col-form-label\">Collection Date</label>\n" +
                        "                <input type=\"text\" id=\"datepicker\" name=\"date\" autocomplete=\"off\" required>\n" +
                        "            </div>").insertAfter('#radiobtn');
                    $( "#datepicker" ).datepicker({ minDate: +1, maxDate: "+1M +10D" });
                }
                else {
                    $('#toshow').remove();
                }
            });
        });



        $(document).ready(function() {
            var acc = "${sessionScope.patientID}";
            $.get("/preorderServlet?mode=getPatient", function(responseJson) {
                var $table = $('<tbody>').appendTo($('#myMainTable'));
                $.each(responseJson, function(key,value) {
                        console.log(key);
                        $('<tr>').appendTo($table)
                            .append($('<td>').text(value.preorder_ID))
                            .append($('<td>').text(value.preorder_mode))
                            .append($('<td>').text(value.collection_date))
                            .append($('<td>').text(value.status))

                            .append($('<td>')
                                .append("<form method=\"post\" action=\"/preorderServlet\">\n" +
                                    "                <input type=\"hidden\" class=\"form-control\" name=\"action\" value=\"View\">\n" +
                                    "                <input type=\"hidden\" class=\"form-control\" name=\"preorder_ID\" value="+value.preorder_ID+">\n" +
                                    "                <button type=\"submit\" class=\"btn btn-info btn-block\">View</button>\n" +
                                    "                <form>")
                            )
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
    </script>
</t:caregiverPage>