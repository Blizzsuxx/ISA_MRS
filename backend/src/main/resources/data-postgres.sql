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
insert into adresa (mesto,ptt,ulica,broj,gduzina,gsirina) values ('Novi Sad','21000','Bulevar despota Stefana','7a',19.83,45.26);



--APOTEKE
insert into apoteka (ime,adresa_id) values ('Prva apoteka',1);
insert into apoteka (ime,adresa_id) values ('Druga apoteka',1);

-- ADMINISTRATORI APOTEKA
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email, prijavljen) values
('dunja', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Dunja', 'Dunjica', 'dunjadunjica@gmail.com',
'2017-10-01 21:58:58', 'ROLE_ADMIN_APOTEKE', true, '2017-10-01 21:58:58', true, true);
insert into administratori (korisnik) values (1);
insert into administratori_apoteke (administrator,apoteka_id) values (1,1);


insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email, prijavljen) values
('visnja', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Visnja', 'Visnjica', 'visnjavisnjica@gmail.com',
'2017-10-01 21:58:58', 'ROLE_ADMIN_APOTEKE', true, '2017-10-01 21:58:58', true, true);
insert into administratori (korisnik) values (2);
insert into administratori_apoteke (administrator) values (2);

insert into korisnik_uloga (korisnik_id, uloga_id) values (1, 2);
insert into korisnik_uloga (korisnik_id, uloga_id) values (2, 2);

-- ADMINISTRATORI SISTEMA
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email, prijavljen) values
('sulejman1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Sulejman', 'Velicanstveni', 'sulejmanvelicanstveni1@gmail.com',
'2017-10-01 21:58:58', 'ROLE_ADMIN_SISTEMA', true, '2017-10-01 21:58:58', true, true);
insert into administratori (korisnik) values (3);
insert into administratori_sistema (administrator) values (3);

insert into korisnik_uloga (korisnik_id, uloga_id) values (3, 1);

insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email, prijavljen) values
('markoni', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Marko', 'markomare@gmail.com',
'2017-10-01 21:58:58', 'ROLE_ADMIN_SISTEMA', true, '2017-10-01 21:58:58', true, false);
insert into administratori (korisnik) values (4);
insert into administratori_sistema (administrator) values (4);

insert into korisnik_uloga (korisnik_id, uloga_id) values (4, 1);

-- DERMATOLOZI
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email, prijavljen) values
('marko1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'markomarkovic1@gmail.com',
'2017-10-01 21:58:58', 'ROLE_DERMATOLOG', true, '2017-10-01 21:58:58', true, true);
insert into radnici (korisnik) values (5);
insert into dermatolozi (radnik) values (5);
insert into apoteka_dermatolozi(apoteke_id,dermatolozi_radnik) values (1,5);
insert into apoteka_dermatolozi(apoteke_id,dermatolozi_radnik) values (2,5);

insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email, prijavljen) values
('nikola1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'nikolanikolic1@gmail.com',
'2017-10-01 21:58:58', 'ROLE_DERMATOLOG', true, '2017-10-01 21:58:58', true, true);
insert into radnici (korisnik) values (6);
insert into dermatolozi (radnik) values (6);
insert into apoteka_dermatolozi(apoteke_id,dermatolozi_radnik) values (1,6);
insert into korisnik_uloga (korisnik_id, uloga_id) values (5, 3);
insert into korisnik_uloga (korisnik_id, uloga_id) values (6, 3);





-- DOBAVLJACI
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email, prijavljen) values
('mika1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mika', 'Mikic', 'mikamikic1@gmail.com',
'2017-10-01 21:58:58', 'ROLE_DOBAVLJAC', true, '2017-10-01 21:58:58', true, true);
insert into radnici (korisnik) values (7);
insert into dobavljaci (radnik) values (7);

insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email, prijavljen) values
('pera1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Pera', 'Peric', 'peraperic1@gmail.com',
'2017-10-01 21:58:58', 'ROLE_DOBAVLJAC', true, '2017-10-01 21:58:58', true, false);
insert into radnici (korisnik) values (8);
insert into dobavljaci (radnik) values (8);

insert into korisnik_uloga (korisnik_id, uloga_id) values (7, 4);
insert into korisnik_uloga (korisnik_id, uloga_id) values (8, 4);

-- PACIJENT
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email, prijavljen) values
('zarko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Zarko', 'Kisa', 'zarkoKisa@gmail.com',
 '2017-10-01 21:58:58', 'ROLE_PACIJENT', true, '2017-10-01 21:58:58', true, true);
insert into pacijenti (korisnik, broj_poena) values (9, 10);
insert into korisnik_uloga (korisnik_id, uloga_id) values (9, 6);



-- FARMACEUTI
insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email, prijavljen) values
('andrija', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'andrija@gmail.com',
 '2017-10-01 21:58:58', 'ROLE_FARMACEUT', true, '2017-10-01 21:58:58', true, true);
insert into radnici (korisnik) values (10);
insert into farmaceuti (radnik,apoteka_id) values (10,1);

insert into korisnici (username, password, first_name, last_name, email, birthday, role, enabled, last_password_reset_date,
potvrda_email, prijavljen) values
('milomir', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'milomir@gmail.com',
 '2017-10-01 21:58:58', 'ROLE_FARMACEUT', true, '2017-10-01 21:58:58', true, true);
insert into radnici (korisnik) values (11);
insert into farmaceuti (radnik,apoteka_id) values (11,null);

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
values ('2021-05-01 00:00:00','2021-05-21 00:00:00',null,1,10);
insert into godisnji_odmor (datum_od,datum_do,odobren,apoteka_id,radnik_korisnik)
values ('2021-06-10 00:00:00','2021-06-30 00:00:00',null,1,11);

--NARUDZBENICE

insert into narudzbenica (rok,zavrsena,apoteka_id,prihvacena,administrator_apoteke_administrator) values ('2021-06-30 00:00:00',true,1,false,1);



--STANEJ LIJEKOVA APOTEKE
insert into stanje_lijeka (kolicina,lijek_id,apoteka_id,prodaja,cijena,datum_isteka_cijene,zatrazen,zatrazen_datum) values (10,1,1,true,10,'2021-06-30 00:00:00',2,'2021-06-30 00:00:00');
insert into stanje_lijeka (kolicina,lijek_id,apoteka_id,prodaja,cijena,datum_isteka_cijene) values (10,2,1,true,20,'2021-06-30 00:00:00');
insert into stanje_lijeka (kolicina,lijek_id,apoteka_id,prodaja,cijena,datum_isteka_cijene) values (10,3,1,true,30,'2021-06-30 00:00:00');


--STANJE LIJEKOVA NARUDZBENICE

insert into stanje_lijeka (kolicina,lijek_id,narudzbenica_id,prodaja,cijena,datum_isteka_cijene) values (3,2,1.00,false,1,'2021-06-30 00:00:00');
insert into stanje_lijeka (kolicina,lijek_id,narudzbenica_id,prodaja,cijena,datum_isteka_cijene) values (3,1,1.00,false,1,'2021-06-30 00:00:00');


--PONUDE

insert into ponuda(cijena_ponude,naziv_ponude,status,dobavljac_radnik,narudzbenica_id) values (100,'Prva',0,7,1);
insert into ponuda(cijena_ponude,naziv_ponude,status,dobavljac_radnik,narudzbenica_id) values (100,'Druga',0,7,1);


-- ZALBE

insert into zalbe(datum_vrijeme, naslov, pacijent, text) values ('2021-05-14 04:40', 'aa', 'zarko', 'aaaa');
insert into zalbe(datum_vrijeme, naslov, pacijent, text) values ('2021-05-14 04:40', 'aab', 'zarko', 'aaaaaa');

-- ODGOVORI

insert into odgovori(datum_vrijeme, administrator, text, zalba_id) values ('2021-05-14 04:40', 'sulejman1', 'aaa', 1);
insert into odgovori(datum_vrijeme, administrator, text, zalba_id) values ('2021-05-14 04:40', 'sulejman1', 'bbb', 1);
