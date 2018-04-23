<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>

<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">
            <span data-feather="search"></span>
            Phone
            </th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Birth Date</th>
            <th scope="col">Email</th>
        </tr>
    </thead>
    <tbody>

    <jstl:forEach var="customer" items="${customers}">
        <jstl:forEach var="phone" items="${customer.phones}">
            <!--<tr id="${customer.id}">-->
            <tr data-href="<jstl:url value="/manager/contract/${customer.id}"/>">
                <td>${phone}</td>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.birthDate}</td>
                <td>${customer.email}</td>
            </tr>
        </jstl:forEach>
    </jstl:forEach>
    </tbody>
</table>
<!-- table rows click & mouseover actions -->
<script>
    $('tr[data-href]').on('click', function() {
        window.location.href = $(this).data('href')
    })
</script>

