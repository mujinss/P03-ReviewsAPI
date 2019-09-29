ALTER TABLE product
Change id product_id int;

ALTER TABLE review
change id review_id int,
ADD CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(product_id);

ALTER TABLE `comment`
change id comment_id int,
modify review_id int NOT NULL,
modify comment_txt varchar(500) NOT NULL,
ADD CONSTRAINT fk_review FOREIGN KEY (review_id) REFERENCES review(review_id);