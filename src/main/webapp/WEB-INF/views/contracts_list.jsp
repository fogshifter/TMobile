<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>


<table class="table table-hover" id="contractList">
    <thead>
        <tr>
            <th scope="col">
            <%--<span data-feather="search"></span>--%>
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
        <jstl:choose>
            <jstl:when test="${user == 'CUSTOMER'}">
                <tr data-href="<jstl:url value="/customer/${contract.id}"/>">
            </jstl:when>
            <jstl:when test="${user == 'MANAGER'}">
                <tr data-href="<jstl:url value="/manager/contract/${contract.id}"/>">
            </jstl:when>
        </jstl:choose>
        <tr data-href="<jstl:url value="/contracts/${contract.id}"/>">

        <%--<tr data-href="<jstl:url value="/manager/contract/${contract.id}"/>">--%>
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

    $('#phoneSearch').on('input', function() {
        data = {
            'phone': $(this).val()
        }

        $.get({
            // dataType : 'json',
            data : data,
            url : '<jstl:url value="/manager/filterByPhone"/>',
            success : function(contracts) {
                console.log(contracts);

                var contractsTable = $('#contractList tbody')
                contractsTable.empty()

                var tableContent = ''
                contracts.forEach(function(contract) {

                    <%--var rowUrl = '<jstl:choose>--%>
                    <%--<jstl:when test="${user == 'CUSTOMER'}">--%>
                        <%--<jstl:url value="/customer/"/>--%>
                    <%--</jstl:when>--%>
                    <%--<jstl:when test="${user == 'MANAGER'}">--%>
                        <%--<jstl:url value="/manager/contract/"/>--%>
                    <%--</jstl:when>--%>
                    <%--</jstl:choose>'--%>
                    var rowUrl = '<jstl:url value="/manager/contract/"/>'

                    tableContent += '<tr data-href="' + rowUrl + contract.id + '">'
                    tableContent += '<td>' + contract.phone + '</td>'
                    tableContent += '<td>' + contract.firstName + '</td>'
                    tableContent += '<td>' + contract.lastName + '</td>'
                    tableContent += '<td>' + contract.email + '</td>'
                    tableContent += '<td>' + contract.tariff + '</td>'
                    tableContent += '</tr>';
                })

                contractsTable.append(tableContent)

                $('tr[data-href]').on('click', function() {
                    window.location.href = $(this).data('href')
                })
            }
        })
    })

</script>

