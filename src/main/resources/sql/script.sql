select username,password,enabled from users where username = 'juliparodi';

INSERT INTO users (username, password, enabled)
VALUES ('juliparodi', '12345', '1');
delete from users where id = 1;
INSERT INTO authorities (username, authority) VALUES ('juliparodi', 'write');
select username,password,enabled from users where username = 'juliparodi';

select username,password,enabled from users where username = 'juliparodi';
select username,authority from authorities where username = 'juliparodi';
delete from users where id = 1;

INSERT INTO users (username, password, enabled)
VALUES ('juliparodi', '12345', '1');
INSERT INTO authorities (username, authority) VALUES ('juliparodi', 'write');