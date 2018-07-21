
var pillboxlist;
var deleteID;
var recordID;
var inventoryID;
var remainder;

//=============== On Load jQuery ===============
$(document).ready(function(){
    setDateToday();

    //=============== Get Medication Names, Values for Add Form ===============
    $.get( "/patient/getMedicationNames" )
        .then(
            function(data,status) {
                $.each(JSON.parse(data), function (i, item) {
                    $('#addDrugName').append($('<option>', {
                        value: item.drug_ID,
                        text : item.drug_name
                    }));
                });
            }
        );
    //=============== Get Medication Type, Value for Add Form ===============
    $.get( "/patient/getMedicationForms" )
        .then(
            function(data,status) {
                $.each(JSON.parse(data), function (i, item) {
                    $('#addDrugForm').append($('<option>', {
                        value: item.medicineform_ID,
                        text : item.medicineform_name
                    }));
                });
            }
        );

    //=============== Get today's medication and pillbox in a Map {today:[], pillbox:[]} ===============
    $.post( "/patient/listPillbox", {userID: userID})
        .then(
            function(data,status) {
                pillboxlist = JSON.parse(data);
                console.log(pillboxlist);


                var today = sortByKey(pillboxlist["today"], 'reminder_time');
                var pillbox = pillboxlist["pillbox"];
                if(today.length<1){
                    $("#todayTable").find('tbody')
                        .append($('<th>')
                            .append("<strong>You have no medications left for today!</strong>")
                        );
                }
                else{
                    $("#todayTable").find('tbody')
                        .append($('<tr>')
                            .append("<th style='width:20%'>Time</th>")
                            .append("<th style='width:60%'>Details</th>")
                            .append("<th style='width:20%' >Actions</th>")
                        )
                }
                $.each(today, function (i, item) {
                    var remainder = parseInt(item['inventory_balance']) - parseInt(item['dose']);
                    if (remainder<0){ remainder = 0;}
                    $("#todayTable").find('tbody')
                        .append($('<tr>')
                            .append($('<td>')
                                .append("<h2>"+item['reminder_time'].slice(11)+"</h2>")
                            )
                            .append($('<td>')
                                .append("<div class='pillbox'>")
                                .append("<h2>"+item['drug_name']+" - "+item['dose']+" "+item['medicineform_name']+"</h2>")
                                .append("<h4>"+item['drugintake_term']+"</h4>")
                                .append("<p>Side Effects: "+item['drug_side_effect']+"</p>")
                                .append("</div>")
                            )
                            .append($('<td>')
                                .append("<div class='pillbox'>")
                                .append("<button type='button' class='btn btn-success btn-block btn-lg' onclick='openRecordModal("+item['reminder_ID']+", "+remainder+","+item['inventory_ID']+")'>Record</button>")
                            )
                        );
                });
                $("#pillboxTable").find('tbody')
                    .append($('<tr>')
                        .append("<th style='width:20%'>Amount</th>")
                        .append("<th style='width:60%'>Details</th>")
                        .append("<th style='width:20%' >Actions</th>")
                    )
                $.each(pillbox, function (i, item) {
                    $("#pillboxTable").find('tbody')
                        .append($('<tr>')
                            .append($('<td>')
                                .append($('<div>')
                                    .append("<h3>Quantity: </h3>")
                                    .append("<h3>"+item['inventory_balance']+" "+item['medicineform_name']+"</h3>")
                                )
                            )
                            .append($('<td>')
                                .append("<div class='pillbox'>")
                                .append("<h2>"+item['drug_name']+"</h2>")
                                .append("<h3>Quantity: "+item['inventory_balance']+" "+item['medicineform_name']+"</h3>")
                                .append("<p>Start Date: "+item['inventory_startdate']+"</p>")
                                .append("</div>")
                            )
                            .append($('<td>')
                                .append("<div class='pillbox'>")
                                /*.append("<button type='button' class='btn btn-info btn-block btn-lg' onclick='openEditModal("+i+")'>Edit</button>")*/
                                .append("<button type='button' class='btn btn-danger btn-block btn-lg' onclick='openDeleteModal("+item['inventory_ID']+")'>Delete</button>")
                            )
                        );
                });
            }
        );

});


//=============== Utility Functions ===============
function toggleDayOfWeek(sel)
{
    if(sel.value.toString()==="2"){
        $("#addDaySelector").show();
    }
    else{
        $("#addDaySelector").hide();
    }
}
function setDateToday(){
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = now.getFullYear()+"-"+(month)+"-"+(day);
    $('#addDrugStartDate').val(today);
}
function sortByKey(array, key) {
    return array.sort(function(a, b) {
        var x = a[key]; var y = b[key];
        return ((x < y) ? -1 : ((x > y) ? 1 : 0));
    });
}

//=============== Modal Functions ===============
var addModal = document.getElementById('addModal');
var editModal = document.getElementById('editModal');
var deleteModal = document.getElementById('deleteModal');
var recordModal = document.getElementById('recordModal');

function openAddModal() {
    addModal.style.display = "block";
}
function closeAddModal() {
    addModal.style.display = "none";
}

/*
function openEditModal(id) {
    editModal.style.display = "block";
    var item = pillboxlist["pillbox"][parseInt(id)];
    console.log(item);
    $('#editDrugName').empty().append($('<option>', {
        value: item.inventory_ID,
        text : item.drug_name
    }));
    $('#editDrugQuantity').val(item.inventory_balance);
    $('#editDrugDose').val(item.dose);
    $('#editDrugMeals').val(item.drugintake_term);

    $('#editDrugFrequency').val(item.medicineform_name);

    $('#editDrugInterval').val(item.drugphase_ID);

    $('#editDrugInstructions').val(item.instructions);

    $('#editDrugStrictness').val(item.strictness);
    $('#editDrugStrictness').trigger("chosen:updated");

    $('#editDrugStartDate').val(item.inventory_startdate);


}
function closeEditModal() {
    editModal.style.display = "none";
}
*/
function openDeleteModal(id) {
    deleteID = id;
    deleteModal.style.display = "block";
}
function deleteFromPillbox() {
    console.log(deleteID);
    $.post( "/patient/deleteFromPillbox", {id: deleteID})
        .then(location.reload());
}
function closeDeleteModal() {
    deleteModal.style.display = "none";
}

function openRecordModal(id,dose,invId) {
    recordID = id;
    remainder = dose;
    inventoryID = invId;
    recordModal.style.display = "block";
}
function recordConsumption() {
    console.log(recordID);

    $.post( "/patient/recordConsumption", {id: recordID, remainder: remainder, inventoryID: inventoryID})
        .then(location.reload());
}
function closeRecordModal() {
    recordModal.style.display = "none";
}



// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == recordModal) {
        recordModal.style.display = "none";
    }
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
//=============== End of Modal Functions ===============





