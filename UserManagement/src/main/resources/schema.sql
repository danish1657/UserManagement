CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       version INT
);

CREATE TABLE units (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       version INT
);

CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       version INT
);

CREATE TABLE user_roles (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            user_id BIGINT NOT NULL,
                            unit_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            valid_from TIMESTAMP NOT NULL,
                            valid_to TIMESTAMP,
                            version INT,
                            CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id),
                            CONSTRAINT fk_unit FOREIGN KEY (unit_id) REFERENCES units (id),
                            CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (id)
);
