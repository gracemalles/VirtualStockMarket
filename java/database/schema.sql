BEGIN TRANSACTION;

DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS games CASCADE;
DROP TABLE IF EXISTS game_players CASCADE;
DROP TABLE IF EXISTS player_history CASCADE;
DROP TABLE IF EXISTS stocks CASCADE;
DROP TABLE IF EXISTS trades CASCADE;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE games (
        id serial PRIMARY KEY,
        name varchar(50) NOT NULL,
        organizer_id int NOT NULL,
        end_date date NOT NULL,
        end_time time NOT NULL,
        
        CONSTRAINT FK_games_users FOREIGN KEY(organizer_id) REFERENCES users(user_id)
);

CREATE TABLE game_players (
        id serial PRIMARY KEY,
        user_id int NOT NULL,
        game_id int NOT NULL,
        available_funds decimal(15, 2) NOT NULL,
        game_status varchar(50) NOT NULL,
        
        CONSTRAINT FK_game_players_users FOREIGN KEY(user_id) REFERENCES users(user_id),
        CONSTRAINT FK_game_players_games FOREIGN KEY(game_id) REFERENCES games(id)
);

CREATE TABLE player_history (
        id serial PRIMARY KEY,
        player_id int NOT NULL,
        date date NOT NULL,
        time time NOT NULL,
        portfolio_value decimal(15, 2) NOT NULL,
        
        CONSTRAINT FK_player_history_players FOREIGN KEY(player_id) REFERENCES game_players(id) 
);

--contains stocks that are currently held by players
CREATE TABLE stocks (
        id serial PRIMARY KEY,
        player_id int NOT NULL,
        stock_name varchar(50) NOT NULL,
        stock_ticker varchar(50) NOT NULL,
        total_shares decimal(15, 4) NOT NULL,
        
        CONSTRAINT FK_stocks_game_players FOREIGN KEY(player_id) REFERENCES game_players(id)
);

CREATE TABLE trades (
        id serial PRIMARY KEY,
        stock_id int NOT NULL,
        shares_traded decimal(15, 4) NOT NULL,
        buy_or_sell varchar(50) NOT NULL,
        --price at time of trade
        price decimal(15, 2) NOT NULL,
        -- entered in shares or dollars
        entered_in varchar(50) NOT NULL,
        -- date and time of trade
        date date NOT NULL,
        time time NOT NULL,
        
        CONSTRAINT FK_trades_stocks FOREIGN KEY(stock_id) REFERENCES stocks(id)
);

COMMIT TRANSACTION;
