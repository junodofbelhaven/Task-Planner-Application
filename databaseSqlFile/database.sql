CREATE SCHEMA task_db;
Use task_db;

CREATE TABLE Tasks (
    taskName VARCHAR(100) PRIMARY KEY,
    description TEXT,
    category VARCHAR(50),
    deadline DATE
);

INSERT INTO Tasks (taskName, description, category, deadline) VALUES
('Complete Report', 'Write the Q4 financial report', 'work', '2025-01-12'),
('Buy Groceries', 'Purchase vegetables and fruits', 'home', '2025-01-13'),
('Plan Vacation', 'Research holiday destinations', 'holiday', '2025-01-15'),
('Team Meeting', 'Attend the weekly team meeting', 'work', '2025-01-16'),
('Prepare Presentation', 'Create slides for the product launch', 'work', '2025-01-17'),
('Submit Budget Plan', 'Finalize and submit the 2025 budget plan', 'work', '2025-01-17'),
('Clean the Garage', 'Organize and clean the garage space', 'home', '2025-01-14'),
('Fix the Sink', 'Call a plumber to fix the kitchen sink', 'home', '2025-01-17'),
('Laundry Day', 'Wash and fold all clothes', 'home', '2025-01-19'),
('Book Flights', 'Reserve flights for summer vacation', 'holiday', '2025-01-21'),
('Pack Luggage', 'Prepare and pack for the trip', 'holiday', '2025-01-22'),
('Buy Souvenirs', 'Purchase gifts for family and friends', 'holiday', '2025-01-23');
