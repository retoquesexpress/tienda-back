CREATE TABLE categories (
    id_category INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL
);
INSERT INTO categories (id_category,name) VALUES
(1,'Uñas'),
(2,'Maquillaje'),
(3,'Peluqueria');

CREATE TABLE services (
    id_service INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    picture_url VARCHAR(100),
    id_category INT NOT NULL,
    FOREIGN KEY (id_category) REFERENCES categories(id_category)
);
CREATE TABLE users (
    id_user INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    user_name VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    address VARCHAR(200),
    birth_date DATE,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE booking (
    id_booking INT PRIMARY KEY AUTO_INCREMENT,
    id_user INT NOT NULL,
    total_price DECIMAL(10,2) DEFAULT 0,
    FOREIGN KEY (id_user) REFERENCES users(id_user)
);

CREATE TABLE booking_items (
    id_booking_item INT PRIMARY KEY AUTO_INCREMENT,
    id_booking INT NOT NULL,
    id_service INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    booking_date DATE NOT NULL,
    FOREIGN KEY (id_booking) REFERENCES booking(id_booking) ON DELETE CASCADE,
    FOREIGN KEY (id_service) REFERENCES services(id_service)
);
