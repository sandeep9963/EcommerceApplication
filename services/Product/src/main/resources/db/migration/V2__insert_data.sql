-- V2__insert_data.sql

-- Insert sample data into the category table
INSERT INTO category (description, name)
VALUES
    ('Electronics', 'Electronics'),
    ('Clothing', 'Clothing'),
    ('Books', 'Books'),
    ('Furniture', 'Furniture');

-- Insert sample data into the product table
INSERT INTO product (description, name, available_quantity, price, category_id)
VALUES
    ('Smartphone with 64GB storage', 'Smartphone', 50, 699.99, (SELECT id FROM category WHERE name='Electronics')),
    ('4K Ultra HD Smart TV', 'Smart TV', 30, 1299.99, (SELECT id FROM category WHERE name='Electronics')),
    ('Cotton T-shirt', 'T-shirt', 200, 19.99, (SELECT id FROM category WHERE name='Clothing')),
    ('Denim Jeans', 'Jeans', 150, 49.99, (SELECT id FROM category WHERE name='Clothing')),
    ('Best-Selling Novel', 'Novel', 100, 14.99, (SELECT id FROM category WHERE name='Books')),
    ('Office Chair', 'Chair', 40, 89.99, (SELECT id FROM category WHERE name='Furniture')),
    ('Dining Table', 'Table', 20, 299.99, (SELECT id FROM category WHERE name='Furniture'));