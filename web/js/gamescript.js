function getstats()
{
    const xhr = new XMLHttpRequest();
    let e = document.getElementsByClassName("stats-div");
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            var username = array_json.name;
            var coins = array_json.coins;
            var diamonds = array_json.diamonds;
            var level = array_json.level;
            e[0].insertAdjacentHTML('afterbegin',
             '<p class="nickname">' + username + '</p">'
            + '<div class="money-stats">'
            + '<p class="currencies"><img src="img/diamond.png" class="money-icon">' + diamonds + '</p>'
            + '<p class="currencies"><img src="img/coin.png" class="money-icon">' + coins + '</p>' 
            + '</div>'
            + '<p class="exp">Lv: ' + level + '</p>'
            );
        }
    }
    let usr = localStorage.getItem("username");
    xhr.open("get", "http://localhost:8080/users/"+usr);
    xhr.send();
}

// function setPieces()
// {
    
// }

function setBoard()
{
    var background_light = "../img/board/" + localStorage.getItem("board") + "light.png";
    var background_dark =  "../img/board/" + localStorage.getItem("board") + "dark.png";
    CSS("light-box","background-image: url("+ background_light + ")");
    CSS("dark-box","background-image: url("+ background_dark + ")");
}

$(document).ready(function()
{
    $("#log-off").click(function()
    {
        window.location.replace("http://127.0.0.1:5500/index.html");
    });
});

getstats();
// setPieces();
setBoard();