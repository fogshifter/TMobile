<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>


<table class="table table-hover" id="tariffsList">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Description</th>
        <%--<th scope="col">Options</th>--%>
    </tr>
    </thead>
    <tbody>

    <jstl:forEach var="tariff" items="${tariffs}">

        <tr data-href="<jstl:url value="/manager/tariffs/${tariff.id}"/>">
            <td>${tariff.name}</td>
            <td>$${tariff.price}</td>
            <td>${tariff.description}</td>
            <%--<td>${tariff.options.size}</td>--%>
        </tr>
    </jstl:forEach>
    </tbody>
</table>

<script>
    $('tr[data-href]').on('click', function() {
        window.location.href = $(this).data('href')
    })
</script>