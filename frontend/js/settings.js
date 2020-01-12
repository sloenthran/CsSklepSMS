$.ajaxSetup({
    beforeSend: function (xhr)
    {
        xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
    },
    dataType: 'json'
});

var shopUrl = "http://localhost:8080";