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

function challengePlayer(name)
{
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function ()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string = xhr.responseText;
            if (string == "error")
            {
                alert("Can't send challenge to said user.");
                console.log("current player:" + localStorage.getItem("username"));
                console.log("rival player:" + name);
            }
            else
            {
                alert(name + " challenged.");
            }
        }
    }
    var myusername = localStorage.getItem("username");
    xhr.open("post", "http://localhost:8080/users/" + myusername + "/games?oponent=" + name);
    xhr.send();
}

function getPendingChallenges()
{
    const xhr = new XMLHttpRequest();
    let e = document.getElementsByClassName("challenges-select");
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            let html_str = '';
            for(var i = 0 ;i < array_json.length; i++)
            {
                let rival = array_json[i].emiter.name;
                let id_pending_game = array_json[i].id;
                let option = id_pending_game + ":" + rival;
                html_str += '<option value="value' + i + '">'+ option + '</option>';
            }
            e[0].insertAdjacentHTML('afterbegin', html_str);
        }
    }
    xhr.open("get", "http://localhost:8080/users/"+localStorage.getItem("username")+"/games/received", true);
    xhr.send();
}

function getCurrentChallenges()
{
    const xhr = new XMLHttpRequest();
    let e = document.getElementsByClassName("pending-challenges-select");
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            let html_str = '';
            for(var i = 0 ;i < array_json.length; i++)
            {
                let rival = array_json[i].emiter.name;
                let id_pending_game = array_json[i].game.id;
                let option = id_pending_game + ":" + rival;
                html_str += '<option value="value' + i + '">'+ option + '</option>';
            }
            e[0].insertAdjacentHTML('afterbegin', html_str);
        }
    }
    xhr.open("get", "http://localhost:8080/users/"+localStorage.getItem("username")+"/games/openGames", true);
    xhr.send();
}

function acceptChallenge(id_pending_game)
{
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            alert("challenge accepted!");
        }
    }
    let current_user = localStorage.getItem("username");
    xhr.open("post", "http://localhost:8080/users/"+ current_user +"/games/received?id="+ id_pending_game +"&accept=true")
    xhr.send();
}

function rejectChallenge(id_pending_game)
{
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            alert("challenge rejected.");
        }
    }
    let current_user = localStorage.getItem("username");
    xhr.open("post", "http://localhost:8080/users/"+ current_user +"/games/received?id="+ id_pending_game +"&accept=false");
    xhr.send();
}

function playGame(id_game)
{
    localStorage.setItem('game_id', id_game);
    window.location.replace("games.html");
}

$(document).ready(function(){
    $("#log-off").click(function(){
        window.location.replace("index.html");
    });
});

$(document).ready(function()
{
    $("#accept-challenge").click(function()
    {
        var option = $('#challenges').find(":selected").text();
        let array_opt = option.split(":");
        acceptChallenge(array_opt[0]);
    });
    $("#reject-challenge").click(function()
    {
        var option = $('#challenges').find(":selected").text();
        let array_opt = option.split(":");
        rejectChallenge(array_opt[0]);
    });
    $("#play-button").click(function()
    {
        var option = $('#pending-challenges-select').find(":selected").text();
        let array_opt = option.split(":");
        playGame(array_opt[0]);
    });
});

$(document).ready(function()
{
    $("#challengebutton").click(function()
    {
        var name = $("#usernm").val();
        challengePlayer(name);
    });
});

getstats();
getPendingChallenges();
getCurrentChallenges();
