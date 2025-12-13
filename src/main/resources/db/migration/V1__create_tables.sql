CREATE TABLE categories (
    id_category VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);
INSERT INTO categories (id_category,name) VALUES
('c1','Uñas'),
('c2','Maquillaje'),
('c3','Peluqueria');
CREATE TABLE services (
    id_service VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    picture_url VARCHAR(100),
    id_category VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_category) REFERENCES categories(id_category)
);
CREATE TABLE users (
    id_user VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    userName VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(20),
    address VARCHAR(200),
    birthDate DATE,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE cart (
    id_cart INT AUTO_INCREMENT PRIMARY KEY,
    id_user VARCHAR(50) UNIQUE NOT NULL,
    total_products INT DEFAULT 0,
    total_price DECIMAL(10,2) DEFAULT 0,
    FOREIGN KEY (id_user) REFERENCES users(id_user)
);

CREATE TABLE cart_items (
    id_item_cart VARCHAR(50) PRIMARY KEY,
    id_cart INT NOT NULL,
    id_service VARCHAR(10) NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    FOREIGN KEY (id_cart) REFERENCES cart(id_cart) ON DELETE CASCADE,
    FOREIGN KEY (id_service) REFERENCES services(id_service)
);
