INSERT INTO my_user (username, password, first_name, last_name, is_admin)
VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MQRqQDP9ZFHWUJ5Ysp6vI4sz5Yd7W2y', 'Admin', 'User',
        true),  -- password: admin123
       ('johndoe', '$2a$10$r3xPKMnGkh/4qGZfrXuZW.FiT7XxlQYV3R3Zh1H6Z2QbfZ7gQbJ0O', 'John', 'Doe',
        false), -- password: john123
       ('janedoe', '$2a$10$W2fKZ5K5l5f5Z5X5Z5X5O.5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z', 'Jane', 'Doe',
        false), -- password: jane123
       ('mike.smith', '$2a$10$5Z5X5Z5X5Z5X5Z5X5Z5X5O.5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z', 'Mike', 'Smith',
        false), -- password: mike123
       ('sarah.johnson', '$2a$10$X5Z5X5Z5X5Z5X5Z5X5Z5XO.5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z', 'Sarah', 'Johnson',
        false), -- password: sarah123
       ('david.wilson', '$2a$10$Z5X5Z5X5Z5X5Z5X5Z5X5ZO.5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z', 'David', 'Wilson',
        false), -- password: david123
       ('lisa.brown', '$2a$10$X5Z5X5Z5X5Z5X5Z5X5Z5XO.5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z', 'Lisa', 'Brown',
        false), -- password: lisa123
       ('robert.taylor', '$2a$10$Z5X5Z5X5Z5X5Z5X5Z5X5ZO.5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z', 'Robert', 'Taylor',
        false), -- password: robert123
       ('emily.white', '$2a$10$X5Z5X5Z5X5Z5X5Z5X5Z5XO.5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z', 'Emily', 'White',
        false), -- password: emily123
       ('thomas.miller', '$2a$10$Z5X5Z5X5Z5X5Z5X5Z5X5ZO.5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z5X5Z', 'Thomas', 'Miller',
        false); -- password: thomas123