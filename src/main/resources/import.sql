INSERT INTO user (id, first_name, last_name, email, password, role) VALUES (1, 'Jan', 'Kowalski', 'jkowalski@test.com', 'password', 'ADMIN');
INSERT INTO user (id, first_name, last_name, email, password, role) VALUES (2, 'Adam', 'Mickiewicz', 'amickiewicz@test.com', 'password', 'STUDENT');
INSERT INTO user (id, first_name, last_name, email, password, role) VALUES (3, 'Piotr', 'Nowak', 'pnowak@test.com', 'password', 'STUDENT');

INSERT INTO activity (id, name, start_date) VALUES (1, 'Pierwsze zajęcia', '2017-10-10 00:00:00');
INSERT INTO activity (id, name, start_date) VALUES (2, 'Drugie zajęcia', '2017-11-10 00:00:00');

INSERT INTO user_activity (id, user_id, activity_id, present) VALUES (1, 1,1, 1);
INSERT INTO user_activity (id, user_id, activity_id, present) VALUES (2, 1,2, 1);
INSERT INTO user_activity (id, user_id, activity_id, present) VALUES (3, 2,1, 1);
INSERT INTO user_activity (id, user_id, activity_id, present) VALUES (4, 2,2, 0);
