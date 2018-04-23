
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>

<!-- Custom styles for this template -->
<!--<link href="form-validation.css" rel="stylesheet">-->

<div class = "row">
    <h4 class = "col-md-4">Customer info</h4>
</div>

<%--<form method="POST" onsubmit="javascript:void(0);" id="contractData" class="needs-validation" novalidate>--%>
<form method="POST" action="<jstl:url value="/manager/register_contract"/>" id="contractData" class="needs-validation" novalidate>
<div class="row">
    <div class="col-md-7 order-md-1">
        <div class="form-group row">
            <label for="firstName" class="col-3 col-form-label">First name</label>
            <div class="col-9">
                <input class="form-control" type="text" id="firstName" name="firstName" placeholder="" value="${contractInfo.firstName}" required>
                <div class="invalid-feedback">
                    First name is required.
                </div>
            </div>
        </div>
        <div class="form-group row">
            <label for="lastName" class="col-3 col-form-label">Last name</label>
            <div class="col-9">
                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="" value="${contractInfo.lastName}" required>
                <div class="invalid-feedback">
                  Last name is required.
                </div>
            </div>
        </div>
        <div class="form-group row">
          <label for="birth_date" class="col-3 col-form-label">Date of birth</label>
          <div class="col-9">
              <input type="date" class="form-control" id="birth_date" name="birthDate" placeholder="" value="${contractInfo.birthDate}" required>
              <div class="invalid-feedback">
                Valid date of birth is required.
              </div>
          </div>
        </div>
        <div class="form-group row">
          <label for="email" class="col-3 col-form-label">Email</label>
            <div class="col-9">
                <input type="email" class="form-control" id="email" name="email" value="${contractInfo.email}" placeholder="customer@mail.com" required>
                <div class="invalid-feedback">
                  Please enter a valid email address.
                </div>
            </div>
        </div>
        <div class="form-group row">
          <label for="password" class="col-3 col-form-label">Password</label>
          <div class="col-9">
            <input type="password" class="form-control" id="password" name="password" placeholder="" value="${contractInfo.password}" required>
            <div class="invalid-feedback">
              Password is required.
            </div>
          </div>
        </div>
        <div class="form-group row">
            <label for="address" class="col-3 col-form-label">Passport Data</label>
            <div class="col-9">
                <input type="text" class="form-control" id="passportData" name="passportData" value="${contractInfo.passportData}" placeholder="33631234 Pass number" required>
                <div class="invalid-feedback">
                    Passport data is required.
                </div>
            </div>
        </div>
        <div class="form-group row">
          <label for="address" class="col-3 col-form-label">Address</label>
          <div class="col-9">
              <input type="text" class="form-control" id="address" name="address" value="${contractInfo.address}" placeholder="1234 Main St" required>
              <div class="invalid-feedback">
                Please enter customer address.
              </div>
          </div>
        </div>
    </div>

    <div class="col-md-5 order-md-2" id="contract_accordion">

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

                  <jstl:set var="t_card_id" value="collapse_tariff_${tariff.id}"/>
                  <jstl:set var="t_head_id" value="t_head_${tariff.id}"/>

                  <div class="card border-light mb-2 mr-2">
                      <div class="card-header d-flex justify-content-between lh-condensed" id="${t_head_id}">
                          <h5 class="mb-0">
                              <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#${t_card_id}" area-expanded="false" aria-controls="${t_card_id}">
                                  ${tariff.name}
                              </button>
                          </h5>

                          <div class="custom-control custom-radio">
                              <input type="radio" id="tar_id_${tariff.id}" name="tariffId" value="${tariff.id}" class="custom-control-input">
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

                  <div class="card border-light mb-2 mr-2">
                      <div class="card-header d-flex justify-content-between lh-condensed" id="${o_head_id}">
                          <h5 class="mb-0">
                              <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#${o_card_id}" area-expanded="false" aria-controls="${o_card_id}">
                                      ${option.name}
                              </button>
                          </h5>
                          <div class="custom-control custom-checkbox">
                              <input type="checkbox" class="custom-control-input" name="optionIds" id="opt_id_${option.id}" value="${option.id}" checked="">
                              <label class="custom-control-label" for="opt_id_${option.id}"></label>
                          </div>
                      </div>

                      <%--<div id="${o_card_id}" class="collapse" aria-labelledby="${o_head_id}" data-parent="#options_accordion">--%>
                      <div id="${o_card_id}" class="collapse" aria-labelledby="${o_head_id}" data-parent="#contract_accordion">
                          <ul class="list-group list-group-flush">
                              <li class="list-group-item d-flex justify-content-between lh-condensed">
                                  <span>Price:</span>
                                  <span>$${option.price}</span>
                              </li>
                              <li class="list-group-item d-flex justify-content-between lh-condensed">
                                  <span>Payment:</span>
                                  <span>$${option.payment}</span>
                              </li>
                          </ul>
                          <div class="card-body">
                                  ${option.description}
                          </div>
                      </div>
                  </div>
              </jstl:forEach>
          </div>
        </div>
        <%--<button class="btn btn-primary btn-lg btn-block mt-5" type="button" onclick="register()">Register</button>--%>
        <button class="btn btn-primary btn-lg btn-block mt-5" type="button" onclick="register()">Register</button>
    </div>
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

    function register() {
        form = $('#contractData')
        sendForm(form, form.attr('action'))
    }

    $('#contractData :input').change(function() {

        var url = '<jstl:url value="/manager/sync_new_contract_info/"/>'
        sendForm($('#contractData'), url)
    })

</script>
