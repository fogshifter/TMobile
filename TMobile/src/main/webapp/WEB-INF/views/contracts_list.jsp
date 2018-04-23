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
            <th scope="col">Email</th>
            <th scope="col">Tariff</th>
        </tr>
    </thead>
    <tbody>

    <jstl:forEach var="contract" items="${contracts}">
        <%--<jstl:forEach var="phone" items="${contract.phones}">--%>
            <!--<tr id="${customer.id}">-->
        <tr data-href="<jstl:url value="/manager/contract/${contract.id}"/>">
            <td>${contract.phone}</td>
            <td>${contract.firstName}</td>
            <td>${contract.lastName}</td>
            <td>${contract.email}</td>
            <td>${contract.tariff}</td>
        </tr>
        <%--</jstl:forEach>--%>
    </jstl:forEach>
    </tbody>
</table>
<!-- table rows click & mouseover actions -->
<script>
    $('tr[data-href]').on('click', function() {
        window.location.href = $(this).data('href')
    })
</script>

