<%@ page import="Dataaccesslayer.models.Package" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Businesslogiclayer.services.PackageService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>

<%
    if (session.getAttribute("Id") == null) {
        response.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
    } else {
%>

<div class="page-header">
    <h3 class="page-title">Packages</h3>
</div>

<div class="card">
    <div class="card_body">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>PACKAGE ID</th>
                    <th>SENDER USER ID</th>
                    <th>RECEIVE USER ID</th>
                    <th>DESCRIPTION</th>
                    <th>STATUS</th>
                    <th>CURRENT LOCATION</th>
                    <th>DELIVERY DATE</th>
                    <th><a href="http://localhost:8080/TransportManagementSystem_war_exploded/add-package.jsp" class="btn btn-success btn-sm" type="button">Add Package</a></th>
                </tr>
                </thead>
                <tbody>
                <%
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                        PackageService locationService = PackageService.getInstance();
                        List<Package> PackageList = locationService.getPackages();

                        for (Package pack : PackageList) {
                %>
                <tr>
                    <td><%= pack.getPackageID() %></td>
                    <td><%= pack.getSenderUserID() %></td>
                    <td><%= pack.getReceiveUserID() %></td>
                    <td><%= pack.getDescription() %></td>
                    <td><%= pack.getStatus() %></td>
                    <td><%= pack.getCurrentLocation() %></td>
                    <td><%= pack.getDeliveryDate() %></td>
                    <td>
                        <form action="delete-package-servlet" method="POST">
                            <a href="http://localhost:8080/TransportManagementSystem_war_exploded/edit-package.jsp?id=<%= pack.getPackageID() %>"
                               class="btn btn-primary btn-sm">Edit</a>
                            <button type="submit" name="delete" class="btn btn-danger btn-sm" value="<%= pack.getPackageID() %>">Delete</button>
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