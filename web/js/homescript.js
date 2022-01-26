var usr = document.getElementById("username");
sessionStorage.setItem("user", usr);

function greeting()
{
    let e = document.getElementsByClassName("greeting");
    var user = sessionStorage.getItem("user");
    e[0].insertAdjacentHTML('afterbegin','<h2 class="welcome-message">Welcome ' + user + '</h2>');    
}
