<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>


<table class="table table-hover" id="contractList">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Payment</th>
        <th scope="col">Compatibility</th>
    </tr>
    </thead>
    <tbody>

    <jstl:forEach var="option" items="${options}">

        <tr data-href="<jstl:url value="/manager/options/${option.id}"/>">
        <td>${option.name}</td>
        <td>$${option.price}</td>
        <td>$${option.payment}</td>
        <td>
            <jstl:if test="${option.compatible}" >
                All
            </jstl:if>
            <jstl:if test="${!option.compatible}">
                Partial
            </jstl:if>
        </td>
        </tr>
        <%--</jstl:forEach>--%>
    </jstl:forEach>
    </tbody>
</table>

<script>
    $('tr[data-href]').on('click', function() {
    window.location.href = $(this).data('href')
    })
</script>