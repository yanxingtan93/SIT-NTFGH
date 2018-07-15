var mytable = $('#myMainTable').DataTable({
    "aLengthMenu": [[5, 50, 75, -1], [5, 50, 75, "All"]],
    "iDisplayLength": 15,
    "paging": true,
    "lengthChange": false,
    "searching": true,
    "ordering": true,
    "info": true,
    "autoWidth": false,
    "sDom": 'lfrtip'

});
$(document).ready(function() {
    $.get("/pharmacistPreorderServlet?mode=get", function(responseJson) {
        $.each(responseJson, function(key,value) {
            var button = "\n" +
                "  <form method=\"post\" action=\"/pharmacist/preorderEdit.jsp?preorderID="+value.preorder_ID+" \">\n" +
                "                    <input type=\"hidden\" class=\"form-control\" name=\"mode\" value=\"Edit\">\n" +
                "                    <input type=\"hidden\" class=\"form-control\" name=\"medID\"  value="+value.preorder_ID+">\n" +
                "                    <button type=\"submit\" class=\"btn btn-success\">Edit</button>\n" +
                "                </form>\n";
            mytable.row.add([value.preorder_ID, value.user_NRIC, value.preorder_mode, value.collection_date, value.status,button]);
        });
        mytable.draw();
    });

});