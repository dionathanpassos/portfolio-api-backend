create table tb_project (
	id bigint auto_increment primary key,
    title varchar(255) not null,
    description varchar(3000) not null,
    slug varchar(255) not null unique,
    repository_url varchar(255) not null,
    demo_url varchar(255),
    featured boolean not null default false,
    status varchar(30) not null,
    created_at timestamp default current_timestamp not null,
    user_id bigint not null,

    constraint fk_project_user foreign key (user_id) references users(id)

);