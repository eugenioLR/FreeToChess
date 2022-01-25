-- EMOJIS
INSERT INTO "Emoji" (id, name, description, c_price, d_price, rarity, purchases, discount_perc, texture_path)
VALUES
(0, 'Gunslinger Horse', 'Cool ass horse you can use to flex on your opponents',
0, 500, 'S', 0, 0.1, 'img/emojis/logo.png'),
(1, 'A', 'Emoji of an A',
100, 0, 'A', 0, 0.1, 'img/emojis/A.png'),
(2, 'B', 'Emoji of a B',
100, 0, 'B', 0, 0.1, 'img/emojis/B.png'),
(3, 'C', 'Emoji of a C',
100, 0, 'C', 0, 0.1, 'img/emojis/C.png'),
(4, 'Happy face', 'Just a regular happy face',
100, 0, 'D', 0, 0.0, 'img/emojis/happy.png'),
(5, 'Sad face', 'Just a regular sad face',
100, 0, 'D', 0, 0.0, 'img/emojis/sad.png'),
(6, 'Angry face', 'Just a regular angry face',
100, 0, 'D', 0, 0.0, 'img/emojis/angry.png');




-- BOARD SKINS
INSERT INTO "BoardSkin" (id, name, description, c_price, d_price, rarity, purchases, discount_perc, texture_path)
VALUES
(7, 'Black and white', 'Regular black and white board',
100, 0, 'D', 0, 0.0, 'img/boards/black_and_white.png'),
(8, 'Black and blue', 'Regular black and blue board',
200, 0, 'D', 0, 0.0, 'img/boards/black_and_blue.png');




-- PIECE SKIN SETS
INSERT INTO "PieceSkinSet" (id, name, description, c_price, d_price, rarity, purchases, discount_perc)
VALUES
(9, 'MSPaint set', 'Textures drawn in MSPaint in like 5 minutes',
200, 0, 'F', 0, 0.0);




-- PIECE SKINS
INSERT INTO "PieceSkin" (id, name, description, c_price, d_price, rarity, purchases, discount_perc, texture_path, "id_PieceSkinSet")
VALUES
(10, 'MSPaint pawn', 'Pawn drawn in MSPaint',
50, 0, 'F', 0, 0.0, 'img/pieces/mspaint_pawn.png', 9),
(11, 'MSPaint knight', 'Knight drawn in MSPaint',
50, 0, 'F', 0, 0.0, 'img/pieces/mspaint_knight.png', 9),
(12, 'MSPaint rook', 'Rook drawn in MSPaint',
50, 0, 'F', 0, 0.0, 'img/pieces/mspaint_rook.png', 9),
(13, 'MSPaint bishop', 'Bishop drawn in MSPaint',
50, 0, 'F', 0, 0.0, 'img/pieces/mspaint_bishop.png', 9),
(14, 'MSPaint king', 'King drawn in MSPaint',
50, 0, 'F', 0, 0.0, 'img/pieces/mspaint_king.png', 9),
(15, 'MSPaint Queen', 'Queen drawn in MSPaint',
50, 0, 'F', 0, 0.0, 'img/pieces/mspaint_queen.png', 9);


-- PRODUCT PACK
INSERT INTO "ProductPack" (id, name, description, c_price, d_price, rarity, purchases, discont_perc)
VALUES
(16, 'Badly Drawn Pack', 'Low effort skins for an affordable price',
250, 0, 'F', 0, 0.0);




-- PACK CONTENTS





-- USERS
INSERT INTO "User" VALUES ('david23', '---', '---', 0, 0, 0, false, '2020/01/01', 0, 0, 0);

