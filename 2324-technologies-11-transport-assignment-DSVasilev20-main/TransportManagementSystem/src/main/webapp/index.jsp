<%@ page import="Dataaccesslayer.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Businesslogiclayer.services.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<%
    if (session.getAttribute("Id") == null) {
        response.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
    } else {
%>

<div class="page-header">
    <h3 class="page-title">Users</h3>
</div>
<div class="card">
    <div class="card_body">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>FIRST NAME</th>
                    <th>LAST NAME</th>
                    <th>EMAIL</th>
                    <th>PHONE NUMBER</th>
                    <th><a href="http://localhost:8080/TransportManagementSystem_war_exploded/add-user.jsp"
                           class="btn btn-success btn-sm" type="button">Add User</a></th>
                </tr>
                </thead>
                <tbody>
                <%
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                        UserService locationService = UserService.getInstance();
                        List<User> userList = locationService.getUsers();

                        for (User user : userList) {
                %>
                <tr>
                    <td><%= user.getId() %>
                    </td>
                    <td><%= user.getFirstName() %>
                    </td>
                    <td><%= user.getLastName() %>
                    </td>
                    <td><%= user.getEmail() %>
                    </td>
                    <td><%= user.getPhoneNumber() %>
                    </td>
                    <td>
                        <form action="delete-user-servlet" method="POST">
                            <a href="http://localhost:8080/TransportManagementSystem_war_exploded/edit-user.jsp?id=<%= user.getId() %>"
                               class="btn btn-primary btn-sm">Edit</a>
                            <button type="submit" name="delete" class="btn btn-danger btn-sm" value="<%= user.getId() %>">Delete</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error: " + e.getMessage());
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%
    }
%>

<%@ include file="footer.jsp" %>