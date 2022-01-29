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

function setPieces()
{
    const xhr = new XMLHttpRequest();
    let e = document.getElementsByClassName();
    xhr.onreadystatechange = function ()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse();
            var htmlcode = '';
            var board_str = array_json.board_str;
            var board_style = localStorage.getItem("board");
            var pieces = localStorage.getItem("pieces");
            e[0].insertAdjacentHTML();
            for (var i = 0 ; i < 8 ; i++)
            {
                html += "<div>"; 
                let row = board_str.split(';');
                let array_pieces = row[i].split(',');
                for (var j = 0 ; array_pieces.length ; j++)
                {
                    switch(array_pieces[j])
                    {
                        case "--":
                            htmlcode+= '<img src="img/nothing" class="piece">';
                            break;
                        default:
                            htmlcode+= '<img src="img/basic"'+ array_pieces[j] +' class="piece">';
                        break;
                    }
                }
                html += "</div>";
            }
        }
    }
    // id del juego a conseguir. 
    xhr.open("get", "http://localhost:8080/game/id/board");
    xhr.send();
}

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