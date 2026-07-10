create table tb_hero(
	id bigint auto_increment primary key,
    name varchar(255) not null,
    position varchar(255) not null,
    eyebrow varchar(255) not null,
    introdution varchar(1000),
    user_id bigint unique not null,

    constraint fk_hero_user foreign key (user_id) references users(id)


);