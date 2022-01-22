-- EMOJIS
INSERT INTO "Emoji" (id, name, description, price, rarity, purchases, discount_perc, texture_path)
VALUES
(0, 'Gunslinger Horse', 'Cool ass horse you can use to flex on your opponents',
0.56, 'S', 0, 0.1, 'img/emojis/logo.png');

INSERT INTO "Emoji" (id, name, description, price, rarity, purchases, discount_perc, texture_path)
VALUES
(1, 'Happy face', 'Just a regular happy face',
0.01, 'D', 0, 0.0, 'img/emojis/happy.png');

INSERT INTO "Emoji" (id, name, description, price, rarity, purchases, discount_perc, texture_path)
VALUES
(2, 'Sad face', 'Just a regular sad face',
0.01, 'D', 0, 0.0, 'img/emojis/sad.png');

INSERT INTO "Emoji" (id, name, description, price, rarity, purchases, discount_perc, texture_path)
VALUES
(3, 'Angry face', 'Just a regular angry face',
0.01, 'D', 0, 0.0, 'img/emojis/angry.png');




-- BOARD SKINS
INSERT INTO "BoardSkin" (id, name, description, price, rarity, purchases, discount_perc, texture_path)
VALUES
(4, 'Black and white', 'Regular black and white board',
0, 'D', 0, 0.0, 'img/boards/black_and_white.png');

INSERT INTO "BoardSkin" (id, name, description, price, rarity, purchases, discount_perc, texture_path)
VALUES
(5, 'Black and blue', 'Regular black and blue board',
0, 'D', 0, 0.0, 'img/boards/black_and_blue.png');




-- PIECE SKIN SETS
INSERT INTO "PieceSkinSet" (id, name, description, price, rarity, purchases, discount_perc)
VALUES
(6, 'MSPaint set', 'Textures drawn in MSPaint in like 5 minutes',
0.01, 'F', 0, 0.0);




-- PIECE SKINS
INSERT INTO "PieceSkin" (id, name, description, price, rarity, purchases, discount_perc, texture_path, "id_PieceSkinSet")
VALUES
(7, 'MSPaint pawn', 'Pawn drawn in MSPaint',
0, 'F', 0, 0.0, 'img/pieces/mspaint_pawn.png', 6);

INSERT INTO "PieceSkin" (id, name, description, price, rarity, purchases, discount_perc, texture_path, "id_PieceSkinSet")
VALUES
(8, 'MSPaint knight', 'Knight drawn in MSPaint',
0, 'F', 0, 0.0, 'img/pieces/mspaint_knight.png', 6);

INSERT INTO "PieceSkin" (id, name, description, price, rarity, purchases, discount_perc, texture_path, "id_PieceSkinSet")
VALUES
(9, 'MSPaint rook', 'Rook drawn in MSPaint',
0, 'F', 0, 0.0, 'img/pieces/mspaint_rook.png', 6);

INSERT INTO "PieceSkin" (id, name, description, price, rarity, purchases, discount_perc, texture_path, "id_PieceSkinSet")
VALUES
(10, 'MSPaint bishop', 'Bishop drawn in MSPaint',
0, 'F', 0, 0.0, 'img/pieces/mspaint_bishop.png', 6);

INSERT INTO "PieceSkin" (id, name, description, price, rarity, purchases, discount_perc, texture_path, "id_PieceSkinSet")
VALUES
(11, 'MSPaint king', 'King drawn in MSPaint',
0, 'F', 0, 0.0, 'img/pieces/mspaint_king.png', 6);

INSERT INTO "PieceSkin" (id, name, description, price, rarity, purchases, discount_perc, texture_path, "id_PieceSkinSet")
VALUES
(12, 'MSPaint Queen', 'Queen drawn in MSPaint',
0, 'F', 0, 0.0, 'img/pieces/mspaint_queen.png', 6);
