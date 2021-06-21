create table user
(
    id       bigint                 not null auto_increment,
    username varchar(63)            not null,
    password varchar(63)            not null,
    score    bigint                 not null,
    role     enum ('ADMIN', 'USER') not null,

    primary key (id)
);

create table menu
(
    id    bigint                                            not null auto_increment,
    type  enum ('MAIN_COURSE', 'GARNISH','DESERT', 'DRINK') not null,
    food  varchar(50)                                       not null,
    price bigint                                            not null,

    primary key (id)
);

create table target
(
    id bigint not null auto_increment,
    cart_id      bigint   not null,
    user_id bigint   not null,
    cost    int      not null,
    time    datetime not null,

    primary key (id),
    foreign key (cart_id) references cart(user_id),
    foreign key (user_id) references user (id)
);

create table cart
(
    id bigint not null auto_increment,
    menu_id   bigint not null,

    primary key (id),
    foreign key (menu_id) references menu (id)

);

create table review
(
    target_id bigint not null,
    mark      bigint,
    comment   varchar(150),

    foreign key (target_id) references target (id)
);