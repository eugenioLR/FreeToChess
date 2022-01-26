-- Sketchy code
ALTER TABLE "PackContents" DROP CONSTRAINT "Product_fk";
ALTER TABLE "PurchasedProduct" DROP CONSTRAINT "Product_fk";


-- EMOJIS
INSERT INTO "Emoji" (id, name, description, c_price, d_price, rarity, purchases, discount_perc, texture_path)
VALUES
(0, 'Gunslinger Horse', 'Cool ass horse you can use to flex on your opponents', 0,   500, 'S', 0, 0.1, 'img/emojis/logo.png'),
(1, 'A',                'Emoji of an A',                                        100, 0,   'A', 0, 0.1, 'img/emojis/A.png'),
(2, 'B',                'Emoji of a B',                                         100, 0,   'B', 0, 0.1, 'img/emojis/B.png'),
(3, 'C',                'Emoji of a C',                                         100, 0,   'C', 0, 0.1, 'img/emojis/C.png'),
(4, 'Happy face',       'Just a regular happy face',                            100, 0,   'D', 0, 0.0, 'img/emojis/happy.png'),
(5, 'Sad face',         'Just a regular sad face',                              100, 0,   'D', 0, 0.0, 'img/emojis/sad.png'),
(6, 'Angry face',       'Just a regular angry face',                            100, 0,   'D', 0, 0.0, 'img/emojis/angry.png');




-- BOARD SKINS
INSERT INTO "BoardSkin" (id, name, description, c_price, d_price, rarity, purchases, discount_perc, texture_path)
VALUES
(7, 'Basic Board',    'Regular black and white board',               100, 0, 'F', 0, 0.0, 'img/boards/basic/'),
(8, 'Wavey Board',    'Wavey chess board with cool colors',          100, 0, 'A', 0, 0.0, 'img/boards/weird/'),
(9, 'Millitar Board', 'Camouflage board ato distract your opponents', 100, 0, 'B', 0, 0.0, 'img/boards/millitar/');




-- PIECE SKIN SETS
INSERT INTO "PieceSkinSet" (id, name, description, c_price, d_price, rarity, purchases, discount_perc, texture_path)
VALUES
(10, 'MSPaint set', 'Textures drawn in MSPaint in like 5 minutes', 200, 0, 'F', 0, 0.0, 'img/pieces/mspaint_pack/'),
(11, 'fantasy set', 'Fantastic piece textures',                    200, 0, 'F', 0, 0.0, 'img/pieces/fantasy_pack/'),
(12, 'cburnet set', 'Basic piece textures',                        200, 0, 'F', 0, 0.0, 'img/pieces/cburnet_pack/');




-- BOOSTER
INSERT INTO "Booster" (id, name, description, c_price, d_price, rarity, purchases, discount_perc)
VALUES
(13, 'Extra Queen',                  'Add an extra queen to the board',           500, 0, 'F', 0, 0.0),
(14, 'Extra King',                   'Add an extra king to the board',            600, 0, 'F', 0, 0.0),
(15, 'Double Move',                  'Allow 2 moves to be done in a row',         800, 0, 'F', 0, 0.0),
(16, 'Promote Pawn (Rook)',          'Instantly promote pawn to a rook',          300, 0, 'F', 0, 0.0),
(17, 'Promote Pawn (Knight/Bishop)', 'Instantly promote pawn to a knight/bishop', 400, 0, 'F', 0, 0.0);


-- PRODUCT PACK
INSERT INTO "ProductPack" (id, name, description, c_price, d_price, rarity, purchases, discount_perc)
VALUES
(18, 'Badly Drawn Pack', 'Low effort skins for an affordable price', 250, 0, 'F', 0, 0.0);




-- PACK CONTENTS
INSERT INTO "PackContents" ("id_Product", "id_ProductPack")
VALUES
(10, 18),
(7,  18);




-- USERS
INSERT INTO "User" VALUES ('david23', '---', '---', 0, 0, 0, false, '2020/01/01', 0, 0, 0);
INSERT INTO "User" VALUES ('michael34', '---', '---', 0, 0, 0, false, '2020/01/01', 0, 0, 0);
INSERT INTO "User" VALUES ('Isaac001', '---', '---', 0, 0, 0, false, '2020/01/01', 0, 0, 0);

