INSERT INTO categories (name, description) VALUES
                                               ('Kabels', 'Alle soorten kabels voor audiovisuele apparatuur'),
                                               ('Verlichting', 'Lampen, spots en LED panelen'),
                                               ('Controlepanelen', 'DMX en andere controlapparatuur');

INSERT INTO products (name, description, rental_price, quantity, category_id, available) VALUES
                                                                                             ('XLR Kabel 10m', 'Professionele XLR kabel met vergulde connectoren', 5.00, 20, 1, true),
                                                                                             ('USB-C Kabel', 'Snelle USB-C oplaadkabel, 2 meter', 3.50, 15, 1, true),
                                                                                             ('Stroomkabel 3m', 'Rood-witte stroomkabel', 2.00, 30, 1, true),
                                                                                             ('PAR LED 36W', 'Full color LED par can, RGB', 25.00, 5, 2, true),
                                                                                             ('Spot 500W', 'Halogenspot met 500W lamp', 40.00, 3, 2, true),
                                                                                             ('LED Panel 1x1m', 'Dimmable RGB LED paneel', 60.00, 2, 2, true),
                                                                                             ('DMX Controller 512', '512-kanaals DMX besturingseenheid', 75.00, 1, 3, true),
                                                                                             ('DMX Kabel 5pin', '5-pins DMX kabel 10 meter', 8.00, 10, 3, true),
                                                                                             ('Voetschakelaar', 'Twee kanaals voetschakelaar voor DMX', 15.00, 4, 3, true),
                                                                                             ('Kaapladder 5m', 'Zwarte kaapladder 5 meter', 12.00, 8, 1, true);