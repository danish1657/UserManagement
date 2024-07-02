-- Insert Users
INSERT INTO users (version, name) VALUES (1, 'Alice');
INSERT INTO users (version, name) VALUES (2, 'Bob');
INSERT INTO users (version, name) VALUES (1, 'Eve');

-- Insert Units
INSERT INTO units (id, version, name) VALUES (11, 2, 'Kreftregisteret');
INSERT INTO units (id, version, name) VALUES (12, 1, 'Akershus universitetssykehus HF');
INSERT INTO units (id, version, name) VALUES (13, 2, 'Sørlandet sykehus HF');
INSERT INTO units (id, version, name) VALUES (14, 2, 'Vestre Viken HF');

-- Insert Roles
INSERT INTO roles (id, version, name) VALUES (101, 1, 'User administration');
INSERT INTO roles (id, version, name) VALUES (102, 2, 'Endoscopist administration');
INSERT INTO roles (id, version, name) VALUES (103, 1, 'Report colonoscopy capacity');
INSERT INTO roles (id, version, name) VALUES (104, 2, 'Send invitations');
INSERT INTO roles (id, version, name) VALUES (105, 1, 'View statistics');

-- Insert UserRoles
INSERT INTO user_roles (id, version, user_id, unit_id, role_id, valid_from, valid_to) VALUES (1001, NULL, 1, 11, 101, '2019-01-02 00:00:00', '2019-12-31 23:59:59');
INSERT INTO user_roles (id, version, user_id, unit_id, role_id, valid_from, valid_to) VALUES (1002, 2, 1, 11, 104, '2019-01-02 00:00:00', '2019-12-31 23:59:59');
INSERT INTO user_roles (id, version, user_id, unit_id, role_id, valid_from, valid_to) VALUES (1003, 1, 1, 11, 105, '2019-06-11 00:00:00', '2019-12-31 23:59:59');
INSERT INTO user_roles (id, version, user_id, unit_id, role_id, valid_from, valid_to) VALUES (1004, 2, 2, 12, 101, '2020-01-28 00:00:00', NULL);
INSERT INTO user_roles (id, version, user_id, unit_id, role_id, valid_from, valid_to) VALUES (1005, 1, 2, 12, 105, '2020-01-28 00:00:00', NULL);
INSERT INTO user_roles (id, version, user_id, unit_id, role_id, valid_from, valid_to) VALUES (1006, 1, 2, 14, 101, '2020-01-28 00:00:00', NULL);
INSERT INTO user_roles (id, version, user_id, unit_id, role_id, valid_from, valid_to) VALUES (1007, 1, 2, 14, 102, '2020-01-28 00:00:00', NULL);
INSERT INTO user_roles (id, version, user_id, unit_id, role_id, valid_from, valid_to) VALUES (1008, 1, 1, 11, 101, '2020-02-01 07:00:00', NULL);
INSERT INTO user_roles (id, version, user_id, unit_id, role_id, valid_from, valid_to) VALUES (1009, 1, 1, 11, 104, '2020-02-01 07:00:00', NULL);
