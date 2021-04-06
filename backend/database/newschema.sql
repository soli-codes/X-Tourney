BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_team_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

  CREATE SEQUENCE seq_game_type_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_tournament_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

  CREATE SEQUENCE seq_match_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	wins integer NOT NULL DEFAULT 0,
	losses integer NOT NULL DEFAULT 0,
	tournament_wins integer NOT NULL DEFAULT 0,
	tournaments_entered integer NOT NULL DEFAULT 0,

	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE team_name (
    team_id int DEFAULT nextval('seq_team_id'::regclass) NOT NULL,
    team_name varchar(50) NOT NULL,
    wins integer NOT NULL DEFAULT 0,
	losses integer NOT NULL DEFAULT 0,
	tournament_wins integer NOT NULL DEFAULT 0,
	tournaments_entered integer NOT NULL DEFAULT 0,

    CONSTRAINT PK_team_id PRIMARY KEY (team_id)
);

CREATE TABLE team_name_users (
    user_id integer NOT NULL,
    team_id integer NOT NULL,

    CONSTRAINT FK_user_id_team_id FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_team_id_user_id FOREIGN KEY (team_id) REFERENCES team_name (team_id)
);

CREATE TABLE game_type (
    game_type_id int DEFAULT nextval('seq_game_type_id'::regclass) NOT NULL,
    game_type varchar(50) NOT NULL,

    CONSTRAINT PK_game_type_id PRIMARY KEY (game_type_id)
);

CREATE TABLE tournament (
    tournament_id int DEFAULT nextval('seq_tournament_id'::regclass) NOT NULL,
    game_type_id integer NOT NULL,
    name varchar(100) NOT NULL,
    description varchar(1000),
    is_private boolean NOT NULL,
    is_open boolean NOT NULL,
    max_teams integer NOT NULL,
    elimination_type varchar(50) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    signup_open date NOT NULL,
    signup_close date NOT NULL,

    CONSTRAINT PK_tournament_id PRIMARY KEY (tournament_id),
    CONSTRAINT FK_game_type_id FOREIGN KEY (game_type_id) REFERENCES game_type (game_type_id)

);

CREATE TABLE tournament_users (
    user_id integer NOT NULL,
    team_id integer NOT NULL,
    tournament_id integer NOT NULL,

    CONSTRAINT FK_user_id_team_id_tournament_id FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_team_id_user_id_tournament_id FOREIGN KEY (team_id) REFERENCES team_name (team_id),
    CONSTRAINT FK_tournament_id_user_id_team_id FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id)
);

CREATE TABLE matches (
    match_id int DEFAULT nextval('seq_match_id'::regclass) NOT NULL,
    tournament_id integer NOT NULL,
    team_1_id integer NOT NULL,
    team_2_id integer NOT NULL,
    winning_team_id integer,
    losing_team_id integer,
    match_date date NOT NULL,
    match_time time NOT NULL,

    CONSTRAINT FK_tournament_id FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


COMMIT TRANSACTION;
