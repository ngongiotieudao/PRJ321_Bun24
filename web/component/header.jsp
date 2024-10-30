<%-- 
    Document   : header
    Created on : Oct 27, 2024, 12:17:33 AM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="header_area sticky-header">
    <div class="main_menu">
        <nav class="navbar navbar-expand-lg navbar-light main_box">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <a class="navbar-brand logo_h" href="home"><img src="img/logo.png" alt=""></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <ul class="nav navbar-nav menu_nav ml-auto">
                        <li class="nav-item"><a class="nav-link" href="home">Home</a></li>                  
                            <c:if test ="${empty sessionScope.account}">
                            <li class="nav-item"><a class="nav-link" href="login">Login</a></li>
                            </c:if>
                            <c:if test ="${not empty sessionScope.account}">
                            <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
                            </c:if>
                            <c:if test ="${sessionScope.account.role eq'ADMIN'}">
                            <li class="nav-item"><a class="nav-link" href="manageProduct">Manage System</a></li>
                            </c:if>

                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="nav-item"><a href="viewCart" class="cart"><span class="ti-bag"></span></a></li>
                        <li class="nav-item">
                            <button class="search"><span class="lnr lnr-magnifier" id="search"></span></button>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div class="search_input" id="search_input_box">
        <div class="container">
            <form action="home" method="get" class="d-flex justify-content-between">
                <input type="text" name="inputName" class="form-control" id="search_input" placeholder="Search Here" value="">
                <button type="submit" name="action" value="searchByName" class="btn"></button>
                <span class="lnr lnr-cross" id="close_search" title="Close Search"></span>
            </form>
        </div>
    </div>

</header>
