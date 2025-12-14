INSERT INTO services (id_service, name, description, price, picture_url, id_category) VALUES
('u1', 'Esmalte normal', 'Aplicación de esmalte tradicional en uñas', 5, 'u1', 'c1'),
('u2', 'Semipermanente', 'Aplicación de esmalte semipermanente para mayor duración', 15, 'u2', 'c1'),
('u3', 'Gel', 'Aplicación de uñas de gel', 20, 'u3', 'c1'),
('u4', 'Con retirada', 'Retirada de esmalte anterior y aplicación de nuevo', 10, 'u4', 'c1'),
('u5', 'Añadiendo dibujo', 'Decoración de uñas con diseño personalizado', 8, 'u5', 'c1'),
('u6', 'Joyas', 'Decoración de uñas con piedras y accesorios', 12, 'u6', 'c1'),

('m1', 'Sencillo', 'Maquillaje diario y natural', 20, 'm1', 'c2'),
('m2', 'De eventos', 'Maquillaje para ocasiones especiales', 35, 'm2', 'c2'),
('m3', 'Artístico', 'Maquillaje creativo o temático', 40, 'm3', 'c2'),

('p1', 'Despunte', 'Corte de puntas para mantener el cabello saludable', 10, 'p1', 'c3'),
('p2', 'Corte + Secado', 'Corte de cabello seguido de secado y peinado', 25, 'p2', 'c3'),
('p3', 'Corte en seco de puntas', 'Corte en seco para mantener la forma y salud del cabello', 15, 'p3', 'c3'),
('p4', 'Estilizado', 'Peinado y estilizado del cabello', 20, 'p4', 'c3'),
('p5', 'Eventos', 'Peinado especial para eventos y ocasiones importantes', 35, 'p5', 'c3');

INSERT INTO users (id_user, name, email, user_name, password, phone_number, address, birth_date, role) VALUES
('us1', 'Alice Smith', 'alice@corp.com', 'asmith', 'pass1', '555-1234', '45 Oak Ave', '1985-03-20', 'admin'),
('us2', 'Bob Johnson', 'bob@corp.com', 'bjohnson', 'pass2', '555-5678', '99 Pine Ln', '1992-11-05', 'user'),
('us3', 'Charlie Brown', 'charlie@corp.com', 'cbrown', 'pass3', '555-9012', '33 Elm Ct', '1976-08-14', 'user');