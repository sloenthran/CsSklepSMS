var user = JSON.parse(localStorage.getItem("user"));

jQuery.ajax ({
    url: shopUrl + "/login/refresh",
    type: "POST",
    dataType: "json",
    success: function(data){
        localStorage.token = data.token;
    },
    error: function() {
        localStorage.clear();
        location.href = "../login.html";    }
});

$('#header').load('include/header.html');
$('#sidebar-menu').load('include/menu.html');
$('#fotter').load('include/fotter.html');
