var isAdmin = false;

for(var i in user.authorities) {
    if(user.authorities[i].role === 'ADMIN') {
        isAdmin = true;
    }
}

if(isAdmin === false) {
    Location.href = "index.html";
}