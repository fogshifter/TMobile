<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
   <!--  <link rel="icon" href="../../../../favicon.ico"> -->

    <title>T-Mobile</title>

    <!-- Bootstrap core CSS -->
   <link href="<jstl:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <!-- <link href="<jstl:url value="/resources/css/carousel.css"/>" rel="stylesheet"> -->
  </head>
  <body>

    <header>
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">
	      <div class="container">
	        <a class="navbar-brand" href="#"><h1>T-Mobile</h1></a>
	        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
	          <span class="navbar-toggler-icon"></span>
	        </button>
	        <div class="collapse navbar-collapse" id="navbarCollapse">
	          <ul class="navbar-nav mr-auto">
	            <li class="nav-item active">
	              <a class="nav-link" href="#"><h4>Home</h4><span class="sr-only">(current)</span></a>
	            </li>
	            <li class="nav-item">
	              <a class="nav-link" href="#"><h4>Link</h4></a>
	            </li>
	            <li class="nav-item">
	              <a class="nav-link disabled" href="#"><h4>Disabled</h4></a>
	            </li>
	          </ul>
	          <a class="nav-link navbar-brand" href="/login"><h4>Sign in</h4></a>
	        </div>
	      </div>
      </nav>
    </header>

<!--<div class="container">-->
<main role="main">
<div class="row">

    <div class="col-md-3">
        <ul class="nav nav-pills nav-stacked admin-menu" >
            <li class="active"><a href="" data-target-id="profile"><i class="glyphicon glyphicon-user"></i> Profile</a></li>
            <li><a href="" data-target-id="change-password"><i class="glyphicon glyphicon-lock"></i> Change Password</a></li>
            <li><a href="" data-target-id="settings"><i class="glyphicon glyphicon-cog"></i> Settings</a></li>
            <li><a href="" data-target-id="logout"><i class="glyphicon glyphicon-log-out"></i> Logout</a></li>
        </ul>
    </div>

    <div class="col-md-9  admin-content" id="profile">
        <div class="panel panel-info" style="margin: 1em;">
            <div class="panel-heading">
                <h3 class="panel-title">Name</h3>
            </div>
            <div class="panel-body">
                <!-- Ashish Patel -->
                ${profile.customerName}
            </div>
        </div>
        <div class="panel panel-info" style="margin: 1em;">
            <div class="panel-heading">
                <h3 class="panel-title">Email</h3>
            </div>
            <div class="panel-body">
                <!-- ashishpatel0720@gmail.com -->
                ${profile.email}
            </div>
        </div>
        <div class="panel panel-info" style="margin: 1em;">
            <div class="panel-heading">
                <h3 class="panel-title">Last Password Change</h3>

            </div>
            <div class="panel-body">
                4 days Ago
            </div>
        </div>

    </div>
   <div class="col-md-9  admin-content" id="settings">
   <div class="panel panel-info" style="margin: 1em;">
       <div class="panel-heading">
           <h3 class="panel-title">Notification</h3>
       </div>
       <div class="panel-body">
           <div class="label label-success">allowed</div>
       </div>
   </div>
   <div class="panel panel-info" style="margin: 1em;">
       <div class="panel-heading">
           <h3 class="panel-title">Newsletter</h3>
       </div>
       <div class="panel-body">
           <div class="badge">Monthly</div>
       </div>
   </div>
   <div class="panel panel-info" style="margin: 1em;">
        <div class="panel-heading">
            <h3 class="panel-title">Admin</h3>

        </div>
        <div class="panel-body">
             <div class="label label-success">yes</div>
        </div>
    </div>

</div>

<div class="col-md-9  admin-content" id="change-password">
    <form action="/password" method="post">


        <div class="panel panel-info" style="margin: 1em;">
           <div class="panel-heading">
               <h3 class="panel-title"><label for="new_password" class="control-label panel-title">New Password</label></h3>
           </div>
           <div class="panel-body">
               <div class="form-group">
                   <div class="col-sm-10">
                       <input type="password" class="form-control" name="password" id="new_password" >
                   </div>
               </div>

           </div>
       </div>


       <div class="panel panel-info" style="margin: 1em;">
           <div class="panel-heading">
               <h3 class="panel-title"><label for="confirm_password" class="control-label panel-title">Confirm password</label></h3>
           </div>
           <div class="panel-body">
               <div class="form-group">
                   <div class="col-sm-10">
                       <input type="password" class="form-control" name="password_confirmation" id="confirm_password" >
                   </div>
               </div>
           </div>
       </div>


       <div class="panel panel-info border" style="margin: 1em;">
            <div class="panel-body">
                <div class="form-group">
                    <div class="pull-left">
                        <input type="submit" class="form-control btn btn-primary" name="submit" id="submit">
                    </div>
                </div>
            </div>
        </div>

    </form>
</div>

<div class="col-md-9  admin-content" id="settings"></div>

<div class="col-md-9  admin-content" id="logout">
    <div class="panel panel-info" style="margin: 1em;">
       <div class="panel-heading">
           <h3 class="panel-title">Confirm Logout</h3>
       </div>
       <div class="panel-body">
           Do you really want to logout ?  
           <a  href="#" class="label label-danger"
               onclick="event.preventDefault();
                                        document.getElementById('logout-form').submit();">
               <span >   Yes   </span>
           </a>    
           <a href="/account" class="label label-success"> <span >  No   </span></a>
       </div>
       <form id="logout-form" action="#" method="POST" style="display: none;">
            </form>
        </div>
    </div>
</div>
<!-- </div> -->
</main>
</body>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>

<script type="text/javascript">
	$(document).ready(function()
    {
      var navItems = $('.admin-menu li > a');
      var navListItems = $('.admin-menu li');
      var allWells = $('.admin-content');
      var allWellsExceptFirst = $('.admin-content:not(:first)');
      allWellsExceptFirst.hide();
      navItems.click(function(e)
      {
          e.preventDefault();
          navListItems.removeClass('active');
          $(this).closest('li').addClass('active');
          allWells.hide();
          var target = $(this).attr('data-target-id');
          $('#' + target).show();
      });
    });
</script>


</html>