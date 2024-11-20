-- Crear la base de datos
CREATE DATABASE nexio;

-- Conectarse a la base de datos
\c nexio;

-- Crear tabla de negocios
CREATE TABLE businesses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    latitude DECIMAL(9, 6) NOT NULL,
    longitude DECIMAL(9, 6) NOT NULL
);

-- Crear tabla de usuarios
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla de reseñas
CREATE TABLE review (
    id SERIAL PRIMARY KEY,
    business_id INT NOT NULL,
    user_id INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Crear tabla de multimedia (fotos y videos)
CREATE TABLE media (
    id SERIAL PRIMARY KEY,
    business_id INT NOT NULL,
    media_type VARCHAR(10) CHECK (media_type IN ('image', 'video')),
    url TEXT NOT NULL,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE
);

-- Índices para mejorar la búsqueda
CREATE INDEX idx_business_location ON businesses (latitude, longitude);


-- Insumos

INSERT INTO businesses (name, category, address, latitude, longitude) VALUES
('Pizzeria Bella Italia', 'Food', '123 Main Street', 40.730610, -73.935242),
('Plumbing Experts', 'Home > Plumbing', '456 Elm Street', 40.712776, -74.005974),
('Metalworks Co.', 'Home > Metalwork', '789 Maple Avenue', 40.678178, -73.944158),
('Green Grocers', 'Food', '101 Oak Lane', 40.741895, -73.989308),
('Tech Fix', 'Technology', '555 Technology Road', 40.730610, -73.945425);

INSERT INTO users (username, email, password) VALUES
('john_doe', 'john.doe@example.com', 'password123'),
('jane_smith', 'jane.smith@example.com', 'mypassword'),
('charlie_brown', 'charlie.brown@example.com', 'secure123'),
('alice_wonder', 'alice.wonder@example.com', 'wonderland'),
('bob_builder', 'bob.builder@example.com', 'builderpass');

INSERT INTO review (business_id, user_id, rating, comment) VALUES
(1, 1, 5, 'Amazing pizza! Highly recommend.'),
(1, 2, 4, 'Great taste, but a bit pricey.'),
(2, 3, 5, 'Quick and professional service.'),
(3, 4, 3, 'Good work, but took longer than expected.'),
(4, 5, 4, 'Fresh vegetables and friendly staff.'),
(5, 1, 5, 'Fixed my laptop in no time!'),
(5, 2, 2, 'Service was slow and unresponsive.');

INSERT INTO media (business_id, media_type, url) VALUES
(1, 'image', 'https://example.com/images/pizzeria.jpg'),
(2, 'image', 'https://example.com/images/plumbing.jpg'),
(3, 'video', 'https://example.com/videos/metalworks.mp4'),
(4, 'image', 'https://example.com/images/greengrocers.jpg'),
(5, 'video', 'https://example.com/videos/techfix.mp4');
