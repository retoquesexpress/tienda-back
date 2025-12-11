USE retoquesexpresdb;

CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL
);
CREATE TABLE servicios (
    id VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    pictureUrl VARCHAR(100),
    id_categoria INT NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);
CREATE TABLE usuarios (
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
    FOREIGN KEY (id_user) REFERENCES usuarios(id_user)
);

CREATE TABLE cart_items (
    id_item_cart VARCHAR(50) PRIMARY KEY,
    id_cart INT NOT NULL,
    id_servicio VARCHAR(10) NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    FOREIGN KEY (id_cart) REFERENCES cart(id_cart) ON DELETE CASCADE,
    FOREIGN KEY (id_servicio) REFERENCES servicios(id)
);
