drop database if exists chess_online;
create database if not exists chess_online;
use chess_online;

create table if not exists chess_online.moves_history
(
    id_moves_history int auto_increment
        primary key
);

create table if not exists chess_online.user
(
    id_user  int auto_increment
        primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

create table if not exists chess_online.chess_piece_move
(
    id_chess_piece_move int auto_increment
        primary key,
    move_from_x         smallint not null,
    move_from_y         smallint not null,
    move_to_x           smallint not null,
    move_to_y           smallint not null,
    user_id             int      not null,
    moves_history_id    int      not null,
    constraint chess_piece_move_moves_history_id_moves_history_fk
        foreign key (moves_history_id) references chess_online.moves_history (id_moves_history)
            on delete cascade,
    constraint chess_piece_move_user_id_user_fk
        foreign key (user_id) references chess_online.user (id_user)
);

create table if not exists chess_online.lap
(
    id_lap           int auto_increment
        primary key,
    user_id_first    int        not null,
    user_id_second   int        not null,
    user_id_winner   int        null,
    active           tinyint(1) not null,
    moves_history_id int        null,
    constraint lap_moves_history_id_moves_history_fk
        foreign key (moves_history_id) references chess_online.moves_history (id_moves_history)
            on delete cascade,
    constraint lap_user_id_user_fk
        foreign key (user_id_first) references chess_online.user (id_user),
    constraint lap_user_id_user_fk2
        foreign key (user_id_second) references chess_online.user (id_user),
    constraint lap_user_id_user_fk3
        foreign key (user_id_winner) references chess_online.user (id_user)
);

create table if not exists chess_online.chat
(
    id_chat int auto_increment
        primary key,
    lap_id  int not null,
    constraint chat_lap_id_lap_fk
        foreign key (lap_id) references chess_online.lap (id_lap)
            on delete cascade
);

create table if not exists chess_online.message
(
    id_message int auto_increment
        primary key,
    text       varchar(10000) not null,
    date       datetime       not null,
    chat_id    int            not null,
    user_id    int            not null,
    constraint message_chat_id_chat_fk
        foreign key (chat_id) references chess_online.chat (id_chat)
            on delete cascade,
    constraint message_user_id_user_fk
        foreign key (user_id) references chess_online.user (id_user)
);

create table if not exists chess_online.user_role
(
    user_id int not null,
    roles   int null,
    constraint user_role_user_id_user_fk
        foreign key (user_id) references chess_online.user (id_user)
);

