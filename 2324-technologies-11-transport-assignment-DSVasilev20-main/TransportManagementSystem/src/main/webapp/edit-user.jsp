<%@ page import="Dataaccesslayer.models.User" %>
<%@ page import="Businesslogiclayer.services.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<%
    if (session.getAttribute("Id") == null) {
        response.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
    } else {
%>

<%
    String id = request.getParameter("id");
    Integer userId = Integer.parseInt(id);


    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    UserService userService = UserService.getInstance();

    User user = userService.getUserById(userId);
%>

<div id="emptyAlert" class="alert alert-warning alert-dismissible fade show" role="alert" style="display: none;">
    <strong>Warning!</strong> You have to enter in all inputs
</div>

<div class="row">
    <h1 class="h2 mb-3"><strong>Edit user</strong></h1>
    <div class="col-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <form action="edit-user-servlet" method="post">
                    <input type="hidden" class="form-control" name="id" value="<%= user.getId() %>">
                    <div class="mb-3">
                        <label for="firstName" class="form-label">First name</label>
                        <input type="text" class="form-control" id="firstName" name="firstName"
                               value="<%= user.getFirstName() %>">
                    </div>
                    <div class="mb-3">
                        <label for="lastName" class="form-label">Last name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName"
                               value="<%= user.getLastName() %>">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="<%= user.getEmail() %>">
                    </div>
                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Phone Number</label>
                        <input type="number" class="form-control" id="phoneNumber" name="phoneNumber"
                               value="<%= user.getPhoneNumber() %>">
                    </div>

                    <a href="http://localhost:8080/TransportManagementSystem_war_exploded/index.jsp"
                       class="btn btn-light">Cancel</a>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    updateDatetime(document.getElementById('currentDatetime'));
</script>

<%
    }
%>

<%@ include file="footer.jsp" %>