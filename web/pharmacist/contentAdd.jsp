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
    <h1>Manage Content such as PDF uploads here</h1>
    <form action="/contentServlet" method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" value="upload" />
    </form>


</t:pharmacistPage>

