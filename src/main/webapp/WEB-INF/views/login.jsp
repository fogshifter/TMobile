<!doctype html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
   <!-- <link rel="icon" href="../../../../favicon.ico"> --> 

    <title>Signin T-mobile</title>

    <!-- Bootstrap core CSS -->
   <%--<link href="<jstl:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">--%>
    <jstl:import url="resources.jsp"></jstl:import>

    <!-- Custom styles for this template -->
    <link href="<jstl:url value="/resources/css/signin.css"/>" rel="stylesheet">
  </head>

  <body class="text-center">
    <form class="form-signin" method="post" action = "<jstl:url value="/login"/>">
      <!-- <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72"> -->
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      <jstl:if test="${param.error != null}">
      	<p class="text-danger">Invalid email or password</p>
      </jstl:if>
      <label for="usermane" class="sr-only">Email address</label>
      <input type="email" id="usermane" name="username" class="form-control" placeholder="Email address" required autofocus>
      <label for="password" class="sr-only">Password</label>
      <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
     <!-- <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div> --> 
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
  </body>
</html>
