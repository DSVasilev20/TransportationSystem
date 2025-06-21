<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ShipEase</title>

    <link rel="stylesheet" href="assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="assets/vendors/jvectormap/jquery-jvectormap.css">
    <link rel="stylesheet" href="assets/vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="assets/vendors/owl-carousel-2/owl.carousel.min.css">
    <link rel="stylesheet" href="assets/vendors/owl-carousel-2/owl.theme.default.min.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
<div class="container-scroller">
    <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <div class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
            <a class="sidebar-brand brand-logo" href="index.jsp">ShipEase</a>
            <a class="sidebar-brand brand-logo-mini" href="index.jsp">SE</a>
        </div>
        <ul class="nav">
            <li class="nav-item nav-category">
                <span class="nav-link">Navigation</span>
            </li>
            <li class="nav-item menu-items">
                <a class="nav-link" href="http://localhost:8080/TransportManagementSystem_war_exploded/index.jsp">
                  <span class="menu-icon">
                    <i class=" mdi mdi-account "></i>
                  </span>
                    <span class="menu-title">Users</span>
                </a>
            </li>
            <li class="nav-item menu-items">
                <a class="nav-link" href="http://localhost:8080/TransportManagementSystem_war_exploded/packages.jsp">
                  <span class="menu-icon">
                    <i class="mdi mdi-package-variant-closed"></i>
                  </span>
                    <span class="menu-title">Packages</span>
                </a>
            </li>
            <li class="nav-item menu-items">
                <a class="nav-link" href="http://localhost:8080/TransportManagementSystem_war_exploded/locations.jsp">
                  <span class="menu-icon">
                    <i class="mdi mdi-map-marker"></i>
                  </span>
                    <span class="menu-title">Locations</span>
                </a>
            </li>
            <li class="nav-item menu-items">
                <a class="nav-link" href="http://localhost:8080/TransportManagementSystem_war_exploded/transportation-vehicles.jsp">
                  <span class="menu-icon">
                    <i class="mdi mdi-car"></i>
                  </span>
                    <span class="menu-title">Vehicles</span>
                </a>
            </li>
        </ul>
    </nav>
    <nav class="navbar p-0 fixed-top d-flex flex-row">
        <div class="navbar-brand-wrapper d-flex d-lg-none align-items-center justify-content-center">
            <a class="navbar-brand brand-logo-mini" href="index.jsp">ShipEase</a>
        </div>
        <div class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">
            <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
                <span class="mdi mdi-menu"></span>
            </button>
            <ul class="navbar-nav navbar-nav-right">
                <li class="nav-item dropdown">
                    <a class="nav-link" id="profileDropdown" href="#" data-toggle="dropdown">
                        <div class="navbar-profile">
                            <p class="mb-0 d-none d-sm-block navbar-profile-name"><%=session.getAttribute("FirstName")%></p>
                            <i class="mdi mdi-menu-down d-none d-sm-block"></i>
                        </div>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="profileDropdown">
                        <div class="dropdown-divider"></div>

                        <form action="logout-servlet" method="post">
                        <button class="dropdown-item preview-item">
                            <div class="preview-thumbnail">
                                <div class="preview-icon bg-dark rounded-circle">
                                    <i class="mdi mdi-logout text-danger"></i>
                                </div>
                            </div>
                            <div class="preview-item-content">
                                <p class="preview-subject mb-1">Log out</p>
                            </div>
                        </button>
                        </form>
                    </div>
                </li>
            </ul>
            <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
                <span class="mdi mdi-format-line-spacing"></span>
            </button>
        </div>
    </nav>
    <div class="main-panel">
        <div class="content-wrapper">