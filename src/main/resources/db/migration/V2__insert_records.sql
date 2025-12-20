INSERT INTO services (id_service, name, description, price, picture_url, id_category) VALUES
-- Uñas (Categoría 1)
(1, 'Manicura Esmaltado Clásico', 'Aplicación profesional de esmalte tradicional con acabado de alto brillo, ideal para quienes buscan un cambio de color versátil con un secado natural impecable.', 5, 'u1', 1),
(2, 'Esmaltado Semipermanente', 'Tratamiento de color de larga duración mediante tecnología LED, que garantiza un brillo espejo y una resistencia perfecta frente a golpes o ralladuras durante semanas.', 15, 'u2', 1),
(3, 'Esculpido en Gel Premium', 'Construcción y diseño de uñas de gel de alta resistencia, proporcionando una estructura estética, equilibrada y natural que embellece la morfología de las manos.', 20, 'u3', 1),
(4, 'Renovación y Retirada Técnica', 'Procedimiento delicado de eliminación de producto anterior mediante técnicas no invasivas, seguido de una nueva aplicación de color para mantener la salud de la uña.', 10, 'u4', 1),
(5, 'Nail Art y Diseño Creativo', 'Transformación artística de la uña mediante diseños personalizados a mano alzada, adaptando patrones y tendencias exclusivas a tu estilo personal.', 8, 'u5', 1),
(6, 'Ornamentación y Joyería Nail', 'Exclusiva decoración mediante la aplicación de cristales, piedras semipreciosas y accesorios de lujo que aportan un relieve sofisticado y un brillo excepcional.', 12, 'u6', 1),

-- Maquillaje (Categoría 2)
(7, 'Maquillaje Social Essential', 'Aplicación técnica orientada a realzar las facciones naturales y corregir imperfecciones, logrando un aspecto fresco, radiante y saludable para el día a día.', 20, 'm1', 2),
(8, 'Maquillaje de Gala y Eventos', 'Protocolo de maquillaje de alta cobertura y larga duración con técnicas de contorneado y definición, diseñado para lucir impecable bajo luces artificiales y fotografía.', 35, 'm2', 2),
(9, 'Maquillaje Editorial y Artístico', 'Creación conceptual de vanguardia que utiliza el rostro como lienzo, combinando texturas, colores vibrantes y técnicas escénicas para resultados temáticos únicos.', 40, 'm3', 2),

-- Peluquería (Categoría 3)
(10, 'Corte de Mantenimiento y Salud', 'Servicio de limpieza técnica de puntas diseñado para eliminar la porosidad y prevenir la rotura, manteniendo la vitalidad y el crecimiento óptimo de la fibra capilar.', 10, 'p1', 3),
(11, 'Corte Integral y Styling', 'Experiencia de transformación completa que incluye un diagnóstico de visagismo, corte adaptado a tus rasgos y una sesión de secado profesional para un acabado perfecto.', 25, 'p2', 3),
(12, 'Corte en Seco de Precisión', 'Técnica especializada de corte sobre cabello seco que permite visualizar la caída natural y el volumen real del cabello, logrando una simetría y textura inigualables.', 15, 'p3', 3),
(13, 'Brushing y Estilizado Profesional', 'Servicio de moldeado mediante técnicas de calor para aportar cuerpo, brillo y movimiento al cabello, adaptando el peinado a tus preferencias de volumen y forma.', 20, 'p4', 3),
(14, 'Recogidos de Alta Peluquería', 'Creación de peinados sofisticados y estructuras capilares elaboradas, ideales para celebraciones y eventos donde la elegancia y la durabilidad son la prioridad.', 35, 'p5', 3);

INSERT INTO users (id_user, name, email, user_name, password, phone_number, address, birth_date, role) VALUES
('us1', 'Alice Smith', 'alice@corp.com', 'asmith', 'pass1', '555-1234', '45 Oak Ave', '1985-03-20', 'admin'),
('us2', 'Bob Johnson', 'bob@corp.com', 'bjohnson', 'pass2', '555-5678', '99 Pine Ln', '1992-11-05', 'user'),
('us3', 'Charlie Brown', 'charlie@corp.com', 'cbrown', 'pass3', '555-9012', '33 Elm Ct', '1976-08-14', 'user');