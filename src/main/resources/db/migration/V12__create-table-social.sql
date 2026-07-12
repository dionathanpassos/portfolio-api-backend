create table tb_social(
	id bigint auto_increment primary key,
    github_url varchar(255) not null,
    linkedin_url varchar(255) not null,
    email varchar(255) not null,
    website varchar(255),
    user_id bigint unique not null,

    constraint fk_social_user foreign key (user_id) references users(id)
);