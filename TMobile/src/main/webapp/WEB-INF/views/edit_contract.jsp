
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>

<!-- Custom styles for this template -->
<!--<link href="form-validation.css" rel="stylesheet">-->

<!--<body class="bg-light">-->

<%--<div class="container">--%>
<div class = "row">
    <h4 class = "col-md-4">Customer info</h4>
    <!--<h4 class = "col-md-4">Tariff</h4>
    <h4 class = "col-md-4">Options</h4>-->

</div>
<%--<form class="needs-validation" novalidate>--%>
<%--<form method="POST" onsubmit="javascript:void(0);" id="contractData" class="needs-validation" novalidate>--%>
    <div class="row">
        <div class="col-md-7 order-md-1">
            <%--<form class="needs-validation" novalidate>--%>
            <div class="form-group row">
                <label for="firstName" class="col-3 col-form-label">First name</label>
                <div class="col-9">
                    <input class="form-control" type="text" id="firstName" value="${contractInfo.firstName}" disabled>
                </div>
            </div>
            <div class="form-group row">
                <label for="lastName" class="col-3 col-form-label">Last name</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="lastName"value="${contractInfo.lastName}" disabled>
                </div>
            </div>
            <div class="form-group row">
                <label for="birthDate" class="col-3 col-form-label">Date of birth</label>
                <div class="col-9">
                    <input type="date" class="form-control" id="birthDate" value="${contractInfo.birthDate}" disabled>
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-3 col-form-label">Phone</label>
                <div class="col-9">
                    <input type="email" class="form-control" id="phone" value="${contractInfo.phone}" disabled>
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-3 col-form-label">Email</label>
                <div class="col-9">
                    <input type="email" class="form-control" id="email" value="${contractInfo.email}" disabled>
                </div>
            </div>
            <div class="form-group row">
                <label for="address" class="col-3 col-form-label">Passport Data</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="passportData" value="${contractInfo.passportData}" disabled>
                </div>
            </div>
            <div class="form-group row">
                <label for="address" class="col-3 col-form-label">Address</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="address" value="${contractInfo.address}" disabled>
                </div>
            </div>

            <%--</form>--%>
        </div>

        <%--<div class="col-md-5 order-md-2" id="tariffs_accordion">--%>
        <div class="col-md-5 order-md-2" id="contract_accordion">
            <form method="POST" onsubmit="javascript:void(0);" id="contractData" customerId="${contractInfo.customerId}" novalidate>
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link active show" data-toggle="tab" href="#tariffs">Tariffs</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#options">Options</a>
                    </li>
                </ul>
                <div id="myTabContent" class="tab-content pre-scrollable" style="height: 260px">
                    <div class="tab-pane fade active show" id="tariffs">
                        <jstl:forEach var="tariff" items="${tariffs}">

                            <jstl:set var="t_card_id" value="collapse_tariff_${tariff.id}"/>
                            <jstl:set var="t_head_id" value="t_head_${tariff.id}"/>
                            <jstl:set var="tariffChecked" value=""/>
                            <jstl:if test="${tariff.id == contractInfo.tariffId}">
                                <jstl:set var="tariffChecked" value = "checked"/>
                            </jstl:if>

                            <div class="card border-light mb-2 mr-2">
                                <div class="card-header d-flex justify-content-between lh-condensed" id="${t_head_id}">
                                    <h5 class="mb-0">
                                        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#${t_card_id}" area-expanded="false" aria-controls="${t_card_id}">
                                                ${tariff.name}
                                        </button>
                                    </h5>

                                    <div class="custom-control custom-radio">
                                        <input type="radio" id="tar_id_${tariff.id}" name="tariffId" value="${tariff.id}" class="custom-control-input" ${tariffChecked}>
                                        <label class="custom-control-label" for="tar_id_${tariff.id}"></label>
                                    </div>
                                </div>

                                <div id="${t_card_id}" class="collapse" aria-labelledby="${t_head_id}" data-parent="#contract_accordion">
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                                            <span>Price:</span>
                                            <span>$${tariff.price}</span>
                                        </li>
                                    </ul>
                                        <%--<div id="${t_card_id}" class="collapse" aria-labelledby="${t_head_id}" data-parent="#contract_accordion">--%>
                                    <div class="card-body">
                                            ${tariff.description}
                                    </div>
                                </div>
                            </div>

                        </jstl:forEach>
                    </div>
                    <div class="tab-pane fade" id="options">
                        <jstl:forEach var="option" items="${options}">

                            <jstl:set var="o_card_id" value="collapse_option_${option.id}"/>
                            <jstl:set var="o_head_id" value="o_head_${option.id}"/>
                            <jstl:set var="optionChecked" value=""/>

                            <jstl:forEach var="contractOptionId" items="${contractInfo.optionIds}">
                                <jstl:if test="${contractOptionId == option.id}">
                                    <jstl:set var="optionChecked" value = "checked"/>
                                    <%--<jstl:if test="${contractOptionId == option.id}">--%>
                                        <%--<jstl:set var="optionDisabled" value="disabled"/>--%>
                                    <%--</jstl:if>--%>
                                </jstl:if>
                            </jstl:forEach>

                            <div class="card border-light mb-2 mr-2">
                                <div class="card-header d-flex justify-content-between lh-condensed" id="${o_head_id}">
                                    <h5 class="mb-0">
                                        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#${o_card_id}" area-expanded="false" aria-controls="${o_card_id}">
                                                ${option.name}
                                        </button>
                                    </h5>
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" name="optionIds" id="opt_id_${option.id}" value="${option.id}"  ${optionChecked}>
                                        <label class="custom-control-label" for="opt_id_${option.id}"></label>
                                    </div>
                                </div>

                                    <%--<div id="${o_card_id}" class="collapse" aria-labelledby="${o_head_id}" data-parent="#options_accordion">--%>
                                <div id="${o_card_id}" class="collapse" aria-labelledby="${o_head_id}" data-parent="#contract_accordion">
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                                            <span>Price:</span>
                                            <span id="option_price">${option.price}</span>
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                                            <span>Payment:</span>
                                            <span id="option_payment">${option.payment}</span>
                                        </li>
                                    </ul>
                                    <div class="card-body" id="option_desc">
                                            ${option.description}
                                    </div>
                                </div>
                            </div>
                        </jstl:forEach>
                    </div>
                </div>
                <button class="btn btn-primary btn-lg btn-block mt-5" type="button" onclick="saveContract()">Save</button>
            </form>
        </div>
    </div>
<%--</form>--%>
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

    $('#contractData:input').change(function() {

        var url = <jstl:url value="/contract/sync_contract_info/"/>
        sendForm($('#contractData'), url, function(options, status){

            temp = $('#options.card:first').clone()
            $('#options.card').remove()

            optionsEl = $('#options')

            options.forEach(function(option) {

                card = temp.clone();
                cardHeader = card.find('.card-header')

                var cardHeaderId = 'o_head_' + option['id']
                var cardId = 'collapse_option_' + option['id']

                cardHeader.attr('id', cardHeaderId)
                cardHeader.find('button')
                    .text(option['name'])
                    .attr('data-target', cardId)
                    .attr('aria-controls',cardId)

                cardHeader.find('input')
                    .attr('id', 'opt_id_' + option['id'])
                    .attr('value', option['id'])

                cardHeader.find('label')
                    .attr('for', 'opt_id_' + option['id'])

                card.find('#option_price')
                    .text(option['price'])

                card.find('#option_payment')
                    .text(option['payment'])

                card.find('#option_desc')
                    .text(option['description'])

                optionsEl.append(card)
            })
        })
    })


    function syncContractInfo() {
        contractData = $('#contractData').serialize()


        $('#email').change(function(){
            var email = $(this).val();
            $.get({
                dataType: 'json',
                data : {'email': email},
                url : '<jstl:url value="/contract/check_email_uniqueness"/>',
                // http://localhost:8080/TMobile/contract/check_email_uniqueness/',
                success : function(response, textStatus) {
                    console.log(response)
                }
            })
        })
</script>
