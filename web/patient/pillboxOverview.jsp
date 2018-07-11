<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:patientPage>
    <h1>Pillbox overview</h1>

    <div class="row">
        <button type="button" class="btn btn-success btn-block btn-lg" onclick="openAddModal()">Add new medication</button>
    </div>
    <div class="row">

        <table id="pillboxTable" class="table table-striped table-bordered">
            <tbody id="pillboxListContent">

            </tbody>
        </table>
    </div>


    <table>
        <tbody>

        <c:forEach items="${getList}" var="getLists">
            <tr>
                <td>${getLists.inventory_id}</td>
                <td>${getLists.drugintake_id}</td>
                <td>${getLists.drugphase_id}</td>
                <td>${getLists.inventory_balance}</td>
                <td>${getLists.inventory_days}</td>
                <td>${getLists.inventory_startdate}</td>
                <td>${getLists.inventory_status}</td>
                <td>${getLists.drug_ID}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <!-- editModal -->
    <div id="editModal" class="modal">
        <div class="modal-content">
            <h1>Edit Entry</h1>
            <br>
            <form action="http://localhost:8080/patient/editPillbox" method="post">
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-4">
                        <select name="editDrugName" id="editDrugName"></select>
                    </div>
                    <label class="col-sm-2 col-form-label">Total Quantity</label>
                    <div class="col-sm-4">
                        <input type="number" class="form-control" id="editDrugQuantity" name="editDrugQuantity" min="1" max="60">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Dose</label>
                    <div class="col-sm-4">
                        <input type="number" class="form-control" name="editDrugDose" id="editDrugDose"min="1" max="10">
                    </div>
                    <label class="col-sm-2 col-form-label">Meals</label>
                    <div class="col-sm-4">
                        <select name="editDrugMeals" id="editDrugMeals">
                            <option value="1">Before Meal</option>
                            <option value="2">After Meal</option>
                            <option value="3">Meal Independent</option>
                        </select>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Frequency</label>
                    <div class="col-sm-4">
                        <select name="editDrugFrequency" id="editDrugFrequency">
                            <option value="1">1 time</option>
                            <option value="2">2 times</option>
                            <option value="3">3 times</option>
                            <option value="4">4 times</option>
                            <option value="5">5 times</option>
                            <option value="6">6 times</option>
                        </select>
                    </div>
                    <label class="col-sm-2 col-form-label">Interval</label>
                    <div class="col-sm-4">
                        <select name="editDrugInterval" id="editDrugInterval">
                            <option value="1">Daily</option>
                            <option value="2">Weekly</option>
                            <option value="3">Fortnightly</option>
                            <option value="4">Monthly</option>
                            <option value="5">Yearly</option>
                            <option value="6">Custom</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Instructions</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="editDrugInstructions" id="editDrugInstructions" disabled>
                    </div>
                    <label class="col-sm-2 col-form-label">Strictness</label>
                    <div class="col-sm-4">
                        <select name="editDrugStrictness" id="editDrugStrictness" >
                            <option value="true">Compulsory</option>
                            <option value="false">If needed</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Start Date</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="editDrugStartDate" id="editDrugStartDate" disabled>
                    </div>
                </div>
                <br>
                <div class="form-group row">
                    <div class="col-sm-6">
                        <button type="submit" class="btn btn-success btn-block btn-lg" onclick="">Submit</button>
                    </div>
                    <div class="col-sm-6">
                        <button type="button" class="btn btn-default btn-block btn-lg"  onclick="closeEditModal()">Cancel</button>
                    </div>
                </div>
            </form>

        </div>
    </div>

    <!-- addModal -->
    <div id="addModal" class="modal">
        <div class="modal-content">
            <h1>Add Entry</h1>
            <br>
            <form action="http://localhost:8080/patient/addToPillbox" method="post">
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-4">
                        <select name="addDrugName" id="addDrugName"></select>
                    </div>
                    <label class="col-sm-2 col-form-label">Total Quantity</label>
                    <div class="col-sm-4">
                        <input type="number" class="form-control" name="addDrugQuantity" min="1" max="60" value="9">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Dose</label>
                    <div class="col-sm-2">
                        <input type="number" class="form-control" name="addDrugDose" min="0.25" max="10" step="0.25" value="2">
                    </div>
                    <div class="col-sm-2">
                        <select name="addDrugForm" id="addDrugForm"></select>
                    </div>
                    <label class="col-sm-2 col-form-label">Meals</label>
                    <div class="col-sm-4">
                        <select name="addDrugMeals">
                            <option value="1">Before Meal</option>
                            <option value="2">After Meal</option>
                            <option value="3">Meal Independent</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Instructions</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="addDrugInstructions" placeholder="">
                    </div>
                    <label class="col-sm-2 col-form-label">Strictness</label>
                    <div class="col-sm-4">
                        <select name="addDrugStrictness">
                            <option value="true">Compulsory</option>
                            <option value="false">If needed</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Frequency</label>
                    <div class="col-sm-4">
                        <select name="addDrugFrequency" id="addDrugFrequency">
                            <option value="8">Every Morning</option>
                            <option value="14">Every Afternoon</option>
                            <option value="20">Every Night</option>
                            <option value="22">At Bedtime</option>
                            <option value="8,20">2 times a day</option>
                            <option value="8,14,20">3 times a day</option>
                            <option value="8,12,16,20">4 times a day</option>
                            <option value="7,10,13,16,19">5 times a day</option>
                            <option value="7,10,13,16,19,22">6 times a day</option>
                        </select>
                    </div>
                    <label class="col-sm-2 col-form-label">Interval</label>
                    <div class="col-sm-4">
                        <select name="addDrugInterval" onchange="toggleDayOfWeek(this);">
                            <option value="1">Daily</option>
                            <option value="2">Custom</option>
                        </select>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Start Date</label>
                    <div class="col-sm-4">
                        <input type="date" name="addDrugStartDate" id="addDrugStartDate">
                    </div>
                    <div class="col-sm-6">
                        <table id="addDaySelector" style="text-align: center; table-layout: fixed; width: 100%; display:none;">
                            <tr><td>Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td><td>Sun</td></tr>
                            <tr>
                                <td><input type="checkbox" name="addDayOfWeek1" value="1"> </td>
                                <td><input type="checkbox" name="addDayOfWeek2" value="1"> </td>
                                <td><input type="checkbox" name="addDayOfWeek3" value="1"> </td>
                                <td><input type="checkbox" name="addDayOfWeek4" value="1"> </td>
                                <td><input type="checkbox" name="addDayOfWeek5" value="1"> </td>
                                <td><input type="checkbox" name="addDayOfWeek6" value="1"> </td>
                                <td><input type="checkbox" name="addDayOfWeek7" value="1"> </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <br>
                <div class="form-group row">
                    <div class="col-sm-6">
                        <button type="submit" class="btn btn-success btn-block btn-lg" onclick="">Submit</button>
                    </div>
                    <div class="col-sm-6">
                        <button type="button" class="btn btn-default btn-block btn-lg"  onclick="closeAddModal()">Cancel</button>
                    </div>
                </div>
            </form>

        </div>
    </div>

    <div id="deleteModal" class="modal">
        <div class="modal-content">
            <h2>Are you sure you want to delete this item?</h2><br>
            <div class="row">
                <div class="col-sm-6">
                    <button type="button" class="btn btn-danger btn-block btn-lg" onclick="deleteFromPillbox()">Delete</button>
                </div>
                <div class="col-sm-6">
                    <button type="button" class="btn btn-default btn-block btn-lg"  onclick="closeDeleteModal()">Cancel</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        var pillboxlist;
        var deleteID;

        // Get the modal
        var addModal = document.getElementById('addModal');
        var editModal = document.getElementById('editModal');
        var deleteModal = document.getElementById('deleteModal');

        //Show/hide dayofweek selector
        function toggleDayOfWeek(sel)
        {
            if(sel.value.toString()==="2"){
                $("#addDaySelector").show();
            }
            else{
                $("#addDaySelector").hide();
            }
        }

        // Get the button that opens the modal
        function openAddModal() {
            addModal.style.display = "block";
        }
        function openEditModal(id) {
            editModal.style.display = "block";
            var item = pillboxlist[parseInt(id)];
            console.log(item);
            $('#editDrugName').empty().append($('<option>', {
                value: item.inventory_ID,
                text : item.drug_name
            }));
            $('#editDrugQuantity').val(item.inventory_balance);
            $('#editDrugDose').val(item.inventory_balance);
            $('#editDrugMeals').val(item.drugintake_ID);

            $('#editDrugFrequency').val(item.frequency);

            $('#editDrugInterval').val(item.drugphase_ID);

            $('#editDrugInstructions').val(item.instructions);
            if(item.strictness){
                $('#editDrugStrictness').empty().append($('<option>', {
                    text: "Compulsory"
                }));
            }
            else {
                $('#editDrugStrictness').empty().append($('<option>', {
                    text: "If needed"
                }));
            }
            $('#editDrugStartDate').val(item.inventory_startdate);


        }
        function openDeleteModal(id) {
            deleteID = id;
            deleteModal.style.display = "block";
        }
        function deleteFromPillbox() {
            console.log(deleteID);
            $.post( "http://localhost:8080/patient/deleteFromPillbox", {id: deleteID})
                .then(location.reload());
        }

        // When the user clicks on <span> (x), close the modal
        function closeAddModal() {
            addModal.style.display = "none";
        }
        function closeEditModal() {
            editModal.style.display = "none";
        }
        function closeDeleteModal() {
            deleteModal.style.display = "none";
        }
        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == addModal) {
                addModal.style.display = "none";
            }
            if (event.target == editModal) {
                editModal.style.display = "none";
            }
            if (event.target == deleteModal) {
                deleteModal.style.display = "none";
            }
        }

        function setDateToday(){
            var now = new Date();
            var day = ("0" + now.getDate()).slice(-2);
            var month = ("0" + (now.getMonth() + 1)).slice(-2);
            var today = now.getFullYear()+"-"+(month)+"-"+(day);
            $('#addDrugStartDate').val(today);
        }

        $(document).ready(function(){
            setDateToday();


            $.get( "http://localhost:8080/patient/getMedicationNames" ).then(
                function(data,status) {
                    $.each(JSON.parse(data), function (i, item) {
                        $('#addDrugName').append($('<option>', {
                            value: item.drug_ID,
                            text : item.drug_name
                        }));
                    });
                }
            );

            $.get( "http://localhost:8080/patient/getMedicationForms" ).then(
                function(data,status) {
                    $.each(JSON.parse(data), function (i, item) {
                        $('#addDrugForm').append($('<option>', {
                            value: item.medicineform_ID,
                            text : item.medicineform_name
                        }));
                    });
                }
            );

            $.get( "http://localhost:8080/patient/listPillbox" ).then(
                function(data,status) {
                    pillboxlist = JSON.parse(data);
                    $.each(pillboxlist, function (i, item) {
                        console.log(item["today"]);
                        $("#pillboxTable").find('tbody')
                            .append($('<tr>')
                                .append($('<td>')
                                    .append($('<img>')
                                        .attr('src', 'https://img.tesco.com/Groceries/pi/718/5000158100718/IDShot_540x540.jpg')
                                        .height('150px')
                                    )
                                )
                                .append($('<td>')
                                    .append("<div class='pillbox'>")
                                    .append("<h2>"+item['drug_name']+"<small> phase "+item['drugphase_term']+"</small></h2>")
                                    .append("<h4>Balance: "+item['inventory_balance']+" pills</h4>")
                                    .append("<p>Side Effects: "+item['drug_side_effect']+" pills</p>")
                                    .append("</div>")
                                )
                                .append($('<td>')
                                    .append("<div class='pillbox'>")
                                    .append("<button type='button' class='btn btn-info btn-block btn-lg' onclick='openEditModal("+item['inventory_ID']+")'>Edit</button>")
                                    .append("<button type='button' class='btn btn-danger btn-block btn-lg' onclick='openDeleteModal("+item['inventory_ID']+")'>Delete</button>")
                                )
                            );
                    });
                }
            );

        });
    </script>

</t:patientPage>
