insert into uloga (name) values ('ROLE_ADMIN_SISTEMA');  -- 1
insert into uloga (name) values ('ROLE_ADMIN_APOTEKA');  -- 2
insert into uloga (name) values ('ROLE_DERMATOLOG');   -- 3
insert into uloga (name) values ('ROLE_DOBAVLJAC');  -- 4
insert into uloga (name) values ('ROLE_FARMACEUT');  -- 5
insert into uloga (name) values ('ROLE_PACIJENT');  -- 6


--POPUST
insert into popust (popust_regular, od_regular,
do_regular, popust_silver, od_silver, do_silver,
popust_gold, od_gold, do_gold, broj_poena_pregleda, broj_poena_savetovanja, odobren)
values (0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false);

--ADRESE
insert into adresa (mesto,ptt,ulica,broj,gduzina,gsirina) values ('Novi Sad','21000','Bulevar despota Stefana','7a',10.0,12.0);



--APOTEKE
insert into apoteka (ime,adresa_id) values ('Prva apoteka',1);
insert into apoteka (ime,adresa_id) values ('Druga apoteka',1);

-- ADMINISTRATORI APOTEKA
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email) values
('dunja', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Dunja', 'Dunjica', 'dunjadunjica@gmail.com',
'2017-10-01 21:58:58', 'ROLE_ADMIN_APOTEKE', true, '2017-10-01 21:58:58', true);
insert into administratori (korisnik) values (1);
insert into administratori_apoteke (administrator,apoteka_id) values (1,1);


insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email) values
('visnja', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Visnja', 'Visnjica', 'visnjavisnjica@gmail.com',
'2017-10-01 21:58:58', 'ROLE_ADMIN_APOTEKE', true, '2017-10-01 21:58:58', true);
insert into administratori (korisnik) values (2);
insert into administratori_apoteke (administrator) values (2);

insert into korisnik_uloga (korisnik_id, uloga_id) values (1, 2);
insert into korisnik_uloga (korisnik_id, uloga_id) values (2, 2);

-- ADMINISTRATORI SISTEMA
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email) values
('sulejman1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Sulejman', 'Velicanstveni', 'sulejmanvelicanstveni1@gmail.com',
'2017-10-01 21:58:58', 'ROLE_ADMIN_SISTEMA', true, '2017-10-01 21:58:58', true);
insert into administratori (korisnik) values (3);
insert into administratori_sistema (administrator) values (3);

insert into korisnik_uloga (korisnik_id, uloga_id) values (3, 1);

-- DERMATOLOZI
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email) values
('marko1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'markomarkovic1@gmail.com',
'2017-10-01 21:58:58', 'ROLE_DERMATOLOG', true, '2017-10-01 21:58:58', true);
insert into radnici (korisnik) values (4);
insert into dermatolozi (radnik) values (4);
insert into apoteka_dermatolozi(apoteke_id,dermatolozi_radnik) values (1,4);
insert into apoteka_dermatolozi(apoteke_id,dermatolozi_radnik) values (2,4);

insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email) values
('nikola1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'nikolanikolic1@gmail.com',
'2017-10-01 21:58:58', 'ROLE_DERMATOLOG', true, '2017-10-01 21:58:58', true);
insert into radnici (korisnik) values (5);
insert into dermatolozi (radnik) values (5);
insert into apoteka_dermatolozi(apoteke_id,dermatolozi_radnik) values (1,5);
insert into korisnik_uloga (korisnik_id, uloga_id) values (4, 3);
insert into korisnik_uloga (korisnik_id, uloga_id) values (5, 3);





-- DOBAVLJACI
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email) values
('mika1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mika', 'Mikic', 'mikamikic1@gmail.com',
'2017-10-01 21:58:58', 'ROLE_DOBAVLJAC', true, '2017-10-01 21:58:58', true);
insert into radnici (korisnik) values (6);
insert into dobavljaci (radnik) values (6);

insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email) values
('pera1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Pera', 'Peric', 'peraperic1@gmail.com',
'2017-10-01 21:58:58', 'ROLE_DOBAVLJAC', true, '2017-10-01 21:58:58', true);
insert into radnici (korisnik) values (7);
insert into dobavljaci (radnik) values (7);

insert into korisnik_uloga (korisnik_id, uloga_id) values (6, 4);
insert into korisnik_uloga (korisnik_id, uloga_id) values (7, 4);

-- PACIJENT
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email) values
('zarko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Zarko', 'Kisa', 'zarkoKisa@gmail.com',
 '2017-10-01 21:58:58', 'ROLE_PACIJENT', true, '2017-10-01 21:58:58', true);
insert into pacijenti (korisnik) values (8);
insert into korisnik_uloga (korisnik_id, uloga_id) values (8, 6);



-- FARMACEUTI
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email) values
('andrija', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'andrija@gmail.com',
 '2017-10-01 21:58:58', 'ROLE_FARMACEUT', true, '2017-10-01 21:58:58', true);
insert into radnici (korisnik) values (9);
insert into farmaceuti (radnik,apoteka_id) values (9,1);

insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email) values
('milomir', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'milomir@gmail.com',
 '2017-10-01 21:58:58', 'ROLE_FARMACEUT', true, '2017-10-01 21:58:58', true);
insert into radnici (korisnik) values (10);
insert into farmaceuti (radnik,apoteka_id) values (10,1);

-- LIJEKOVI
insert into lijek (napomena, naziv, oblik_lijeka, ocijena, proizvodjac, sastav, vrsta_lijeka, dobavljac_radnik, poeni)
values ('napomena1', 'paracetamol', 'kapsula', 10.0, 'biofarm', 'sastav1', 'vrsta1', 7, 1);
insert into lijek (napomena, naziv, oblik_lijeka, ocijena, proizvodjac, sastav, vrsta_lijeka, dobavljac_radnik, poeni)
values ('napomena1', 'kafetin', 'kapsula', 10.0, 'biofarm', 'sastav1', 'vrsta1', 7, 1);
insert into lijek (napomena, naziv, oblik_lijeka, ocijena, proizvodjac, sastav, vrsta_lijeka, dobavljac_radnik, poeni)
values ('napomena1', 'analgin', 'kapsula', 10.0, 'biofarm', 'sastav1', 'vrsta1', 7, 1);
insert into lijek (napomena, naziv, oblik_lijeka, ocijena, proizvodjac, sastav, vrsta_lijeka, dobavljac_radnik, poeni)
values ('napomena1', 'paracetamol2', 'kapsula', 10.0, 'biofarm', 'sastav1', 'vrsta1', 7, 1);



--GODISNJI ODMORI
insert into godisnji_odmor (datum_od,datum_do,odobren,apoteka_id,radnik_korisnik)
values ('2021-05-01 00:00:00','2021-05-21 00:00:00',null,1,9);
insert into godisnji_odmor (datum_od,datum_do,odobren,apoteka_id,radnik_korisnik)
values ('2021-06-10 00:00:00','2021-06-30 00:00:00',null,1,10);
