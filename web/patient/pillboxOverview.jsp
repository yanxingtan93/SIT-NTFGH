<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<t:patientPage>
    <h1>Pillbox overview</h1>

    <div class="row">
        <button type="button" class="btn btn-success btn-block btn-lg" onclick="openAddModal()">Add new medication</button>
    </div>
    <div class="row">

        <table id="pillboxTable" class="table table-striped table-bordered">
            <tbody>
            <tr>
                <td style="width: 15%">
                    <img src="https://img.tesco.com/Groceries/pi/718/5000158100718/IDShot_540x540.jpg" height="150px">
                </td>
                <td style="width: 60%">
                    <div class="pillbox">

                        <h2>Ibuprofen  <small>(1 pill 3 times a day)</small></h2>
                        <h4>Balance: 15 pills</h4>
                    </div>
                </td>
                <td style="width: 25%">
                    <button type="button" class="btn btn-info btn-block btn-lg" onclick="openEditModal(0)">Edit</button>
                    <button type="button" class="btn btn-danger btn-block btn-lg" onclick="openDeleteModal(0)">Delete</button>
                </td>
            </tr>
            <tr>
                <td style="width: 15%">
                    <img src="https://img.tesco.com/Groceries/pi/718/5000158100718/IDShot_540x540.jpg" height="150px">
                </td>
                <td style="width: 60%">
                    <div class="pillbox">

                        <h2>Ibuprofen  <small>(1 pill 3 times a day)</small></h2>
                        <h4>Balance: 15 pills</h4>
                    </div>
                </td>
                <td style="width: 25%">
                    <button type="button" class="btn btn-info btn-block btn-lg" onclick="openEditModal(1)">Edit</button>
                    <button type="button" class="btn btn-danger btn-block btn-lg" onclick="openDeleteModal(1)">Delete</button>
                </td>
            </tr>
            <tr>
                <td style="width: 15%">
                    <img src="https://img.tesco.com/Groceries/pi/718/5000158100718/IDShot_540x540.jpg" height="150px">
                </td>
                <td style="width: 60%">
                    <div class="pillbox">

                        <h2>Ibuprofen  <small>(1 pill 3 times a day)</small></h2>
                        <h4>Balance: 15 pills</h4>
                    </div>
                </td>
                <td style="width: 25%">
                    <button type="button" class="btn btn-info btn-block btn-lg" onclick="openEditModal(2)">Edit</button>
                    <button type="button" class="btn btn-danger btn-block btn-lg"  onclick="openDeleteModal(3)">Delete</button>
                </td>
            </tr>
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
            <form>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="editDrugName" placeholder="">
                    </div>
                    <label class="col-sm-2 col-form-label">Total Quantity</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="editDrugQuantity" placeholder="">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Dose</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="editDrugDose" placeholder="">
                    </div>
                    <label class="col-sm-2 col-form-label">Meals</label>
                    <div class="col-sm-4">
                        <select name="meals">
                            <option value="1">- NIL -</option>
                            <option value="2">Before Food</option>
                            <option value="3">After Food</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Interval</label>
                    <div class="col-sm-4">
                        <select name="numberOfTimes">
                            <option value="1">1 time</option>
                            <option value="2">2 times</option>
                            <option value="3">3 times</option>
                            <option value="4">4 times</option>
                            <option value="5">5 times</option>
                            <option value="6">6 times</option>
                        </select>
                    </div>
                    <label class="col-sm-2 col-form-label">Per</label>
                    <div class="col-sm-4">
                        <select name="intervalUnit">
                            <option value="1">Day</option>
                            <option value="2">Week</option>
                            <option value="3">2 Weeks</option>
                            <option value="4">Month</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Start Date</label>
                    <div class="col-sm-4">
                        <input type="date" name="startDate">
                    </div>
                    <label class="col-sm-2 col-form-label">End Date</label>
                    <div class="col-sm-4">
                        <input type="date" name="endDate">
                    </div>
                </div>
                <br>
                <div class="form-group row">
                    <div class="col-sm-6">
                        <button type="button" class="btn btn-success btn-block btn-lg" onclick="">Save</button>
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
            <form>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-4">
                        <select name="addDrugName" id="addDrugName"></select>
                    </div>
                    <label class="col-sm-2 col-form-label">Total Quantity</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="addDrugQuantity" placeholder="">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Dose</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="addDrugDose" placeholder="">
                    </div>
                    <label class="col-sm-2 col-form-label">Meals</label>
                    <div class="col-sm-4">
                        <select name="meals">
                            <option value="1">- NIL -</option>
                            <option value="2">Before Food</option>
                            <option value="3">After Food</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Interval</label>
                    <div class="col-sm-4">
                        <select name="numberOfTimes">
                            <option value="1">1 time</option>
                            <option value="2">2 times</option>
                            <option value="3">3 times</option>
                            <option value="4">4 times</option>
                            <option value="5">5 times</option>
                            <option value="6">6 times</option>
                        </select>
                    </div>
                    <label class="col-sm-2 col-form-label">Per</label>
                    <div class="col-sm-4">
                        <select name="intervalUnit">
                            <option value="1">Day</option>
                            <option value="2">Week</option>
                            <option value="3">2 Weeks</option>
                            <option value="4">Month</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Start Date</label>
                    <div class="col-sm-4">
                        <input type="date" name="startDate">
                    </div>
                    <label class="col-sm-2 col-form-label">End Date</label>
                    <div class="col-sm-4">
                        <input type="date" name="endDate">
                    </div>
                </div>
                <br>
                <div class="form-group row">
                    <div class="col-sm-6">
                        <button type="button" class="btn btn-success btn-block btn-lg" onclick="">Submit</button>
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
                    <button type="button" class="btn btn-danger btn-block btn-lg" onclick="">Delete</button>
                </div>
                <div class="col-sm-6">
                    <button type="button" class="btn btn-default btn-block btn-lg"  onclick="closeDeleteModal()">Cancel</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Get the modal
        var addModal = document.getElementById('addModal');
        var editModal = document.getElementById('editModal');
        var deleteModal = document.getElementById('deleteModal');

        // Get the button that opens the modal
        function openAddModal() {
            addModal.style.display = "block";
        }
        function openEditModal(id) {
            editModal.style.display = "block";
        }
        function openDeleteModal(id) {
            deleteModal.style.display = "block";
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
    </script>
    <script>
        $(document).ready(function(){
            $.get("http://localhost:8080/patient/getMedicationNames", function(data, status){
                $.each(JSON.parse(data), function (i, item) {
                    $('#addDrugName').append($('<option>', {
                        value: item,
                        text : item
                    }));
                });
            });
        });
    </script>
</t:patientPage>
