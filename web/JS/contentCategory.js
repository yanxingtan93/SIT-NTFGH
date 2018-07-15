(function($) {
    $(document).ready(function() {               // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
        $.get("/ContentCategoryServlet", function(responseJson) {                 // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
            var $select = $("#content-category");                           // Locate HTML DOM element with ID "someselect"
            $.each(responseJson, function(key,value) {               // Iterate over the JSON object.
                $("<option>").val(value).text(value).appendTo($select); // Create HTML <option> element, set its value with currently iterated key and its text content with currently iterated item and finally append it to the <select>.
            });
        });
    });
})(jQuery)