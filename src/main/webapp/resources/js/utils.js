jQuery.fn.serializeObject = function() {
    var arrayData, objectData;
    arrayData = this.serializeArray();
    objectData = {};

    $.each(arrayData, function() {
        var value;

        if (this.value != null) {
            value = this.value;
        } else {
            value = '';
        }

        if (objectData[this.name] != null) {
            if (!objectData[this.name].push) {
                objectData[this.name] = [objectData[this.name]];
            }

            objectData[this.name].push(value);
        } else {
            objectData[this.name] = value;
        }
    });

    return objectData;
};

function callREST(url, method, jsonData, clb) {
    $.ajax({
        method: method,
        url: url,
        dataType: 'json',
        data: jsonData,
        contentType : 'application/json; charset=UTF-8',
        success: function(data, status) {
            console.log('response status: ' + status)
            console.log('response data: ' + data)
            clb(data)
        }
    })
}

function getOptionCard(optionCardIdPrefix, optionCardHeadIdPrefix, optionInputName, checkboxAttrs, option) {

    var optionCardId = optionCardIdPrefix + option.id;
    var optionCardHeadId = optionCardHeadIdPrefix + option.id;

    // var optionInputName

    var cardHTML =
        '<div class="card border-light mb-2 mr-2">' +
            '<div class="card-header d-flex justify-content-between lh-condensed" id="' + optionCardHeadId + '">' +
                '<h5 class="mb-0">' +
                    '<button class="btn btn-link" type="button" data-toggle="collapse" data-target="#' + optionCardId + '" area-expanded="false" aria-controls="' + optionCardId +'">' +
                        option.name +
                    '</button>' +
                '</h5>' +
                '<div class="custom-control custom-checkbox">' +
                    '<input type="checkbox" id="checkbox' + optionCardId + '" name="'+ optionInputName +'" value="'+ option.id +'" class="custom-control-input"'+ checkboxAttrs +'> ' +
                    '<label class="custom-control-label" for="checkbox'+ option.id +'"></label> ' +
                '</div> ' +
            '</div> ' +
            '<div id="'+ optionCardId +'" class="collapse" aria-labelledby="'+ optionCardHeadId +'" data-parent="#'+ optionInputName +'"> ' +
                '<ul class="list-group list-group-flush"> ' +
                    '<li class="list-group-item d-flex justify-content-between lh-condensed"> ' +
                        '<span>Price:</span> ' +
                        '<span>$' + option.price + '</span> ' +
                    '</li> ' +
                    '<li class="list-group-item d-flex justify-content-between lh-condensed"> ' +
                        '<span>Payment:</span> ' +
                        '<span>$' + option.payment + '</span> ' +
                    '</li> ' +
                '</ul> ' +
                '<div class="card-body">' + option.description + '</div> ' +
            '</div> ' +
        '</div>'

    return $(cardHTML)
}

function sendForm(form, url, successClb) {

    // form = $(formId)
    formData = form.serializeObject();
    formMethod = form.attr('method')

    $.ajax({
        method : formMethod,
        dataType: 'json',
        data : JSON.stringify(formData),
        // data : JSON.stringify({
        //     'customerId': 1,
        //     'tariffId': 1,
        //     'optionIds': [1, 2, 3],
        //     'firstName': 'First Name',
        //     'lastName': 'Last Name',
        //     'birthDate': '20-10-2018',
        //     'email': 'mail@gmail.com',
        //     'address': 'address',
        //     'phone': '8902124524',
        //     'password': '123456',
        //     'passportData': 'data'
        // }),
        contentType : 'application/json; charset=UTF-8',
        url : url,
        success: successClb
        })
}