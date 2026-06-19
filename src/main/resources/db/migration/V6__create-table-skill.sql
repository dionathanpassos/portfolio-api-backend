create table tb_skill (
	id bigint auto_increment primary key,
    name varchar(255) not null,
    category_skill varchar(255) not null,
    icon_url varchar(255),
	level int not null,
    active boolean not null default true,
    deleted boolean not null default false,
    user_id bigint not null,

    constraint fk_skill_user foreign key (user_id) references users(id)
);