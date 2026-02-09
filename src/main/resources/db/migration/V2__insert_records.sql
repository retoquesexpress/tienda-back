INSERT INTO services (id_service, name, description, price, picture_url, id_category) VALUES
-- Uñas (Categoría 1)
(1, 'Esmalte Normal', 'Aplicación profesional de esmalte tradicional con acabado de alto brillo, ideal para quienes buscan un cambio de color versátil con un secado natural impecable.', 15, 'ManicuraFrancesaBloque1.jpg', 1),
(2, 'Semipermanente', 'Tratamiento de color de larga duración mediante tecnología LED, que garantiza un brillo espejo y una resistencia perfecta frente a golpes o ralladuras durante semanas.', 22, 'SemipermanenteBloque2.png', 1),
(3, 'Ornamentación y Joyería Nail', 'Exclusiva decoración mediante la aplicación de cristales, piedras semipreciosas y accesorios de lujo que aportan un relieve sofisticado y un brillo excepcional.', 40, 'UnasOrnamentadasBloque3.jpg', 1),
(4, 'Gel', 'Construcción y diseño de uñas de gel de alta resistencia, proporcionando una estructura estética, equilibrada y natural que embellece la morfología de las manos.', 35, 'gel.jpg', 1),
(5, 'Añadir retirada', 'Procedimiento delicado de eliminación de producto anterior mediante técnicas no invasivas, seguido de una nueva aplicación de color para mantener la salud de la uña.', 6, 'retirada.jpg', 1),
(6, 'Añadir dibujo', 'Transformación artística de la uña mediante diseños personalizados a mano alzada, adaptando patrones y tendencias exclusivas a tu estilo personal.', 6, 'dibujo.jpg', 1),
(7, 'Añadir joya', 'Exclusiva decoración mediante la aplicación de cristales, piedras semipreciosas y accesorios de lujo que aportan un relieve sofisticado y un brillo excepcional.', 1, 'joya.jpg', 1),

-- Maquillaje (Categoría 2)
(11, 'Maquillaje Sencillo', 'Aplicación técnica orientada a realzar las facciones naturales y corregir imperfecciones, logrando un aspecto fresco, radiante y saludable para el día a día.', 25, 'MaquillajeSencilloPanel1.png', 2),
(12, 'Maquillaje de Eventos', 'Protocolo de maquillaje de alta cobertura y larga duración con técnicas de contorneado y definición, diseñado para lucir impecable bajo luces artificiales y fotografía.', 45, 'MaquillajeEventosPanel2.png', 2),
(13, 'Maquillaje Artístico', 'Creación conceptual de vanguardia que utiliza el rostro como lienzo, combinando texturas, colores vibrantes y técnicas escénicas para resultados temáticos únicos.', 60, 'MaquillajeArtisticoPanel3.png', 2),
(14, 'Maquillaje + Skincare', 'Creación conceptual de vanguardia que utiliza el rostro como lienzo, combinando texturas, colores vibrantes y técnicas escénicas para resultados temáticos únicos.', 50, 'skincare.jpg', 2),
(15, 'Maquillaje de Novia', 'Creación conceptual de vanguardia que utiliza el rostro como lienzo, combinando texturas, colores vibrantes y técnicas escénicas para resultados temáticos únicos.', 120, 'novia.jpg', 2),
(16, 'Descubre tu Colorimetría', 'Creación conceptual de vanguardia que utiliza el rostro como lienzo, combinando texturas, colores vibrantes y técnicas escénicas para resultados temáticos únicos.', 50, 'colorimetria.jpg', 2),

-- Peluquería (Categoría 3)
(21, 'Corte', 'Técnica especializada de corte sobre cabello seco que permite visualizar la caída natural y el volumen real del cabello, logrando una simetría y textura inigualables.', 18, 'CortePanel.png', 3),
(22, 'Peinado para evento', 'Creación de peinados sofisticados y estructuras capilares elaboradas, ideales para celebraciones y eventos donde la elegancia y la durabilidad son la prioridad.', 40, 'PeinadoPanel.png', 3),
(23, 'Corte de Pelo + Barba', 'Creación de peinados sofisticados y estructuras capilares elaboradas, ideales para celebraciones y eventos donde la elegancia y la durabilidad son la prioridad.', 30, 'BarbaPanel.png', 3),
(24, 'Despunte', 'Servicio de limpieza técnica de puntas diseñado para eliminar la porosidad y prevenir la rotura, manteniendo la vitalidad y el crecimiento óptimo de la fibra capilar.', 15, 'despunte.jpg', 3),
(25, 'Corte + Secado', 'Experiencia de transformación completa que incluye un diagnóstico de visagismo, corte adaptado a tus rasgos y una sesión de secado profesional para un acabado perfecto.', 35, 'secado.jpg', 3),
(26, 'Tinte', 'Servicio de moldeado mediante técnicas de calor para aportar cuerpo, brillo y movimiento al cabello, adaptando el peinado a tus preferencias de volumen y forma.', 45, 'tinte.jpg', 3);

INSERT INTO users (id_user, name, email, user_name, password, phone_number, address, birth_date, role) VALUES
(1, 'Alice Smith', 'alice@corp.com', 'asmith', 'pass1', '555-1234', '45 Oak Ave', '1985-03-20', 'admin'),
(2, 'Bob Johnson', 'bob@corp.com', 'bjohnson', 'pass2', '555-5678', '99 Pine Ln', '1992-11-05', 'user'),
(3, 'Charlie Brown', 'charlie@corp.com', 'cbrown', 'pass3', '555-9012', '33 Elm Ct', '1976-08-14', 'user');
