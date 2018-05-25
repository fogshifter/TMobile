
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>

<!-- Custom styles for this template -->
<!--<link href="form-validation.css" rel="stylesheet">-->

<jstl:url var="compatibleOptionsURL" value="/options/compatible"/>
<jstl:url var="requiredOptionsURL" value="/options/required"/>

<jstl:url var="requiredOpsRestrictionsURL" value="/options/restrictions"/>
<jstl:url var="allOptionsURL" value="/options"/>
<jstl:set value="true" var="compatibleToAll"/>
<jstl:set value="Full Compatibility" var="compatToAllBtnText"/>


<jstl:if test="${page == 'OPTION'}">
    <jstl:set var="buttonName" value="Save"/>
    <jstl:set var="saveRequestType" value="PUT"/>
    <jstl:url value="/options" var="saveRequestURL"/>

    <jstl:if test="${!option.compatible}">
        <jstl:set var="compatibleToAll" value=""/>
        <jstl:set value="Partial Compatibility" var="compatToAllBtnText"/>
    </jstl:if>
</jstl:if>
<jstl:if test="${page == 'NEW_OPTION'}">
    <jstl:set value="" var="requiredOptions"/>
    <jstl:set var="buttonName" value="Create"/>
    <jstl:set var="saveRequestType" value="POST"/>
    <jstl:url value="/options" var="saveRequestURL"/>
</jstl:if>

<div class = "row">
    <h4 class = "col-md-4">Option information</h4>
    <h4 class = "col-md-4">Compatible options</h4>
    <h4 class = "col-md-4">Required options</h4>
</div>


<form action="<jstl:url value="/manager/options"/>" id="optionData" class="needs-validation" novalidate>
    <input type="hidden" name="id" value="${option.id}">
    <input type="hidden" id="compatible" name="compatible" value="${compatibleToAll}">
    <div class="row">
        <div class="col-md-4 order-md-1">
            <div class="form-group">
                <label for="optionName">Option name</label>
                <input class="form-control" type="text" id="optionName" name="name" placeholder="" value="${option.name}" required>
                <div class="invalid-feedback">
                    Option name is required.
                </div>
            </div>
            <div class="form-group">
                <label for="optionDescription">Option description</label>
                <input type="text" class="form-control" id="optionDescription" name="description" placeholder="" value="${option.description}" required>
                <div class="invalid-feedback">
                    Description is required.
                </div>
            </div>
            <div class="form-group">
                <label for="optionPrice">Option price: $</label>
                <input type="number" class="form-control" id="optionPrice" name="price" placeholder="" value="${option.price}" required>
                <div class="invalid-feedback">
                    Valid option price is required.
                </div>
            </div>
            <div class="form-group">
                <label for="optionPayment">Monthly payment: $</label>
                <input type="number" class="form-control" id="optionPayment" name="payment" value="${option.payment}" required>
                <div class="invalid-feedback">
                    Please enter a valid payment.
                </div>
            </div>
        </div>

        <div class="col-md-4 order-md-2">

            <%--<div class="tab-content pre-scrollable" style="height: 313px">--%>
            <div class="tab-content pre-scrollable" id="compatibleOptions">
                <%--<div class="tab-pane fade active show" id="compatible">--%>

                    <jstl:forEach var="compatibleOption" items="${allOptions}">
                        <jstl:set var="compatibleOptionsAttr" value=""/>
                        <jstl:if test="${option.compatible}">
                            <jstl:set var="compatibleOpticmonsAttr" value="checked"/>
                        </jstl:if>
                        <jstl:forEach var="compatOpId" items="${option.compatibleOptions}">
                            <jstl:if test="${compatibleOption.id == compatOpId}">
                                <jstl:set var="compatibleOptionsAttr" value="checked"/>
                            </jstl:if>
                        </jstl:forEach>

                        <jstl:import url="option_card.jsp">
                            <jstl:param name="optionCardId" value="compatibleOption${compatibleOption.id}" />
                            <jstl:param name="optionCardHeadId" value="compatibleOptionHead${compatibleOption.id}" />
                            <jstl:param name="cardOptionId" value="${compatibleOption.id}" />
                            <jstl:param name="cardOptionName" value="${compatibleOption.name}" />
                            <jstl:param name="cardOptionDescription" value="${compatibleOption.description}" />
                            <jstl:param name="cardOptionPrice" value="${compatibleOption.price}" />
                            <jstl:param name="cardOptionPayment" value="${compatibleOption.payment}" />
                            <jstl:param name="optionInputName" value="compatibleOptions" />
                            <jstl:param name="optionCardAttrs" value="${compatibleOptionsAttr}" />
                        </jstl:import>
                    </jstl:forEach>
                <%--</div>--%>
            </div>
        </div>

        <div class="col-md-4 order-md-3">

            <%--<div class="tab-content pre-scrollable" style="height: 313px">--%>
            <div class="tab-content pre-scrollable" id="requiredOptions">
                <%--<div class="tab-pane fade active show" id="compatible">--%>
                <jstl:forEach var="compatibleOption" items="${allOptions}">

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
    <div class="row justify-content-md-end">
        <div class="col-md-4">
            <button class="btn btn-primary btn-lg btn-block" type="button" id="compatibleSwitch" onclick="toggleCompatible()">${compatToAllBtnText}</button>
        </div>
        <div class="col-md-4">

            <button class="btn btn-primary btn-lg btn-block" type="button" onclick="saveOption()">${buttonName}</button>
        </div>
    </div>
</form>
<%--<div class="row">--%>
<%--<button class="col-4 btn btn-primary btn-lg btn-block col-auto" type="submit">Register</button>--%>
<%--</div>--%>
<%--</form>--%>

</div>
</div>


<script>

    registerRequiredListener()
    registerCompatibleListener()

    function registerRequiredListener() {
        $('#requiredOptions :checkbox').change(function() {

        checkedRequiredOptions = []
        $('#requiredOptions :input:checked').each(function(idx, checkbox) {
            checkedRequiredOptions.push($(checkbox).val())
            console.log(checkbox)
        })
        console.log('checked required options: ' + checkedRequiredOptions)

        if(checkedRequiredOptions.length == 0) {

            callREST('${allOptionsURL}', 'GET', null, function(responseData, status, xhr) {
                console.log('all required uncheched response status :' + xhr.status)
                console.log('all required uncheched response data:' + responseData)

                replaceOptionsList('required', responseData)
                replaceOptionsList('compatible', responseData)
                registerRequiredListener()
                registerCompatibleListener()
                setCompatible(true)

                $('#compatibleSwitch').prop('disabled', false)
            })



            return
        }

        var requiredOptions = {'requiredOptions' : checkedRequiredOptions.join(',')}

        console.log('data: ' + requiredOptions)

        callREST('${requiredOpsRestrictionsURL}', 'GET', requiredOptions, function(responseData, status, xhr) {
            console.log('compatible response status :' + xhr.status)
            console.log('compatible response data:' + responseData)

            replaceOptionsList('compatible', responseData['compatible'])
            replaceOptionsList('required', responseData['required'], 'checked')
            addOptionsList('required', responseData['compatible'])
            registerRequiredListener()
            registerCompatibleListener()

            var requiredOptionsCompatible = true
            responseData['required'].forEach(function(option, idx){

                if(option.compatible == false) {
                    requiredOptionsCompatible = false;
                }
            })

            if(requiredOptionsCompatible) {
                setCompatible(true)
            }
            else {
                setCompatible(false)
                $('#compatibleSwitch').prop('disabled', true)
            }

            // updateRequiredOptions(requiredOptions, responseData);
        })
    })
    }

    function registerCompatibleListener() {
        $('#compatibleOptions :checkbox').change(function() {
            if ($('#compatibleOptions :input:checked').length == $('#compatibleOptions :checkbox').length) {
                if ($('#requiredOptions :input:checked').length == 0) {
                    setCompatible(true)
                    $('#compatibleSwitch').prop('disabled', false)
                }
            }
            else {
                $('#compatible').val('false')
                $('#compatibleSwitch').text('Partial Compatibility')
                $('#compatibleSwitch').prop('disabled', false)
            }
        })
    }

    function addOptionsList(listType, options, checked = '') {
        if(listType == 'compatible') {
            options.forEach(function(option) {
                if(${option.id} != option.id) {
                    var optionCheckboxAttrs = ''
                    var optionCardIdPrefix = 'compatibleOption'
                    var optionCardHeadIdPrefix = 'compatibleOptionHead'
                    var optionInputName = 'compatibleOptions'
                    var compatibleOptionCard = getOptionCard(optionCardIdPrefix, optionCardHeadIdPrefix, optionInputName, optionCheckboxAttrs, option)
                    compatibleOptionCard.appendTo($('#compatibleOptions'))
                }
            })
        }
        else if(listType == 'required') {

            options.forEach(function(option){
                if(${option.id} != option.id) {
                    var optionCheckboxAttrs = checked
                    var optionCardIdPrefix = 'requiredOption'
                    var optionCardHeadIdPrefix = 'requiredOptionHead'
                    var optionInputName = 'requiredOptions'

                    var requiredOptionCard = getOptionCard(optionCardIdPrefix, optionCardHeadIdPrefix, optionInputName, optionCheckboxAttrs, option)
                    requiredOptionCard.appendTo($('#requiredOptions'))
                }
            })
        }
    }

    function replaceOptionsList(listType, options, checked = '') {

        if(listType == 'compatible') {
            var compatibleOptionsEl = $('#compatibleOptions')
            compatibleOptionsEl.empty()
        }
        else if(listType == 'required') {
            var requiredOptionsEl = $('#requiredOptions')
            requiredOptionsEl.empty()
        }
        addOptionsList(listType, options, checked)
    }

    function setCompatible(compatible) {
        compatibleEl = $('#compatible')
        compatibleBtn = $('#compatibleSwitch')
        compatibleCheckboxes = $('#compatibleOptions :checkbox')

        if(compatible == true) {
            compatibleCheckboxes.prop('checked', true)
            compatibleEl.val('true')
            compatibleBtn.text('Full Compatibility')
        }
        else {
            compatibleCheckboxes.prop('checked', false)
            compatibleEl.val('false')
            compatibleBtn.text('Partial Compatibility')
        }
    }

    function toggleCompatible() {

        compatible = $('#compatible').val()

        if(compatible == 'false') {
            setCompatible(true)
        }
        else {
            setCompatible(false)
        }
    }

    function saveOption() {
        form = $('#optionData').serializeObject()

        if(!Array.isArray(form.compatibleOptions)) {
            if('compatibleOptions' in form) {
                form.compatibleOptions = [form.compatibleOptions]
            }
            else {
                form.compatibleOptions = []
            }
        }

        if(!Array.isArray(form.requiredOptions)) {
            if('requiredOptions' in form) {
                form.requiredOptions = [form.requiredOptions]
            }
            else {
                form.requiredOptions = []
            }
        }

        var url = '${saveRequestURL}'
        callREST(url, '${saveRequestType}', JSON.stringify(form), function (responseData, status, xhr) {
            // window
        })

    }

</script>
