-- Sketchy code
ALTER TABLE "PackContents" DROP CONSTRAINT "Product_fk";
ALTER TABLE "PurchasedProduct" DROP CONSTRAINT "Product_fk";


-- EMOJIS
INSERT INTO "Emoji" (id, name, description, c_price, d_price, rarity, purchases, discount_perc, texture_path, for_sale)
VALUES
(0, 'Gunslinger Horse', 'Cool ass horse you can use to flex on your opponents', 0,   500, 'S', 0, 0.1, 'img/emojis/logo.png',  true),
(1, 'A',                'Emoji of an A',                                        100, 0,   'A', 0, 0.1, 'img/emojis/A.png',     true),
(2, 'B',                'Emoji of a B',                                         100, 0,   'B', 0, 0.1, 'img/emojis/B.png',     true),
(3, 'C',                'Emoji of a C',                                         100, 0,   'C', 0, 0.1, 'img/emojis/C.png',     true),
(4, 'Happy face',       'Just a regular happy face',                            100, 0,   'D', 0, 0.0, 'img/emojis/happy.png', true),
(5, 'Sad face',         'Just a regular sad face',                              100, 0,   'D', 0, 0.0, 'img/emojis/sad.png',   true),
(6, 'Angry face',       'Just a regular angry face',                            100, 0,   'D', 0, 0.0, 'img/emojis/angry.png', true);




-- BOARD SKINS
INSERT INTO "BoardSkin" (id, name, description, c_price, d_price, rarity, purchases, discount_perc, texture_path, for_sale)
VALUES
(7, 'Basic Board',    'Regular black and white board',               100, 0, 'F', 0, 0.0, 'img/boards/basic/',     true),
(8, 'Wavey Board',    'Wavey chess board with cool colors',          100, 0, 'A', 0, 0.0, 'img/boards/weird/',     true),
(9, 'Millitar Board', 'Camouflage board ato distract your opponents', 100, 0, 'B', 0, 0.0, 'img/boards/millitar/', true);




-- PIECE SKIN SETS
INSERT INTO "PieceSkinSet" (id, name, description, c_price, d_price, rarity, purchases, discount_perc, texture_path, for_sale)
VALUES
(10, 'MSPaint set', 'Textures drawn in MSPaint in like 5 minutes', 200, 0, 'F', 0, 0.0, 'img/pieces/mspaint_pack/', true),
(11, 'fantasy set', 'Fantastic piece textures',                    200, 0, 'F', 0, 0.0, 'img/pieces/fantasy_pack/', true),
(12, 'cburnet set', 'Basic piece textures',                        200, 0, 'F', 0, 0.0, 'img/pieces/cburnet_pack/', true);




-- BOOSTER
INSERT INTO "Booster" (id, name, description, c_price, d_price, rarity, purchases, discount_perc, for_sale)
VALUES
(13, 'Extra Queen',                  'Add an extra queen to the board',           500, 0, 'F', 0, 0.0, true),
(14, 'Extra King',                   'Add an extra king to the board',            600, 0, 'F', 0, 0.0, true),
(15, 'Double Move',                  'Allow 2 moves to be done in a row',         800, 0, 'F', 0, 0.0, true),
(16, 'Promote Pawn (Rook)',          'Instantly promote pawn to a rook',          300, 0, 'F', 0, 0.0, true),
(17, 'Promote Pawn (Knight/Bishop)', 'Instantly promote pawn to a knight/bishop', 400, 0, 'F', 0, 0.0, true);


-- PRODUCT PACK
INSERT INTO "ProductPack" (id, name, description, c_price, d_price, rarity, purchases, discount_perc, for_sale)
VALUES
(18, 'Badly Drawn Pack', 'Low effort skins for an affordable price', 250, 0, 'F', 0, 0.0, true);




-- PACK CONTENTS
INSERT INTO "PackContents" ("id_Product", "id_ProductPack")
VALUES
(10, 18),
(7,  18);




-- USERS
INSERT INTO "User" VALUES ('david23', 'email1', 'b3891aec309e4eacc3b9c34d6747b92515be672f969d9a1664124aeb308f80d0', 'SALTY-', 1200, 0, 0, false, '2020/01/01', 0, 0, 0);
INSERT INTO "User" VALUES ('michael34', 'email2', '58dfa8c747ba3fa25cb4e58930b5a68f258c9efec3c9c1b39c942d0c6d374df4', 'SALTY.', 900, 0, 0, false, '2020/01/01', 0, 0, 0);
INSERT INTO "User" VALUES ('Isaac001', 'email3', '8c7b1dc078bb6a8add9876fc87c347bf7da01a6166a77d6647a71000f20f0f4e', 'SALTY,', 800, 0, 0, false, '2020/01/01', 0, 0, 0);
INSERT INTO "User" VALUES ('a', 'email4', '8c7b1dc078bb6a8add9876fc87c347bf7da01a6166a77d6647a71000f20f0f4e', 'SALTY,', 400, 0, 0, false, '2020/01/01', 0, 0, 0);
INSERT INTO "User" VALUES ('b', 'email2', '8c7b1dc078bb6a8add9876fc87c347bf7da01a6166a77d6647a71000f20f0f4e', 'SALTY,', 2000, 0, 0, false, '2020/01/01', 0, 0, 0);
INSERT INTO "User" VALUES ('c', 'email6', '8c7b1dc078bb6a8add9876fc87c347bf7da01a6166a77d6647a71000f20f0f4e', 'SALTY,', 1800, 0, 0, false, '2020/01/01', 0, 0, 0);
INSERT INTO "User" VALUES ('d', 'email7', '8c7b1dc078bb6a8add9876fc87c347bf7da01a6166a77d6647a71000f20f0f4e', 'SALTY,', 950, 0, 0, false, '2020/01/01', 0, 0, 0);
INSERT INTO "User" VALUES ('e', 'email3', '8c7b1dc078bb6a8add9876fc87c347bf7da01a6166a77d6647a71000f20f0f4e', 'SALTY,', 1100, 0, 0, false, '2020/01/01', 0, 0, 0);
