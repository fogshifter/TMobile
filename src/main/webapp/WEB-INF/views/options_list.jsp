<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>

<jstl:url var="removeURL" value="/options"/>

<div class="row">
    <table class="table table-hover" id="optionsList">
        <thead>
        <tr>
            <th scope="col"></th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Payment</th>
            <th scope="col">Compatibility</th>
        </tr>
        </thead>
        <tbody>

        <jstl:forEach var="option" items="${options}">

            <tr>
            <td>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" id="checkbox${option.id}" name="optionsIds" value="${option.id}" class="custom-control-input">
                    <label class="custom-control-label" for="checkbox${option.id}"></label>
                </div>
            </td>
            <td data-href="<jstl:url value="/manager/options/${option.id}"/>">${option.name}</td>
            <td data-href="<jstl:url value="/manager/options/${option.id}"/>">$${option.price}</td>
            <td data-href="<jstl:url value="/manager/options/${option.id}"/>">$${option.payment}</td>
            <td data-href="<jstl:url value="/manager/options/${option.id}"/>">
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
</div>
<div class="row justify-content-md-end">
    <div class="col-md-5">
        <button class="btn btn-primary btn-lg btn-block" type="button" id="removeOptions" onclick="removeOptions()">Remove</button>
    </div>
</div>

<script>
    $('td[data-href]').on('click', function() {
    window.location.href = $(this).data('href')
    })

    function removeOptions() {

        selectedOptions = []
        $('#optionsList :input:checked').each(function(idx, checkbox) {
            selectedOptions.push($(checkbox).val())
            console.log('selectedOptions: ' + selectedOptions)
        })

        if(selectedOptions.length != 0) {
            <%--callREST('${removeURL}', 'DELETE', {'optionsIds': selectedOptions.join(',')}, function(responseData, status, xhr) {--%>
            callREST('${removeURL}', 'DELETE', JSON.stringify(selectedOptions), function(responseData, status, xhr) {
                location.reload()
            })
        }
    }
</script>