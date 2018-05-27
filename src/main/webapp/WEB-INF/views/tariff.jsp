
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>

<!-- Custom styles for this template -->
<!--<link href="form-validation.css" rel="stylesheet">-->
<jstl:url var="requiredURL" value="/options/required"/>


<jstl:if test="${page == 'TARIFF'}">
    <jstl:set var="buttonName" value="Save"/>
    <jstl:set var="saveRequestType" value="PUT"/>
    <jstl:url value="/manager/options/" var="saveRequestURL"/>
</jstl:if>
<jstl:if test="${page == 'NEW_TARIFF'}">
    <jstl:set var="buttonName" value="Create"/>
    <jstl:set var="saveRequestType" value="POST"/>
    <jstl:url value="/manager/options/new" var="requestUrl"/>
</jstl:if>

<div class = "row">
    <h4 class = "col-md-7">Tariff information</h4>
    <h4 class = "col-md-5">Compatible options</h4>

</div>


<form action="<jstl:url value="/tariffs"/>" id="tariffData" class="needs-validation" method="${saveRequestType}" novalidate>
    <input type="hidden" name="id" value="${tariff.id}"/>

    <div class="row">
        <div class="col-md-7 order-md-1">
            <div class="form-group row ">
                <label for="optionName" class="col-3">Tariff name</label>
                <input class="form-control col-9" type="text" id="optionName" name="name" placeholder="" value="${tariff.name}" required>
                <div class="invalid-feedback">
                    Tariff name is required.
                </div>
            </div>
            <div class="form-group row">
                <label for="optionDescription" class="col-3">Tariff description</label>
                <input type="text" class="form-control col-9" id="optionDescription" name="description" placeholder="" value="${tariff.description}" required>
                <div class="invalid-feedback">
                    Description is required.
                </div>
            </div>
            <div class="form-group row">
                <label for="optionPrice" class="col-3">Tariff price $:</label>
                <input type="number" class="form-control col-9" id="optionPrice" name="price" placeholder="" value="${tariff.price}" required>
                <div class="invalid-feedback">
                    Valid tariff price is required.
                </div>
            </div>
        </div>

        <div class="col-md-5 order-md-2">

            <%--<div class="tab-content pre-scrollable" style="height: 313px">--%>
            <div class="tab-content pre-scrollable" id="compatibleOptions">
                <%--<div class="tab-pane fade active show" id="compatible">--%>

                <jstl:forEach var="option" items="${allOptions}">

                    <jstl:set var="optionChecked" value=""/>
                    <jstl:forEach var="compatibleOption" items="${tariff.compatibleOptions}">
                        <jstl:if test="${option.id == compatibleOption}">
                            <jstl:set var="optionChecked" value="checked"/>
                        </jstl:if>
                    </jstl:forEach>

                    <jstl:import url="option_card.jsp">
                        <jstl:param name="optionCardId" value="compatibleOption${option.id}" />
                        <jstl:param name="optionCardHeadId" value="compatibleOptionHead${option.id}" />
                        <jstl:param name="cardOptionId" value="${option.id}" />
                        <jstl:param name="cardOptionName" value="${option.name}" />
                        <jstl:param name="cardOptionDescription" value="${option.description}" />
                        <jstl:param name="cardOptionPrice" value="${option.price}" />
                        <jstl:param name="optionInputName" value="compatibleOptions" />
                        <jstl:param name="optionCardAttrs" value="${optionChecked}" />
                    </jstl:import>
                </jstl:forEach>
                <%--</div>--%>
            </div>
        </div>
    </div>
    <div class="row justify-content-md-end">
        <button class="btn btn-primary btn-lg btn-block col-md-5" type="button" onclick="saveTariff()">${buttonName}</button>
    </div>
</form>
<%--<div class="row">--%>
<%--<button class="col-4 btn btn-primary btn-lg btn-block col-auto" type="submit">Register</button>--%>
<%--</div>--%>
<%--</form>--%>

</div>
</div>

<!--<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
'use strict';

window.addEventListener('load', function() {
// Fetch all the forms we want to apply custom Bootstrap validation styles to
var forms = document.getElementsByClassName('needs-validation');

// Loop over them and prevent submission
var validation = Array.prototype.filter.call(forms, function(form) {
form.addEventListener('submit', function(event) {
if (form.checkValidity() === false) {
event.preventDefault();
event.stopPropagation();
}
form.classList.add('was-validated');
}, false);
});
}, false);
})();
</script>-->



<script>

    // TODO: polling for new options (TSD)
    optionsCheckboxes = $('#compatibleOptions :input')
    optionsCheckboxes.change(function() {
        // checkedOptionsElements = $('#compatibleOptions :input:checked')
        checkedOptionsElements = optionsCheckboxes.filter(':checked')

        if(checkedOptionsElements.length == 0) {
            return;
        }

        checkedCompatibleOptions = []
        checkedOptionsElements.not(':disabled').each(function(idx, checkbox) {
            checkedCompatibleOptions.push($(checkbox).val())
        })

        if(checkedCompatibleOptions.length == 0) {
            checkedOptionsElements.filter(':disabled').prop('disabled', false).prop('checked', false)
            return
        }

        data = {'optionsIds' : checkedCompatibleOptions.join(',')}

        callREST('${requiredURL}', 'GET', data, function(responseData, status, xhr) {

            checkedOptionsElements.filter(':disabled').prop('disabled', false).prop('checked', false)

            responseData.forEach(function(option) {
                requiredOptionEl = optionsCheckboxes.not(':checked').filter('#checkboxcompatibleOption' + option.id)
                requiredOptionEl.prop('disabled', true)
                requiredOptionEl.prop('checked', true)
            })
        })
    })

    // }

    function saveTariff() {
        // to allow serializeObject()
        $('#compatibleOptions :input:checked').prop('disabled', false)

        form = $('#tariffData')
        formData = form.serializeObject()
        formData = JSON.stringify(formData)
        console.log('Tariff data: ' + formData)
        console.log('URL: ' + form.attr('action'))

        callREST(form.attr('action'), form.attr('method'), formData, function() {
            window.location.reload();
        })
    }


</script>
