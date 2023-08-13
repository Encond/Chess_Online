DROP DATABASE IF EXISTS chess_online;
CREATE DATABASE IF NOT EXISTS chess_online;
USE chess_online;

create table chess_online.user
(
    id_user  int auto_increment
        primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

create table chess_online.chess_piece_move
(
    id_chess_piece_move int auto_increment
        primary key,
    move_from_x         smallint not null,
    move_from_y         smallint not null,
    move_to_x           smallint not null,
    move_to_y           smallint not null,
    user_id             int      not null,
    constraint chess_piece_move_user_id_user_fk
        foreign key (user_id) references chess_online.user (id_user)
);

create table chess_online.moves_history
(
    id_moves_history    int auto_increment
        primary key,
    chess_piece_move_id int not null,
    constraint moves_history_chess_piece_move_id_chess_piece_move_fk
        foreign key (chess_piece_move_id) references chess_online.chess_piece_move (id_chess_piece_move)
);

create table chess_online.lap
(
    id_lap           int auto_increment
        primary key,
    user_id_first    int        not null,
    user_id_second   int        not null,
    user_id_winner   int        null,
    active           tinyint(1) not null,
    moves_history_id int        null,
    constraint lap_moves_history_id_moves_history_fk
        foreign key (moves_history_id) references chess_online.moves_history (id_moves_history),
    constraint lap_user_id_user_fk
        foreign key (user_id_first) references chess_online.user (id_user),
    constraint lap_user_id_user_fk2
        foreign key (user_id_second) references chess_online.user (id_user),
    constraint lap_user_id_user_fk3
        foreign key (user_id_winner) references chess_online.user (id_user)
);

create table chess_online.chat
(
    id_chat int auto_increment
        primary key,
    lap_id  int not null,
    constraint chat_lap_id_lap_fk
        foreign key (lap_id) references chess_online.lap (id_lap)
);

create table chess_online.message
(
    id_message int auto_increment
        primary key,
    text       varchar(10000) not null,
    date       datetime       not null,
    chat_id    int            not null,
    constraint message_chat_id_chat_fk
        foreign key (chat_id) references chess_online.chat (id_chat)
);

create table chess_online.user_role
(
    user_id int not null,
    roles   int null,
    constraint user_role_user_id_user_fk
        foreign key (user_id) references chess_online.user (id_user)
);

