function delay(delayInms) {
    return new Promise(resolve => {
      setTimeout(() => {
        resolve(2);
      }, delayInms);
    });
}

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
            var passwd = array_json.password;
            var salt = array_json.salt;
            const msgBuffer = new TextEncoder().encode(pwd+salt);
            const hashBuffer = await crypto.subtle.digest('SHA-256', msgBuffer);
            const hashArray = Array.from(new Uint8Array(hashBuffer));
            const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
            if (hashHex == passwd)
            {
                await delay(1500);
                localStorage.setItem('username',usr);
                window.location.replace("home.html");
            }
            else 
            {
                window.alert("Haha, no se sabe su contraseña!");
                await delay(1500);
            }
        }
    }
    var direction = "http://localhost:8080/users/" + usr;
    xhr.open("get",direction, true);
    xhr.send();
}

function cleanCache()
{
    if (localStorage.getItem('username') != null)
    {
        localStorage.removeItem('username');
    }
}

$(document).ready(function(){
    $("#loginbutton").on("click", function(){
        validateUser();
    })
});

cleanCache();

console.log(localStorage.getItem("board"));
console.log(localStorage.getItem("pieces"));