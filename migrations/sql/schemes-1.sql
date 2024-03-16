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
    chat_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (chat_id) REFERENCES chat(id)
);