create table tb_timeline(
	id bigint auto_increment primary key,
    type varchar(40) not null,
    title varchar(255) not null,
    subtitle varchar(255) not null,
    description varchar(1000),
    start_date date not null,
    end_date date,
    current boolean not null default false,
    featured boolean not null default true,
    user_id bigint not null,

    constraint fk_timeline_user foreign key (user_id) references users(id)
);