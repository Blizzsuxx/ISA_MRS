insert into uloga (name) values ('ROLE_ADMIN_SISTEMA');
insert into uloga (name) values ('ROLE_ADMIN_APOTEKA');

insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date) values
('dunja', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Dunja', 'Dunjica', 'dunjadunjica@gmail.com',
'2017-10-01 21:58:58', 'ROLE_ADMIN_APOTEKE', true, '2017-10-01 21:58:58');
insert into administratori (korisnik) values (1);
insert into administratori_apoteke (administrator) values (1);

insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date) values
('visnja', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Visnja', 'Visnjica', 'visnjavisnjica@gmail.com',
'2017-10-01 21:58:58', 'ROLE_ADMIN_APOTEKE', true, '2017-10-01 21:58:58');
insert into administratori (korisnik) values (2);
insert into administratori_apoteke (administrator) values (2);

insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date) values
('sulejman', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Sulejman', 'Velicanstveni', 'sulejmanvelicanstveni@gmail.com',
'2017-10-01 21:58:58', 'ROLE_ADMIN_SISTEMA', true, '2017-10-01 21:58:58');
insert into administratori (korisnik) values (3);
insert into administratori_sistema (administrator) values (3);

insert into korisnik_uloga (korisnik_id, uloga_id) values (1, 2);
insert into korisnik_uloga (korisnik_id, uloga_id) values (2, 2);
insert into korisnik_uloga (korisnik_id, uloga_id) values (3, 1);
