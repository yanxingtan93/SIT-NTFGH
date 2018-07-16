<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">


<t:caregiverPage>
    <h1> <i class="fa fa-calendar-check"></i>&nbsp Reminders</h1>
    <div class="row">

        <table id="todayTable" class="table table-striped table-bordered">

            <tbody>


            </tbody>
        </table>
    </div>

    <h1><i class="fas fa-medkit"></i>&nbsp Pillbox</h1>
    <div class="row">
        <button type="button" class="btn btn-success btn-block btn-lg" onclick="openAddModal()">Add new medication</button>
    </div>
    <div class="row">

        <table id="pillboxTable" class="table table-striped table-bordered">

            <tbody>

            </tbody>
        </table>
    </div>

    <div id="recordModal" class="modal">
        <div class="modal-content">
            <h2>Please confirm.</h2><br>
            <div class="row">
                <div class="col-sm-6">
                    <button type="button" class="btn btn-success btn-block btn-lg" onclick="recordConsumption()">Confirm</button>
                </div>
                <div class="col-sm-6">
                    <button type="button" class="btn btn-default btn-block btn-lg"  onclick="closeRecordModal()">Cancel</button>
                </div>
            </div>
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
                        <input type="number" class="form-control" name="addDrugQuantity" min="1" max="1000" value="9" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Dose</label>
                    <div class="col-sm-2">
                        <input type="number" class="form-control" name="addDrugDose" min="0.25" max="200" step="0.25" value="2" required>
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
                        <input type="hidden" id="userID" name="userID" value="${sessionScope.userID}">
                        <button type="submit" class="btn btn-success btn-block btn-lg" onclick="">Submit</button>
                    </div>
                    <div class="col-sm-6">
                        <button type="button" class="btn btn-default btn-block btn-lg"  onclick="closeAddModal()">Cancel</button>
                    </div>
                </div>
            </form>

        </div>
    </div>

    <!-- editModal
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
                        <input type="number" class="form-control" id="editDrugQuantity" name="editDrugQuantity" disabled>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Dose</label>
                    <div class="col-sm-2">
                        <input type="number" class="form-control" name="editDrugDose" id="editDrugDose" disabled>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="editDrugForm" id="editDrugForm" disabled>
                    </div>
                    <label class="col-sm-2 col-form-label">Meals</label>
                    <div class="col-sm-4">
                        <select name="editDrugMeals" id="editDrugMeals">
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
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Frequency</label>
                    <div class="col-sm-4">
                        <select name="editDrugFrequency" id="editDrugFrequency" disabled>
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
                        <select name="editDrugInterval" id="editDrugInterval" disabled >
                            <option value="1">Daily</option>
                            <option value="2">Custom</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Instructions</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="editDrugInstructions" id="editDrugInstructions">
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
    -->
    <!-- deleteModal-->
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
        var validAcc1 = "${sessionScope.patientID}";
        var userID = validAcc1;
        $('#userID').val(userID);
    </script>
    <script src ="../JS/pillboxOverview.js"></script>
</t:caregiverPage>
