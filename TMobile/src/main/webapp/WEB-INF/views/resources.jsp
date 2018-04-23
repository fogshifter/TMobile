<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>

<!-- Bootstrap core CSS -->
<link href="<jstl:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">


<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.parameterName}"/>

<!-- Bootstrap core JavaScript
================================================== -->
<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>--%>

<script
        src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous">
</script>

<script>
$(function () {
    var token = '${_csrf.token}';
    var header = '${_csrf.headerName}';
    $(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
    });
});
</script>

<script src="<jstl:url value="/resources/js/sendForm.js"/>"></script>

<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script src="<jstl:url value="/resources/js/popper.min.js"/>"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
