  CREATE TABLE   products 
                (article CHAR(7) PRIMARY KEY, 
                 product_name VARCHAR(50) NOT NULL,
                 product_color VARCHAR(20), 
                 product_price INTEGER CHECK(product_price > 0) NOT NULL, 
                 product_in_stock INTEGER NOT NULL CHECK(product_in_stock >= 0));
 
   INSERT INTO   products 
        VALUES
                ('3251615', 'Стол кухонный', 'белый', 8000, 12),
                ('3251616', 'Стол кухонный', NULL, 8000, 15),
                ('3251617', 'Стул столовый "гусарский"', 'орех', 4000, 0),
                ('3251619', 'Стул столовый с высокой спинкой', 'белый', 3500, 37),
                ('3251620', 'Стул столовый с высокой спинкой', 'коричневый', 3500, 52);

 CREATE TABLE   orders
                (order_id INTEGER PRIMARY KEY,  
                 order_date DATE NOT NULL, 
                 сustomer_name VARCHAR(100) NOT NULL, 
                 сustomer_phone VARCHAR(50), 
                 сustomer_email VARCHAR(50),
                 shipment_address VARCHAR(200) NOT NULL, 
                 order_status CHAR(1) NOT NULL,
                 shipment_date DATE);

   ALTER TABLE   orders 
ADD CONSTRAINT   or_stat   CHECK (order_status IN ('P', 'S', 'C'));
   ALTER TABLE   orders 
ADD CONSTRAINT   date_null CHECK ((order_status = 'S' AND shipment_date IS NOT NULL) 
                             OR (order_status <> 'S' AND shipment_date IS NULL)); 

   INSERT INTO   orders 
        VALUES
                (1,'20.11.2020','Сергей Иванов','(981)123-45-67',NULL,'ул. Веденеева, 20-1-41','S','29.11.2020'),
                (2,'22.11.2020','Алексей Комаров','(921)001-22-33',NULL,' пр. Пархоменко 51-2-123','S','29.11.2020'),
                (3,'28.11.2020','Ирина Викторова','(911)009-88-77',NULL,'Тихорецкий пр. 21-21','P',NULL),
                (4,'03.12.2020','Павел Николаев',NULL,'pasha_nick@mail.ru','ул. Хлопина 3-88','P',NULL),
                (5,'03.12.2020','Антонина Васильева','(931)777-66-55','antvas66@gmail.com',' пр. Науки, 11-3-9','P',NULL),
                (6,'10.12.2020','Ирина Викторова','(911)009-88-77',NULL,'Тихорецкий пр. 21-21','P',NULL);

CREATE TABLE  order_positions 
               (order_cod INTEGER NOT NULL, 
                product_article CHAR(7) NOT NULL, 
                order_price INTEGER CHECK(order_price > 0) NOT NULL, 
                amount INTEGER CHECK(amount > 0) NOT NULL,
   PRIMARY KEY (order_cod, product_article),
   FOREIGN KEY (order_cod) REFERENCES orders (order_id),
   FOREIGN KEY (product_article) REFERENCES products (article));

   INSERT INTO  order_positions 
        VALUES
               (1,'3251616',7500,1),
               (2,'3251615',7500,1),
               (3,'3251615',8000,1),
               (3,'3251617',4000,4),
               (4,'3251619',3500,2),
               (5,'3251615',8000,1),
               (5,'3251617',4000,4),
               (6,'3251617',4000,2);

/*
список заказов, созданных: в ноябре, в декабре;
*/
SELECT * FROM orders WHERE order_date BETWEEN '01.11.2020' AND '30.11.2020'; 
SELECT * FROM orders WHERE order_date BETWEEN '01.12.2020' AND '31.12.2020';

/*
список заказов, отгруженных: в ноябре, в декабре;
*/
SELECT * FROM orders WHERE shipment_date BETWEEN '01.11.2020' AND '30.11.2020'; 
SELECT * FROM orders WHERE shipment_date BETWEEN '01.12.2020' AND '31.12.2020';
/*
список клиентов: для каждого клиента должны быть выведены его ФИО, телефон и 
адрес электронной почты;
*/
      SELECT  
              сustomer_name AS "Ф.И.О.", 
              сustomer_phone AS телефон, 
              сustomer_email AS "адрес электронной почты" 
        FROM  orders; 
/*
список позиций заказа с id=3;
*/
SELECT * FROM order_positions WHERE order_cod=3;
/*
названия товаров, включённых в заказ с id=3;
*/
      SELECT  
              order_cod AS "Номер заказа", 
              product_name AS "Название товара" 
        FROM  products INNER JOIN order_positions ON order_cod=3 
         AND  article=product_article;
/*
список отгруженных заказов, и количество позиций в каждом из них;
*/
      SELECT 
              order_id AS "Номер заказа",
              COUNT (order_cod) AS "Количество позиций", 
              order_date AS "Дата заказа",
              сustomer_name AS "Ф.И.О.",
              сustomer_phone AS "Телефон",
              сustomer_email AS "Электронная почта",
              shipment_address AS "Адрес доставки",
              order_status AS "Статус заказа",
              shipment_date AS "Дата отгрузки заказа"   
        FROM  orders INNER JOIN order_positions
          ON  order_id=order_cod
         AND  order_status='S'
    GROUP BY  order_id, 
              order_date, 
              сustomer_name, 
              сustomer_phone,
              сustomer_email,
              shipment_address,
              order_status,
              shipment_date;  
/*
доработайте запрос из предыдущего пункта, чтобы он дополнительно вычислял общую 
стоимость заказа.
*/   
      SELECT 
              order_id AS "Номер заказа",
              COUNT (order_cod) AS "Количество позиций",
              SUM (order_price * amount) AS "Общая стоимость заказа", 
              order_date AS "Дата заказа",
              сustomer_name AS "Ф.И.О.",
              сustomer_phone AS "Телефон",
              сustomer_email AS "Электронная почта",
              shipment_address AS "Адрес доставки",
              order_status AS "Статус заказа",
              shipment_date AS "Дата отгрузки заказа"   
        FROM  orders INNER JOIN order_positions
          ON  order_id=order_cod 
         AND  order_status='S'
    GROUP BY  order_id, 
              order_date, 
              сustomer_name, 
              сustomer_phone,
              сustomer_email,
              shipment_address,
              order_status,
              shipment_date;
/*
Напишите запрос, фиксирующий отгрузку заказа с id=5. Запрос должен:
• менять статус заказа и фиксировать дату отгрузки;
• уменьшать остаток товара на складе
*/
-- чтобы отгрузить заказ с id=5, нужно чтобы на складе было нужное количество товара...добавляем
      UPDATE  products
         SET  product_in_stock = (product_in_stock + 10)
       WHERE  article = '3251617';
--отгружаем 
      UPDATE orders  SET order_status = 'S', shipment_date = '2020-12-24' WHERE order_id = 5;      
      UPDATE products
         SET product_in_stock = product_in_stock - (SELECT amount FROM order_positions 
       WHERE order_cod = 5 AND product_article = article)
       WHERE article IN (SELECT product_article FROM order_positions WHERE order_cod = 5);