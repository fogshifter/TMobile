<!doctype html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>


<jstl:choose>
    <jstl:when test="${page == 'CONTRACTS'}">
        <jstl:set var="page_url" value="contracts_list.jsp"/>
        <jstl:set var="page_title" value="Contracts list"/>
    </jstl:when>
    <jstl:when test="${page == 'EDIT_CONTRACT'}">
        <jstl:set var="page_url" value="contract.jsp"/>
        <jstl:set var="page_title" value="Contract profile"/>
    </jstl:when>
    <jstl:when test="${page == 'NEW_CONTRACT'}">
        <jstl:set var="page_url" value="contract.jsp"/>
        <jstl:set var="page_title" value="Contract information"/>
    </jstl:when>
    <jstl:when test="${page == 'TARIFFS'}">
        <jstl:set var="page_url" value="tariffs_list.jsp"/>
        <jstl:set var="page_title" value="Tariffs"/>
    </jstl:when>
    <jstl:when test="${page == 'TARIFF' || page == 'NEW_TARIFF'}">
        <jstl:set var="page_url" value="tariff.jsp"/>
        <jstl:set var="page_title" value="Tariff"/>
    </jstl:when>
    <jstl:when test="${page == 'OPTIONS'}">
        <jstl:set var="page_url" value="options_list.jsp"/>
        <jstl:set var="page_title" value="Options"/>
    </jstl:when>
    <jstl:when test="${page == 'OPTION' || page == 'NEW_OPTION'}">
        <jstl:set var="page_url" value="option.jsp"/>
        <jstl:set var="page_title" value="Option"/>
    </jstl:when>
</jstl:choose>


<jstl:if test="${user == 'CUSTOMER'}">
    <jstl:set var="contractsListUrl" value="/customer"/>
</jstl:if>
<jstl:if test="${user == 'MANAGER'}">
    <jstl:set var="contractsListUrl" value="/manager"/>
</jstl:if>


<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- <link rel="icon" href="../../../../favicon.ico"> -->

        <title>T-Mobile Control Panel</title>

        <jstl:import url="resources.jsp"></jstl:import>

        <!-- Custom styles for this page -->
        <link href="<jstl:url value="/resources/css/dashboard.css"/>" rel="stylesheet">

    </head>

    <body>

        <nav class="navbar navbar-dark sticky-top bg-primary flex-md-nowrap p-0">
        <!-- <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">T-Mobile</a> text-center -->
        <a class="nav-link text-white col-sm-3 col-md-2 mr-0" href="#"><h3>T-Mobile</h3></a>
        <!-- <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search"> -->
        <!--<input class="form-control form-control-primary w-100" type="text" placeholder="Search" aria-label="Search"> -->
        <!--<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">-->
        <%--<h4 class="text-white mr-auto px-3">Control panel</h4>--%>
        <h4 class="text-white px-3 mr-auto">Control panel</h4>
        <jstl:if test="${page == 'CONTRACTS' && user == 'MANAGER'}">
            <input class="form-control mr-sm-2 col-2" type="text" placeholder="Phone" id="phoneSearch">
        </jstl:if>
        <!--</div>-->
        <ul class="navbar-nav px-3">
            <li class="nav-item text-nowrap">
                <form method="post" action="<jstl:url value="/logout"/>">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button class="btn btn-primary btn-block" type="submit">Sign out</button>
                </form>

                <!-- <a class="nav-link" href="#">Sign out</a> -->
                <!-- <a class="nav-link" href="<jstl:url value="/logout"/>">Sign out</a> -->
            </li>
        </ul>
    </nav>

        <div class="container-fluid">
            <div class="row">
                <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                    <div class="sidebar-sticky">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <!-- <a class="nav-link active" href="#">
                                  <span data-feather="home"></span>
                                  Customers <span class="sr-only">(current)</span>
                                </a> -->
                                <a class="nav-link
                                  <jstl:if test="${page == 'CONTRACTS' || page == 'EDIT_CONTRACT'}">
                                    active
                                      </jstl:if>" href="<jstl:url value="${contractsListUrl}"/>">
                                  <%--</jstl:if>" href="<jstl:if test="${user}" <jstl:url value="/manager"/>">--%>
                                    <span data-feather="list"></span>
                                    Contracts
                                </a>
                            </li>
                            <jstl:if test="${user == 'MANAGER'}">
                                <li class="nav-item">
                                    <a class="nav-link
                                      <jstl:if test="${page == 'NEW_CONTRACT'}">
                                        active
                                      </jstl:if>" href="<jstl:url value="/manager/new_contract"/>">
                                        <span data-feather="users"></span>
                                        New contract
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link
                                      <jstl:if test="${page == 'TARIFFS'}">
                                        active
                                      </jstl:if>" href="<jstl:url value="/manager/tariffs"/>">
                                        <span data-feather="file"></span>
                                        Tariffs
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link
                                      <jstl:if test="${page == 'NEW_TARIFF'}">
                                        active
                                      </jstl:if>" href="<jstl:url value="/manager/tariffs/new"/>">
                                        <span data-feather="file"></span>
                                        New tariff
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link
                                      <jstl:if test="${page == 'OPTIONS' || page == 'EDIT_OPTION'}">
                                        active
                                      </jstl:if>" href="<jstl:url value="/manager/options"/>">
                                        <span data-feather="shopping-cart"></span>
                                        Options
                                    </a>
                                </li>
                                <li class="nav-item">
                                <a class="nav-link
                                      <jstl:if test="${page == 'NEW_OPTION'}">
                                        active
                                      </jstl:if>" href="<jstl:url value="/manager/options/new"/>">
                                    <span data-feather="shopping-cart"></span>
                                    New option
                                </a>
                                </li>
                            </jstl:if>
                        </ul>
                    </div>
                </nav>

                <main role="main" class="ml-sm-auto col-lg-10 pt-3 px-4">

                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                        <h1 class="h2">${page_title}</h1>
                    </div>

                    <jstl:import url="${page_url}"/>
                </main>
            </div>
        </div>
    </body>

    <!-- Icons -->
    <script>
        feather.replace()
    </script>
</html>


