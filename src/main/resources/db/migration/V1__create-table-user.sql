create table users (
	id bigint auto_increment primary key,
    name varchar(255) not null,
    username varchar(50) unique not null,
    email varchar(255) unique not null,
    password varchar(255) not null,
    role varchar(125) not null,

    created_at timestamp default current_timestamp not null
);