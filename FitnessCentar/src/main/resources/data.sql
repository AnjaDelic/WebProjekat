INSERT INTO ADMINISTRATOR(name,surname,username,password,phone,email,birthday,active) VALUES ('Anja','Delic','anjica','123','0645523895','delicc.anja@gmail.com','2000-03-17','1')
INSERT INTO ADMINISTRATOR(name,surname,username,password,phone,email,birthday,active) VALUES ('Stefan','Delic','stefke','123456','0645521895','stefke@gmail.com','2000-04-06','1')

INSERT INTO CLAN (active,birthday,email,name,password,phone,surname,username) VALUES ('1','2000-04-10','anka@gmail.com','Anka','siji','0645968741','Petrovic','ankica123')
INSERT INTO CLAN (active,birthday,email,name,password,phone,surname,username) VALUES ('1','2000-10-10','marko@gmail.com','Marko','11m','0645966741','Markovic','makikavasaki')
INSERT INTO CLAN (active,birthday,email,name,password,phone,surname,username) VALUES ('1','1997-05-25','gavrilo97@gmail.com','Gavrilo','ffg','0612368741','Subotin','gavras')
INSERT INTO CLAN (active,birthday,email,name,password,phone,surname,username) VALUES ('1','1995-12-01','mica95@gmail.com','Milica','sifra','064596665','Celic','micacica')


INSERT INTO FC (adresa,broj,email,naziv) VALUES ('Mise Dimitrijevica 56','02145221','ahilejgrbavica@gmail.com','AhilejGrbavica')
INSERT INTO FC (adresa,broj,email,naziv) VALUES ('Rumenacka','02145223','ahilejdtlnr@gmail.com','AhilejDetelinara')

INSERT INTO SALA (kapacitet,oznaka,broj,fitness_centar_id) VALUES ('100','a1','2',1)
INSERT INTO SALA (kapacitet,oznaka,broj,fitness_centar_id) VALUES ('100','a2','3',1)
INSERT INTO SALA (kapacitet,oznaka,broj,fitness_centar_id) VALUES ('50','b1','1',2)
INSERT INTO SALA (kapacitet,oznaka,broj,fitness_centar_id) VALUES ('15','b2','4',2)

INSERT INTO TRENER (active,birthday,email,name,password,phone,prosek,surname,username,fitness_centri_id) VALUES ('1','1999-04-10','milan@gmail.com','Milan','calca','0645968336','0','Milanovic','mikicatrener',1)
INSERT INTO TRENER (active,birthday,email,name,password,phone,prosek,surname,username,fitness_centri_id) VALUES ('1','1999-02-02','milana@gmail.com','Milana','kajax','0644448336','0','Markov','milanatrener',1)
INSERT INTO TRENER (active,birthday,email,name,password,phone,prosek,surname,username,fitness_centri_id) VALUES ('1','2000-12-12','marina00@gmail.com','Marina','zzz','0645968325','0','Jankovic','marinatrener',2)
INSERT INTO TRENER (active,birthday,email,name,password,phone,prosek,surname,username,fitness_centri_id) VALUES ('1','1996-05-31','andjela96@gmail.com','Andjela','call','0644468375','0','Jovanovic','andjatrener',2)

INSERT INTO TRENER (active,birthday,email,name,password,phone,prosek,surname,username,fitness_centri_id) VALUES ('0','2002-10-12','milan02@gmail.com','Milan','123','0640008375','0','Jakovljevic','milanjakovljevic',1)
INSERT INTO TRENER (active,birthday,email,name,password,phone,prosek,surname,username,fitness_centri_id) VALUES ('0','1998-07-31','anastasija@gmail.com','Anastasija','123','0640055575','0','Blagojjevic','anastaijatrener',2)
INSERT INTO TRENER (active,birthday,email,name,password,phone,prosek,surname,username,fitness_centri_id) VALUES ('0','1999-05-10','milica@gmail.com','Milica','123','0640111375','0','Jovanov','milica99',2)


INSERT INTO TRENING (naziv,opis,trajanje,tip) VALUES ('crossfit','za napredne vezbace','90','snage')
INSERT INTO TRENING (naziv,opis,trajanje,tip) VALUES ('zumba','za pocetnike vezbace','30','anaerobni')
INSERT INTO TRENING (naziv,opis,trajanje,tip) VALUES ('tabata','za sve vezbace','60','cardio')
INSERT INTO TRENING (naziv,opis,trajanje,tip) VALUES ('box','za napredne vezbace','60','cardio')

INSERT INTO TERMIN (cena,pocetak,kraj,sala_id,trener_id,trening_id) VALUES ('1500','2021-05-10 10:00:00','2021-05-10 11:30:00',1,1,1)
INSERT INTO TERMIN (cena,pocetak,kraj,sala_id,trener_id,trening_id) VALUES ('900','2021-05-15 12:30:00','2021-05-15 13:00:00',2,3,2)
INSERT INTO TERMIN (cena,pocetak,kraj,sala_id,trener_id,trening_id) VALUES ('1000','2021-05-08 09:00:00','2021-05-08 10:00:00',3,2,4)
INSERT INTO TERMIN (cena,pocetak,kraj,sala_id,trener_id,trening_id) VALUES ('1150','2021-05-05 20:00:00','2021-05-05 21:00:00',4,4,3)

INSERT INTO OCENA (ocena,clan_id,termin_id) VALUES ('5',4,4)
INSERT INTO OCENA (ocena,clan_id,termin_id) VALUES ('5',2,3)
INSERT INTO OCENA (ocena,clan_id,termin_id) VALUES ('5',1,1)


INSERT INTO ODRADJENI_TERMINI(terminID,clanID) VALUES (1,1)
INSERT INTO ODRADJENI_TERMINI(terminID,clanID) VALUES (4,4)
INSERT INTO ODRADJENI_TERMINI(terminID,clanID) VALUES (3,2)
INSERT INTO ODRADJENI_TERMINI(terminID,clanID) VALUES (1,3)


INSERT INTO PRIJAVLJENI_TERMINI(terminID,clanID) VALUES (4,1)
INSERT INTO PRIJAVLJENI_TERMINI(terminID,clanID) VALUES (1,1)
INSERT INTO PRIJAVLJENI_TERMINI(terminID,clanID) VALUES (4,4)
INSERT INTO PRIJAVLJENI_TERMINI(terminID,clanID) VALUES (3,2)
INSERT INTO PRIJAVLJENI_TERMINI(terminID,clanID) VALUES (1,3)