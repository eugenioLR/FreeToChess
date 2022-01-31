function delay(delayInms) {
    return new Promise(resolve => {
      setTimeout(() => {
        resolve(2);
      }, delayInms);
    });
}

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
    let e = document.getElementsByClassName("pieces");
    xhr.onreadystatechange = function ()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            var htmlBuffer = [];
            var htmlcode = '';
            var board_str = array_json.boardStr;
            // var board_style = localStorage.getItem("board");
            // var pieces = localStorage.getItem("pieces");
            var cid = 0;
            var rows = board_str.split(';');
            for (var i = 0 ; i < 8 ; i++)
            {   
                htmlBuffer.push("<div>"); 
                let array_pieces = rows[i].split(',');
                for (var j = 0 ; j < 8 ; j++)
                {
                    cid+=1;
                    switch(array_pieces[j])
                    {
                        case "--":
                            htmlBuffer.push('<img src="../img/pieces/nothing.png" id="square' + cid + '" class="piece">');
                            break;
                        default:
                            htmlBuffer.push('<img src="../img/pieces/cburnett/'+ array_pieces[j] +'.png" id="square' + cid + '" class="piece">');
                    }
                }
                htmlBuffer.push("</div>");
            }
            htmlcode = htmlBuffer.join("\n");
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

function getTurn()
{

}

function movePiece(previousMove, i)
{
    prev_col = (previousMove-1)%8;
    prev_row = ~~((previousMove-1) / 8);
    prev_move = [prev_row, prev_col];

    current_col =(i-1)%8;
    current_row = ~~((i-1) / 8);
    current_move = [current_row, current_col];
    const xhr = new XMLHttpRequest();
    var id_game = localStorage.getItem("game_id"); 
    let usr = localStorage.getItem("username");
    xhr.open("post","http://localhost:8080/game/"+ id_game +"/board?name="+usr);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify({init:prev_move,last:current_move}));
    
}

$(document).ready(function()
{
    $("#log-off").click(function()
    {
        window.location.replace("index.html");
    });
});

var clicked = 0;
$(document).ready(function()
{
    for (let i = 1; i <= 64 ; i++)
    {
        
        // var myTurn = 0; // var turn = getTurn() // de algun lado del sv...
        
        var squareid = "#square"+i;
        var previousMove = -1;
        $(document).on("click",squareid, function()
        {
            // if (myTurn)
            // {
                if (clicked == 0) 
                {
                    // una funcion para obtener el turno? variables globales igual...
                    // mueves la pieza
                    // funcion de mover pieza
                    console.log("firstMove: "+ i);
                    clicked += 1; 
                    previousMove = i;
                }
                else
                {
                    console.log("Move to: "+i);
                    movePiece(previousMove, i)  // igual da problemas de sincrono?
                    clicked = 0;
                       
                    // delay 2 segundos y print de nuevo.
                        document.location.reload(true);
                        setPieces();
                    
                    // estas 3 funciones en movePiece...
                    // setPieces();
                    // clicked = 0;
                    // moved = true;
                                       
                    // envio de la pieza movida al servidor
                    // en caso de que el mov sea erroneo... me devuelve un error por lo q tengo que mover
                    // restauramos la string board_str de antes y volvemos a ejecutar esta funcion??
                }
            // }
        });
    }
});

// window.onbeforeunload = function(){
//     localStorage.removeItem("game_id");
// }

getstats();
setPieces();
// setBoard();
