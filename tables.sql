CREATE TABLE Users (
    user_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE Items (
    item_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    buy_price DECIMAL(10, 2),
    quantity INT,
    sale_price DECIMAL(10, 2),
    date_created TIMESTAMP,
    date_updated TIMESTAMP
);

CREATE TABLE Purchases (
    purchase_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    description TEXT,
    item_id INT NOT NULL,
    quantity INT,
    purchase_price DECIMAL(10, 2),
    date_purchased TIMESTAMP,
    FOREIGN KEY (item_id) REFERENCES Items(item_id) ON UPDATE CASCADE
);

CREATE TABLE Sales (
    sales_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    item_id INT NOT NULL,
    date_sold TIMESTAMP,
    quantity INT,
    FOREIGN KEY (item_id) REFERENCES Items(item_id) ON UPDATE CASCADE
);