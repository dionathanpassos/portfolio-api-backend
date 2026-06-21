create table tb_project_skill (
	project_id bigint not null,
    skill_id bigint not null,

    primary key(project_id, skill_id),

    constraint fk_project_skill_project foreign key(project_id) references tb_project(id),
    constraint fk_project_skill_skill foreign key(skill_id) references tb_skill(id)
);