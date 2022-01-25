

const xhr = new XMLHttpRequest();

function insertProductPack() 
{
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            var e = document.getElementsByClassName("product-pack");
            var string_json = xhr.responseText;
            var array_json = JSON.parse(string_json);
            for (var i = 0; i < array_json.length ; i++)
            {
                e[0].insertAdjacentHTML('afterbegin','<div class="productS">' +
                                        + '<form action="">' 
                                        + '<button type="button" class="purchasebutton">Purchase</button>'
                                        + '</form>'
                                        + '<p class="producttitle">' + String(array_json[i].name) + '</p>'
                                        + '<img src="img/lootboxes/'+ String(array_json[i].rarity) +'.png" class="producticon">'
                                        + '<p class="description">' + String(array_json[i].description) + '</p>'
                                        + '<p class="price">' + parseFloat(array_json[i].price) + '<img src="/img/diamond.png" class="diamond"></p>'
                                        + '<p class="discount">' + parseFloat(array_json[i].discount_perc * 100) + '%</p>'
                                        + '<p class="rarity">' + String(array_json[i].rarity) + '</p>'
                                        + '</div>');
            }
        }
    }
    xhr.open("get", "http://localhost:8080/store", true);
    xhr.send();
}


// xhr.onreadystatechange = function() 
// {
//     if (xhr.readyState == 4 && xhr.status == 200) 
//     {
//         var string_json = xhr.responseText;
//         var array_json = JSON.parse(string_json);
//         for (var i = 0 ; i < array_json.length; i++) 
//         {
//             console.log(array_json[i].name);
//         }
//     }    
// }

// xhr.open("get", "http://localhost:8080/store", true);
// xhr.send();

insertProductPack();
