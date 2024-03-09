CREATE TABLE Chat
(
    chat_id BIGINT,
    PRIMARY KEY(chat_id)
);

CREATE TABLE Link
(
    link_id BIGINT,
    url VARCHAR(255) NOT NULL ,
    last_update TIMESTAMP WITH TIME ZONE NOT NULL,
    last_check_time TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(link_id)
);

CREATE TABLE ChatLink
(
    chat_id BIGINT,
    link_id BIGINT,
    FOREIGN KEY (chat_id) REFERENCES Chat(chat_id),
    FOREIGN KEY (link_id) REFERENCES Link(link_id),
    PRIMARY KEY (chat_id, link_id)
);