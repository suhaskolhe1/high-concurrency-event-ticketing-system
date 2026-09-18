CREATE TABLE events (
                        id VARCHAR(36) PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        description TEXT,
                        date TIMESTAMP WITH TIME ZONE NOT NULL,
                        location VARCHAR(255) NOT NULL,
                        total_seats INTEGER NOT NULL,
                        available_seats INTEGER NOT NULL,
                        price DECIMAL(10,2) NOT NULL,
                        created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
