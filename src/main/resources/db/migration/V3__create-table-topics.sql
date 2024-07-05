create table topics(

    id bigint not null auto_increment,
    title varchar(100) not null unique,
    message varchar(255) not null unique,
    date_time datetime not null,
    status tinyint not null,
    topic_author_id bigint not null,
    course_id bigint not null,

    primary key(id),

    constraint fk_topics_topic_author_id foreign key(topic_author_id) references users(id),
    constraint fk_topics_course_id foreign key(course_id) references courses(id)
);