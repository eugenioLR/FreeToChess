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
    let e = document.getElementsByClassName("board");
    xhr.onreadystatechange = function ()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            console.log(array_json);
            var htmlcode = '';
            var board_str = array_json.boardStr;

            console.log(board_str);
            // var board_style = localStorage.getItem("board");
            // var pieces = localStorage.getItem("pieces");
            
            var rows = board_str.split(';');
            for (var i = 0 ; i < 8 ; i++)
            {
                htmlcode += "<div>"; 
                let array_pieces = rows[i].split(',');
                for (var j = 0 ; array_pieces.length ; j++)
                {
                    switch(array_pieces[j])
                    {
                        case "--":
                            htmlcode+= '<img src="img/nothing.png" id="squareid" class="piece">';
                            break;
                        default:
                            htmlcode+= '<img src="img/basic/'+ array_pieces[j] +'.png" id="squareid" class="piece">';
                        break;
                    }
                }
                htmlcode += "</div>";
            }
            e[0].insertAdjacentHTML('afterbegin', htmlcode);
        }
    }
    var id_game = localStorage.getItem("game_id");
    xhr.open("get", "http://localhost:8080/game/"+ id_game +"/board");
    xhr.send();
}

function setBoard()
{
    var background_light = "../img/board/" + localStorage.getItem("board") + "light.png";
    var background_dark =  "../img/board/" + localStorage.getItem("board") + "dark.png";
    CSS("light-box","background-image: url("+ background_light + ")");
    CSS("dark-box","background-image: url("+ background_dark + ")");
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
            alert(string);
        }
    }
    var myusername = localStorage.getItem("username");
    xhr.open("post", "http://localhost:8080/users/" + myusername + "/games?oponent=" + name);
    xhr.send();
}

function getTurn()
{

}

function movePiece(previousMove, i)
{
    currentMove = 3;
    prev_col = previousMove%8;
    prev_row = Math.floor(previousMove/8)
    current_col = i%8;
    current_row = Math.floor(i/8);

}

$(document).ready(function()
{
    $("#challengebutton").click(function()
    {
        var name = $("#usernm").val();
        console.log(name);
        challengePlayer(name);
    });
});

$(document).ready(function()
{
    $("#log-off").click(function()
    {
        window.location.replace("index.html");
    });
});

$(document).ready(function()
{
    // for (let i = 0; i < 64 ; i++)
    // {
    //     var myTurn = 0; // var turn = getTurn() // de algun lado del sv...
    //     var clicked = 0;
    //     var squareid = "#square"+i;
    //     var previousMove = -1;
    //     $(document).on("click",squareid, function()
    //     {
    //         if (myTurn)
    //         {
    //             if (clicked == 0) 
    //             {
    //                 // una funcion para obtener el turno? variables globales igual...
    //                 // mueves la pieza
    //                 //funcion de mover pieza
    //                 clicked += 1; 
    //                 previousMove = i;
    //             }
    //             else
    //             {
    //                 if (movePiece(previousMove, i))  // igual da problemas de sincrono?
    //                 {
    //                     setPieces();
    //                     clicked = 0;
    //                     moved = true;
    //                 }                    
    //                 // envio de la pieza movida al servidor
    //                 // en caso de que el mov sea erroneo... me devuelve un error por lo q tengo que mover
    //                 // restauramos la string board_str de antes y volvemos a ejecutar esta funcion??
    //             }
    //         }
    //     });
    // }
});

console.log(localStorage.getItem("game_id"));

window.onbeforeunload = function(){
    localStorage.removeItem("game_id");
}


getstats();
setPieces();
// setBoard();