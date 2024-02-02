-- Create task_status type
CREATE TYPE task_status AS ENUM ('TODO', 'IN_PROGRESS', 'DONE');

-- Create the 'tasks' table
CREATE TABLE IF NOT EXISTS tasks
(
    id            SERIAL PRIMARY KEY,
    title         VARCHAR(200) NOT NULL,
    description   TEXT,
    status        task_status NOT NULL,
    creation_date TIMESTAMP NOT NULL
);

-- Insert mock data
INSERT INTO tasks (title, description, status, creation_date) VALUES
    ('Bug fixing in backend code', 'Identifying and fixing bugs in the backend codebase. Debugging and testing to ensure proper functionality.', 'TODO'::task_status, '2024-02-01 13:07:15');

INSERT INTO tasks (title, description, status, creation_date) VALUES
    ('Optimizing database queries', 'Analyzing and optimizing database queries for improved performance. Index optimization and query tuning.', 'IN_PROGRESS'::task_status, '2024-02-01 13:08:02');

INSERT INTO tasks (title, description, status, creation_date) VALUES
    ('Code review for new feature', 'Conducting a thorough code review for a new feature implementation. Checking for coding standards and best practices.', 'DONE'::task_status, '2024-02-01 13:09:20');

INSERT INTO tasks (title, description, status, creation_date) VALUES
    ('Writing unit tests', 'Creating unit tests to ensure the reliability and correctness of code. Writing test cases and test data.', 'TODO'::task_status, '2024-02-01 13:10:45');

INSERT INTO tasks (title, description, status, creation_date) VALUES
    ('Debugging production issue', 'Investigating and debugging a critical production issue. Analyzing logs and tracing the root cause.', 'IN_PROGRESS'::task_status, '2024-02-01 13:12:01');

INSERT INTO tasks (title, description, status, creation_date) VALUES
    ('Refactoring legacy code', 'Refactoring and modernizing legacy code. Improving code structure and removing deprecated components.', 'DONE'::task_status, '2024-02-01 13:13:30');

INSERT INTO tasks (title, description, status, creation_date) VALUES
    ('Meeting with team for sprint planning', 'Participating in a team meeting for sprint planning. Discussing user stories and task prioritization.', 'TODO'::task_status, '2024-02-01 13:14:42');

INSERT INTO tasks (title, description, status, creation_date) VALUES
    ('Creating API documentation', 'Documenting the API endpoints and usage. Generating API documentation for developers and clients.', 'IN_PROGRESS'::task_status, '2024-02-01 13:16:05');

INSERT INTO tasks (title, description, status, creation_date) VALUES
    ('Deploying application to production', 'Preparing and deploying the application to the production environment. Ensuring a smooth release process.', 'TODO'::task_status, '2024-02-01 13:17:20');

INSERT INTO tasks (title, description, status, creation_date) VALUES
    ('Testing new feature', 'Performing extensive testing on the recently implemented feature. Writing test cases and conducting regression testing.', 'TODO'::task_status, '2024-02-01 13:18:45');
