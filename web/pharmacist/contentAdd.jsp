<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../JS/contentCategory.js"></script>


<t:pharmacistPage>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">

    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body{ margin-top:20px; }
    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>



    <h1> <i class="fa fa-book"></i> Content Management</h1><br>
    <form action="/contentServlet" method="post" enctype="multipart/form-data">

        <label for="file">Select a File to Upload</label><br />
        <input type="file" class="form-control btn-info" style="height: 40px;width:550px"  name="file" id="file" onchange="fileSelected();"/>
        <br>
        <div id="fileName"></div>
        <div id="fileSize"></div>
        <div id="fileType"></div>


        <table>

        <tr>
            <td><label>Document Title: &nbsp;</label></td>
            <td><input type="text" class="form-control" name ="content-title"></td>
        </tr>

            <tr>

                <td><label>Content Category: &nbsp;</label></td>
                <td><select  class="form-control" style="height: 40px" name="contentCategory" id="content-category">   </select></td>

            </tr>

        </table>
        <br/>
        <br/>
        <input type="submit" class="form-control btn-success" value="Upload" />
    </form>


</t:pharmacistPage>

<script>

    function fileSelected() {
        var file = document.getElementById('file').files[0];
        if (file) {
            var fileSize = 0;
            if (file.size > 1024 * 1024)
                fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
            else
                fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';

            document.getElementById('fileName').innerHTML = '<b> Name: ' + file.name + '</b>';
            document.getElementById('fileSize').innerHTML = '<b>Size: ' + fileSize + '</b>';
            document.getElementById('fileType').innerHTML = '<b>Type: ' + file.type + '</b><br><br></br>';
        }
    }
</script>