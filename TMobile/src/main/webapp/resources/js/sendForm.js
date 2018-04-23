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