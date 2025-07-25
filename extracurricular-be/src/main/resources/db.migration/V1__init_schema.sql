CREATE TABLE users
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(100) NOT NULL,
    role       SMALLINT     NOT NULL,
);

CREATE TABLE activity
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(100) NOT NULL,
    description TEXT,
    start_time  TIMESTAMP    NOT NULL,
    end_time    TIMESTAMP    NOT NULL,
    location    VARCHAR(200),
    created_by  BIGINT,
    FOREIGN KEY (created_by) REFERENCES users (id)
);

CREATE TABLE registration
(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id        BIGINT      NOT NULL,
    activity_id       BIGINT      NOT NULL,
    registration_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status            VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED')),
    FOREIGN KEY (student_id) REFERENCES users (id),
    FOREIGN KEY (activity_id) REFERENCES activity (id)
);

CREATE TABLE notification
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT NOT NULL,
    message    TEXT   NOT NULL,
    is_read    BOOLEAN   DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO users (username, password, role)
VALUES
    ('student01', '2a$10$r.XIN4K9vTioiuYQwaTop.UVQ5r5FvrKk2V5Orm9Hc6n4i9Tvjthy', 0),
    ('teacher01', '2a$10$r.XIN4K9vTioiuYQwaTop.UVQ5r5FvrKk2V5Orm9Hc6n4i9Tvjthy', 1),
    ('admin', '2a$10$r.XIN4K9vTioiuYQwaTop.UVQ5r5FvrKk2V5Orm9Hc6n4i9Tvjthy', 2);
