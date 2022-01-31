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

function insertProductPack()
{
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let e = document.getElementsByClassName("left");
            let e1 = document.getElementsByClassName("right");
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            for (var i = 0 ; i < array_json.length ; i++)
            {
                if (i < array_json.length / 2) 
                {
                    e[0].insertAdjacentHTML('afterbegin',
                    '<p class="idp">' + array_json[i].id + '</p>' 
                    + '<div class="product' + array_json[i].rarity + '">'
                    + '<button type="button" id="' + array_json[i].id + 'button" class="purchasebutton">Purchase</button>'
                    + '<p class="producttitle">' + array_json[i].name + '</p>'
                    + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                    + '<p class="description">' + array_json[i].description + '</p>'
                    + '<p class="price1">' + array_json[i].c_price + '<img src="img/coin.png" class="coin"></p>'
                    + '<p class="price">' + array_json[i].d_price + '<img src="/img/diamond.png" class="diamond"></p>'
                    + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                    + '<p class="rarity">' + array_json[i].rarity + '</p>'
                    + '</div><br><br>');
                }
                else 
                {
                    e1[0].insertAdjacentHTML('afterbegin',
                    '<p class="idp">' + array_json[i].id + '</p>' 
                    + '<div class="product' + array_json[i].rarity + '">'
                    + '<button type="button" id="' + array_json[i].id + 'button" class="purchasebutton">Purchase</button>'
                    + '<p class="producttitle">' + array_json[i].name + '</p>'
                    + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                    + '<p class="description">' + array_json[i].description + '</p>'
                    + '<p class="price1">' + array_json[i].c_price + '<img src="img/coin.png" class="coin"></p>'
                    + '<p class="price">' + array_json[i].d_price + '<img src="/img/diamond.png" class="diamond"></p>'
                    + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                    + '<p class="rarity">' + array_json[i].rarity + '</p>'
                    + '</div><br><br>');
                }
            }   
        }
    }
    xhr.open("get", "http://localhost:8080/store/packs", true);
    xhr.send();
}

function insertPieceSet()
{
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let e = document.getElementsByClassName("left");
            let e1 = document.getElementsByClassName("right");
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            for (var i = 0; i < array_json.length ; i++)
            {
                if (i < array_json.length / 2) 
                {
                    e[1].insertAdjacentHTML('afterbegin',
                    '<p class="idp">' + array_json[i].id + '</p>' 
                    + '<div class="product' + array_json[i].rarity + '">'
                    + '<button type="button" id="' + array_json[i].id + 'button" class="purchasebutton">Purchase</button>'
                    + '<p class="producttitle">' + array_json[i].name + '</p>'
                    + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                    + '<p class="description">' + array_json[i].description + '</p>'
                    + '<p class="price1">' + array_json[i].c_price + '<img src="img/coin.png" class="coin"></p>'
                    + '<p class="price">' + array_json[i].d_price + '<img src="/img/diamond.png" class="diamond"></p>'
                    + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                    + '<p class="rarity">' + array_json[i].rarity + '</p>'
                    + '</div><br><br>');
                }
                else 
                {
                    e1[1].insertAdjacentHTML('afterbegin',
                    '<p class="idp">' + array_json[i].id + '</p>' 
                    + '<div class="product' + array_json[i].rarity + '">'
                    + '<button type="button" id="' + array_json[i].id + 'button" class="purchasebutton">Purchase</button>'
                    + '<p class="producttitle">' + array_json[i].name + '</p>'
                    + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                    + '<p class="description">' + array_json[i].description + '</p>'
                    + '<p class="price1">' + array_json[i].c_price + '<img src="img/coin.png" class="coin"></p>'
                    + '<p class="price">' + array_json[i].d_price + '<img src="/img/diamond.png" class="diamond"></p>'
                    + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                    + '<p class="rarity">' + array_json[i].rarity + '</p>'
                    + '</div><br><br>');
                }
            }
        }
    }
    xhr.open("get", "http://localhost:8080/store/pieceSkinSets", true);
    xhr.send();
}

function insertBoardSet()
{
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let e = document.getElementsByClassName("left");
            let e1 = document.getElementsByClassName("right");
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            for (var i = 0; i < array_json.length ; i++)
            {
                if (i < array_json.length / 2) 
                {
                    e[1].insertAdjacentHTML('afterbegin',
                    '<p class="idp">' + array_json[i].id + '</p>' 
                    + '<div class="product' + array_json[i].rarity + '">'
                    + '<button type="button" id="' + array_json[i].id + 'button" class="purchasebutton">Purchase</button>'
                    + '<p class="producttitle">' + array_json[i].name + '</p>'
                    + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                    + '<p class="description">' + array_json[i].description + '</p>'
                    + '<p class="price1">' + array_json[i].c_price + '<img src="img/coin.png" class="coin"></p>'
                    + '<p class="price">' + array_json[i].d_price + '<img src="/img/diamond.png" class="diamond"></p>'
                    + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                    + '<p class="rarity">' + array_json[i].rarity + '</p>'
                    + '</div><br><br>');
                }
                else
                {
                    e1[2].insertAdjacentHTML('afterbegin',
                    '<p class="idp">' + array_json[i].id + '</p>' 
                    + '<div class="product' + array_json[i].rarity + '">'
                    + '<button type="button" id="' + array_json[i].id + 'button" class="purchasebutton">Purchase</button>'
                    + '<p class="producttitle">' + array_json[i].name + '</p>'
                    + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                    + '<p class="description">' + array_json[i].description + '</p>'
                    + '<p class="price1">' + array_json[i].c_price + '<img src="img/coin.png" class="coin"></p>'
                    + '<p class="price">' + array_json[i].d_price + '<img src="/img/diamond.png" class="diamond"></p>'
                    + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                    + '<p class="rarity">' + array_json[i].rarity + '</p>'
                    + '</div><br><br>');
                }
            }
        }
    }
    xhr.open("get","http://localhost:8080/store/boardSkins",true);
    xhr.send();
}

function insertBooster()
{
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let e = document.getElementsByClassName("left");
            let e1 = document.getElementsByClassName("right");
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            for (var i = 0; i < array_json.length ; i++)
            {
                if (i < array_json.length / 2) 
                {
                    e[3].insertAdjacentHTML('afterbegin',
                    '<p class="idp">' + array_json[i].id + '</p>' 
                    + '<div class="product' + array_json[i].rarity + '">'
                    + '<button type="button" id="' + array_json[i].id + 'button" class="purchasebutton">Purchase</button>'
                    + '<p class="producttitle">' + array_json[i].name + '</p>'
                    + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                    + '<p class="description">' + array_json[i].description + '</p>'
                    + '<p class="price1">' + array_json[i].c_price + '<img src="img/coin.png" class="coin"></p>'
                    + '<p class="price">' + array_json[i].d_price + '<img src="/img/diamond.png" class="diamond"></p>'
                    + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                    + '<p class="rarity">' + array_json[i].rarity + '</p>'
                    + '</div><br><br>');
                }
                else
                {
                    e1[3].insertAdjacentHTML('afterbegin',
                    '<p class="idp">' + array_json[i].id + '</p>' 
                    + '<div class="product' + array_json[i].rarity + '">'
                    + '<button type="button" id="' + array_json[i].id + 'button" class="purchasebutton">Purchase</button>'
                    + '<p class="producttitle">' + array_json[i].name + '</p>'
                    + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                    + '<p class="description">' + array_json[i].description + '</p>'
                    + '<p class="price1">' + array_json[i].c_price + '<img src="img/coin.png" class="coin"></p>'
                    + '<p class="price">' + array_json[i].d_price + '<img src="/img/diamond.png" class="diamond"></p>'
                    + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                    + '<p class="rarity">' + array_json[i].rarity + '</p>'
                    + '</div><br><br>');
                }
            }
        }
    }
    xhr.open("get","http://localhost:8080/store/boosters",true);
    xhr.send();
}

function insertEmoji()
{
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let e = document.getElementsByClassName("left");
            let e1 = document.getElementsByClassName("right");
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            for (var i = 0; i < array_json.length ; i++)
            {
                if (i < array_json.length / 2) 
                {
                    e[4].insertAdjacentHTML('afterbegin',
                    '<p class="idp">' + array_json[i].id + '</p>' 
                    + '<div class="product' + array_json[i].rarity + '">'
                    + '<button type="button" id="' + array_json[i].id + 'button" class="purchasebutton">Purchase</button>'
                    + '<p class="producttitle">' + array_json[i].name + '</p>'
                    + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                    + '<p class="description">' + array_json[i].description + '</p>'
                    + '<p class="price1">' + array_json[i].c_price + '<img src="img/coin.png" class="coin"></p>'
                    + '<p class="price">' + array_json[i].d_price + '<img src="/img/diamond.png" class="diamond"></p>'
                    + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                    + '<p class="rarity">' + array_json[i].rarity + '</p>'
                    + '</div><br><br>');
                }
                else
                {
                    e1[4].insertAdjacentHTML('afterbegin',
                    '<p class="idp">' + array_json[i].id + '</p>' 
                    + '<div class="product' + array_json[i].rarity + '">'
                    + '<button type="button" id="' + array_json[i].id + 'button" class="purchasebutton">Purchase</button>'
                    + '<p class="producttitle">' + array_json[i].name + '</p>'
                    + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                    + '<p class="description">' + array_json[i].description + '</p>'
                    + '<p class="price1">' + array_json[i].c_price + '<img src="img/coin.png" class="coin"></p>'
                    + '<p class="price">' + array_json[i].d_price + '<img src="/img/diamond.png" class="diamond"></p>'
                    + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                    + '<p class="rarity">' + array_json[i].rarity + '</p>'
                    + '</div><br><br>');
                }
            }
        }
    }
    xhr.open("get","http://localhost:8080/store/emojis", true);
    xhr.send();
}

function payment(idp)
{
    const xhr = new XMLHttpRequest();
    var usr = localStorage.getItem("username");  
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            console.log(string_json);
            if (string_json=="error")
            {
                window.alert(string_json);
            } 
        }
    }
    xhr.open("post","http://localhost:8080/store/buy?name="+usr, true); 
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify({id:idp}));
}

function checkCoins(id)
{
    var condition = false;
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            let string_json = xhr.responseText;
            let array_json = JSON.parse(string_json);
            c_user = array_json.coins;
            d_user = array_json.diamonds;
            condition = checkPrice(id, c_user, d_user);
            console.log(condition);
        }
    }
    var usr = localStorage.getItem("username");
    xhr.open("get","http://localhost:8080/users/"+usr, false);
    xhr.send();
    console.log(condition);
}

function checkPrice(id, c_user, d_user)
{
    const xhr = new XMLHttpRequest();
    if (xhr.readyState == 4 && xhr.status == 200)
    {
        let string_json = xhr.responseText;
        let array_json = JSON.parse(string_json);
        var c = array_json[0].c_price;
        var d = array_json[0].d_price;
        return ((c_user >= c && c != 0) && (d_user >= d && d != 0)); 
    }
    xhr.open("get","http://localhost:8080/store/"+id);
    xhr.send();
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
    for (let i = 0 ; i < 30 ; i++)  
    {
        var btnid = '#'+i+"button"; 
        $(document).on("click",btnid, function(){
            payment(i);
        });
    }
});

getstats();
insertProductPack();
insertPieceSet();
insertBoardSet();
insertBooster();
insertEmoji();