function getStats()
{
    const xhr = new XMLHttpRequest();
    let e = document.getElementsByClassName("profile");
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            var usernm = array_json.name;
            var level = array_json.level;
            var elo = array_json.level;
            var exp = array_json.exp
            e[0].insertAdjacentHTML('afterbegin',
              '<div class="stat">'
            + '<h2 class="nickname">' + usernm + '</h2">'
            + '<h2 class="elo">ELO: ' + elo + '</h2>'
            + '<p class="exp">Lv: ' + level + '   EXP: ' + exp + '</p>'
            + '</div>'
            );
        }
    }
    let usr = localStorage.getItem("username");
    xhr.open("get", "http://localhost:8080/users/"+usr);
    xhr.send();
}

function getOptions()
{
    const xhr = new XMLHttpRequest();
    let e = document.getElementsByClassName("piece-selector");
    let e1 = document.getElementsByClassName("board-selector");
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            for (var i = 0 ; i < array_json.length ; i++)
            {
                if (isBoardSkin(array_json[i].id))
                {      
                    e1[0].insertAdjacentHTML('afterbegin',                    
                    '<option value="' + array_json[i].texture_path + '">' + array_json[i].name + '</option>');
                }
                else if (isPieceSkin(array_json[i].id))
                {
                    e[0].insertAdjacentHTML('afterbegin',
                    '<option value="' + array_json[i].texture_path + '">' + array_json[i].texture_path + '</option>');
                }
            }
        }
    }
    let usr = localStorage.getItem("username");
    xhr.open("get","http://localhost:8080/users/" + usr + "/products", true);
    xhr.send();
}

function isBoardSkin(id_to_check)
{
    var condition = false;
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            for (var i = 0 ; i < array_json.length ; i++)
            {
                if (array_json[i].id == id_to_check) condition = true;
            }
        }
    }
    xhr.open("get","http://localhost:8080/store/boardSkins/", false);
    xhr.send();
    return condition;
}

function isPieceSkin(id_to_check)
{
    var condition = false;
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            for (var i = 0 ; i < array_json.length ; i++)
            {
                if (array_json[i].id == id_to_check) condition = true;
            }
        }
    }
    xhr.open("get","http://localhost:8080/store/pieceSkinSets/", false);
    xhr.send();
    return condition;
}

$(document).ready(function()
{
    $("#log-off").click(function()
    {
        window.location.replace("index.html");
    });
});

$(document).ready(function()
{
    $("#set-skin").click(function()
    {
        var selected_board = $('#board-selector').find(":selected").text();
        var selected_piece = $('#piece-selector').find(":selected").text();
        localStorage.setItem('board',selected_board);
        localStorage.setItem('pieces',selected_piece);
    });
});

getStats();
getOptions();
