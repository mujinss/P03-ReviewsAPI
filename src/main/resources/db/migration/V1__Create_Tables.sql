CREATE TABLE IF NOT EXISTS `product` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product_name` varchar(100),
    `product_desc` varchar(500)
);

CREATE TABLE IF NOT EXISTS `review` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `review_username` varchar(20) NOT NULL,
    `review_title` varchar(20) NOT NULL,
    `review_txt` varchar(500) NOT NULL,
    `created` timestamp default CURRENT_TIMESTAMP,
    `product_id` int NOT NULL
);

CREATE TABLE IF NOT EXISTS `comment` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `review_id` int,
    `comment_txt` varchar(500),
    `created` timestamp default CURRENT_TIMESTAMP
);