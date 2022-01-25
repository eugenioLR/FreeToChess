

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
            for (var i = 0; i < array_json.length ; i++)
            {
                if (i < array_json.length / 2) 
                {
                    e[0].insertAdjacentHTML('afterbegin','<div class="product' + array_json[i].rarity + '">'
                                        + '<form action="">'
                                        + '<button type="button" class="purchasebutton">Purchase</button>'
                                        + '</form>'
                                        + '<p class="producttitle">' + array_json[i].name + '</p>'
                                        + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                                        + '<p class="description">' + array_json[i].description + '</p>'
                                        + '<p class="price">' + array_json[i].price + '<img src="/img/diamond.png" class="diamond"></p>'
                                        + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                                        + '<p class="rarity">' + array_json[i].rarity + '</p>'
                                        + '</div><br><br>');
                }
                else 
                {
                    e1[0].insertAdjacentHTML('afterbegin','<div class="product' + array_json[i].rarity + '">'
                                        + '<form action="">'
                                        + '<button type="button" class="purchasebutton">Purchase</button>'
                                        + '</form>'
                                        + '<p class="producttitle">' + array_json[i].name + '</p>'
                                        + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                                        + '<p class="description">' + array_json[i].description + '</p>'
                                        + '<p class="price">' + array_json[i].price + '<img src="/img/diamond.png" class="diamond"></p>'
                                        + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                                        + '<p class="rarity">' + array_json[i].rarity + '</p>'
                                        + '</div><br><br>');
                }
            }
                
        }
    }
    xhr.open("get", "http://localhost:8080/store/productPacks", true);
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
                    e[1].insertAdjacentHTML('afterbegin','<div class="product' + array_json[i].rarity + '">'
                                        + '<form action="">'
                                        + '<button type="button" class="purchasebutton">Purchase</button>'
                                        + '</form>'
                                        + '<p class="producttitle">' + array_json[i].name + '</p>'
                                        + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                                        + '<p class="description">' + array_json[i].description + '</p>'
                                        + '<p class="price">' + array_json[i].price + '<img src="/img/diamond.png" class="diamond"></p>'
                                        + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                                        + '<p class="rarity">' + array_json[i].rarity + '</p>'
                                        + '</div><br><br>');
                }
                else 
                {
                    e1[1].insertAdjacentHTML('afterbegin','<div class="product' + array_json[i].rarity + '">'
                                        + '<form action="">'
                                        + '<button type="button" class="purchasebutton">Purchase</button>'
                                        + '</form>'
                                        + '<p class="producttitle">' + array_json[i].name + '</p>'
                                        + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                                        + '<p class="description">' + array_json[i].description + '</p>'
                                        + '<p class="price">' + array_json[i].price + '<img src="/img/diamond.png" class="diamond"></p>'
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
                    e[2].insertAdjacentHTML('afterbegin','<div class="product' + array_json[i].rarity + '">'
                                        + '<form action="">'
                                        + '<button type="button" class="purchasebutton">Purchase</button>'
                                        + '</form>'
                                        + '<p class="producttitle">' + array_json[i].name + '</p>'
                                        + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                                        + '<p class="description">' + array_json[i].description + '</p>'
                                        + '<p class="price">' + array_json[i].price + '<img src="/img/diamond.png" class="diamond"></p>'
                                        + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                                        + '<p class="rarity">' + array_json[i].rarity + '</p>'
                                        + '</div><br><br>');
                }
                else
                {
                    e1[2].insertAdjacentHTML('afterbegin','<div class="product' + array_json[i].rarity + '">'
                                        + '<form action="">'
                                        + '<button type="button" class="purchasebutton">Purchase</button>'
                                        + '</form>'
                                        + '<p class="producttitle">' + array_json[i].name + '</p>'
                                        + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                                        + '<p class="description">' + array_json[i].description + '</p>'
                                        + '<p class="price">' + array_json[i].price + '<img src="/img/diamond.png" class="diamond"></p>'
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
                    e[3].insertAdjacentHTML('afterbegin','<div class="product' + array_json[i].rarity + '">'
                                        + '<form action="">'
                                        + '<button type="button" class="purchasebutton">Purchase</button>'
                                        + '</form>'
                                        + '<p class="producttitle">' + array_json[i].name + '</p>'
                                        + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                                        + '<p class="description">' + array_json[i].description + '</p>'
                                        + '<p class="price">' + array_json[i].price + '<img src="/img/diamond.png" class="diamond"></p>'
                                        + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                                        + '<p class="rarity">' + array_json[i].rarity + '</p>'
                                        + '</div><br><br>');
                }
                else
                {
                    e1[3].insertAdjacentHTML('afterbegin','<div class="product' + array_json[i].rarity + '">'
                                        + '<form action="">'
                                        + '<button type="button" class="purchasebutton">Purchase</button>'
                                        + '</form>'
                                        + '<p class="producttitle">' + array_json[i].name + '</p>'
                                        + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                                        + '<p class="description">' + array_json[i].description + '</p>'
                                        + '<p class="price">' + array_json[i].price + '<img src="/img/diamond.png" class="diamond"></p>'
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
                    e[4].insertAdjacentHTML('afterbegin','<div class="product' + array_json[i].rarity + '">'
                                        + '<form action="">'
                                        + '<button type="button" class="purchasebutton">Purchase</button>'
                                        + '</form>'
                                        + '<p class="producttitle">' + array_json[i].name + '</p>'
                                        + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                                        + '<p class="description">' + array_json[i].description + '</p>'
                                        + '<p class="price">' + array_json[i].price + '<img src="/img/diamond.png" class="diamond"></p>'
                                        + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                                        + '<p class="rarity">' + array_json[i].rarity + '</p>'
                                        + '</div><br><br>');
                }
                else
                {
                    e1[4].insertAdjacentHTML('afterbegin','<div class="product' + array_json[i].rarity + '">'
                                        + '<form action="">'
                                        + '<button type="button" class="purchasebutton">Purchase</button>'
                                        + '</form>'
                                        + '<p class="producttitle">' + array_json[i].name + '</p>'
                                        + '<img src="img/lootboxes/'+ array_json[i].rarity +'.png" class="producticon">'
                                        + '<p class="description">' + array_json[i].description + '</p>'
                                        + '<p class="price">' + array_json[i].price + '<img src="/img/diamond.png" class="diamond"></p>'
                                        + '<p class="discount">' + array_json[i].discount_perc * 100 + '%</p>'
                                        + '<p class="rarity">' + array_json[i].rarity + '</p>'
                                        + '</div><br><br>');
                }
            }
        }
    }
    xhr.open("get","http://localhost:8080/store/emojis",true);
    xhr.send();
}

// insertProductPack();
insertPieceSet();
insertBoardSet();
insertBooster();
insertEmoji();

