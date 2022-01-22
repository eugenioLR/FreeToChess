-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.3
-- PostgreSQL version: 13.0
-- Project Site: pgmodeler.io
-- Model Author: ---

-- Database creation must be performed outside a multi lined SQL file. 
-- These commands were put in this file only as a convenience.
-- 
-- object: "Free2chess" | type: DATABASE --
-- DROP DATABASE IF EXISTS "Free2chess";
CREATE DATABASE "Free2chess";
-- ddl-end --


-- object: public."User" | type: TABLE --
-- DROP TABLE IF EXISTS public."User" CASCADE;
CREATE TABLE public."User" (
	id smallint NOT NULL,
	name varchar(20),
	password smallint,
	salt smallint,
	elo smallint,
	level smallint,
	exp smallint,
	subscribed bool,
	subs_date date,
	paypal_id varchar(20),
	coins smallint,
	diamonds smallint,
	CONSTRAINT "User_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."User" OWNER TO postgres;
-- ddl-end --

-- object: public."Game" | type: TABLE --
-- DROP TABLE IF EXISTS public."Game" CASCADE;
CREATE TABLE public."Game" (
	id smallint NOT NULL,
	CONSTRAINT "Game_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."Game" OWNER TO postgres;
-- ddl-end --

-- object: public."Booster" | type: TABLE --
-- DROP TABLE IF EXISTS public."Booster" CASCADE;
CREATE TABLE public."Booster" (

-- 	id smallint NOT NULL,
-- 	name varchar(50),
-- 	description text,
-- 	price numeric,
-- 	rarity char(1),
-- 	purchases smallint,
-- 	discount_perc float,
	CONSTRAINT "Booster_pk" PRIMARY KEY (id)

)
 INHERITS(public."Product");
-- ddl-end --
ALTER TABLE public."Booster" OWNER TO postgres;
-- ddl-end --

-- object: public."Product" | type: TABLE --
-- DROP TABLE IF EXISTS public."Product" CASCADE;
CREATE TABLE public."Product" (
	id smallint NOT NULL,
	name varchar(50),
	description text,
	price numeric,
	rarity char(1),
	purchases smallint,
	discount_perc float,
	CONSTRAINT "Product_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."Product" OWNER TO postgres;
-- ddl-end --

-- object: public."GameState" | type: TABLE --
-- DROP TABLE IF EXISTS public."GameState" CASCADE;
CREATE TABLE public."GameState" (
	id smallint NOT NULL,
	"id_Game" smallint,
	"moveString" text,
	CONSTRAINT "GameState_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public."GameState" OWNER TO postgres;
-- ddl-end --

-- object: public."Emoji" | type: TABLE --
-- DROP TABLE IF EXISTS public."Emoji" CASCADE;
CREATE TABLE public."Emoji" (
	texture_path varchar(150),

-- 	id smallint NOT NULL,
-- 	name varchar(50),
-- 	description text,
-- 	price numeric,
-- 	rarity char(1),
-- 	purchases smallint,
-- 	discount_perc float,
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
-- 	price numeric,
-- 	rarity char(1),
-- 	purchases smallint,
-- 	discount_perc float,
	CONSTRAINT "BoardSkin_pk" PRIMARY KEY (id)

)
 INHERITS(public."Product");
-- ddl-end --
ALTER TABLE public."BoardSkin" OWNER TO postgres;
-- ddl-end --

-- object: public."PieceSkinSet" | type: TABLE --
-- DROP TABLE IF EXISTS public."PieceSkinSet" CASCADE;
CREATE TABLE public."PieceSkinSet" (

-- 	id smallint NOT NULL,
-- 	name varchar(50),
-- 	description text,
-- 	price numeric,
-- 	rarity char(1),
-- 	purchases smallint,
-- 	discount_perc float,
	CONSTRAINT "PieceSkinSet_pk" PRIMARY KEY (id)

)
 INHERITS(public."Product");
-- ddl-end --
ALTER TABLE public."PieceSkinSet" OWNER TO postgres;
-- ddl-end --

-- object: public."PieceSkin" | type: TABLE --
-- DROP TABLE IF EXISTS public."PieceSkin" CASCADE;
CREATE TABLE public."PieceSkin" (
	id smallint NOT NULL,
	"id_PieceSkinSet" smallint,
	texture_path varchar(150),

-- 	name varchar(50),
-- 	description text,
-- 	price numeric,
-- 	rarity char(1),
-- 	purchases smallint,
-- 	discount_perc float,
	CONSTRAINT "PieceSkin_pk" PRIMARY KEY (id)

)
 INHERITS(public."Product");
-- ddl-end --
ALTER TABLE public."PieceSkin" OWNER TO postgres;
-- ddl-end --

-- object: public."PendingGames" | type: TABLE --
-- DROP TABLE IF EXISTS public."PendingGames" CASCADE;
CREATE TABLE public."PendingGames" (
	id smallint NOT NULL,
	"id_Tournament" smallint,
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

-- object: "Tournament_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PendingGames" DROP CONSTRAINT IF EXISTS "Tournament_fk" CASCADE;
ALTER TABLE public."PendingGames" ADD CONSTRAINT "Tournament_fk" FOREIGN KEY ("id_Tournament")
REFERENCES public."Tournament" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "Game_fk" | type: CONSTRAINT --
-- ALTER TABLE public."GameState" DROP CONSTRAINT IF EXISTS "Game_fk" CASCADE;
ALTER TABLE public."GameState" ADD CONSTRAINT "Game_fk" FOREIGN KEY ("id_Game")
REFERENCES public."Game" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public."PurchasedProduct" | type: TABLE --
-- DROP TABLE IF EXISTS public."PurchasedProduct" CASCADE;
CREATE TABLE public."PurchasedProduct" (
	"id_User" smallint NOT NULL,
	"id_Product" smallint NOT NULL,
	CONSTRAINT "PurchasedProduct_pk" PRIMARY KEY ("id_User","id_Product")

);
-- ddl-end --

-- object: "User_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PurchasedProduct" DROP CONSTRAINT IF EXISTS "User_fk" CASCADE;
ALTER TABLE public."PurchasedProduct" ADD CONSTRAINT "User_fk" FOREIGN KEY ("id_User")
REFERENCES public."User" (id) MATCH FULL
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
	"id_User" smallint NOT NULL,
	CONSTRAINT "CompletedAchivement_pk" PRIMARY KEY ("id_Achievement","id_User")

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
ALTER TABLE public."CompletedAchivement" ADD CONSTRAINT "User_fk" FOREIGN KEY ("id_User")
REFERENCES public."User" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."PlayedGame" | type: TABLE --
-- DROP TABLE IF EXISTS public."PlayedGame" CASCADE;
CREATE TABLE public."PlayedGame" (
	"id_Game" smallint NOT NULL,
	"id_User" smallint NOT NULL,
	CONSTRAINT "PlayedGame_pk" PRIMARY KEY ("id_Game","id_User")

);
-- ddl-end --

-- object: "Game_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PlayedGame" DROP CONSTRAINT IF EXISTS "Game_fk" CASCADE;
ALTER TABLE public."PlayedGame" ADD CONSTRAINT "Game_fk" FOREIGN KEY ("id_Game")
REFERENCES public."Game" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "User_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PlayedGame" DROP CONSTRAINT IF EXISTS "User_fk" CASCADE;
ALTER TABLE public."PlayedGame" ADD CONSTRAINT "User_fk" FOREIGN KEY ("id_User")
REFERENCES public."User" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."JoinedTournament" | type: TABLE --
-- DROP TABLE IF EXISTS public."JoinedTournament" CASCADE;
CREATE TABLE public."JoinedTournament" (
	"id_Tournament" smallint NOT NULL,
	"id_User" smallint NOT NULL,
	CONSTRAINT "JoinedTournament_pk" PRIMARY KEY ("id_Tournament","id_User")

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
ALTER TABLE public."JoinedTournament" ADD CONSTRAINT "User_fk" FOREIGN KEY ("id_User")
REFERENCES public."User" (id) MATCH FULL
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

-- object: "PieceSkinSet_fk" | type: CONSTRAINT --
-- ALTER TABLE public."PieceSkin" DROP CONSTRAINT IF EXISTS "PieceSkinSet_fk" CASCADE;
ALTER TABLE public."PieceSkin" ADD CONSTRAINT "PieceSkinSet_fk" FOREIGN KEY ("id_PieceSkinSet")
REFERENCES public."PieceSkinSet" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public."GamesToAccept" | type: TABLE --
-- DROP TABLE IF EXISTS public."GamesToAccept" CASCADE;
CREATE TABLE public."GamesToAccept" (
	"id_User" smallint NOT NULL,
	"id_PendingGames" smallint NOT NULL,
	CONSTRAINT "GamesToAccept_pk" PRIMARY KEY ("id_User","id_PendingGames")

);
-- ddl-end --

-- object: "User_fk" | type: CONSTRAINT --
-- ALTER TABLE public."GamesToAccept" DROP CONSTRAINT IF EXISTS "User_fk" CASCADE;
ALTER TABLE public."GamesToAccept" ADD CONSTRAINT "User_fk" FOREIGN KEY ("id_User")
REFERENCES public."User" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "PendingGames_fk" | type: CONSTRAINT --
-- ALTER TABLE public."GamesToAccept" DROP CONSTRAINT IF EXISTS "PendingGames_fk" CASCADE;
ALTER TABLE public."GamesToAccept" ADD CONSTRAINT "PendingGames_fk" FOREIGN KEY ("id_PendingGames")
REFERENCES public."PendingGames" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."ProductPack" | type: TABLE --
-- DROP TABLE IF EXISTS public."ProductPack" CASCADE;
CREATE TABLE public."ProductPack" (
	pack_id smallint NOT NULL,

-- 	id smallint NOT NULL,
-- 	name varchar(50),
-- 	description text,
-- 	price numeric,
-- 	rarity char(1),
-- 	purchases smallint,
-- 	discount_perc float,
	CONSTRAINT "ProductPack_pk" PRIMARY KEY (pack_id)

)
 INHERITS(public."Product");
-- ddl-end --
ALTER TABLE public."ProductPack" OWNER TO postgres;
-- ddl-end --

-- object: public."many_Product_has_many_ProductPack" | type: TABLE --
-- DROP TABLE IF EXISTS public."many_Product_has_many_ProductPack" CASCADE;
CREATE TABLE public."many_Product_has_many_ProductPack" (
	"id_Product" smallint NOT NULL,
	"pack_id_ProductPack" smallint NOT NULL,
	CONSTRAINT "many_Product_has_many_ProductPack_pk" PRIMARY KEY ("id_Product","pack_id_ProductPack")

);
-- ddl-end --

-- object: "Product_fk" | type: CONSTRAINT --
-- ALTER TABLE public."many_Product_has_many_ProductPack" DROP CONSTRAINT IF EXISTS "Product_fk" CASCADE;
ALTER TABLE public."many_Product_has_many_ProductPack" ADD CONSTRAINT "Product_fk" FOREIGN KEY ("id_Product")
REFERENCES public."Product" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "ProductPack_fk" | type: CONSTRAINT --
-- ALTER TABLE public."many_Product_has_many_ProductPack" DROP CONSTRAINT IF EXISTS "ProductPack_fk" CASCADE;
ALTER TABLE public."many_Product_has_many_ProductPack" ADD CONSTRAINT "ProductPack_fk" FOREIGN KEY ("pack_id_ProductPack")
REFERENCES public."ProductPack" (pack_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


