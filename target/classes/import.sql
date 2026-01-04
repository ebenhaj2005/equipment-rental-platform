-- ===============================
-- Schema instellen
-- ===============================
SET SCHEMA PUBLIC;

-- ===============================
-- Tabellen verwijderen (veilig opnieuw uitvoeren)
-- ===============================
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

-- ===============================
-- Tabel: categories
-- ===============================
CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            description VARCHAR(255)
);

-- ===============================
-- Tabel: products
-- ===============================
CREATE TABLE products (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(150) NOT NULL,
                          description VARCHAR(255),
                          rental_price DECIMAL(10,2) NOT NULL,
                          quantity INT NOT NULL,
                          category_id INT NOT NULL,
                          available BOOLEAN NOT NULL,
                          CONSTRAINT fk_category
                              FOREIGN KEY (category_id)
                                  REFERENCES categories(id)
);

-- ===============================
-- Data: categories
-- ===============================
INSERT INTO categories (name, description) VALUES
                                               ('Kabels', 'Alle soorten kabels voor audiovisuele apparatuur'),
                                               ('Verlichting', 'Lampen, spots en LED panelen'),
                                               ('Controlepanelen', 'DMX en andere controlapparatuur');


INSERT INTO products (name, description, rental_price, quantity, category_id, available) VALUES
                                                                                             ('XLR Kabel 10m', 'Professionele XLR kabel met vergulde connectoren', 5.00, 20, 1, 1),
                                                                                             ('USB-C Kabel', 'Snelle USB-C oplaadkabel, 2 meter', 3.50, 15, 1, 1),
                                                                                             ('Stroomkabel 3m', 'Rood-witte stroomkabel', 2.00, 30, 1, 1),
                                                                                             ('PAR LED 36W', 'Full color LED par can, RGB', 25.00, 5, 2, 1),
                                                                                             ('Spot 500W', 'Halogenspot met 500W lamp', 40.00, 3, 2, 1),
                                                                                             ('LED Panel 1x1m', 'Dimbaar RGB LED paneel', 60.00, 2, 2, 1),
                                                                                             ('DMX Controller 512', '512-kanaals DMX besturingseenheid', 75.00, 1, 3, 1),
                                                                                             ('DMX Kabel 5pin', '5-pins DMX kabel 10 meter', 8.00, 10, 3, 1),
                                                                                             ('Voetschakelaar', 'Twee kanaals voetschakelaar voor DMX', 15.00, 4, 3, 1),
                                                                                             ('Kaapladder 5m', 'Zwarte kaapladder 5 meter', 12.00, 8, 1, 1);


SELECT * FROM categories;
SELECT * FROM products;
