show tables
SELECT * FROM customer
WHERE email = 'julianparodi19@gmail.com'
LIMIT 1;

INSERT INTO customer (email, pwd, role)
VALUES ('julianparodi19@gmail.com',
        '$2a$12$MuaBQxRXzjCuHOByepMH/eqPZgcAzsM8Etkscib0Sc9ZJ0rEXmjsK',
        'admin');

CREATE TABLE customer (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             email VARCHAR(50) NOT NULL,
                             pwd VARCHAR(200) NOT NULL,
                             role VARCHAR(50) NOT NULL
);

select username,password,enabled from users where username = 'juliparodi';

