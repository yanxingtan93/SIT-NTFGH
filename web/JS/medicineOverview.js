var mytable = $('#myMainTable').DataTable({
    "aLengthMenu": [[5, 50, 75, -1], [5, 50, 75, "All"]],
    "iDisplayLength": 5,
    "paging": true,
    "lengthChange": false,
    "searching": true,
    "ordering": true,
    "info": true,
    "autoWidth": false,
    "sDom": 'lfrtip'

});
$(document).ready(function() {
    $.get("/drugCatalogueServlet?route=all", function(responseJson) {


        $.each(responseJson, function(key,value) {
            var button = "\n" +

                "  <form method=\"post\" action=\"/pharmacist/medicationEdit.jsp?xmedID="+value.id+" \">\n" +
                "                    <input type=\"hidden\" class=\"form-control\" name=\"mode\" value=\"Edit\">\n" +
                "                    <input type=\"hidden\" class=\"form-control\" name=\"xmedID\"  value="+value.id+">\n" +
                "                    <button type=\"submit\" class=\"btn btn-success\">Edit</button>\n" +
                "                </form>\n" +
                "                    <form method=\"post\" action=\"/drugCatalogueServlet\">\n" +
                "                        <input type=\"hidden\" class=\"form-control\" name=\"route\"  value=\"Delete\">\n" +
                "                        <input type=\"hidden\" class=\"form-control\" name=\"drugid\"  value="+value.id+">\n" +
                "                    <button type=\"submit\" class=\"btn btn-danger\">Delete</button>\n" +
                "                    </form>";


            mytable.row.add([value.id, value.medicineName, value.brand, value.medicineForm, value.description, value.sideEffect,button]);


        });
        mytable.draw();
    });

});


