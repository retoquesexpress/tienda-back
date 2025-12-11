INSERT INTO categorias (nombre) VALUES
('Uñas'),
('Maquillaje'),
('Peluqueria');

INSERT INTO servicios (id, nombre, descripcion, precio, pictureUrl, id_categoria) VALUES
('u1', 'Esmalte normal', 'Aplicación de esmalte tradicional en uñas', 5, 'u1', 1),
('u2', 'Semipermanente', 'Aplicación de esmalte semipermanente para mayor duración', 15, 'u2', 1),
('u3', 'Gel', 'Aplicación de uñas de gel', 20, 'u3', 1),
('u4', 'Con retirada', 'Retirada de esmalte anterior y aplicación de nuevo', 10, 'u4', 1),
('u5', 'Añadiendo dibujo', 'Decoración de uñas con diseño personalizado', 8, 'u5', 1),
('u6', 'Joyas', 'Decoración de uñas con piedras y accesorios', 12, 'u6', 1),

('m1', 'Sencillo', 'Maquillaje diario y natural', 20, 'm1', 2),
('m2', 'De eventos', 'Maquillaje para ocasiones especiales', 35, 'm2', 2),
('m3', 'Artístico', 'Maquillaje creativo o temático', 40, 'm3', 2),

('p1', 'Despunte', 'Corte de puntas para mantener el cabello saludable', 10, 'p1', 3),
('p2', 'Corte + Secado', 'Corte de cabello seguido de secado y peinado', 25, 'p2', 3),
('p3', 'Corte en seco de puntas', 'Corte en seco para mantener la forma y salud del cabello', 15, 'p3', 3),
('p4', 'Estilizado', 'Peinado y estilizado del cabello', 20, 'p4', 3),
('p5', 'Eventos', 'Peinado especial para eventos y ocasiones importantes', 35, 'p5', 3);
