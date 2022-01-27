function validateUser()
{
    var usr = document.getElementById("usernm").value;
    var pwd = document.getElementById("pass").value;
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = async function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            var passwd = array_json[0]["password"];
            var salt = array_json[0]["salt"];
            const msgBuffer = new TextEncoder().encode(pwd+salt);
            const hashBuffer = await crypto.subtle.digest('SHA-256', msgBuffer);
            const hashArray = Array.from(new Uint8Array(hashBuffer));
            const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
            if (hashHex == passwd)
            {
                window.location.replace("http://127.0.0.1:5500/home.html");
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
