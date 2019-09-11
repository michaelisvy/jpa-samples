-- Below is executed when spring.jpa.hibernate.ddl-auto is set to `create` or `create-drop`
insert into customer (id, first_name, last_name, version) values (1,'Jack','Bauer',1);
insert into account(id, amount, customer_id) values (1, 50, 1);
insert into account(id, amount, customer_id) values (2, 100, 1);

insert into customer (id, first_name, last_name, version) values (2,'Chloe','OBrian',1);
insert into account(id, amount, customer_id) values (3, 500, 2);
insert into account(id, amount, customer_id) values (4, 1000, 2);

insert into payment (id, payment_number, first_name, last_name, amount, status) values (1, 1001, 'Jack','Bauer', 200, 'in progress')