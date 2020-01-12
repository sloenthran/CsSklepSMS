var shopUrl = "http://localhost:8080";

if(!localStorage.user) {
    $.ajaxSetup({
        beforeSend: function (xhr)
        {
            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
        },
        dataType: 'json'
    });
} else {
    $.ajaxSetup({
        beforeSend: function (xhr)
        {
            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
            xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
        },
        dataType: 'json'
    });
}