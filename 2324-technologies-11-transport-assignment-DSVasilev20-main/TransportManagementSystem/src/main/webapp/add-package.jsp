<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ page import="Businesslogiclayer.services.UserService" %>
<%@ page import="Dataaccesslayer.models.User" %>
<%@ page import="Dataaccesslayer.repositories.UserRepository" %>
<%@ page import="java.util.List" %>

<%
    if (session.getAttribute("Id") == null) {
        response.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
    } else {
%>

<div class="row">
    <h1 class="h2 mb-3"><strong>Add Package</strong></h1>
    <div class="col-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <form action="add-package-servlet" method="post">

                    <div class="mb-3">
                        <label for="senderUserID" class="form-label">Sender UserID</label>
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
                        <label for="receiveUserID" class="form-label">Receive UserID</label>
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
                            <option value="fragile">Fragile</option>
                            <option value="electronics">Electronics</option>
                            <option value="clothes">Clothes</option>
                            <option value="toys">Toys</option>
                            <option value="books">Books</option>
                            <option value="documents">Documents</option>
                            <option value="medicine">Medicine</option>
                            <option value="other">Other</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="status" class="form-label">Status</label>
                        <select class="form-control" id="status" name="status">
                            <option value="Pending">Pending</option>
                            <option value="In Progress">In Progress</option>
                            <option value="Delivered">Delivered</option>
                        </select>

                    </div>
                    <div class="mb-3">
                        <label for="currentLocation" class="form-label">Current Location</label>
                        <select class="form-control" id="currentLocation" name="currentLocation">
                            <option value="123 Main Street">123 Main Street</option>
                            <option value="456 Elm Avenue">456 Elm Avenue</option>
                            <option value="789 Oak Lane">789 Oak Lane</option>
                            <option value="101 Pine Boulevard">101 Pine Boulevard</option>
                            <option value="202 Cedar Road">202 Cedar Road</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="deliveryDate" class="form-label">Delivery Date</label>
                        <input type="date" class="form-control" id="deliveryDate" name="deliveryDate">
                    </div>

                    <a href="http://localhost:8080/TransportManagementSystem_war_exploded/packages.jsp" class="btn btn-light">Cancel</a>
                    <button type="submit" class="btn btn-primary">Add</button>
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