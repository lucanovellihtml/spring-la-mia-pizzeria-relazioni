INSERT INTO pizze (id, price, update_at, description, name, photo) VALUES (1, 12.99, CURRENT_TIMESTAMP(), 'Classic Margherita with tomato sauce, mozzarella cheese, and basil.', 'Margherita', 'margherita.png'), (2, 14.99, CURRENT_TIMESTAMP(), 'Pepperoni, mushrooms, onions, and extra cheese.', 'Meat Lover', 'meat_lover.png'), (3, 13.99, CURRENT_TIMESTAMP(), 'Spinach, artichokes, and feta cheese.', 'Vegetarian Delight', 'vegetarian_delight.png'), (4, 15.99, CURRENT_TIMESTAMP(), 'Spicy sausage, jalapeños, and hot sauce.', 'Firestarter', 'firestarter.png');
INSERT INTO discounts (id, title, start_date, finish_date, pizza_id) VALUES (1, 'Discounts Margherita', '2023-06-15', '2023-08-31', 1), (2, 'Holiday Discount', '2023-12-20', '2024-01-05', 1), (3, 'Early Bird Offer', '2024-02-10', '2024-02-29', 1), (4, 'Summer Discount', '2024-02-10', '2024-02-29', 2);