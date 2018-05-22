<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>

<jstl:url var="removeURL" value="/tariffs"/>

<div class="row">
<table class="table table-hover" id="tariffsList">
    <thead>
    <tr>
        <th></th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Description</th>
        <%--<th scope="col">Options</th>--%>
    </tr>
    </thead>
    <tbody>

    <jstl:forEach var="tariff" items="${tariffs}">

        <tr>
            <td>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" id="checkbox${tariff.id}" name="optionsIds" value="${tariff.id}" class="custom-control-input">
                    <label class="custom-control-label" for="checkbox${tariff.id}"></label>
                </div>
            </td>
            <td  data-href="<jstl:url value="/manager/tariffs/${tariff.id}"/>">${tariff.name}</td>
            <td  data-href="<jstl:url value="/manager/tariffs/${tariff.id}"/>">$${tariff.price}</td>
            <td  data-href="<jstl:url value="/manager/tariffs/${tariff.id}"/>">${tariff.description}</td>
            <%--<td>${tariff.options.size}</td>--%>
        </tr>
    </jstl:forEach>
    </tbody>
</table>
</div>
<div class="row justify-content-md-end">
    <div class="col-md-5">
        <button class="btn btn-primary btn-lg btn-block" type="button" id="removeTariffs" onclick="removeTariff()">Remove</button>
    </div>
</div>

<script>
    $('td[data-href]').on('click', function() {
        window.location.href = $(this).data('href')
    })

    function removeTariff() {

        selectedTariffs = []
        $('#tariffsList :input:checked').each(function(idx, checkbox) {
            selectedTariffs.push($(checkbox).val())
            console.log('selectedTariffs: ' + selectedTariffs)
        })

        if(selectedTariffs.length != 0) {
            <%--callREST('${removeURL}', 'DELETE', {'optionsIds': selectedOptions.join(',')}, function(responseData, status, xhr) {--%>
            callREST('${removeURL}', 'DELETE', JSON.stringify(selectedTariffs), function(responseData, status, xhr) {
                location.reload()
            })
        }
    }
</script>