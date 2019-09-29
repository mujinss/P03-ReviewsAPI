ALTER TABLE product
Change id product_id int AUTO_INCREMENT;

ALTER TABLE review
Change id review_id int AUTO_INCREMENT;
ALTER TABLE review
ADD CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(product_id);

ALTER TABLE comment
Change id comment_id int AUTO_INCREMENT;

ALTER TABLE comment
modify review_id int NOT NULL;
ALTER TABLE comment
modify comment_txt varchar(500) NOT NULL;

ALTER TABLE comment
ADD CONSTRAINT fk_review FOREIGN KEY (review_id) REFERENCES review(review_id);
