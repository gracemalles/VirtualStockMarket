INSERT INTO users (username, password_hash, role)
VALUES ('Alison', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO users (username, password_hash, role)
VALUES ('Grace', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO users (username, password_hash, role)
VALUES ('Kailey', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO users (username, password_hash, role)
VALUES ('Russell', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO games (name, organizer_id, end_date, end_time)
VALUES ('Game One', 4, '2021-10-01', '11:59:59 PM');

INSERT INTO games (name, organizer_id, end_date, end_time)
VALUES ('Demo', 5, '2021-10-02', '11:59:59 PM');

INSERT INTO games (name, organizer_id, end_date, end_time)
VALUES ('Alison Game', 3, '2021-10-05', '07:59:59 PM');

INSERT INTO games (name, organizer_id, end_date, end_time)
VALUES ('Game Over Demo', 6, '2021-09-28', '08:59:59 PM');

INSERT INTO games (name, organizer_id, end_date, end_time)
VALUES ('Game Over Demo 2', 6, '2021-09-28', '08:59:59 PM');


INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (4, 2, 95497.82, 'Accepted');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (2, 2, 70430.20, 'Accepted');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (5, 2, 44342.45, 'Accepted');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (3, 1, 100000.00, 'Accepted');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (2, 1, 100000.00, 'Pending');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (5, 1, 100000.00, 'Pending');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (3, 3, 100000.00, 'Accepted');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (2, 3, 100000.00, 'Pending');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (5, 3, 100000.00, 'Pending');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (2, 4, 110437.36, 'Finished'); 

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (5, 5, 110437.36, 'Finished'); 

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (6, 4, 95497.82, 'Finished');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (6, 5, 95497.82, 'Finished');  


INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (1, 'Apple Inc', 'AAPL', 50);

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (2, 'Apple Inc', 'AAPL', 100);

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (3, 'Tesla Inc', 'TSLA', 50);

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (2, 'Facebook Inc', 'FB', 50);

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (3, 'Facebook Inc', 'FB', 50);

INSERT INTO trades (stock_id, shares_traded, buy_or_sell, price, entered_in, date, time)
VALUES (1, 50, 'Buy', 7268, 'Shares', '2021-9-30', '11:59:59');

INSERT INTO trades (stock_id, shares_traded, buy_or_sell, price, entered_in, date, time)
VALUES (2, 50, 'Buy', 7268, 'Shares', '2021-9-30', '11:59:59');

INSERT INTO trades (stock_id, shares_traded, buy_or_sell, price, entered_in, date, time)
VALUES (2, 50, 'Buy', 7268, 'Shares', '2021-9-30', '12:01:19');

INSERT INTO trades (stock_id, shares_traded, buy_or_sell, price, entered_in, date, time)
VALUES (3, 100, 'Buy', 77640, 'Shares', '2021-9-30', '02:03:44');

INSERT INTO trades (stock_id, shares_traded, buy_or_sell, price, entered_in, date, time)
VALUES (3, 50, 'Sell', 38820, 'Shares', '2021-9-30', '02:04:27');

INSERT INTO trades (stock_id, shares_traded, buy_or_sell, price, entered_in, date, time)
VALUES (4, 50, 'Buy', 17018, 'Shares', '2021-9-30', '11:59:59');

INSERT INTO trades (stock_id, shares_traded, buy_or_sell, price, entered_in, date, time)
VALUES (5, 50, 'Buy', 17018, 'Shares', '2021-9-30', '01:24:51');

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '14:00:00', 101497.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '14:10:00', 109526.38);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '14:20:00', 116602.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '14:30:00', 111944.18);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '14:40:00', 114417.57);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '14:50:00', 113197.82);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '15:00:00', 115487.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '15:10:00', 111697.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '15:20:00', 112111.11);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '15:30:00', 112477.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '15:40:00', 112888.17);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '15:50:00', 111497.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (1, '2021-09-30', '16:00:00', 110497.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '14:00:00', 97497.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '14:10:00', 98526.38);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '14:20:00', 100602.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '14:30:00', 99944.18);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '14:40:00', 102417.57);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '14:50:00', 101222.82);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '15:00:00', 101232.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '15:10:00', 99000.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '15:20:00', 96123.11);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '15:30:00', 96342.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '15:40:00', 98111.17);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '15:50:00', 97033.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (2, '2021-09-30', '16:00:00', 95497.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '14:00:00', 111497.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '14:10:00', 109506.38);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '14:20:00', 108002.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '14:30:00', 112944.18);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '14:40:00', 113519.57);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '14:50:00', 113197.82);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '15:00:00', 113487.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '15:10:00', 113697.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '15:20:00', 113111.11);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '15:30:00', 112877.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '15:40:00', 112388.17);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '15:50:00', 111997.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (3, '2021-09-30', '16:00:00', 110437.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '14:00:00', 97497.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '14:10:00', 98526.38);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '14:20:00', 100602.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '14:30:00', 99944.18);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '14:40:00', 102417.57);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '14:50:00', 101222.82);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '15:00:00', 101232.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '15:10:00', 99000.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '15:20:00', 96123.11);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '15:30:00', 96342.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '15:40:00', 98111.17);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '15:50:00', 97033.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (12, '2021-09-30', '16:00:00', 95497.82);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '14:00:00', 111497.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '14:10:00', 109506.38);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '14:20:00', 108002.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '14:30:00', 112944.18);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '14:40:00', 113519.57);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '14:50:00', 113197.82);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '15:00:00', 113487.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '15:10:00', 113697.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '15:20:00', 113111.11);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '15:30:00', 112877.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '15:40:00', 112388.17);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '15:50:00', 111997.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (10, '2021-09-30', '16:00:00', 110437.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '14:00:00', 97497.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '14:10:00', 98526.38);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '14:20:00', 100602.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '14:30:00', 99944.18);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '14:40:00', 102417.57);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '14:50:00', 101222.82);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '15:00:00', 101232.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '15:10:00', 99000.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '15:20:00', 96123.11);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '15:30:00', 96342.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '15:40:00', 98111.17);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '15:50:00', 97033.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (13, '2021-09-30', '16:00:00', 95497.82);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '14:00:00', 111497.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '14:10:00', 109506.38);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '14:20:00', 108002.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '14:30:00', 112944.18);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '14:40:00', 113519.57);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '14:50:00', 113197.82);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '15:00:00', 113487.12);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '15:10:00', 113697.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '15:20:00', 113111.11);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '15:30:00', 112877.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '15:40:00', 112388.17);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '15:50:00', 111997.36);

INSERT INTO player_history (player_id, date, time, portfolio_value)
VALUES (11, '2021-09-30', '16:00:00', 110437.36);