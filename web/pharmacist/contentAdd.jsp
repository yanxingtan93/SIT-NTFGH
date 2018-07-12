<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 3/6/18
  Time: 1:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../JS/contentCategory.js"></script>

<t:pharmacistPage>
    <h1>Manage Content such as PDF uploads here</h1>
    <form action="/contentServlet" method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <br/>
        <label>Document Title:</label>
        <input type="text" name ="content-title">
        <br/>
        <label>Content Category:</label>
        <select name="contentCategory" id="content-category">

    </select>
        <br/>

        <input type="submit" value="upload" />
    </form>


</t:pharmacistPage>

