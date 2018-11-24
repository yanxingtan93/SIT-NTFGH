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
    $.get("/contentServlet?route=all", function(responseJson) {


        $.each(responseJson, function(key,value) {
            var button = "\n" +
                "                    <form method=\"get\" action=\"/contentServlet\">\n" +
                "                        <input type=\"hidden\" class=\"form-control\" name=\"route\"  value=\"Delete\">\n" +
                "                        <input type=\"hidden\" class=\"form-control\" name=\"contentid\"  value="+value.id+">\n" +
                "                    <button type=\"submit\" class=\"btn btn-danger\">Delete</button>\n" +
                "                    </form>";
            console.log(value.conntentTitle);


            mytable.row.add([value.id,value.contentTitle, value.contentCategory,button]);


        });
        mytable.draw();
    });

});


