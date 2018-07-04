// (function($) {
//     $(document).ready(function() {               // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
//         $.get("/preorderFormServlet", function(responseJson) {                 // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
//             var $select = $("#medication-Preorder");                           // Locate HTML DOM element with ID "someselect"
//             $.each(responseJson, function(key, value) {               // Iterate over the JSON object.
//                 $("<option>").val(key).text(value).appendTo($select); // Create HTML <option> element, set its value with currently iterated key and its text content with currently iterated item and finally append it to the <select>.
//             });
//         });
//     });
// })(jQuery)

$(function() {
    $( "#search" ).autocomplete({
        source: function (request, response) {
            $.ajax({
                url: "/preoderFormServlet",
                dataType: 'json',
                data: request,
                success: function (data) {
                    if (typeof Array.prototype.forEach != 'function') {
                        Array.prototype.forEach = function(callback){
                            for (var i = 0; i < this.length; i++){
                                callback.apply(this, [this[i], i, this]);
                            }
                        };
                    }

                    var values = data;
                    var newArray = new Array(values.length);
                    var i = 0;
                    values.forEach(function (entry) {
                        var newObject = {
                            label: entry.name
                        };
                        newArray[i] = newObject;
                        i++;
                    });

                    response(newArray);
                }
            });
        },
        minLength: 1
    });
});