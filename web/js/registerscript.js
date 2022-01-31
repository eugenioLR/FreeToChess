$(document).ready(function()
{
    const xhr = new XMLHttpRequest();
    $("#registerbutton").on("click", function(){
        var usr = document.getElementById("usernm").value;
        var pwd = document.getElementById("pass").value;
        var email_input = document.getElementById("email").value;
        var paypal = document.getElementById("paypal").value;
        if (usr=="" && pwd=="" && email_input=="" && email_input=="")return;
        xhr.open("post","http://localhost:8080/users");
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.send(JSON.stringify({name:usr,email:email_input,password:pwd,paypal_id:paypal}));
        localStorage.setItem('board','basic');
        localStorage.setItem('pieces','fantasy');
        alert("User created.");
        window.location.replace("index.html");
    });    
});
