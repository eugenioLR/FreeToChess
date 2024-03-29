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
    xhr.open("get", "http://localhost:8080/users/"+usr, true);
    xhr.send();
}

function getRanking()
{
    const xhr = new XMLHttpRequest();
    let e = document.getElementsByClassName("ranking-players");
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            for (var i = 0 ; i < array_json.length ; i++)
            {
                e[0].insertAdjacentHTML('afterbegin', '<h3>'+array_json[i].name+ " ELO: "+ array_json[i].elo + '<h3/>')
            }
        }
    }
    xhr.open("get", "http://localhost:8080/users/ranking", true);
    xhr.send();
}

$(document).ready(function()
{
    $("#log-off").click(function()
    {
        window.location.replace("index.html");
    });
});

getstats();
getRanking();