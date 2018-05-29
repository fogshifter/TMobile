
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--<%@ page import="com.tmobile.util.Types.ContractBlocked" %>--%>

<!-- Custom styles for this template -->
<!--<link href="form-validation.css" rel="stylesheet">-->

<sec:authorize access="hasRole('MANAGER')">
    <jstl:set value="MANAGER" var="user"/>
</sec:authorize>
<sec:authorize access="hasRole('CUSTOMER')">
    <jstl:set value="CUSTOMER" var="user"/>
</sec:authorize>

<jstl:set var="contractBlocked" value=""/>
<jstl:set var="customerInfoEditable" value=""/>
<jstl:set var="actionMethod" value="POST"/>
<jstl:url var="syncInfoURL" value="/manager/sync_new_contract_info"/>
<jstl:url var="requiredOptionsURL" value="/options/required"/>
<jstl:url var="blockContractURL" value="/contracts/block/"/>
<jstl:url var="comatibleOptionsURL" value="/tariffs/compatible/"/>
<jstl:set var="saveBtnText" value="Create"/>

<jstl:if test="${page != 'NEW_CONTRACT'}">
    <%--<jstl:set var="customerInfoEditable" value="disabled"/>--%>
    <jstl:set var="blockBtnClb" value="blockContract()"/>
    <jstl:set var="blockBtnText" value="Block"/>
    <jstl:set var="blockBtnClass" value=""/>
    <jstl:set var="saveBtnText" value="Save"/>

    <jstl:set var="actionMethod" value="PUT"/>
    <jstl:set var="customerInfoEditable" value="disabled"/>


    <jstl:if test="${contractInfo.blocked != 0}">
        <jstl:set var="blockBtnClb" value="unblockContract()"/>
        <jstl:set var="contractBlocked" value="disabled"/>
        <jstl:set var="blockBtnText" value="Unblock"/>
        <jstl:set var="blockBtnClass" value="disabled"/>
        <jstl:set var="blockBtnAttr" value=""/>

        <jstl:if test="${user == 'CUSTOMER' && contractInfo.blocked == 2}">
            <jstl:set var="blockBtnClb" value=""/>
            <jstl:set var="blockBtnAttr" value="disabled"/>
        </jstl:if>

    </jstl:if>

</jstl:if>

<jstl:if test="${user == 'CUSTOMER'}">
    <jstl:url value="/customer" var="redirectURL"/>
</jstl:if>
<jstl:if test="${user == 'MANAGER'}">
    <jstl:url value="/manager" var="redirectURL"/>
</jstl:if>


<form method="${actionMethod}" action="<jstl:url value="/contracts/"/>" id="contractData" customerId="${contractInfo.customerId}" novalidate>

<input type="hidden" id = "contractId" name="contractId" value="${contractInfo.contractId}">
<input type="hidden" name="blocked" value="${contractInfo.blocked}">

<div class = "row">
    <h4 class = "col-md-4">Customer info</h4>
</div>
<%--<form class="needs-validation" novalidate>--%>
<%--<form method="POST" onsubmit="javascript:void(0);" id="contractData" class="needs-validation" novalidate>--%>
<div class="row">
        <div class="col-md-7 order-md-1" id="inputFields">
            <%--<form class="needs-validation" novalidate>--%>
            <div class="form-group row">
                <label for="firstName" class="col-3 col-form-label">First name</label>
                <div class="col-9">
                    <input class="form-control" type="text" id="firstName" name="firstName" value="${contractInfo.firstName}" ${customerInfoEditable}>
                </div>
            </div>
            <div class="form-group row">
                <label for="lastName" class="col-3 col-form-label">Last name</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="lastName" name="lastName" value="${contractInfo.lastName}" ${customerInfoEditable}>
                </div>
            </div>
            <div class="form-group row">
                <label for="birthDate" class="col-3 col-form-label">Date of birth</label>
                <div class="col-9">
                    <input type="date" class="form-control" id="birthDate" name="birthDate" value="${contractInfo.birthDate}" ${customerInfoEditable}>
                </div>
            </div>
                <jstl:if test="${page != 'NEW_CONTRACT'}">
            <div class="form-group row">
                <label for="phone" class="col-3 col-form-label">Phone</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="phone" name="phone" value="${contractInfo.phone}" ${customerInfoEditable}>
                </div>
                </jstl:if>
            </div>
            <div class="form-group row">
                <label for="email" class="col-3 col-form-label">Email</label>
                <div class="col-9">
                    <input type="email" class="form-control" id="email" name="email" value="${contractInfo.email}" placeholder="customer@mail.com" ${customerInfoEditable}>
                </div>
            </div>
            <jstl:if test="${page == 'NEW_CONTRACT'}">
            <div class="form-group row">
               <label for="password" class="col-3 col-form-label">Password</label>
               <div class="col-9">
                 <input type="password" class="form-control" id="password" name="password" placeholder="" value="${contractInfo.password}" required>
               </div>
            </div>
            </jstl:if>
            <div class="form-group row">
                <label for="passportData" class="col-3 col-form-label">Passport Data</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="passportData" name="passportData" value="${contractInfo.passportData}" ${customerInfoEditable}>
                </div>
            </div>
            <div class="form-group row">
                <label for="address" class="col-3 col-form-label">Address</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="address" name="address" value="${contractInfo.address}" ${customerInfoEditable}>
                </div>
            </div>

            <%--</form>--%>
        </div>

        <%--<div class="col-md-5 order-md-2" id="tariffs_accordion">--%>
        <div class="col-md-5 order-md-2" id="contract_accordion">
            <%--<form method="POST" onsubmit="javascript:void(0);" id="contractData" customerId="${contractInfo.customerId}" novalidate>--%>
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link active show" data-toggle="tab" href="#tariffs">Tariffs</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#options">Options</a>
                    </li>
                </ul>
                <div id="myTabContent" class="tab-content pre-scrollable" style="height: 315px">
                    <div class="tab-pane fade active show" id="tariffs">
                        <jstl:forEach var="tariff" items="${tariffs}">

                            <jstl:set var="tariffCardId" value="collapse_tariff_${tariff.id}"/>
                            <jstl:set var="tariffHeadId" value="t_head_${tariff.id}"/>
                            <jstl:set var="tariffAttrs" value="${contractBlocked}"/>
                            <jstl:if test="${tariff.id == contractInfo.tariffId}">
                                <jstl:set var="tariffAttrs" value = "checked ${contractBlocked}"/>
                            </jstl:if>

                            <div class="card border-light mb-2 mr-2">
                                <div class="card-header d-flex justify-content-between lh-condensed" id="${tariffHeadId}">
                                    <h5 class="mb-0">
                                        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#${tariffCardId}" area-expanded="false" aria-controls="${tariffCardId}">
                                                ${tariff.name}
                                        </button>
                                    </h5>

                                    <div class="custom-control custom-radio">
                                        <input type="radio" id="tar_id_${tariff.id}" name="tariffId" value="${tariff.id}" class="custom-control-input" ${tariffAttrs}>
                                        <label class="custom-control-label" for="tar_id_${tariff.id}"></label>
                                    </div>
                                </div>

                                <div id="${tariffCardId}" class="collapse" aria-labelledby="${tariffHeadId}" data-parent="#contract_accordion">
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

                            <jstl:set var="optionAttrs" value="${contractBlocked}"/>

                            <jstl:forEach var="contractOptionId" items="${contractInfo.optionIds}">
                                <jstl:if test="${contractOptionId == option.id}">
                                    <jstl:set var="optionAttrs" value = "checked ${contractBlocked}"/>
                                </jstl:if>
                            </jstl:forEach>

                            <jstl:import url="option_card.jsp">
                                <jstl:param name="optionCardId" value="collapse_option_${option.id}" />
                                <jstl:param name="optionCardHeadId" value="o_head_${option.id}" />
                                <jstl:param name="cardOptionId" value="${option.id}" />
                                <jstl:param name="cardOptionName" value="${option.name}" />
                                <jstl:param name="cardOptionDescription" value="${option.description}" />
                                <jstl:param name="cardOptionPrice" value="${option.price}" />
                                <jstl:param name="optionInputName" value="optionIds" />
                                <jstl:param name="optionCardAttrs" value="${optionAttrs}" />

                            </jstl:import>

                        </jstl:forEach>
                    </div>
                </div>
                <%--<button class="btn btn-primary btn-lg btn-block mt-5" type="button" onclick="saveContract()">Save</button>--%>
            <%--<input type="hidden" name="contractId" value="${contractInfo.contractId}">--%>

            <%--</form>--%>
        </div>
    </div>

<div class="row justify-content-md-end">
    <jstl:if test="${page != 'NEW_CONTRACT'}">
        <div class="col-5">

            <button class="btn btn-primary btn-lg btn-block ${blockButtonClass}" type="button" id="blockBtn" onclick="${blockBtnClb}" ${blockBtnAttr}>${blockBtnText}</button>
        </div>
    </jstl:if>

    <div class="col-5">
        <button class="btn btn-primary btn-lg btn-block" type="button" id="saveBtn" onclick="saveContract()" ${contractBlocked}>${saveBtnText}</button>
    </div>
</div>

</form>

<script>

    registerTariffsListener();
    registerOptionsListener();

    function blockContract() {

        var contarctId = $('#contractId').val()//formEl = $('#contractData')
        callREST('${blockContractURL}' + contarctId, 'PUT', JSON.stringify(true), function() {
            window.location.reload();
        })
    }

    function unblockContract() {
        var contarctId = $('#contractId').val()//formEl = $('#contractData')
        callREST('${blockContractURL}' + contarctId, 'PUT', JSON.stringify(false), function() {
            window.location.reload();
        })
    }

    function updateOptions(options) {
        console.log(options)

        var optionsEl = $('#options')
        optionsEl.empty()

        options.forEach(function(option){
            var optionCheckboxAttrs = '${contractBlocked}'
            var optionCardIdPrefix = 'collapse_option_'
            var optionCardHeadIdPrefix = 'o_head_'
            var optionInputName = 'optionIds'

            getOptionCard(optionCardIdPrefix, optionCardHeadIdPrefix, optionInputName, optionCheckboxAttrs, option).appendTo(optionsEl)
        })
    }

    function registerTariffsListener() {
        $('#tariffs :input').change(function() {
            if(this.checked) {
                $(this).val()

                var url = '${comatibleOptionsURL}' + $(this).val()

                callREST(url, 'GET', null, function(options) {
                    updateOptions(options)
                    registerOptionsListener()
                    registerChangeListener()
                })
            }
        })
    }

    function registerOptionsListener() {
        optionsCheckboxes = $('#options :input')
        optionsCheckboxes.change(function() {
            checkedOptionsElements = optionsCheckboxes.filter(':checked')

            if(checkedOptionsElements.length == 0) {
                return;
            }

            checkedOptions = []
            checkedOptionsElements.not(':disabled').each(function(idx, checkbox) {
                checkedOptions.push($(checkbox).val())
            })

            if(checkedOptions.length == 0) {
                checkedOptionsElements.filter(':disabled').prop('disabled', false).prop('checked', false)
                return
            }

            data = {'optionsIds' : checkedOptions.join(',')}
            callREST('${requiredOptionsURL}', 'GET', data, function(responseData, status, xhr) {
                checkedOptionsElements.filter(':disabled').prop('disabled', false).prop('checked', false)

                responseData.forEach(function(option) {
                    requiredOptionEl = optionsCheckboxes.not(':checked').filter('#checkboxcollapse_option_' + option.id)
                    requiredOptionEl.prop('disabled', true)
                    requiredOptionEl.prop('checked', true)
                })

                sync_new_contract()
            })
        })

        //checkboxcollapse_option_5
    }

    function sync_new_contract() {
    <jstl:if test="${page == 'NEW_CONTRACT'}">
        var formData = getContractInfo()
        callREST('${syncInfoURL}', 'PUT', JSON.stringify(formData))
    </jstl:if>
    }
    registerChangeListener();

    function registerChangeListener() {
        $('#contractData :input').change(function() {
            sync_new_contract()
            // var formData = $('#contractData').serializeObject()

            <%--callREST('${syncInfoURL}', 'PUT', JSON.stringify(formData), updateOptions)--%>
        })
    }


    function getContractInfo() {
        var contractData = {}
        $('#inputFields :input').each(function(idx, inputEl){
            var name = $(inputEl).attr('name')
            var val =  $(inputEl).val()
            contractData[name] = val
        })

        contractData.tariffId = $('#tariffs :input:checked').val()

        contractData.optionIds = []

        $('#options :input:checked').each(function(idx, optionEl){
            contractData.optionIds.push($(optionEl).val())
        })

        contractData.contractId = $('#contractId').val()

        return contractData
    }

    function saveContract() {

        contractData = getContractInfo()
        console.log('contractData: ' + contractData)

        var formEl = $('#contractData')
        var url = formEl.attr('action')
        var method = formEl.attr('method')

        callREST(url, method, JSON.stringify(contractData), function(responseData, status, xhr) {
            window.location.href = '${redirectURL}'
        })
    }

</script>
