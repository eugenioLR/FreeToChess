-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.3
-- PostgreSQL version: 13.0
-- Project Site: pgmodeler.io
-- Model Author: ---

-- Database creation must be performed outside a multi lined SQL file. 
-- These commands were put in this file only as a convenience.
-- 
-- object: "free2chessDB" | type: DATABASE --
-- DROP DATABASE IF EXISTS "free2chessDB";
CREATE DATABASE "free2chessDB";
-- ddl-end --


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

-- 	id smallint NOT NULL,
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

-- object: public."Achievement" | type: TABLE --
-- DROP TABLE IF EXISTS public."Achievement" CASCADE;
CREATE TABLE public."Achievement" (
	id smallint NOT NULL,
	name varchar(50),
	description text,
	exp_reward smallint,
	coin_reward smallint,
	CONSTRAINT "Achievement_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."Achievement" OWNER TO postgres;
-- ddl-end --

-- object: public."BoardSkin" | type: TABLE --
-- DROP TABLE IF EXISTS public."BoardSkin" CASCADE;
CREATE TABLE public."BoardSkin" (
	texture_path varchar(150),

-- 	id smallint NOT NULL,
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

-- 	id smallint NOT NULL,
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
	"id_Tournament" smallint,
	w_player_name varchar(20),
	b_player_name varchar(20),
	CONSTRAINT "PendingGames_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."PendingGames" OWNER TO postgres;
-- ddl-end --

-- object: public."Tournament" | type: TABLE --
-- DROP TABLE IF EXISTS public."Tournament" CASCADE;
CREATE TABLE public."Tournament" (
	id smallint NOT NULL,
	name smallint,
	min_elo smallint,
	max_elo smallint,
	CONSTRAINT "Tournament_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."Tournament" OWNER TO postgres;
-- ddl-end --

-- object: public."Booster" | type: TABLE --
-- DROP TABLE IF EXISTS public."Booster" CASCADE;
CREATE TABLE public."Booster" (

-- 	id smallint NOT NULL,
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

-- object: "Tournament_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PendingGames" DROP CONSTRAINT IF EXISTS "Tournament_fk" CASCADE;
ALTER TABLE public."PendingGames" ADD CONSTRAINT "Tournament_fk" FOREIGN KEY ("id_Tournament")
REFERENCES public."Tournament" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Game" | type: TABLE --
-- DROP TABLE IF EXISTS public."Game" CASCADE;
CREATE TABLE public."Game" (
	id smallint NOT NULL DEFAULT nextval('public.game_id_generator'::regclass),
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

-- object: public."CompletedAchivement" | type: TABLE --
-- DROP TABLE IF EXISTS public."CompletedAchivement" CASCADE;
CREATE TABLE public."CompletedAchivement" (
	"id_Achievement" smallint NOT NULL,
	"name_User" varchar(20) NOT NULL,
	CONSTRAINT "CompletedAchivement_pk" PRIMARY KEY ("id_Achievement","name_User")

);
-- ddl-end --

-- object: "Achievement_fk" | type: CONSTRAINT --
-- ALTER TABLE public."CompletedAchivement" DROP CONSTRAINT IF EXISTS "Achievement_fk" CASCADE;
ALTER TABLE public."CompletedAchivement" ADD CONSTRAINT "Achievement_fk" FOREIGN KEY ("id_Achievement")
REFERENCES public."Achievement" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "User_fk" | type: CONSTRAINT --
-- ALTER TABLE public."CompletedAchivement" DROP CONSTRAINT IF EXISTS "User_fk" CASCADE;
ALTER TABLE public."CompletedAchivement" ADD CONSTRAINT "User_fk" FOREIGN KEY ("name_User")
REFERENCES public."User" (name) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."JoinedTournament" | type: TABLE --
-- DROP TABLE IF EXISTS public."JoinedTournament" CASCADE;
CREATE TABLE public."JoinedTournament" (
	"id_Tournament" smallint NOT NULL,
	"name_User" varchar(20) NOT NULL,
	CONSTRAINT "JoinedTournament_pk" PRIMARY KEY ("id_Tournament","name_User")

);
-- ddl-end --

-- object: "Tournament_fk" | type: CONSTRAINT --
-- ALTER TABLE public."JoinedTournament" DROP CONSTRAINT IF EXISTS "Tournament_fk" CASCADE;
ALTER TABLE public."JoinedTournament" ADD CONSTRAINT "Tournament_fk" FOREIGN KEY ("id_Tournament")
REFERENCES public."Tournament" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "User_fk" | type: CONSTRAINT --
-- ALTER TABLE public."JoinedTournament" DROP CONSTRAINT IF EXISTS "User_fk" CASCADE;
ALTER TABLE public."JoinedTournament" ADD CONSTRAINT "User_fk" FOREIGN KEY ("name_User")
REFERENCES public."User" (name) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."ProductReward" | type: TABLE --
-- DROP TABLE IF EXISTS public."ProductReward" CASCADE;
CREATE TABLE public."ProductReward" (
	"id_Achievement" smallint NOT NULL,
	"id_Product" smallint NOT NULL,
	CONSTRAINT "ProductReward_pk" PRIMARY KEY ("id_Achievement","id_Product")

);
-- ddl-end --

-- object: "Achievement_fk" | type: CONSTRAINT --
-- ALTER TABLE public."ProductReward" DROP CONSTRAINT IF EXISTS "Achievement_fk" CASCADE;
ALTER TABLE public."ProductReward" ADD CONSTRAINT "Achievement_fk" FOREIGN KEY ("id_Achievement")
REFERENCES public."Achievement" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Product_fk" | type: CONSTRAINT --
-- ALTER TABLE public."ProductReward" DROP CONSTRAINT IF EXISTS "Product_fk" CASCADE;
ALTER TABLE public."ProductReward" ADD CONSTRAINT "Product_fk" FOREIGN KEY ("id_Product")
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

-- object: "User1_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PendingGames" DROP CONSTRAINT IF EXISTS "User1_fk" CASCADE;
ALTER TABLE public."PendingGames" ADD CONSTRAINT "User1_fk" FOREIGN KEY (w_player_name)
REFERENCES public."User" (name) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: "User2_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PendingGames" DROP CONSTRAINT IF EXISTS "User2_fk" CASCADE;
ALTER TABLE public."PendingGames" ADD CONSTRAINT "User2_fk" FOREIGN KEY (b_player_name)
REFERENCES public."User" (name) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


