create database shopping_cart;
use shopping_cart;

CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    description VARCHAR(255)
);

INSERT INTO product (name, price, description) VALUES ('Phở Bò', 50000.00, 'Món phở truyền thống Việt Nam với thịt bò thái mỏng và nước dùng đậm đà.');
INSERT INTO product (name, price, description) VALUES ('Bún Chả', 45000.00, 'Bún chả Hà Nội với chả nướng thơm lừng và nước chấm chua ngọt.');
INSERT INTO product (name, price, description) VALUES ('Cà Phê Sữa Đá', 25000.00, 'Cà phê rang xay đậm đặc pha với sữa đặc và đá.');
INSERT INTO product (name, price, description) VALUES ('Bánh Mì Pate', 20000.00, 'Bánh mì giòn rụm với nhân pate, chả, rau thơm và dưa chuột.');
INSERT INTO product (name, price, description) VALUES ('Nước Cam Ép', 30000.00, 'Nước cam tươi 100% nguyên chất, giàu vitamin C.');
INSERT INTO product (name, price, description) VALUES ('Gỏi Cuốn', 35000.00, 'Gỏi cuốn thanh mát với tôm, thịt luộc, bún tươi và rau sống.');
INSERT INTO product (name, price, description) VALUES ('Trà Sữa Trân Châu Đường Đen', 40000.00, 'Trà sữa thơm ngon với trân châu đường đen dai giòn.');
INSERT INTO product (name, price, description) VALUES ('Mì Quảng', 55000.00, 'Mì Quảng đặc trưng của miền Trung với tôm, thịt, trứng và nước lèo sệt.');
INSERT INTO product (name, price, description) VALUES ('Sinh Tố Bơ', 40000.00, 'Sinh tố bơ sánh mịn, bổ dưỡng và thơm ngon.');
INSERT INTO product (name, price, description) VALUES ('Nem Lụi', 60000.00, 'Nem lụi nướng than hoa, ăn kèm với rau sống, bún và nước chấm đặc biệt.');

select*from product;