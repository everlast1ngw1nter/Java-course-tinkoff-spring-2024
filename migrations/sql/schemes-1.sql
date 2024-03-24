CREATE TABLE chat
(
    id BIGINT,
    PRIMARY KEY(id)
);

CREATE TABLE link
(
    id BIGINT,
    url VARCHAR(255) NOT NULL ,
    last_update TIMESTAMP WITH TIME ZONE NOT NULL,
    last_check_time TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE chat_link
(
    chat_id BIGINT,
    link_id BIGINT,
    FOREIGN KEY (chat_id) REFERENCES chat(id)
        ON DELETE CASCADE,
    FOREIGN KEY (link_id) REFERENCES link(id),
    PRIMARY KEY (chat_id, link_id)
);