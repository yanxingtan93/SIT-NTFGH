<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 3/6/18
  Time: 1:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pharmacistPage>
    <h1>Content Manager</h1>
    <div class="container col-md-8 col-lg-9">
        <br>
    <form action="/contentServlet" method="post" enctype="multipart/form-data">
        <input type="file" class="form-control btn btn-info" name="file" />
        <br/><br>
        <label>Document Title:</label>
        <input type="text"  class="form-control" name ="content-title">
        <small id="emailHelp" class="form-text text-muted">Title of Document displayed to users.</small>
        <br/>
       <label>Document Description:</label>
        <textarea class="form-control" rows="5"></textarea>
        <br/>

        <button type="submit" class="btn btn-primary ">Upload</button>
    </form>
    </div>

</t:pharmacistPage>

