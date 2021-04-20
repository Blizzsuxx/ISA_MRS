insert into uloga (name) values ('ROLE_ADMIN_SISTEMA');  -- 1
insert into uloga (name) values ('ROLE_ADMIN_APOTEKA');  -- 2
insert into uloga (name) values ('ROLE_DERMATOLOG');   -- 3
insert into uloga (name) values ('ROLE_DOBAVLJAC');  -- 4
insert into uloga (name) values ('ROLE_FARMACEUT');  -- 5
insert into uloga (name) values ('ROLE_PACIJENT');  -- 6

-- ADMINISTRATORI APOTEKA
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

insert into korisnik_uloga (korisnik_id, uloga_id) values (1, 2);
insert into korisnik_uloga (korisnik_id, uloga_id) values (2, 2);

-- ADMINISTRATORI SISTEMA
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date) values
('sulejman', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Sulejman', 'Velicanstveni', 'sulejmanvelicanstveni@gmail.com',
'2017-10-01 21:58:58', 'ROLE_ADMIN_SISTEMA', true, '2017-10-01 21:58:58');
insert into administratori (korisnik) values (3);
insert into administratori_sistema (administrator) values (3);

insert into korisnik_uloga (korisnik_id, uloga_id) values (3, 1);

-- DERMATOLOZI
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date) values
('marko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'markomarkovic@gmail.com',
'2017-10-01 21:58:58', 'ROLE_DERMATOLOG', true, '2017-10-01 21:58:58');
insert into radnici (korisnik) values (4);
insert into dermatolozi (radnik) values (4);

insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date) values
('nikola', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'nikolanikolic@gmail.com',
'2017-10-01 21:58:58', 'ROLE_DERMATOLOG', true, '2017-10-01 21:58:58');
insert into radnici (korisnik) values (5);
insert into dermatolozi (radnik) values (5);

insert into korisnik_uloga (korisnik_id, uloga_id) values (4, 3);
insert into korisnik_uloga (korisnik_id, uloga_id) values (5, 3);

-- DOBAVLJACI
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date) values
('mika', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mika', 'Mikic', 'mikamikic@gmail.com',
'2017-10-01 21:58:58', 'ROLE_DOBAVLJAC', true, '2017-10-01 21:58:58');
insert into radnici (korisnik) values (6);
insert into dobavljaci (radnik) values (6);

insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date) values
('pera', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Pera', 'Peric', 'peraperic@gmail.com',
'2017-10-01 21:58:58', 'ROLE_DOBAVLJAC', true, '2017-10-01 21:58:58');
insert into radnici (korisnik) values (7);
insert into dobavljaci (radnik) values (7);

insert into korisnik_uloga (korisnik_id, uloga_id) values (6, 4);
insert into korisnik_uloga (korisnik_id, uloga_id) values (7, 4);