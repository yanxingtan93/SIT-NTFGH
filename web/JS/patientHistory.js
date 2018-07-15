var historylist;
$.post( "http://localhost:8080/patient/listHistory", {userID: userID})
    .then(
        function(data,status) {
            historylist = JSON.parse(data);
            var history = sortByKey(historylist, 'reminder_time');
            console.log(history);
            if(history.length<1){
                $("#historyTable").find('tbody')
                    .append($('<th>')
                        .append("<strong>You have no medications left for today!</strong>")
                    );
            }
            else{
                $("#historyTable").find('tbody')
                    .append($('<tr>')
                        .append("<th style='width:20%'>Time</th>")
                        .append("<th style='width:60%'>Details</th>")
                        .append("<th style='width:20%'>Status</th>")
                    )
            }
            $.each(historylist, function (i, item) {
                var consumptionStatus = "<h2 style='color:green;'>Consumed</h2>";
                if (item['consumed']==="0"){ consumptionStatus = "<h2 style='color:red;'>Missed</h2>";}
                $("#historyTable").find('tbody')
                    .append($('<tr>')
                        .append($('<td>')
                            .append("<h2>"+item['reminder_time'].slice(11)+"</h2>")
                        )
                        .append($('<td>')
                            .append("<div class='pillbox'>")
                            .append("<h2>"+item['drug_name']+" - "+item['dose']+" "+item['medicineform_name']+"</h2>")
                            .append("</div>")
                        )
                        .append($('<td>')
                            .append(consumptionStatus)

                        )
                    );
            });
        }
    );
function sortByKey(array, key) {
    return array.sort(function(a, b) {
        var x = a[key]; var y = b[key];
        return ((x < y) ? -1 : ((x > y) ? 1 : 0));
    });
}