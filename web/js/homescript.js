
function getstats()
{
    const xhr = new XMLHttpRequest();
    let e = document.getElementsByClassName("stats");
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            var username = array_json.user;
            var coins = array_json.coins;
            var diamonds = array_json.diamonds;
            var exp = array_json.coins;
            e[0].insertAdjacentHTML('afterbegin',
             '<p class="nickname">' + username + '</p">'
            + '<div class="money-stats">'
            + '<p class="currencies"><img src="img/diamond.png" class="money-icon">' + diamonds + '</p>'
            + '<p class="currencies"><img src="img/coin.png" class="money-icon">' + coins + '</p>' 
            + '</div>'
            + '<p class="exp">' + exp + '</p>'
            );
        }
    }
    xhr.open("get", "http://localhost:8080/users/user");
    xhr.send();
}

getstats();
// var usr = document.getElementById("username");
// sessionStorage.setItem("user", usr);

// function greeting()
// {
//     let e = document.getElementsByClassName("greeting");
//     var user = sessionStorage.getItem("user");
//     e[0].insertAdjacentHTML('afterbegin','<h2 class="welcome-message">Welcome ' + user + '</h2>');    
// }