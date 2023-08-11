create table if not exists image
(
    id_image int auto_increment
        primary key,
    url      longblob not null
);

create table if not exists chess_piece
(
    id_chess_piece int auto_increment
        primary key,
    image_id       int         not null,
    type           varchar(30) not null,
    on_board       tinyint(1)  not null,
    color          tinyint(1)  not null,
    constraint chess_piece_image_id_image_fk
        foreign key (image_id) references image (id_image)
);

create table if not exists user
(
    id_user  int auto_increment
        primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

create table if not exists lap
(
    id_lap           int auto_increment
        primary key,
    user_id_first    int        not null,
    user_id_second   int        not null,
    user_id_winner   int        null,
    active           tinyint(1) not null,
    moves_history_id int        not null,
    constraint lap_user_id_user_fk
        foreign key (user_id_first) references user (id_user),
    constraint lap_user_id_user_fk2
        foreign key (user_id_second) references user (id_user),
    constraint lap_user_id_user_fk3
        foreign key (user_id_winner) references user (id_user)
);

create table if not exists chat
(
    id_chat int auto_increment
        primary key,
    lap_id  int not null,
    constraint chat_lap_id_lap_fk
        foreign key (lap_id) references lap (id_lap)
);

create table if not exists message
(
    id_message int auto_increment
        primary key,
    text       varchar(10000) not null,
    date       datetime       not null,
    chat_id    int            not null,
    constraint message_chat_id_chat_fk
        foreign key (chat_id) references chat (id_chat)
);

create table if not exists moves_history
(
    id_moves_history int auto_increment
        primary key,
    chess_piece_move varchar(15) not null,
    chess_piece_id   int         not null,
    move_count       smallint    not null,
    user_id          int         not null,
    lap_id           int         not null,
    constraint moves_history_chess_piece_id_chess_piece_fk
        foreign key (chess_piece_id) references chess_piece (id_chess_piece),
    constraint moves_history_lap_id_lap_fk
        foreign key (lap_id) references lap (id_lap),
    constraint moves_history_user_id_user_fk
        foreign key (user_id) references user (id_user)
);

alter table lap
    add constraint lap_moves_history_id_moves_history_fk
        foreign key (moves_history_id) references moves_history (id_moves_history);

create table if not exists user_role
(
    user_id int not null,
    roles   int null,
    constraint user_role_user_id_user_fk
        foreign key (user_id) references user (id_user)
);

