CREATE TABLE team_membership
(
    role       VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    user_id    BIGINT NOT NULL,
    team_id    BIGINT NOT NULL,
    CONSTRAINT pk_team_membership PRIMARY KEY (user_id, team_id)
);

ALTER TABLE team_membership
    ADD CONSTRAINT FK_TEAM_MEMBERSHIP_ON_TEAM FOREIGN KEY (team_id) REFERENCES teams (id);

ALTER TABLE team_membership
    ADD CONSTRAINT FK_TEAM_MEMBERSHIP_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);