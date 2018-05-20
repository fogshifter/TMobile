
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>

<!-- Custom styles for this template -->
<!--<link href="form-validation.css" rel="stylesheet">-->

<jstl:if test="${page == 'OPTION'}">
    <jstl:set value="${option.name}" var="optionName"/>
    <jstl:set value="${option.description}" var="optionDescription"/>
    <jstl:set value="${option.price}" var="optionPrice"/>
    <jstl:set value="${option.payment}" var="optionPayment"/>
    <jstl:set var="buttonName" value="Save"/>
    <jstl:set var="requestType" value="PUT"/>
    <jstl:url value="/manager/options/" var="requestUrl"/>
</jstl:if>
<jstl:if test="${page == 'NEW_OPTION'}">
    <jstl:set value="" var="optionName"/>
    <jstl:set value="" var="optionDescription"/>
    <jstl:set value="" var="optionPrice"/>
    <jstl:set value="" var="optionPayment"/>
    <jstl:set value="" var="optionId"/>
    <jstl:set value="" var="requiredOptions"/>
    <jstl:set var="buttonName" value="Create"/>
    <jstl:set var="requestType" value="POST"/>
    <jstl:url value="/manager/options/new" var="requestUrl"/>
</jstl:if>

<div class = "row">
    <h4 class = "col-md-4">Option information</h4>
    <h4 class = "col-md-4">Compatible options</h4>
    <h4 class = "col-md-4">Required options</h4>
</div>

<%--<form method="POST" onsubmit="javascript:void(0);" id="contractData" class="needs-validation" novalidate>--%>
<%--<form method="POST" action="<jstl:url value="/manager/options"/>" id="optionData" class="needs-validation" novalidate>--%>
<form action="<jstl:url value="/manager/options"/>" id="optionData" class="needs-validation" novalidate>
    <div class="row">
        <div class="col-md-4 order-md-1">
            <div class="form-group">
                <label for="optionName">Option name</label>
                <input class="form-control" type="text" id="optionName" name="name" placeholder="" value="${optionName}" required>
                <div class="invalid-feedback">
                    Option name is required.
                </div>
            </div>
            <div class="form-group">
                <label for="optionDescription">Option description</label>
                <input type="text" class="form-control" id="optionDescription" name="description" placeholder="" value="${optionDescription}" required>
                <div class="invalid-feedback">
                    Description is required.
                </div>
            </div>
            <div class="form-group">
                <label for="optionPrice">Option price: $</label>
                <input type="number" class="form-control" id="optionPrice" name="price" placeholder="" value="$${optionPrice}" required>
                <div class="invalid-feedback">
                    Valid option price is required.
                </div>
            </div>
            <div class="form-group">
                <label for="optionPayment">Monthly payment: $</label>
                <input type="number" class="form-control" id="optionPayment" name="payment" value="$${optionPayment}" required>
                <div class="invalid-feedback">
                    Please enter a valid payment.
                </div>
            </div>
        </div>

        <div class="col-md-4 order-md-2">

            <%--<div class="tab-content pre-scrollable" style="height: 313px">--%>
            <div class="tab-content pre-scrollable" id="compatibleOptions">
                <%--<div class="tab-pane fade active show" id="compatible">--%>

                    <jstl:forEach var="compatibleOption" items="${compatibleOptions}">

                        <jstl:import url="option_card.jsp">
                            <jstl:param name="optionCardId" value="compatibleOption${compatibleOption.id}" />
                            <jstl:param name="optionCardHeadId" value="compatibleOptionHead${compatibleOption.id}" />
                            <jstl:param name="cardOptionId" value="${compatibleOption.id}" />
                            <jstl:param name="cardOptionName" value="${compatibleOption.name}" />
                            <jstl:param name="cardOptionDescription" value="${compatibleOption.description}" />
                            <jstl:param name="cardOptionPrice" value="${compatibleOption.price}" />
                            <jstl:param name="cardOptionPayment" value="${compatibleOption.payment}" />
                            <jstl:param name="optionInputName" value="compatibleOptions" />
                            <jstl:param name="optionCardAttrs" value="" />
                        </jstl:import>
                    </jstl:forEach>
                <%--</div>--%>
            </div>
        </div>

        <div class="col-md-4 order-md-3">

            <%--<div class="tab-content pre-scrollable" style="height: 313px">--%>
            <div class="tab-content pre-scrollable" id="requiredOptions">
                <%--<div class="tab-pane fade active show" id="compatible">--%>
                <jstl:forEach var="compatibleOption" items="${compatibleOptions}">

                    <jstl:forEach var="requiredOption" items="${requiredOptions}">
                        <jstl:if test="${optionCard == requiredOption}">
                            <jstl:set var="optionCardAttrs" value="checked"/>
                        </jstl:if>
                    </jstl:forEach>

                    <jstl:import url="option_card.jsp">
                        <jstl:param name="optionCardId" value="requiredOption${compatibleOption.id}" />
                        <jstl:param name="optionCardHeadId" value="requiredOptionHead${compatibleOption.id}" />
                        <jstl:param name="cardOptionId" value="${compatibleOption.id}" />
                        <jstl:param name="cardOptionName" value="${compatibleOption.name}" />
                        <jstl:param name="cardOptionDescription" value="${compatibleOption.description}" />
                        <jstl:param name="cardOptionPrice" value="${compatibleOption.price}" />
                        <jstl:param name="cardOptionPayment" value="${compatibleOption.payment}" />
                        <jstl:param name="optionInputName" value="requiredOptions" />
                        <jstl:param name="optionCardAttrs" value="${optionCardAttrs}" />
                    </jstl:import>
                </jstl:forEach>
                <%--</div>--%>
            </div>
        </div>
    </div>
    <div class="row">
        <button class="btn btn-primary btn-lg btn-block mt-5" type="button" onclick="saveOption()">${buttonName}</button>
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

    checkedCompatibleOptions = []

    $('#requiredOptions :checkbox').change(function() {
        checkedRequiredOptions = []
        $('#requiredOptions :input:checked').each(function(idx, checkbox) {
            checkedRequiredOptions.push($(checkbox).val())
            console.log(checkbox)
        })
        console.log('checked required options: ' + checkedRequiredOptions)

        // var requiredOptions = JSON.stringify({'requiredOptions' : checkedRequiredOptions})
        var requiredOptions = {'requiredOptions' : checkedRequiredOptions}

        console.log('data: ' + requiredOptions)

        callREST('/TMobile/options/compatible', 'GET', requiredOptions, function(responseData, status, xhr) {
            console.log('response status :' + xhr.status)
            console.log('response data:' + responseData)

            checkedCompatibleOptions = []

            responseData.forEach(function(option) {


                optionCard = getOptionCard()
            })
        })
        // $.ajax({
        //     url : '/TMobile/options/compatible',
        //     method : 'GET',
        //     // data : encodeURI(requiredOptions),
        //     data : requiredOptions,
        //     traditional : true,
        //     success : function(responseData, status, xhr) {
        //         console.log('response status :' + xhr.status)
        //         console.log('response data:' + responseData)
        //
        //
        //     }
        // })
    })


    $('#compatibleOptions :checkbox').change(function() {
        // compatibleOptions = []
        if(this.checked) {
            checkedCompatibleOptions.push(this)
        } else {
            checkedCompatibleOptions
        }

        $('#compatibleOptions :input:checked').each(function(idx, checkbox) {
            compatibleOptions.push($(checkbox).val())
            console.log(checkbox)
        })
        console.log('checked compatible options: ' + compatibleOptions)
    })

    function addOptionCard(optionCardIdPrefix, optionCardHeadIdPrefix, option, checked) {
        var cardHTML =
            '<div class="card border-light mb-2 mr-2">' +
                '<div class="card-header d-flex justify-content-between lh-condensed" id="' + optionCardHeadIdPrefix + option.id + '">' +
                    '<h5 class="mb-0">' +
                        '<button class="btn btn-link" type="button" data-toggle="collapse" data-target="#requiredOption2" area-expanded="false" aria-controls="requiredOption2">' +
                            'Superhit 3G ' +
                        '</button>' +
                    '</h5>' +
                '<div class="custom-control custom-checkbox">' +
                    '<input type="checkbox" id="checkboxrequiredOption2" name="requiredOptions" value="2" class="custom-control-input"> ' +
                    '<label class="custom-control-label" for="checkboxrequiredOption2"></label> ' +
                '</div> ' +
            '</div> ' +
            '<div id="requiredOption2" class="collapse" aria-labelledby="requiredOptionHead2" data-parent="#requiredOptions"> ' +
                '<ul class="list-group list-group-flush"> ' +
                    '<li class="list-group-item d-flex justify-content-between lh-condensed"> ' +
                        '<span>Price:</span> ' +
                        '<span>$15</span> ' +
                    '</li> ' +
                '<li class="list-group-item d-flex justify-content-between lh-condensed"> ' +
                    '<span>Payment:</span> ' +
                    '<span>$10</span> ' +
                '</li> ' +
                '</ul> ' +
                '<div class="card-body">' +
                    'Serf world wide internet without any further delay! ' +
                '</div> ' +
            '</div> ' +
        '</div>'

    }


    function saveOption() {
        form = $('#optionData')
    }
    function register() {
        form = $('#contractData')
        <%--sendForm(form, form.attr('action'), function() {--%>
        <%--window.location.replace('<jstl:url value="/manager"/>')--%>
        <%--})--%>

        sendForm(form, form.attr('action'))
        setTimeout(function(){
            window.location.replace('<jstl:url value="/manager"/>')
        }, 1000)
    }

</script>
