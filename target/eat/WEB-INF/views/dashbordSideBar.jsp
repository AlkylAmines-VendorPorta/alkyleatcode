<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div id="sidebar-wrapper">
        <div class="sidebar-nav">
            <div class="nav-side-menu">
                <div class="brand"><img src="<%=request.getContextPath()%>/resource/images/logo.png?appVer=${appVer}" class="navlogo logo"></div>

                <div class="menu-list">

                    <ul id="menu-content" class="menu-content">
                        <li data-toggle="collapse" data-target="#products" class="collapsed active">
                            <a href="#"><i class="fa fa-user-circle usericon" aria-hidden="true"></i>Welcome<span class="arrow"></span></a>
                        </li>
                        <ul class="sub-menu collapse" id="products">
                            <li><a href="logout">Logout</a></li>
                        </ul>
                        <li>
                            <a href="#">
                                <i class="fa fa-dashboard fa-lg"></i> Dashboard
                            </a>
                        </li>

                        <li data-toggle="collapse" data-target="#service" class="collapsed">
                            <a href="#"><i class="fa fa-globe fa-lg"></i> Services <span class="arrow"></span></a>
                        </li>
                        <ul class="sub-menu collapse" id="service">
                            <li>New Service 1</li>
                            <li>New Service 2</li>
                            <li>New Service 3</li>
                        </ul>
                        <li>
                            <a href="#">
                                <i class="fa fa-user fa-lg"></i> Profile
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="fa fa-users fa-lg"></i> Users
                            </a>
                        </li>
                        <li>
                            <a href="advanceSearch">
                                <i class="fa fa-search fa-lg"></i>Advance Search
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="clearfix"></div>

        </div>
    </div>