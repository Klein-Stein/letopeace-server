create table if not exists db_info (
    id varchar not null,
    "value" varchar not null
);

insert into db_info (id, "value") values ('db_version', 'v1.0');

create table if not exists genders (
	id int primary key,
	"name" varchar(6) not null
);

insert into genders (id, name) 
	values (1, 'Female'), (2, 'Male')
	on conflict do nothing;

create table if not exists users (
	id bigint generated always as identity,
	first_name varchar(128) not null,
	last_name varchar(128) not null,
	nickname varchar(128) not null,
	phone varchar(15) not null,
	email varchar(320),
	date_of_birth date not null,
	gender_id int,
	is_deleted boolean default false,
	created_at timestamptz default now(),
	modified_at timestamptz default now(),
	primary key(id),
	constraint fk_users_gender_id foreign key(gender_id) references genders(id),
	constraint uq_users_email unique (email),
	constraint uq_users_phone unique (phone)
);

create table if not exists posts (
	id bigint generated always as identity,
	msg varchar(4096) not null,
	likes_count int not null default 0,
	comments_count int not null default 0,
	created_by bigint not null,
	created_at timestamptz default now(),
	primary key(id),
	constraint fk_posts_created_by foreign key(created_by) references users(id)
);

create table if not exists post_likes (
	id bigint generated always as identity,
	post_id bigint not null,
	created_by bigint not null,
	created_at timestamptz default now(),
	primary key(id),
	constraint fk_post_likes_post_id foreign key(post_id) references posts(id) on update cascade on delete cascade,
	constraint fk_post_likes_created_by foreign key(created_by) references users(id),
	constraint uq_post_likes unique (post_id, created_by)
);


create table if not exists comments (
	id bigint generated always as identity,
	post_id bigint not null,
	msg varchar(2048) not null,
	likes_count int not null default 0,
	created_by bigint not null,
	created_at timestamptz default now(),
	primary key(id),
	constraint fk_comments_post_id foreign key(post_id) references posts(id) on update cascade on delete cascade,
	constraint fk_comments_created_by foreign key(created_by) references users(id)
);

create table if not exists comment_likes (
	id bigint generated always as identity,
	comment_id bigint not null,
	created_by bigint not null,
	created_at timestamptz default now(),
	primary key(id),
	constraint fk_comment_likes_post_id foreign key(comment_id) references comments(id) on update cascade on delete cascade,
	constraint fk_comment_likes_created_by foreign key(created_by) references users(id),
	constraint uq_comment_likes unique (comment_id, created_by)
);
