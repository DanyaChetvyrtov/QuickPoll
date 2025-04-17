INSERT INTO poll (question)
VALUES ('Какой ваш любимый язык программирования?'),
       ('Какой фреймворк вы предпочитаете для backend-разработки?'),
       ('Как часто вы пьёте кофе?');

INSERT INTO option (option_value, poll_id)
VALUES ('Java', 1),
       ('Python', 1),
       ('JavaScript', 1),
       ('C#', 1),
       ('Go', 1);

INSERT INTO option (option_value, poll_id)
VALUES ('Spring Boot', 2),
       ('Django', 2),
       ('Express.js', 2),
       ('ASP.NET Core', 2),
       ('Flask', 2);

INSERT INTO option (option_value, poll_id)
VALUES ('Каждый день', 3),
       ('Несколько раз в неделю', 3),
       ('Редко', 3),
       ('Не пью вообще', 3);

INSERT INTO vote (option_id)
VALUES (1),
       (1),
       (1),
       (2),
       (2),
       (3),
       (3),
       (3),
       (3),
       (3),
       (4),
       (5);

INSERT INTO vote (option_id)
VALUES (6),
       (6),
       (6),
       (7),
       (8),
       (8),
       (9),
       (10),
       (10),
       (10);

INSERT INTO vote (option_id)
VALUES (11),
       (11),
       (11),
       (11),
       (11),
       (12),
       (12),
       (13),
       (14);