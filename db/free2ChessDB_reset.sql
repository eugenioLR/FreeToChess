DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;


-- object: public."User" | type: TABLE --
-- DROP TABLE IF EXISTS public."User" CASCADE;
CREATE TABLE public."User" (
	name varchar(20) NOT NULL,
	email varchar(100),
	password varchar(256),
	salt varchar(100),
	elo smallint,
	level smallint,
	exp smallint,
	subscribed bool,
	subs_date date,
	paypal_id varchar(20),
	coins smallint,
	diamonds smallint,
	CONSTRAINT "User_pk" PRIMARY KEY (name)

);
-- ddl-end --
ALTER TABLE public."User" OWNER TO postgres;
-- ddl-end --

-- object: public.game_id_generator | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.game_id_generator CASCADE;
CREATE SEQUENCE public.game_id_generator
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --
ALTER SEQUENCE public.game_id_generator OWNER TO postgres;
-- ddl-end --

-- object: public.product_id_generator | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.product_id_generator CASCADE;
CREATE SEQUENCE public.product_id_generator
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --
ALTER SEQUENCE public.product_id_generator OWNER TO postgres;
-- ddl-end --

-- object: public."Product" | type: TABLE --
-- DROP TABLE IF EXISTS public."Product" CASCADE;
CREATE TABLE public."Product" (
	id smallint NOT NULL DEFAULT nextval('public.product_id_generator'::regclass),
	name varchar(50),
	description text,
	c_price integer,
	d_price integer,
	rarity char(1),
	purchases smallint,
	discount_perc float,
	for_sale boolean,
	CONSTRAINT "Product_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."Product" OWNER TO postgres;
-- ddl-end --

-- object: public.gamestate_id_generator | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.gamestate_id_generator CASCADE;
CREATE SEQUENCE public.gamestate_id_generator
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --
ALTER SEQUENCE public.gamestate_id_generator OWNER TO postgres;
-- ddl-end --

-- object: public."Emoji" | type: TABLE --
-- DROP TABLE IF EXISTS public."Emoji" CASCADE;
CREATE TABLE public."Emoji" (
	texture_path varchar(150),

-- 	id smallint NOT NULL DEFAULT nextval('public.product_id_generator'::regclass),
-- 	name varchar(50),
-- 	description text,
-- 	c_price integer,
-- 	d_price integer,
-- 	rarity char(1),
-- 	purchases smallint,
-- 	discount_perc float,
-- 	for_sale boolean,
	CONSTRAINT "Emoji_pk" PRIMARY KEY (id)

)
 INHERITS(public."Product");
-- ddl-end --
ALTER TABLE public."Emoji" OWNER TO postgres;
-- ddl-end --

-- object: public."BoardSkin" | type: TABLE --
-- DROP TABLE IF EXISTS public."BoardSkin" CASCADE;
CREATE TABLE public."BoardSkin" (
	texture_path varchar(150),

-- 	id smallint NOT NULL DEFAULT nextval('public.product_id_generator'::regclass),
-- 	name varchar(50),
-- 	description text,
-- 	c_price integer,
-- 	d_price integer,
-- 	rarity char(1),
-- 	purchases smallint,
-- 	discount_perc float,
-- 	for_sale boolean,
	CONSTRAINT "BoardSkin_pk" PRIMARY KEY (id)

)
 INHERITS(public."Product");
-- ddl-end --
ALTER TABLE public."BoardSkin" OWNER TO postgres;
-- ddl-end --

-- object: public."PieceSkinSet" | type: TABLE --
-- DROP TABLE IF EXISTS public."PieceSkinSet" CASCADE;
CREATE TABLE public."PieceSkinSet" (
	texture_path varchar(150),

-- 	id smallint NOT NULL DEFAULT nextval('public.product_id_generator'::regclass),
-- 	name varchar(50),
-- 	description text,
-- 	c_price integer,
-- 	d_price integer,
-- 	rarity char(1),
-- 	purchases smallint,
-- 	discount_perc float,
-- 	for_sale boolean,
	CONSTRAINT "PieceSkinSet_pk" PRIMARY KEY (id)

)
 INHERITS(public."Product");
-- ddl-end --
ALTER TABLE public."PieceSkinSet" OWNER TO postgres;
-- ddl-end --

-- object: public."PendingGames" | type: TABLE --
-- DROP TABLE IF EXISTS public."PendingGames" CASCADE;
CREATE TABLE public."PendingGames" (
	id smallint NOT NULL,
	acepted boolean,
	emiter_name varchar(20),
	receiver_name varchar(20),
	"id_Game" smallint,
	CONSTRAINT "PendingGames_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."PendingGames" OWNER TO postgres;
-- ddl-end --

-- object: public."Booster" | type: TABLE --
-- DROP TABLE IF EXISTS public."Booster" CASCADE;
CREATE TABLE public."Booster" (

-- 	id smallint NOT NULL DEFAULT nextval('public.product_id_generator'::regclass),
-- 	name varchar(50),
-- 	description text,
-- 	c_price integer,
-- 	d_price integer,
-- 	rarity char(1),
-- 	purchases smallint,
-- 	discount_perc float,
-- 	for_sale boolean,
	CONSTRAINT "Booster_pk" PRIMARY KEY (id)

)
 INHERITS(public."Product");
-- ddl-end --
ALTER TABLE public."Booster" OWNER TO postgres;
-- ddl-end --

-- object: public."Game" | type: TABLE --
-- DROP TABLE IF EXISTS public."Game" CASCADE;
CREATE TABLE public."Game" (
	id smallint NOT NULL DEFAULT nextval('public.game_id_generator'::regclass),
	next_player smallint,
	w_player_name varchar(20),
	b_player_name varchar(20),
	CONSTRAINT "Game_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."Game" OWNER TO postgres;
-- ddl-end --

-- object: public."PurchasedProduct" | type: TABLE --
-- DROP TABLE IF EXISTS public."PurchasedProduct" CASCADE;
CREATE TABLE public."PurchasedProduct" (
	"name_User" varchar(20) NOT NULL,
	"id_Product" smallint NOT NULL,
	CONSTRAINT "PurchasedProduct_pk" PRIMARY KEY ("name_User","id_Product")

);
-- ddl-end --

-- object: "User_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PurchasedProduct" DROP CONSTRAINT IF EXISTS "User_fk" CASCADE;
ALTER TABLE public."PurchasedProduct" ADD CONSTRAINT "User_fk" FOREIGN KEY ("name_User")
REFERENCES public."User" (name) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Product_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PurchasedProduct" DROP CONSTRAINT IF EXISTS "Product_fk" CASCADE;
ALTER TABLE public."PurchasedProduct" ADD CONSTRAINT "Product_fk" FOREIGN KEY ("id_Product")
REFERENCES public."Product" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."ProductPack" | type: TABLE --
-- DROP TABLE IF EXISTS public."ProductPack" CASCADE;
CREATE TABLE public."ProductPack" (
	id smallint NOT NULL DEFAULT nextval('public.product_id_generator'::regclass),
	name varchar(50),
	description text,
	c_price integer,
	d_price integer,
	rarity char(1),
	purchases smallint,
	discount_perc float,
	for_sale boolean,
	CONSTRAINT "ProductPack_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."ProductPack" OWNER TO postgres;
-- ddl-end --

-- object: public."PackContents" | type: TABLE --
-- DROP TABLE IF EXISTS public."PackContents" CASCADE;
CREATE TABLE public."PackContents" (
	"id_Product" smallint NOT NULL,
	"id_ProductPack" smallint NOT NULL,
	CONSTRAINT "PackContents_pk" PRIMARY KEY ("id_Product","id_ProductPack")

);
-- ddl-end --

-- object: "Product_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PackContents" DROP CONSTRAINT IF EXISTS "Product_fk" CASCADE;
ALTER TABLE public."PackContents" ADD CONSTRAINT "Product_fk" FOREIGN KEY ("id_Product")
REFERENCES public."Product" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "ProductPack_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PackContents" DROP CONSTRAINT IF EXISTS "ProductPack_fk" CASCADE;
ALTER TABLE public."PackContents" ADD CONSTRAINT "ProductPack_fk" FOREIGN KEY ("id_ProductPack")
REFERENCES public."ProductPack" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."GameState" | type: TABLE --
-- DROP TABLE IF EXISTS public."GameState" CASCADE;
CREATE TABLE public."GameState" (
	id smallint NOT NULL DEFAULT nextval('public.gamestate_id_generator'::regclass),
	"id_Game" smallint,
	move_order smallint,
	board_str text,
	CONSTRAINT "GameState_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."GameState" OWNER TO postgres;
-- ddl-end --

-- object: "Game_fk" | type: CONSTRAINT --
-- ALTER TABLE public."GameState" DROP CONSTRAINT IF EXISTS "Game_fk" CASCADE;
ALTER TABLE public."GameState" ADD CONSTRAINT "Game_fk" FOREIGN KEY ("id_Game")
REFERENCES public."Game" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "Game_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PendingGames" DROP CONSTRAINT IF EXISTS "Game_fk" CASCADE;
ALTER TABLE public."PendingGames" ADD CONSTRAINT "Game_fk" FOREIGN KEY ("id_Game")
REFERENCES public."Game" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "PendingGames_uq" | type: CONSTRAINT --
-- ALTER TABLE public."PendingGames" DROP CONSTRAINT IF EXISTS "PendingGames_uq" CASCADE;
ALTER TABLE public."PendingGames" ADD CONSTRAINT "PendingGames_uq" UNIQUE ("id_Game");
-- ddl-end --

-- object: public.pending_game_id_generator | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.pending_game_id_generator CASCADE;
CREATE SEQUENCE public.pending_game_id_generator
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --
ALTER SEQUENCE public.pending_game_id_generator OWNER TO postgres;
-- ddl-end --

-- object: "User1_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PendingGames" DROP CONSTRAINT IF EXISTS "User1_fk" CASCADE;
ALTER TABLE public."PendingGames" ADD CONSTRAINT "User1_fk" FOREIGN KEY (emiter_name)
REFERENCES public."User" (name) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: "User2_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PendingGames" DROP CONSTRAINT IF EXISTS "User2_fk" CASCADE;
ALTER TABLE public."PendingGames" ADD CONSTRAINT "User2_fk" FOREIGN KEY (receiver_name)
REFERENCES public."User" (name) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: "User1_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Game" DROP CONSTRAINT IF EXISTS "User1_fk" CASCADE;
ALTER TABLE public."Game" ADD CONSTRAINT "User1_fk" FOREIGN KEY (w_player_name)
REFERENCES public."User" (name) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: "User2_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Game" DROP CONSTRAINT IF EXISTS "User2_fk" CASCADE;
ALTER TABLE public."Game" ADD CONSTRAINT "User2_fk" FOREIGN KEY (b_player_name)
REFERENCES public."User" (name) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

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
INSERT INTO "User" VALUES ('david23', 'email1', 'b3891aec309e4eacc3b9c34d6747b92515be672f969d9a1664124aeb308f80d0', 'SALTY-', 0, 0, 0, false, '2020/01/01', 0, 0, 0);
INSERT INTO "User" VALUES ('michael34', 'email2', '58dfa8c747ba3fa25cb4e58930b5a68f258c9efec3c9c1b39c942d0c6d374df4', 'SALTY.', 0, 0, 0, false, '2020/01/01', 0, 0, 0);
INSERT INTO "User" VALUES ('Isaac001', 'email3', '8c7b1dc078bb6a8add9876fc87c347bf7da01a6166a77d6647a71000f20f0f4e', 'SALTY,', 0, 0, 0, false, '2020/01/01', 0, 0, 0);







