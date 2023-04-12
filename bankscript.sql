CREATE TABLE transaction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    source_account_id INT,
    dest_account_id INT,
    amount DECIMAL(10,2),
    date_time DATETIME,
    FOREIGN KEY (source_account_id) REFERENCES account(id),
    FOREIGN KEY (dest_account_id) REFERENCES account(id)
);
