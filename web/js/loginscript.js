
async function validateUser()
{
    const encoder = new TextEncoder();
    var usr = document.getElementById("username");
    var pwd = document.getElementById("pass");
    const pwd1 = encoder.encode(pwd);
    const hash = await crypto.subtle.digest('SHA-256', pwd1);
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            var salt = array_json[0].salt;
            var password = array_json[0].password;
            if (hash == array_json)
            {
                // window.location.replace("http://127.0.0.1:5500/home.html");
                window.location.replace("google.es");
            }
        }
    }
    var direction = "http://localhost:8080/users/" + usr;
    xhr.open("get",direction, true);
    xhr.send();
}

$(document).ready(function(){
    $("#loginbutton").on("click", function(){
        validateUser();
    })
});
