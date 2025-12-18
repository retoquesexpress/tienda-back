INSERT INTO services (id_service, name, description, price, picture_url, id_category) VALUES
(1, 'Esmalte normal', 'Aplicación de esmalte tradicional en uñas', 5, 'u1', 1),
(2, 'Semipermanente', 'Aplicación de esmalte semipermanente para mayor duración', 15, 'u2', 1),
(3, 'Gel', 'Aplicación de uñas de gel', 20, 'u3', 1),
(4, 'Con retirada', 'Retirada de esmalte anterior y aplicación de nuevo', 10, 'u4', 1),
(5, 'Añadiendo dibujo', 'Decoración de uñas con diseño personalizado', 8, 'u5', 1),
(6, 'Joyas', 'Decoración de uñas con piedras y accesorios', 12, 'u6', 1),

(7, 'Sencillo', 'Maquillaje diario y natural', 20, 'm1', 2),
(8, 'De eventos', 'Maquillaje para ocasiones especiales', 35, 'm2', 2),
(9, 'Artístico', 'Maquillaje creativo o temático', 40, 'm3', 2),

(10, 'Despunte', 'Corte de puntas para mantener el cabello saludable', 10, 'p1', 3),
(11, 'Corte + Secado', 'Corte de cabello seguido de secado y peinado', 25, 'p2', 3),
(12, 'Corte en seco de puntas', 'Corte en seco para mantener la forma y salud del cabello', 15, 'p3',3),
(13, 'Estilizado', 'Peinado y estilizado del cabello', 20, 'p4', 3),
(14, 'Eventos', 'Peinado especial para eventos y ocasiones importantes', 35, 'p5', 3);

INSERT INTO users (id_user, name, email, user_name, password, phone_number, address, birth_date, role) VALUES
('us1', 'Alice Smith', 'alice@corp.com', 'asmith', 'pass1', '555-1234', '45 Oak Ave', '1985-03-20', 'admin'),
('us2', 'Bob Johnson', 'bob@corp.com', 'bjohnson', 'pass2', '555-5678', '99 Pine Ln', '1992-11-05', 'user'),
('us3', 'Charlie Brown', 'charlie@corp.com', 'cbrown', 'pass3', '555-9012', '33 Elm Ct', '1976-08-14', 'user');