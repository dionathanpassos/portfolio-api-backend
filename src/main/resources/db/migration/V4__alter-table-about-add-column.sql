create table tb_about (
	id bigint auto_increment primary key,
    name varchar(255) not null,
    title varchar(255) not null,
    bio text not null,
    location varchar(255),
    github_url varchar(255) not null,
    linkedin_url varchar(255) not null,
    email varchar(255) not null,
    user_id bigint unique not null,

    constraint fk_about_user foreign key (user_id) references users(id)

);