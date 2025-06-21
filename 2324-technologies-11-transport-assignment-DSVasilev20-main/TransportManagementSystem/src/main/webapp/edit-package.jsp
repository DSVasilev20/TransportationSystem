<%@ page import="Dataaccesslayer.models.Package" %>
<%@ page import="Businesslogiclayer.services.PackageService" %>
<%@ page import="Businesslogiclayer.services.UserService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<%
    if (session.getAttribute("Id") == null) {
        response.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
    } else {
%>

<%
    String id = request.getParameter("id");
    Integer packId = Integer.parseInt(id);


    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    PackageService packageService = PackageService.getInstance();

    Package pack = packageService.getPackageById(packId);
%>

<div id="emptyAlert" class="alert alert-warning alert-dismissible fade show" role="alert" style="display: none;">
    <strong>Warning!</strong> You have to enter in all inputs
</div>

<div class="row">
    <h1 class="h2 mb-3"><strong>Edit Package</strong></h1>
    <div class="col-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <form action="edit-package-servlet" method="post">
                    <input type="hidden" class="form-control" name="id" value="<%= pack.getPackageID() %>">
                    <div class="mb-3">
                        <label for="senderUserID" class="form-label">Sender User ID</label>
                        <select class="form-control" id="senderUserID" name="senderUserID">
                            <%
                                List<Integer> userIds = UserService.getInstance().getUserIds();
                                if (!userIds.isEmpty()) {
                                    for (Integer userId : userIds) {
                            %>
                            <option value="<%= userId %>"><%= userId %>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="receiveUserID" class="form-label">Receive User ID</label>
                        <select class="form-control" id="receiveUserID" name="receiveUserID">
                            <%
                                userIds = UserService.getInstance().getUserIds();
                                if (!userIds.isEmpty()) {
                                    for (Integer userId : userIds) {
                            %>
                            <option value="<%= userId %>"><%= userId %>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <select class="form-control" id="description" name="description">
                            <option value="fragile" <%= pack.getCurrentLocation().equals("fragile") ? "selected" : "" %>>Fragile</option>
                            <option value="electronics" <%= pack.getCurrentLocation().equals("electronics") ? "selected" : "" %>>Electronics</option>
                            <option value="clothes" <%= pack.getCurrentLocation().equals("clothes") ? "selected" : "" %>>Clothes</option>
                            <option value="toys" <%= pack.getCurrentLocation().equals("toys") ? "selected" : "" %>>Toys</option>
                            <option value="books" <%= pack.getCurrentLocation().equals("books") ? "selected" : "" %>>Books</option>
                            <option value="documents" <%= pack.getCurrentLocation().equals("documents") ? "selected" : "" %>>Documents</option>
                            <option value="medicine" <%= pack.getCurrentLocation().equals("medicine") ? "selected" : "" %>>Medicine</option>
                        </select>

                    </div>
                    <div class="mb-3">
                        <label for="status" class="form-label">Status</label>
                        <select class="form-control" id="status" name="status">
                            <option value="Pending" <%= pack.getStatus().equals("Pending") ? "selected" : "" %>>Pending</option>
                            <option value="In Progress" <%= pack.getStatus().equals("In Progress") ? "selected" : "" %>>In Progress</option>
                            <option value="Delivered" <%= pack.getStatus().equals("Delivered") ? "selected" : "" %>>Delivered</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="currentLocation" class="form-label">Current Location</label>
                        <select class="form-control" id="currentLocation" name="currentLocation">
                            <option value="123 Main Street" <%= pack.getCurrentLocation().equals("123 Main Street") ? "selected" : "" %>>123 Main Street</option>
                            <option value="456 Elm Avenue" <%= pack.getCurrentLocation().equals("456 Elm Avenue") ? "selected" : "" %>>456 Elm Avenue</option>
                            <option value="789 Oak Lane" <%= pack.getCurrentLocation().equals("789 Oak Lane") ? "selected" : "" %>>789 Oak Lane</option>
                            <option value="101 Pine Boulevard" <%= pack.getCurrentLocation().equals("101 Pine Boulevard") ? "selected" : "" %>>101 Pine Boulevard</option>
                            <option value="202 Cedar Road" <%= pack.getCurrentLocation().equals("202 Cedar Road") ? "selected" : "" %>>202 Cedar Road</option>
                        </select>

                    </div>
                    <div class="mb-3">
                        <label for="deliveryDate" class="form-label">Delivery Date</label>
                        <input type="date" class="form-control" id="deliveryDate" name="deliveryDate"
                               value="<%= pack.getDeliveryDate() %>">
                    </div>

                    <a href="http://localhost:8080/TransportManagementSystem_war_exploded/packages.jsp"
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