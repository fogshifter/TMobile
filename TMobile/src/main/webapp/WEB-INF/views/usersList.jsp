<!DOCTYPE html>

<p> users list: </p>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jstl:forEach var="customer" items="${customers}">

<p>${customer.firstName}</p>
<p>${customer.lastName}</p>
<p>${customer.email}</p>
<p>${customer.address}</p>
<p>${customer.birthDate}</p>

</jstl:forEach>

</html>