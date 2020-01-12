var user = JSON.parse(localStorage.getItem("user"));

if(!user) {
    location.href = "../login.html";
}

$('#header').load('include/header.html');
$('#sidebar-menu').load('include/menu.html');
$('#fotter').load('include/fotter.html');
