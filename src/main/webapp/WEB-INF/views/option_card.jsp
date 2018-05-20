<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>


<%--<jstl:set var="optionCardId" value="compatOption${param.cardOptionId}"/>--%>
<%--<jstl:set var="optionCardHeadId" value="compatOptionHead${param.cardOptionId}"/>--%>

<div class="card border-light mb-2 mr-2">
    <div class="card-header d-flex justify-content-between lh-condensed" id="${param.optionCardHeadId}">
        <h5 class="mb-0">
            <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#${param.optionCardId}" area-expanded="false" aria-controls="${param.optionCardId}">
                ${param.cardOptionName}
            </button>
        </h5>

        <div class="custom-control custom-checkbox">
            <input type="checkbox" id="checkbox${param.optionCardId}" name="${param.optionInputName}" value="${param.cardOptionId}" class="custom-control-input" ${param.optionCardAttrs}>
            <label class="custom-control-label" for="checkbox${param.optionCardId}"></label>
        </div>
    </div>

    <div id="${param.optionCardId}" class="collapse" aria-labelledby="${param.optionCardHeadId}" data-parent="#${param.optionInputName}">
        <ul class="list-group list-group-flush">
            <li class="list-group-item d-flex justify-content-between lh-condensed">
                <span>Price:</span>
                <span>$${param.cardOptionPrice}</span>
            </li>
            <li class="list-group-item d-flex justify-content-between lh-condensed">
                <span>Payment:</span>
                <span>$${param.cardOptionPayment}</span>
            </li>
        </ul>
        <%--<div id="${t_card_id}" class="collapse" aria-labelledby="${t_head_id}" data-parent="#contract_accordion">--%>
        <div class="card-body">
            ${param.cardOptionDescription}
        </div>
    </div>
</div>
