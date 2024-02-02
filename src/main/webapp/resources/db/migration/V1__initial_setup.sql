-- Create the 'tasks' table
CREATE TABLE IF NOT EXISTS tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL,
    due_date TIMESTAMP,
    creation_date TIMESTAMP NOT NULL
    );

-- You can add more SQL statements here for other tables or constraints if needed
