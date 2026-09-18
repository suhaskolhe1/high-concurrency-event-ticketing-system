CREATE TABLE bookings (
                          id VARCHAR(36) PRIMARY KEY,
                          user_id VARCHAR(36) NOT NULL,
                          event_id VARCHAR(36) NOT NULL,
                          seat_id VARCHAR(36) NOT NULL,
                          price DECIMAL(10,2) NOT NULL,
                          status VARCHAR(20) NOT NULL, -- PENDING, CONFIRMED, FAILED
                          created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
