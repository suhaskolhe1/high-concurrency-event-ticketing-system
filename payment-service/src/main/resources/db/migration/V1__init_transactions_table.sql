CREATE TABLE transactions (
                              id VARCHAR(36) PRIMARY KEY,
                              booking_id VARCHAR(36) UNIQUE NOT NULL, -- This UNIQUE constraint prevents double-charging!
                              amount DECIMAL(10,2) NOT NULL,
                              status VARCHAR(20) NOT NULL,
                              created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);