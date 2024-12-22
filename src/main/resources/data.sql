-- Insert data into default_exercise table
INSERT INTO default_exercise (name)
VALUES
('Flat Barbell Bench Press'),
('Incline Barbell Bench Press'),
('Flat Dumbbell Bench Press'),
('Incline Dumbbell Bench Press');

-- Insert data into role table
INSERT INTO role (name)
VALUES
('ADMIN'),
('USER');

-- Insert data into app_user table
INSERT INTO app_user (name, password, role_id)
VALUES
('admin', '$2a$12$v/mRrRfQ18gpz5ekVluQ8eEEUiN.7gNJ4MtxQCbmWWCavtagHl6cy', 1),
('user', '$2a$12$.gvecgyfihq.ozfUhC/pGe48uNmgzsYpluQabfwWWvlO4G5xsXQXy', 2);

-- Insert data into exercise table
INSERT INTO exercise (name, app_user_id)
SELECT name, 2
FROM default_exercise;

-- Insert data into history table
INSERT INTO history (app_user_id, exercise_id, weight, reps, created_on)
VALUES
(2, 1, 100, 10, CURRENT_DATE()),
(2, 1, 100, 10, CURRENT_DATE()),
(2, 1, 100, 10, CURRENT_DATE()),
(2, 2, 60, 10, CURRENT_DATE()),
(2, 2, 60, 10, CURRENT_DATE()),
(2, 2, 60, 10, CURRENT_DATE());