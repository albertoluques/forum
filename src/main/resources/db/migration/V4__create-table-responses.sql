create table responses(

    id bigint not null auto_increment,
    message varchar(255) not null,
    topic_id bigint not null,
    creation_date datetime not null,
    author_response_id bigint not null,
    solved tinyint not null,

    primary key(id),

    constraint fk_responses_author_response_id foreign key(author_response_id) references users(id),
    constraint fk_responses_topic_id foreign key(topic_id) references topics(id)
);